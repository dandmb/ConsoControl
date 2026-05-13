package com.dmb25.consoprotection.presentation.ui.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmb25.consoprotection.domain.repository.RecallRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.dmb25.consoprotection.domain.model.Product
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlin.collections.emptyList

@OptIn(FlowPreview::class)
class ProductListViewModel(
    private val repository: RecallRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Product>>>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore = _isLoadingMore.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Product>>(emptyList())
    val searchResults = _searchResults.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()


    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory = _selectedCategory.asStateFlow()

    val categories: StateFlow<List<String>> = combine(
        _uiState, _searchResults, _searchQuery
    ) { state, searchResults, query ->
        if (query.isNotBlank()) {
            searchResults.map { it.categorieProduit }.distinct().sorted()
        } else if (state is UiState.Success) {
            state.data.map { it.categorieProduit }.distinct().sorted()
        } else emptyList()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val filteredProducts: StateFlow<List<Product>> = combine(
        _uiState, _selectedCategory, _searchResults, _searchQuery
    ) { state, category, searchResults, query ->
        val baseList = if (query.isNotBlank()) searchResults
        else if (state is UiState.Success) state.data
        else emptyList()

        if (category != null) {
            baseList.filter { it.categorieProduit == category }
        } else {
            baseList
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )


    fun onCategorySelected(category: String?) {
        _selectedCategory.value = if (_selectedCategory.value == category) null else category
    }


    init {
        observeLocalData()
        observeSearchQuery()
    }
    private fun observeLocalData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRecalls()
                .collect { products ->
                    if (products.isEmpty()) {
                        _uiState.value = UiState.Loading
                    } else {
                        _uiState.value = UiState.Success(data = products)
                    }
                }
        }
    }

    fun fetchNextPage() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_isLoadingMore.value) return@launch
            if (!repository.canLoadMore()) return@launch

            _isLoadingMore.value = true
            try {
                val offset = repository.getCurrentOffset()
                repository.fetchAndSave(offset)
            } catch (e: Exception) {
                if (_uiState.value is UiState.Loading) {
                    _uiState.value = UiState.Error(e.message ?: "Erreur réseau")
                }
            } finally {
                _isLoadingMore.value = false
            }
        }
    }


    private fun observeSearchQuery() {
        viewModelScope.launch(Dispatchers.IO) {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
                    search(query)
                }
        }
    }


    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun search(query: String) {
        if (query.isBlank()) {
            _searchResults.value = emptyList()
            _isSearching.value = false
            return
        }
        _selectedCategory.value = null
        viewModelScope.launch(Dispatchers.IO) {
            _isSearching.value = true
            try {
                _searchResults.value = repository.searchRecalls(query)
            } catch (e: Exception) {
                _searchResults.value = emptyList()
            } finally {
                _isSearching.value = false
            }
        }
    }
}
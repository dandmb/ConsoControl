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

    init {
        observeLocalData()
        fetchNextPage()
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

    fun search(query: String) {
        if (query.isBlank()) {
            _searchResults.value = emptyList()
            _isSearching.value = false
            return
        }
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
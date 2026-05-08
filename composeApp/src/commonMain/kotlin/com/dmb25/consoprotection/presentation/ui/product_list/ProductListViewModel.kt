package com.dmb25.consoprotection.presentation.ui.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmb25.consoprotection.data.model.Product
import com.dmb25.consoprotection.domain.repository.RecallRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProductListViewModel(
    val repository: RecallRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Product>>>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    init {
        getRecalls()
    }

    fun getRecalls(){
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState.Loading
            repository.getRecalls()
                .catch { e ->
                    _uiState.value = UiState.Error(e.message ?: "Erreur")
                    print(e.message)
                }
                .collect { products ->
                    _uiState.value = UiState.Success(data = products)
                    print(products)
                }
        }
    }
}
package com.dmb25.consoprotection.presentation.ui.product_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmb25.consoprotection.domain.repository.RecallRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val repository: RecallRepository
) : ViewModel(){
    private val _state = MutableStateFlow<ProductDetailsViewState>(ProductDetailsViewState.Loading)
    val state: StateFlow<ProductDetailsViewState> = _state.asStateFlow()

    fun onFetch(id: Int) {
        viewModelScope.launch {
            _state.value = ProductDetailsViewState.Loading
            try {
                val product = repository.getProductById(id)
                if (product != null) {
                    _state.value = ProductDetailsViewState.Success(product)
                } else {
                    _state.value = ProductDetailsViewState.Error("Oups quelque chose s'est mal passé")
                }
            } catch (e: Exception) {
                _state.value = ProductDetailsViewState.Error(errorType = e.message ?: "Oups quelque chose s'est mal passé")
            }
        }
    }
}
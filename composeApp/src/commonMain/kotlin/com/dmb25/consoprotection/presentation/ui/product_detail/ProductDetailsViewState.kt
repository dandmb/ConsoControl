package com.dmb25.consoprotection.presentation.ui.product_detail

import com.dmb25.consoprotection.domain.model.Product

sealed interface ProductDetailsViewState {
    data object Loading : ProductDetailsViewState
    data class Success(val product: Product) : ProductDetailsViewState
    data class Error(val errorType: String) : ProductDetailsViewState
}
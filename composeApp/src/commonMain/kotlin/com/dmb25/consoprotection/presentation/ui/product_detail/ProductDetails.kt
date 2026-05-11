package com.dmb25.consoprotection.presentation.ui.product_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    onBackClick: () -> Unit
) {
    val viewModel = koinViewModel<ProductDetailsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onFetch(productId)
    }
    ProductDetailsScreenContent(
        state = state,
        onBackClick = onBackClick,
        onShareClick = {

        },
        onReadMoreClick = {},
        onRetryClick = {
            viewModel.onFetch(productId)
        }
    )
}
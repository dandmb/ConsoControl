package com.dmb25.consoprotection.presentation.ui.product_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreenContent(
    state: ProductDetailsViewState,
    onRetryClick: () -> Unit,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onReadMoreClick: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Details")
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            when (state) {

                is ProductDetailsViewState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is ProductDetailsViewState.Success -> {
                    Text(state.product.libelle)
                }
                is ProductDetailsViewState.Error -> {
                    Text(state.errorType, modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }

}
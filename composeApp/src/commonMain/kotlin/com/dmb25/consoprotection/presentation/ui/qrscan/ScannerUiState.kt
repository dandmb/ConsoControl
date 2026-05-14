package com.dmb25.consoprotection.presentation.ui.qrscan

sealed class ScannerUiState{
    object Loading : ScannerUiState()
    data class Error(val error: String) : ScannerUiState()
}


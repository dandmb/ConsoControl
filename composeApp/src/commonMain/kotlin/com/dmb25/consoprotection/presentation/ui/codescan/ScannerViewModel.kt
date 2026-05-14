package com.dmb25.consoprotection.presentation.ui.codescan

import androidx.lifecycle.ViewModel
import com.dmb25.consoprotection.domain.repository.RecallRepository
import com.dmb25.consoprotection.scanner.BarcodeScanner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScannerViewModel(
    private val scanner: BarcodeScanner,
    private val repository: RecallRepository
) : ViewModel() {

    var _uiState = MutableStateFlow<ScannerUiState>(ScannerUiState.Loading)
    val uiState = _uiState.asStateFlow()

    suspend fun startScan(
        onProductFound: (String) -> Unit
    ) {

        _uiState.value = ScannerUiState.Loading

        val code = scanner.scan()

        if (code == null) {
            _uiState.value = ScannerUiState.Error(
                error = "Aucun code détecté"
            )
            return
        }

        try {

            val product = repository.getProductByGtin(code.toLong())

            if (product == null) {
                _uiState.value = ScannerUiState.Error(
                    error = "Le produit avec ce barre code : $code est introuvable"
                )
            } else{
                onProductFound(product.id.toString())
            }
        } catch (e: Exception) {
            _uiState.value = ScannerUiState.Error(
                error = "Oups quelque chose s'est mal passée"
            )
        }
    }
}
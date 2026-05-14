package com.dmb25.consoprotection.scanner

import com.dmb25.consoprotection.presentation.utils.currentActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class BarcodeScanner {

    private var scanContinuation: CancellableContinuation<String?>? = null

    private val activity = currentActivity ?: throw IllegalStateException("BarcodeScanner requires a ComponentActivity")

    actual suspend fun scan(): String? {

        val launcher = activity.activityResultRegistry.register(
            "barcode_scanner",
            ScanContract()
        ) { result ->
            scanContinuation?.resume(result.contents)
            scanContinuation = null
        }

        return try {
            suspendCancellableCoroutine { continuation ->
                scanContinuation = continuation

                val options = ScanOptions().apply {
                    setDesiredBarcodeFormats(
                        ScanOptions.EAN_13,
                        ScanOptions.EAN_8,
                        ScanOptions.UPC_A,
                        ScanOptions.UPC_E
                    )
                    setPrompt("Scanner un code")
                    setBeepEnabled(true)
                    setOrientationLocked(false)
                }

                launcher.launch(options)
            }
        } finally {
            launcher.unregister()
        }
    }
}
package com.dmb25.consoprotection.scanner

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.AVFoundation.*
import platform.CoreGraphics.CGRectMake
import platform.UIKit.*
import kotlin.coroutines.resume

import platform.UIKit.UIApplication
import platform.darwin.dispatch_get_main_queue
import platform.darwin.sel_registerName

actual class BarcodeScanner {

    actual suspend fun scan(): String? =
        suspendCancellableCoroutine { continuation ->

            val rootViewController =
                UIApplication.sharedApplication.keyWindow?.rootViewController
                    ?: run {
                        continuation.resume(null)
                        return@suspendCancellableCoroutine
                    }

            val scannerViewController =
                BarcodeScannerViewController { result ->

                    continuation.resume(result)
                }

            rootViewController.presentViewController(
                scannerViewController,
                animated = true,
                completion = null
            )

            continuation.invokeOnCancellation {

                scannerViewController.dismissViewControllerAnimated(
                    true,
                    completion = null
                )
            }
        }
}





class BarcodeScannerViewController(
    private val onResult: (String?) -> Unit
) : UIViewController(
    nibName = null,
    bundle = null
), AVCaptureMetadataOutputObjectsDelegateProtocol {

    private var captureSession: AVCaptureSession? = null

    private var previewLayer: AVCaptureVideoPreviewLayer? = null

    private var hasResult = false

    override fun viewDidLoad() {
        super.viewDidLoad()

        view.backgroundColor = UIColor.blackColor

        setupCamera()

        setupCloseButton()
    }

    override fun viewDidDisappear(animated: Boolean) {
        super.viewDidDisappear(animated)

        if (!hasResult) {
            finishWithResult(null)
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun setupCamera() {

        val session = AVCaptureSession()

        captureSession = session

        val device =
            AVCaptureDevice.defaultDeviceWithMediaType(
                AVMediaTypeVideo
            ) ?: run {

                finishWithResult(null)

                return
            }

        val input = try {

            AVCaptureDeviceInput(
                device = device,
                error = null
            )

        } catch (e: Exception) {

            finishWithResult(null)

            return
        }

        if (session.canAddInput(input)) {
            session.addInput(input)
        }

        val output = AVCaptureMetadataOutput()

        if (session.canAddOutput(output)) {

            session.addOutput(output)

            output.setMetadataObjectsDelegate(
                this,
                queue = dispatch_get_main_queue()
            )

            output.metadataObjectTypes = listOf(
                AVMetadataObjectTypeEAN13Code,
                AVMetadataObjectTypeEAN8Code,
                AVMetadataObjectTypeUPCECode,
                AVMetadataObjectTypeQRCode
            )
        }

        val preview = AVCaptureVideoPreviewLayer(
            session = session
        )

        preview.frame = view.layer.bounds

        preview.videoGravity =
            AVLayerVideoGravityResizeAspectFill

        view.layer.addSublayer(preview)

        previewLayer = preview

        platform.darwin.dispatch_async(
            platform.darwin.dispatch_get_global_queue(
                platform.darwin.DISPATCH_QUEUE_PRIORITY_DEFAULT.toLong(),
                0u
            )
        ) {
            session.startRunning()
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun setupCloseButton() {

        val button =
            UIButton.buttonWithType(
                UIButtonTypeSystem
            )

        button.setTitle(
            "Fermer",
            forState = UIControlStateNormal
        )

        button.setTitleColor(
            UIColor.whiteColor,
            forState = UIControlStateNormal
        )

        button.setFrame(
            CGRectMake(
                16.0,
                50.0,
                100.0,
                44.0
            )
        )

        button.addTarget(
            target = this,
            action = sel_registerName("closeScanner"),
            forControlEvents = UIControlEventTouchUpInside
        )

        view.addSubview(button)
    }

    @OptIn(BetaInteropApi::class)
    @ObjCAction
    fun closeScanner() {

        finishWithResult(null)
    }

    override fun captureOutput(
        output: AVCaptureOutput,
        didOutputMetadataObjects: List<*>,
        fromConnection: AVCaptureConnection
    ) {

        if (hasResult) return

        val metadataObject =
            didOutputMetadataObjects.firstOrNull()
                    as? AVMetadataMachineReadableCodeObject

        val code = metadataObject?.stringValue

        finishWithResult(code)
    }

    private fun finishWithResult(
        result: String?
    ) {

        if (hasResult) return

        hasResult = true

        platform.darwin.dispatch_async(
            platform.darwin.dispatch_get_global_queue(
                platform.darwin.DISPATCH_QUEUE_PRIORITY_DEFAULT.toLong(),
                0u
            )
        ) {
            captureSession?.stopRunning()
        }

        dismissViewControllerAnimated(
            true
        ) {
            onResult(result)
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        previewLayer?.frame = view.layer.bounds
    }
}
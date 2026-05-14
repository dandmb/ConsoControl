package com.dmb25.consoprotection.scanner

expect class BarcodeScanner {
    suspend fun scan(): String?
}
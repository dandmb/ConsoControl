package com.dmb25.consoprotection.presentation.utils

import android.content.Intent
import android.net.Uri
import android.app.Application
import androidx.activity.ComponentActivity

lateinit var application: Application

actual fun openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    application.startActivity(intent)
}
package com.dmb25.consoprotection

import androidx.compose.ui.window.ComposeUIViewController
import com.dmb25.consoprotection.di.initKoin
import com.dmb25.consoprotection.presentation.ui.App

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }
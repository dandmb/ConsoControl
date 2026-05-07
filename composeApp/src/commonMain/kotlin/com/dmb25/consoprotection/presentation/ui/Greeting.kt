package com.dmb25.consoprotection.presentation.ui

import com.dmb25.consoprotection.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
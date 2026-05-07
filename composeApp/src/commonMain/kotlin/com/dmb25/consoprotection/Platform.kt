package com.dmb25.consoprotection

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
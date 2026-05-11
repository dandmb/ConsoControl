package com.dmb25.consoprotection.presentation.ui.navigation
import kotlinx.serialization.Serializable

sealed interface MainNavigationDestination {
    @Serializable
    data object Home : MainNavigationDestination
    @Serializable
    data class Details(val productId: Int) : MainNavigationDestination
}
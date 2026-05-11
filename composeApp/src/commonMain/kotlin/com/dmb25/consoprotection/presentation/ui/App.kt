package com.dmb25.consoprotection.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.dmb25.consoprotection.presentation.theme.AppTheme
import com.dmb25.consoprotection.presentation.ui.navigation.MainNavigationDestination
import com.dmb25.consoprotection.presentation.ui.navigation.MainNavigationHost
import com.dmb25.consoprotection.presentation.ui.product_list.ProductListScreen

import consoprotection.composeapp.generated.resources.Res
import consoprotection.composeapp.generated.resources.title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    AppTheme {
        MainNavigationHost()
    }
}
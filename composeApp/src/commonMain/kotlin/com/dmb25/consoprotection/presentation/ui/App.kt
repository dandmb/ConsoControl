package com.dmb25.consoprotection.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dmb25.consoprotection.presentation.theme.AppTheme
import com.dmb25.consoprotection.presentation.ui.product_list.ProductListContent
import com.dmb25.consoprotection.presentation.ui.product_list.ProductListScreen
import org.jetbrains.compose.resources.painterResource

import consoprotection.composeapp.generated.resources.Res
import consoprotection.composeapp.generated.resources.app_name
import consoprotection.composeapp.generated.resources.compose_multiplatform
import consoprotection.composeapp.generated.resources.title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(Res.string.title))
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                ProductListScreen()
            }
        }
    }
}
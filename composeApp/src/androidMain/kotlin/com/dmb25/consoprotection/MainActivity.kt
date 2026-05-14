package com.dmb25.consoprotection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dmb25.consoprotection.presentation.ui.App
import com.dmb25.consoprotection.presentation.utils.currentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        currentActivity = this

        setContent {
            App()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (currentActivity == this) {
            currentActivity = null
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
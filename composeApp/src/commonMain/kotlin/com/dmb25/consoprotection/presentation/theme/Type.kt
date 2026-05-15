package com.dmb25.consoprotection.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import consoprotection.composeapp.generated.resources.Inter_bold
import consoprotection.composeapp.generated.resources.Inter_medium
import consoprotection.composeapp.generated.resources.Inter_regular
import consoprotection.composeapp.generated.resources.Inter_semibold
import org.jetbrains.compose.resources.Font

import consoprotection.composeapp.generated.resources.Res

@Composable
fun appTypography(): Typography {

    val interFontFamily = FontFamily(
        Font(
            resource = Res.font.Inter_regular,
            weight = FontWeight.Normal
        ),
        Font(
            resource = Res.font.Inter_medium,
            weight = FontWeight.Medium
        ),
        Font(
            resource = Res.font.Inter_semibold,
            weight = FontWeight.SemiBold
        ),
        Font(
            resource = Res.font.Inter_bold,
            weight = FontWeight.Bold
        )
    )

    return Typography(

        displayLarge = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            lineHeight = 42.sp
        ),

        headlineLarge = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
            lineHeight = 34.sp
        ),

        headlineMedium = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 30.sp
        ),

        titleLarge = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 28.sp
        ),

        titleMedium = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 24.sp
        ),

        bodyLarge = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        ),

        bodyMedium = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 22.sp
        ),

        bodySmall = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 18.sp
        ),

        labelLarge = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        labelMedium = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.2.sp
        )
    )
}
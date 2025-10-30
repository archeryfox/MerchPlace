package com.example.merchplace.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryRed,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = DarkBorder,
    onSecondary = TextPrimary,
    tertiary = AccentOrange,
    onTertiary = androidx.compose.ui.graphics.Color.White,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = DarkCard,
    onSurfaceVariant = TextSecondary,
    outline = DarkBorder,
    error = ErrorRed,
    onError = androidx.compose.ui.graphics.Color.White
)

@Composable
fun MerchPlaceTheme(
    darkTheme: Boolean = true, // Always dark theme by default (like prototype)
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
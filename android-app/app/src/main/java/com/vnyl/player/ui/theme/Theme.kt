package com.vnyl.player.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val VnylColorScheme = darkColorScheme(
    primary = Color(0xFFFF2D55),
    secondary = Color(0xFFFF5C8A),
    background = Color(0xFF0A0A0A),
    surface = Color(0xFF121212),
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun VnylPlayerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = VnylColorScheme,
        typography = Typography,
        content = content
    )
}
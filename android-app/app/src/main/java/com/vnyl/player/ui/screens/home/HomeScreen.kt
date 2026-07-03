package com.vnyl.player.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vnyl.player.ui.components.player.MiniPlayer

@Composable
fun HomeScreen() {

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF0A0A0A),
            Color(0xFF1A0010),
            Color(0xFF0A0A0A)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {

        // ── Main content ────────────────────────────────────────
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Vyl Player",
                color = Color(0xFFFF2D55),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Retro Neon Music Experience",
                color = Color.LightGray,
                fontSize = 16.sp
            )
        }

        // ── Bottom mini-player ──────────────────────────────────
        MiniPlayer(
            songTitle = "Digital Horizon",
            artistName = "The Vector",
            isPlaying = false,
            playbackProgress = 0.35f,
            onPlayPauseClick = { /* TODO: wire to playback engine */ },
            onSkipNextClick = { /* TODO: wire to playback engine */ },
            onMiniPlayerClick = { /* TODO: expand to full player */ },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
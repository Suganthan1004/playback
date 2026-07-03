package com.vnyl.player.ui.components.player

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ── Neon palette ────────────────────────────────────────────────
private val NeonPink = Color(0xFFFF2D55)
private val NeonPinkDim = Color(0x80FF2D55)
private val BarBackground = Color(0xFF1A1A2E)
private val ProgressTrack = Color(0xFF2A2A3E)
private val ArtCircle = Color(0xFF12121E)
private val ArtBorder = Color(0x40FF2D55)
private val SubtitleGray = Color(0xFF8A8A9A)
private val ControlGray = Color(0xFFB0B0C0)

/**
 * A reusable bottom mini-player bar with synthwave neon aesthetics.
 *
 * Designed to sit flush at the bottom of the screen (above any bottom nav).
 * Matches the Vyl Player UI mockup: full-width bar, progress line,
 * circular album art, skip-next, and a filled neon play/pause button.
 *
 * @param songTitle        Title of the current song.
 * @param artistName       Artist / album name shown below the title.
 * @param isPlaying        Toggles play ↔ pause icon and button glow.
 * @param playbackProgress Current progress 0f..1f for the thin accent line.
 * @param onPlayPauseClick Callback when the play/pause button is tapped.
 * @param onSkipNextClick  Callback when the skip-next button is tapped.
 * @param onMiniPlayerClick Callback when the bar body is tapped (expand to full).
 * @param modifier         Optional outer [Modifier].
 */
@Composable
fun MiniPlayer(
    songTitle: String = "No song playing",
    artistName: String = "Unknown artist",
    isPlaying: Boolean = false,
    playbackProgress: Float = 0f,
    onPlayPauseClick: () -> Unit = {},
    onSkipNextClick: () -> Unit = {},
    onMiniPlayerClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    // Smoothly animate the progress bar width
    val animatedProgress by animateFloatAsState(
        targetValue = playbackProgress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 300),
        label = "progress"
    )

    // Animate play button glow based on playback state
    val playBtnBackground by animateColorAsState(
        targetValue = if (isPlaying) NeonPink else NeonPinkDim,
        animationSpec = tween(durationMillis = 300),
        label = "playBg"
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onMiniPlayerClick),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        color = BarBackground,
        tonalElevation = 8.dp,
        shadowElevation = 12.dp
    ) {

        Column {

            // ── Progress indicator ──────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.5.dp)
                    .background(ProgressTrack)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = animatedProgress)
                        .fillMaxHeight()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(NeonPink, NeonPink.copy(alpha = 0.7f))
                            )
                        )
                )
            }

            // ── Main row ────────────────────────────────────────
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // ── Album-art placeholder ───────────────────────
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    ArtBorder,
                                    ArtCircle
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.MusicNote,
                        contentDescription = null,
                        tint = NeonPinkDim,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                // ── Song info ───────────────────────────────────
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = songTitle,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = artistName,
                        color = SubtitleGray,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // ── Skip next ───────────────────────────────────
                IconButton(
                    onClick = onSkipNextClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.SkipNext,
                        contentDescription = "Skip next",
                        tint = ControlGray,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                // ── Play / Pause — filled circle ────────────────
                IconButton(
                    onClick = onPlayPauseClick,
                    modifier = Modifier.size(42.dp),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = playBtnBackground
                    )
                ) {
                    Icon(
                        imageVector = if (isPlaying)
                            Icons.Rounded.Pause
                        else
                            Icons.Rounded.PlayArrow,
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

// ── IDE Previews ────────────────────────────────────────────────

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
private fun MiniPlayerPausedPreview() {
    MiniPlayer(
        songTitle = "Digital Horizon",
        artistName = "The Vector",
        isPlaying = false,
        playbackProgress = 0.35f
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
private fun MiniPlayerPlayingPreview() {
    MiniPlayer(
        songTitle = "Midnight Reflection",
        artistName = "Synthwave Archive",
        isPlaying = true,
        playbackProgress = 0.65f
    )
}
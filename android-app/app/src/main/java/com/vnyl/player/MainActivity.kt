package com.vnyl.player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.vnyl.player.ui.navigation.VnylNavigation
import com.vnyl.player.ui.theme.VnylPlayerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            VnylPlayerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    VnylNavigation()
                }
            }
        }
    }
}
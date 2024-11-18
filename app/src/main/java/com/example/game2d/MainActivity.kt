package com.example.game2d

import Game
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.game2d.ui.theme.Game2DTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Game2DTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                    val game = Game(GlobalScope)
                    Start(m = Modifier.padding(innerPadding),game)
                }
            }
        }
    }
}

@Composable
fun Start(m: Modifier, game: Game) {
    val counter by game.state.collectAsState()
    var counter2 by remember { mutableStateOf(0) }

    Row {
        Button(
            onClick = {
                game.Play()
            },
            modifier = m
        )

        {
            Text(text = "開始")
        }
        Text(text = counter.toString(), modifier = m)

        Button(
            onClick = {
                counter2++
            },
            modifier = m
        )
        {
            Text(text = "加1")
        }
        Text(text = counter2.toString(), modifier = m)
    }
}

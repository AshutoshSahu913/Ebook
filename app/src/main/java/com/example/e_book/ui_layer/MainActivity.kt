package com.example.e_book.ui_layer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.e_book.ui.theme.EBookTheme
import com.example.e_book.ui_layer.Screens.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EBookTheme {
                Scaffold(content = {
                    Box(modifier = Modifier.background(Color.White)
                        .fillMaxSize()
                        .padding(it)) {
                        TabLayout()

                    }
                })
            }
        }

    }
}

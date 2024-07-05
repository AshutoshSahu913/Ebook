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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.e_book.ui.theme.EBookTheme
import com.example.e_book.ui_layer.Navigation.NavControllerHost
import com.example.e_book.ui_layer.Screens.ProfileScreen
import com.example.e_book.ui_layer.Screens.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            EBookTheme {
                val navController = rememberNavController()
                Scaffold(
                    content = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .padding(it)
                    ) {
                        NavControllerHost(navController = navController)
//
//                        ProfileScreen(
//                            navController = navController,
//                            userId = 1,
//                            userName = "",
//                            userPhone = "",
//                            userImg = ""
//                        )
                    }
                })
            }
        }
    }
}

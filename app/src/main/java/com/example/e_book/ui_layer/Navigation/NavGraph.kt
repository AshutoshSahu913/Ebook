package com.example.e_book.ui_layer.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.e_book.ui_layer.Screens.BookByCategory
import com.example.e_book.ui_layer.Screens.HomeScreen
import com.example.e_book.ui_layer.Screens.LoginScreen
import com.example.e_book.ui_layer.Screens.ShowPdfScreen
import com.example.e_book.ui_layer.Screens.WelcomeScreen


@Composable
fun NavControllerHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavigationItem.LoginScreen) {
        composable<NavigationItem.HomeScreen> {
            HomeScreen(navController = navController)
        }

        composable<NavigationItem.CategoryScreen> {
            val category = it.toRoute<NavigationItem.CategoryScreen>()
            BookByCategory(category = category.category, navController = navController)
        }

        composable<NavigationItem.WelcomeScreen> {
            WelcomeScreen(navController = navController)
        }
        composable<NavigationItem.ShowPdfScreen> {
            val url = it.toRoute<NavigationItem.ShowPdfScreen>()
            ShowPdfScreen(navController = navController, url = url.url)
        }

        composable<NavigationItem.LoginScreen> {
            LoginScreen(navController = navController)
        }
    }
}
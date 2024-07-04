package com.example.e_book.ui_layer.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.e_book.ui_layer.Screens.BookByCategory
import com.example.e_book.ui_layer.Screens.HomeScreen
import com.example.e_book.ui_layer.Screens.LoginScreen
import com.example.e_book.ui_layer.Screens.ProfileScreen
import com.example.e_book.ui_layer.Screens.ShowPdfScreen
import com.example.e_book.ui_layer.Screens.WelcomeScreen
import com.example.e_book.ui_layer.ViewModel.ViewModel


@Composable
fun NavControllerHost(navController: NavHostController) {
    val viewModel: ViewModel = hiltViewModel()
    val userId = viewModel.userIdFlow.collectAsState().value
    val userName = viewModel.userNameFlow.collectAsState().value
    val userPhone = viewModel.userPhoneFlow.collectAsState().value
    val userImg = viewModel.userImgFlow.collectAsState().value


    val startScreen = if (userId == 1) {
        NavigationItem.HomeScreen
    } else {
        NavigationItem.LoginScreen
    }

    NavHost(navController = navController, startDestination = startScreen) {
        composable<NavigationItem.HomeScreen> {
            HomeScreen(navController = navController, userId, userName, userPhone, userImg)
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

        composable<NavigationItem.ProfileScreen> {
            ProfileScreen(navController = navController, userId, userName, userPhone, userImg)
        }
    }
}
package com.example.e_book.ui_layer.Navigation

import kotlinx.serialization.Serializable

sealed class NavigationItem {
    @Serializable
    object HomeScreen


    @Serializable
    data class CategoryScreen(val category: String)

    @Serializable
    data class ShowPdfScreen(val url: String)

    @Serializable
    object WelcomeScreen

    @Serializable
    object LoginScreen

    @Serializable
    object ProfileScreen
}

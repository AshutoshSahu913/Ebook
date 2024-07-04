package com.example.e_book


sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error<T>(val exception: Throwable) : ResultState<T>()
    data object Loading : ResultState<Nothing>()
}

data class BookModel(
    var bookAuthor: String = "",
    var bookImgUrl: String = "",
    var bookName: String = "",
    var bookPdfUrl: String = "",
    var bookCategory: String = ""
)

data class BookCategory(
    var Name: String = "",
    var img: String = ""
)
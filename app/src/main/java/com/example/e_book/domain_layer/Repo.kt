package com.example.e_book.domain_layer

import com.example.e_book.BookCategory
import com.example.e_book.BookModel
import com.example.e_book.ResultState
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun getAllBook(): Flow<ResultState<List<BookModel>>>
    fun getBooksByCategory(category: String): Flow<ResultState<List<BookModel>>>
    fun getAllCategory(): Flow<ResultState<List<BookCategory>>>
}
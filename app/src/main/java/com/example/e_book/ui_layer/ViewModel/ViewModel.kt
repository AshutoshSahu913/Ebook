package com.example.e_book.ui_layer.ViewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book.BookCategory
import com.example.e_book.BookModel
import com.example.e_book.ResultState
import com.example.e_book.domain_layer.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModel @Inject constructor(val repo: Repo) : ViewModel() {
    private val _state: MutableState<ItemsState> = mutableStateOf(ItemsState())
    val state: MutableState<ItemsState> = _state


    fun getAllCategories() {
        viewModelScope.launch {
            repo.getAllCategory().collect {
                when (it) {
                    is ResultState.Error -> {
                        _state.value =
                            ItemsState(error = it.exception.localizedMessage!!.toString())
                    }

                    ResultState.Loading -> {
                        _state.value = ItemsState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        Log.d("CATEGORY", "ViewModel 2: ${it.data} ")
                        _state.value = ItemsState(category = it.data)
                    }
                }
            }
        }
    }

    fun getAllBooks() {
        viewModelScope.launch {
            repo.getAllBook().collect {
                when (it) {
                    is ResultState.Error -> {
                        _state.value =
                            ItemsState(error = it.exception.localizedMessage!!.toString())
                    }

                    ResultState.Loading -> {
                        _state.value = ItemsState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        Log.d("BOOK", "ViewModel:------------------${it.data} ")
                        _state.value = ItemsState(books = it.data)
                    }
                }
            }
        }
    }

    fun getBooksByCategory(category: String) {
        viewModelScope.launch {
            repo.getBooksByCategory(category=category).collect {
                when (it) {
                    is ResultState.Error -> {
                        _state.value =
                            ItemsState(error = it.exception.localizedMessage!!.toString())
                    }

                    ResultState.Loading -> {
                        _state.value = ItemsState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        Log.d("BOOK", "ViewModel:------------------${it.data} ")
                        _state.value = ItemsState(books = it.data)
                    }
                }
            }
        }
    }

}

data class ItemsState(
    val books: List<BookModel> = emptyList(),
    val category: List<BookCategory> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)
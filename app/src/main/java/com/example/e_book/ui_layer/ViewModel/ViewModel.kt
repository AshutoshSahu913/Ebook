package com.example.e_book.ui_layer.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book.BookModel
import com.example.e_book.ResultState
import com.example.e_book.domain_layer.AllBookRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModel @Inject constructor(val repo: AllBookRepo) : ViewModel() {

    private val _state: MutableState<ItemsState> = mutableStateOf(ItemsState())
    val state: MutableState<ItemsState> = _state

    init {
        viewModelScope.launch {
            repo.getAllBook().collect {
                when (it) {
                    is ResultState.Error -> {
                        _state.value = ItemsState(error = it.exception.localizedMessage.toString())
                    }
                    ResultState.Loading -> {
                        _state.value = ItemsState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _state.value = ItemsState(books = it.data)
                    }
                }
            }

        }
    }

}

data class ItemsState(
    val books: List<BookModel> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)
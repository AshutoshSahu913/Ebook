package com.example.e_book.ui_layer.ViewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_book.BookCategory
import com.example.e_book.BookModel
import com.example.e_book.ResultState
import com.example.e_book.domain_layer.Repo
import com.example.e_book.ui_layer.Pref.UserPrefImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModel @Inject constructor(
    private val repo: Repo,
    private val dataStore: DataStore<Preferences>
) :
    ViewModel() {
    private val _state: MutableState<ItemsState> = mutableStateOf(ItemsState())
    val state: MutableState<ItemsState> = _state
    private val userPref = UserPrefImpl(dataStore)
    val userIdFlow = userPref.getUserId().stateIn(
        scope = viewModelScope,
        initialValue = 0,
        started = SharingStarted.WhileSubscribed()
    )
    val userNameFlow = userPref.getUserName().stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed()
    )
    val userPhoneFlow = userPref.getUserPhone().stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed()
    )

    val userImgFlow = userPref.getUserImg().stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed()
    )


    fun saveUserId(userId: Int) {
        viewModelScope.launch {
            userPref.saveUserId(userId = userId)
        }
    }


    fun saveUserName(userName: String) {
        viewModelScope.launch {
            userPref.saveUserName(userName = userName)
        }
    }

    fun saveUserPhone(userPhone: String) {
        viewModelScope.launch {
            userPref.saveUserPhone(userPhone = userPhone)
        }
    }

    fun saveUserImg(userImg: String) {
        viewModelScope.launch {
            userPref.saveUserImg(userImg = userImg)
        }
    }


    fun deleteUserId() {
        viewModelScope.launch {
            userPref.deleteUserId()
        }
    }

    fun deleteUserName() {
        viewModelScope.launch {
            userPref.deleteUserName()
        }
    }

    fun deleteUserPhone() {
        viewModelScope.launch {
            userPref.deleteUserPhone()
        }
    }

    fun deleteUserImg() {
        viewModelScope.launch {
            userPref.deleteUserImg()
        }
    }
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
//                        Log.d("BOOK", "ViewModel:------------------${it.data} ")
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
//                        Log.d("BOOK", "ViewModel:------------------${it.data} ")
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
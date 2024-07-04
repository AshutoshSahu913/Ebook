package com.example.e_book.ui_layer.Pref

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow


val USER_KEY = intPreferencesKey("user_key")
val USER_NAME = stringPreferencesKey("user_name_key")
val USER_PHONE = stringPreferencesKey("user_phone_key")
val USER_IMG = stringPreferencesKey("user_img_key")

interface UserPref {

    fun getUserId(): Flow<Int>
    suspend fun saveUserId(userId: Int)
    suspend fun deleteUserId()


    fun getUserName(): Flow<String>
    suspend fun saveUserName(userName: String)
    suspend fun deleteUserName()


    fun getUserPhone(): Flow<String>
    suspend fun saveUserPhone(userPhone: String)
    suspend fun deleteUserPhone()

    fun getUserImg(): Flow<String>
    suspend fun saveUserImg(userImg: String)
    suspend fun deleteUserImg()

}
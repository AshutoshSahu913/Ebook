package com.example.e_book.ui_layer.Pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPrefImpl(private val dataStore: DataStore<Preferences>) : UserPref {
    override fun getUserId(): Flow<Int> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[USER_KEY] ?: 0
        }
    }

    override suspend fun saveUserId(userId: Int) {
        dataStore.edit {
            it[USER_KEY] = userId
        }
    }

    override suspend fun deleteUserId() {
        dataStore.edit { pref ->
            pref.remove(USER_KEY)
        }
    }

    override fun getUserName(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[USER_NAME] ?: "NOOB"
        }
    }

    override suspend fun saveUserName(userName: String) {
        dataStore.edit {
            it[USER_NAME] = userName
        }
    }

    override suspend fun deleteUserName() {
        dataStore.edit { pref ->
            pref.remove(USER_NAME)

        }
    }

    override fun getUserPhone(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[USER_PHONE] ?: "9211 6162 "
        }
    }

    override suspend fun saveUserPhone(userPhone: String) {
        dataStore.edit {
            it[USER_PHONE] = userPhone
        }
    }

    override suspend fun deleteUserPhone() {
        dataStore.edit { pref ->
            pref.remove(USER_PHONE)
        }
    }

    override fun getUserImg(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[USER_IMG] ?: "Empty"
        }
    }

    override suspend fun saveUserImg(userImg: String) {
        dataStore.edit {
            it[USER_IMG] = userImg
        }
    }

    override suspend fun deleteUserImg() {
        dataStore.edit { pref ->
            pref.remove(USER_IMG)
        }
    }
}
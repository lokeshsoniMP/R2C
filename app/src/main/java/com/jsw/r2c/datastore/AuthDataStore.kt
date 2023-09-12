package com.jsw.r2c.datastore

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val USER_DATA = "r2c_auth"


private val Context.dataStore by preferencesDataStore(
    name = USER_DATA,
    produceMigrations = { context ->
        listOf(SharedPreferencesMigration(context, USER_DATA))
    }
)

class AuthDataStoreManager(val context: Context) {

    companion object {
        val ID = stringPreferencesKey("ID")
        val isLoggedIn = booleanPreferencesKey("isloggedIn")

    }


    suspend fun isLogin(login: Boolean) {
        context.dataStore.edit {
            it[isLoggedIn] = login
        }
    }


    val getUserLogInState: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[isLoggedIn] ?: false
    }

    suspend fun saveId(id: String) {
        context.dataStore.edit {
            it[ID] = id
        }
    }
    suspend fun removeUser() {
        context.dataStore.edit {
            it.clear()
        }
    }


    val getId: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[ID] ?: ""
    }


}
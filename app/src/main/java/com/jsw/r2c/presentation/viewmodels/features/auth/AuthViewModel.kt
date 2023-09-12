package com.jsw.r2c.presentation.viewmodels.features.auth

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.jsw.r2c.base.ResponseConst.API_ERROR_MSG
import com.jsw.r2c.data.model.UserModel
import com.jsw.r2c.datastore.AuthDataStoreManager
import com.jsw.r2c.repository.AuthRepository
import com.jsw.r2c.retrofit.request.auth.LoginRequest
import com.jsw.r2c.retrofit.response.auth.LoginResponse
import com.jsw.r2c.retrofit.response.material.MaterialResponse
import com.jsw.r2c.retrofit.utlis.ApiState
import com.jsw.r2c.room.dao.AuthDao

import com.jsw.r2c.room.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val authRepository: AuthRepository,
    private val authDao: AuthDao
) :
    ViewModel() {
    val loginResponse: MutableState<ApiState<LoginResponse>> = mutableStateOf(ApiState.Empty)
    var authDataStore: AuthDataStoreManager
   private lateinit var getUser: UserEntity

    init {
        authDataStore = AuthDataStoreManager(context)



    }

    fun getUser(): UserEntity {

        viewModelScope.launch {
            if (authDataStore.getUserLogInState.first()) {
                getUser = authDao.getUser(authDataStore.getId.first())

            }

        }
        return getUser
    }


    fun login(loginRequest: LoginRequest) = viewModelScope.launch {
        authRepository.login(
            loginRequest
        ).onStart {
            loginResponse.value = ApiState.Loading
        }.catch {

            loginResponse.value = ApiState.Failure(API_ERROR_MSG)

        }.collect {


            loginResponse.value = ApiState.Success(it)
        }
    }

    fun saveUserAppLoginPref(id: String) = viewModelScope.launch {
        authDataStore.saveId(id)
        authDataStore.isLogin(true)

    }

    fun saveUser(token: String) {
        val jwt = JWT(token)
        val id = jwt.getClaim("Id").asString()
        val name = jwt.getClaim("name").asString()
        val role = jwt.getClaim("role").asString()
        val jti = jwt.getClaim("jti").asString()
        val nbf = jwt.getClaim("nbf").asInt()
        val exp = jwt.getClaim("exp").asInt()
        val iat = jwt.getClaim("iat").asInt()
        val iss = jwt.getClaim("iss").asString()
        val aud = jwt.getClaim("aud").asString()


        val user = UserEntity(
            Id = id.toString(),
            name = name.toString(),
            role = role.toString(),
            jti = jti.toString(),
            nbf = nbf ?: 0,
            exp = exp ?: 0,
            iat = iat ?: 0,
            iss = iss.toString(),
            aud = aud.toString()
        )
        authDao.insertUser(user = user)
        saveUserAppLoginPref(id.toString())

    }

}
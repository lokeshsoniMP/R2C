package com.jsw.r2c.repository

import com.jsw.r2c.retrofit.networkApi.AuthAPIService
import com.jsw.r2c.retrofit.request.auth.LoginRequest
import com.jsw.r2c.retrofit.request.requisition.CreateRequisitionRequest
import com.jsw.r2c.retrofit.response.auth.LoginResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val
    authAPIService: AuthAPIService
) {
    fun login(loginRequest: LoginRequest): Flow<LoginResponse> =
        flow {
            emit(authAPIService.Login(loginRequest))
        }
}
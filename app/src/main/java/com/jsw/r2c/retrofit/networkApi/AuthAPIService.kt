package com.jsw.r2c.retrofit.networkApi

import com.jsw.r2c.retrofit.request.auth.LoginRequest
import com.jsw.r2c.retrofit.response.auth.LoginResponse
import com.jsw.r2c.retrofit.response.material.MaterialResponse
import com.jsw.r2c.retrofit.response.material.materialDetail.MaterialDetailResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthAPIService {


    @POST("Accounts/logintest")
    suspend fun Login(
        @Body loginRequest: LoginRequest
    ): LoginResponse


}
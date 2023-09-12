package com.jsw.r2c.retrofit.request.auth

data class LoginRequest(
    val password: String,
    val userName: String
)
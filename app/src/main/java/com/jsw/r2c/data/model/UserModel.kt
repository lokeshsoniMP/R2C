package com.jsw.r2c.data.model

data class UserModel(
    val Id: String,
    val aud: String,
    val exp: Int,
    val iat: Int,
    val iss: String,
    val jti: String,
    val name: String,
    val nbf: Int,
    val role: String
)
package com.jsw.r2c.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserEntity(
    val Id: String,
    val aud: String,
    val exp: Int,
    val iat: Int,
    val iss: String,
    val jti: String,
    val name: String,
    val nbf: Int,
    val role: String
) {
    @PrimaryKey(autoGenerate = true)
    var pid: Int? = null
}
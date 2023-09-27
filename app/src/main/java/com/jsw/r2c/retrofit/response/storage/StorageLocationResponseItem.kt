package com.jsw.r2c.retrofit.response.storage

data class StorageLocationResponseItem(
    val id: Int,
    val name: String,
    val plant: String?,
    val plantId: Int?
)
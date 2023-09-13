package com.jsw.r2c.retrofit.response.tracking

data class Tracking(
    val actionBy: String,
    val actionDate: String,
    val id: Int,
    val requisitionId: Int,
    val status: String
)
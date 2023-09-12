package com.jsw.r2c.retrofit.response.requisition.requisitionApproveResponse

data class Tracking(
    val actionBy: String,
    val actionDate: String,
    val id: Int,
    val requisitionId: Int,
    val status: String
)
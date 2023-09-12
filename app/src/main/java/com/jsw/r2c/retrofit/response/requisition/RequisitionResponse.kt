package com.jsw.r2c.retrofit.response.requisition

data class RequisitionResponse(
    val deliveryDate: String,
    val id: Int,
    val materialId: Int,
    val plantId: Int,
    val quantity: Int,
    val storageLocationId: Int,
    val unitsId: Int,
    val userId: String
)
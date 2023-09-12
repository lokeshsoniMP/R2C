package com.jsw.r2c.retrofit.request.requisition

data class CreateRequisitionRequest(
    val deliveryDate: String,
    val materialId: Int,
    val plantId: Int,
    val quantity: Int,
    val storageLocationId: Int,
    val unitsId: Int,
    val userId: String
)
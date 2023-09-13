package com.jsw.r2c.retrofit.response.tracking

data class TrackingResponse(
    val approver1: String,
    val approver2: Any,
    val deliveryDate: String,
    val id: Int,
    val material: Any,
    val materialId: Int,
    val plant: Any,
    val plantId: Int,
    val quantity: Int,
    val rejectedBy: Any,
    val rejectedByReason: Any,
    val status: String,
    val storageLocation: Any,
    val storageLocationId: Int,
    val tracking: List<Tracking>,
    val units: Any,
    val unitsId: Int,
    val userId: String
)
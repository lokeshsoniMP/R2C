package com.jsw.r2c.retrofit.request.requisition

data class CreateGINRequest(

    val quantity: Int,
    val unitsId: Int,
    val documentDate: String,
    val ginStorageLocationId: Int

)
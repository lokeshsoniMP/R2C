package com.jsw.r2c.retrofit.request.requisition

data class AssignPackagingSupervisorRequest(

    val plantId: Int,
    val storageId: Int,
    val packagingSupervisor: String,
    val shortText: Any

)
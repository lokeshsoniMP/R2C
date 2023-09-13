package com.jsw.r2c.retrofit.response.notification

data class NotificationResponseItem(
    val approver1: Any,
    val approver2: Any,
    val id: Int,
    val notificationText: String,
    val requisition: Any,
    val requisitionId: Int
)
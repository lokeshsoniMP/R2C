package com.jsw.r2c.retrofit.networkApi

import com.jsw.r2c.retrofit.request.auth.LoginRequest
import com.jsw.r2c.retrofit.request.requisition.CreateRequisitionRequest
import com.jsw.r2c.retrofit.response.material.MaterialResponse
import com.jsw.r2c.retrofit.response.material.materialDetail.MaterialDetailResponse
import com.jsw.r2c.retrofit.response.notification.NotificationResponse
import com.jsw.r2c.retrofit.response.plant.PlantResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionResponse
import com.jsw.r2c.retrofit.response.requisition.requisitionApproveResponse.RequisitionApproveStatusResponse
import com.jsw.r2c.retrofit.response.storage.StorageLocationResponse
import com.jsw.r2c.retrofit.response.unit.UnitTypeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RequisitionAPIService {

    @GET("Requisition/GetRequisitions")
    suspend fun GetRequisitionsList(
    ): RequisitionListResponse

    @GET("Materials/GetMaterials")
    suspend fun GetMaterials(
    ): MaterialResponse


    @GET("Notifications/GetNotifications")
    suspend fun GetNotification(
    ): NotificationResponse
    @GET("Materials/GetMaterialDetails?id={id}")
    suspend fun GetMaterialDetailById(
        @Path("id") userId: Int?,
    ): MaterialDetailResponse

    @GET("Units/GetUnits")
    suspend fun GetUnits(
    ): UnitTypeResponse

    @GET("Plants/GetPlants")
    suspend fun GetPlants(
    ): PlantResponse

    @GET("StorageLocation/GetStorageLocations")
    suspend fun GetStorageLocation(
        @Query("plantId") plantId: Int?
    ): StorageLocationResponse

    @POST("Requisition/SaveRequisition")
    suspend fun CreateRequisition(
        @Body requisitionRequest: CreateRequisitionRequest
    ): RequisitionResponse


    @POST("Requisition/{requistion_id}/status?requisitionStatus=1&approver=manager%40gmail.com")
    suspend fun ApproveRequisitionStatus(
        @Path("requistion_id") requistion_id: Int?,
        @Query("requisitionStatus") requisitionStatus: Int?,
        @Query("approver") approver: String?
    ): RequisitionApproveStatusResponse

}
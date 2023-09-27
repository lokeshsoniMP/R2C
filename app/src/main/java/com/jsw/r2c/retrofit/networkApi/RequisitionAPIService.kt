package com.jsw.r2c.retrofit.networkApi

import com.jsw.r2c.retrofit.request.auth.LoginRequest
import com.jsw.r2c.retrofit.request.requisition.AssignPackagingSupervisorRequest
import com.jsw.r2c.retrofit.request.requisition.CreateGINRequest
import com.jsw.r2c.retrofit.request.requisition.CreateRequisitionRequest
import com.jsw.r2c.retrofit.response.material.MaterialResponse
import com.jsw.r2c.retrofit.response.material.materialDetail.MaterialDetailResponse
import com.jsw.r2c.retrofit.response.notification.NotificationResponse
import com.jsw.r2c.retrofit.response.plant.PlantResponse
import com.jsw.r2c.retrofit.response.requisition.AssignPackagingSupervisorResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionGINResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionResponse
import com.jsw.r2c.retrofit.response.requisition.requisitionApproveResponse.RequisitionApproveStatusResponse
import com.jsw.r2c.retrofit.response.storage.StorageLocationResponse
import com.jsw.r2c.retrofit.response.tracking.TrackingResponse
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


    @POST("Requisition/{id}/status?requisitionStatus={requisitionStatus}&approver={approver}")
    suspend fun ApproveRequisitionStatus(
        @Path("id") requistion_id: Int?,
        @Query("requisitionStatus") requisitionStatus: Int?,
        @Query("approver") approver: String?
    ): RequisitionApproveStatusResponse

    @POST("Requisition/{id}")
    suspend fun GetRequisitionDetails(
        @Path("id") requistion_id: Int?
    ): TrackingResponse

    @POST("Requisition/{id}/AssignPackagingSupervisor")
    suspend fun AssignPackagingSupervisor(
        @Path("id") requistion_id: Int?,
        @Body assignPackagingSupervisorRequest: AssignPackagingSupervisorRequest
    ): AssignPackagingSupervisorResponse

    @POST("Requisition/{id}/Gin")
    suspend fun createGINRequisition(
        @Path("id") requistion_id: Int?,
        @Body ginRequest: CreateGINRequest
    ): RequisitionGINResponse
}
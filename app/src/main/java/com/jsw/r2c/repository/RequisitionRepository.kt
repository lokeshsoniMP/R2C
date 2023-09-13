package com.jsw.r2c.repository

import com.jsw.r2c.retrofit.networkApi.RequisitionAPIService
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RequisitionRepository @Inject constructor(
    private val
    requisitionApiService: RequisitionAPIService,

    ) {

    fun getMaterials(): Flow<MaterialResponse> = flow {
        emit(requisitionApiService.GetMaterials())
    }

    fun getNotification(): Flow<NotificationResponse> = flow {
        emit(requisitionApiService.GetNotification())
    }

    fun getMaterialGroup(id: Int): Flow<MaterialDetailResponse> = flow {
        emit(requisitionApiService.GetMaterialDetailById(id))
    }

    fun getMaterialUnitType(): Flow<UnitTypeResponse> = flow {
        emit(requisitionApiService.GetUnits())
    }

    fun getPlants(): Flow<PlantResponse> = flow {
        emit(requisitionApiService.GetPlants())
    }

    fun createRequisition(createRequisitionRequest: CreateRequisitionRequest): Flow<RequisitionResponse> =
        flow {
            emit(requisitionApiService.CreateRequisition(createRequisitionRequest))
        }

    fun getRequisitionList(): Flow<RequisitionListResponse> =
        flow {
            emit(requisitionApiService.GetRequisitionsList())
        }

    fun getStorageLocation(plantId: Int): Flow<StorageLocationResponse> =
        flow {
            emit(requisitionApiService.GetStorageLocation(plantId))
        }

    fun approveRequisitionStatus(
        requsitionId: Int,
        approver: String,
        status: Int
    ): Flow<RequisitionApproveStatusResponse> =
        flow {
            emit(
                requisitionApiService.ApproveRequisitionStatus(
                    requistion_id = requsitionId,
                    approver = approver,
                    requisitionStatus = status
                )
            )
        }

}
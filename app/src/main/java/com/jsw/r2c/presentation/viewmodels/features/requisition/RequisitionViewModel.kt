package com.jsw.r2c.presentation.viewmodels.features.requisition

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsw.r2c.base.ResponseConst.API_ERROR_MSG
import com.jsw.r2c.repository.RequisitionRepository
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
import com.jsw.r2c.retrofit.utlis.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RequisitionViewModel @Inject constructor(private val requisitionRepository: RequisitionRepository) :
    ViewModel() {
    val materialIdResponse: MutableState<ApiState<MaterialResponse>> =
        mutableStateOf(ApiState.Empty)
    val materialGroupResponse: MutableState<ApiState<MaterialDetailResponse>> =
        mutableStateOf(ApiState.Empty)
    val unitTypeResponse: MutableState<ApiState<UnitTypeResponse>> =
        mutableStateOf(ApiState.Empty)
    val createRequisitionResponse: MutableState<ApiState<RequisitionResponse>> =
        mutableStateOf(ApiState.Empty)
    val getPlantsResponse: MutableState<ApiState<PlantResponse>> =
        mutableStateOf(ApiState.Empty)
    val getRequisitionListResponse: MutableState<ApiState<RequisitionListResponse>> =
        mutableStateOf(ApiState.Empty)

    val getStorageLocationResponse: MutableState<ApiState<StorageLocationResponse>> =
        mutableStateOf(ApiState.Empty)
    val approveStatusResponse: MutableState<ApiState<RequisitionApproveStatusResponse>> =
        mutableStateOf(ApiState.Empty)

    val getNotification: MutableState<ApiState<NotificationResponse>> =
        mutableStateOf(ApiState.Empty)

    fun getMaterial() = viewModelScope.launch {
        requisitionRepository.getMaterials().onStart {
            materialIdResponse.value = ApiState.Loading
        }.catch {

            materialIdResponse.value = ApiState.Failure(API_ERROR_MSG)

        }.collect {
            materialIdResponse.value = ApiState.Success(it)
        }
    }

    fun getNotificationResponse() = viewModelScope.launch {
        requisitionRepository.getNotification().onStart {
            getNotification.value = ApiState.Loading
        }.catch {

            getNotification.value = ApiState.Failure(API_ERROR_MSG)

        }.collect {
            getNotification.value = ApiState.Success(it)
        }
    }


    fun getRequisitionList() = viewModelScope.launch {
        requisitionRepository.getRequisitionList().onStart {
            getRequisitionListResponse.value = ApiState.Loading
        }.catch {

            getRequisitionListResponse.value = ApiState.Failure(API_ERROR_MSG)

        }.collect {
            getRequisitionListResponse.value = ApiState.Success(it)
        }
    }

    fun getMaterialGroup(id: Int) = viewModelScope.launch {
        requisitionRepository.getMaterialGroup(id).onStart {
            materialGroupResponse.value = ApiState.Loading
        }.catch {

            materialGroupResponse.value = ApiState.Failure(API_ERROR_MSG)

        }.collect {
            materialGroupResponse.value = ApiState.Success(it)
        }
    }

    fun getStorageLocation(id: Int) = viewModelScope.launch {
        requisitionRepository.getStorageLocation(id).onStart {
            getStorageLocationResponse.value = ApiState.Loading
        }.catch {

            getStorageLocationResponse.value = ApiState.Failure(API_ERROR_MSG)

        }.collect {

            getStorageLocationResponse.value = ApiState.Success(it)
        }
    }


    fun getMaterialUnitType() = viewModelScope.launch {
        requisitionRepository.getMaterialUnitType().onStart {
            unitTypeResponse.value = ApiState.Loading
        }.catch {

            unitTypeResponse.value = ApiState.Failure(API_ERROR_MSG)

        }.collect {
            unitTypeResponse.value = ApiState.Success(it)
        }
    }

    fun getPlants() = viewModelScope.launch {
        requisitionRepository.getPlants().onStart {
            getPlantsResponse.value = ApiState.Loading
        }.catch {

            getPlantsResponse.value = ApiState.Failure(API_ERROR_MSG)

        }.collect {
            getPlantsResponse.value = ApiState.Success(it)
        }
    }


    fun changeRequisitionRequestStatus(
        requisitionId: Int,
        approver: String,
        status: Int
    ) = viewModelScope.launch {
        requisitionRepository.approveRequisitionStatus(requisitionId, approver, status).onStart {
            approveStatusResponse.value = ApiState.Loading
        }.catch {

            approveStatusResponse.value = ApiState.Failure(API_ERROR_MSG)

        }.collect {
            approveStatusResponse.value = ApiState.Success(it)
        }
    }


    fun createRequisition(createRequisitionRequest: CreateRequisitionRequest) =
        viewModelScope.launch {
            requisitionRepository.createRequisition(createRequisitionRequest).onStart {
                createRequisitionResponse.value = ApiState.Loading
            }.catch {

                createRequisitionResponse.value = ApiState.Failure(API_ERROR_MSG)

            }.collect {
                createRequisitionResponse.value = ApiState.Success(it)
            }
        }
}
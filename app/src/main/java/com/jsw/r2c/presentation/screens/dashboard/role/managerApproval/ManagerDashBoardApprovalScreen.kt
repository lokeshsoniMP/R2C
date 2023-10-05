package com.jsw.r2c.presentation.screens.dashboard.role.managerApproval

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.sp

import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jsw.r2c.base.Utilis
import com.jsw.r2c.presentation.screens.dashboard.navigation.DashBoardNavigationRoute
import com.jsw.r2c.presentation.screens.dashboard.role.RequisitionText
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa
import com.jsw.r2c.presentation.viewmodels.features.auth.AuthViewModel
import com.jsw.r2c.presentation.viewmodels.features.requisition.RequisitionViewModel
import com.jsw.r2c.retrofit.response.material.MaterialResponse
import com.jsw.r2c.retrofit.response.material.MaterialResponseItem
import com.jsw.r2c.retrofit.response.plant.PlantResponse
import com.jsw.r2c.retrofit.response.plant.PlantResponseItem
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponseItem
import com.jsw.r2c.retrofit.response.requisition.requisitionApproveResponse.RequisitionApproveStatusResponse
import com.jsw.r2c.retrofit.response.storage.StorageLocationResponse
import com.jsw.r2c.retrofit.response.unit.UnitTypeResponse
import com.jsw.r2c.retrofit.response.unit.UnitTypeResponseItem
import com.jsw.r2c.retrofit.utlis.ApiState
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ManagerDashBoardApprovalScreen(
    navController: NavController,
    requisitionViewModel: RequisitionViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var requisitionList = rememberSaveable {
        mutableListOf<RequisitionListResponseItem>()
    }
    var materialList = rememberSaveable {
        mutableListOf<MaterialResponseItem>()
    }
    var plantLocationList = rememberSaveable {
        mutableListOf<PlantResponseItem>()
    }
    var unityTypeList = rememberSaveable {
        mutableListOf<UnitTypeResponseItem>()
    }
    var requisitionRequestIndex by remember {
        mutableStateOf(0)
    }
    var isStatusApproved by remember {
        mutableStateOf("New")
    }

    LaunchedEffect(key1 = Unit) {
        requisitionViewModel.getRequisitionList()
        requisitionViewModel.getMaterial()
        requisitionViewModel.getMaterialUnitType()
        requisitionViewModel.getPlants()

    }
    when (requisitionViewModel.getRequisitionListResponse.value) {
        ApiState.Loading -> {
        }

        is ApiState.Success -> {
            val response =
                (requisitionViewModel.getRequisitionListResponse.value as ApiState.Success<RequisitionListResponse>).data
            requisitionList.clear()
            requisitionList = response.toMutableList()

        }

        is ApiState.Failure -> {
            val response =
                (requisitionViewModel.getRequisitionListResponse.value as ApiState.Failure).msg


        }

        else -> {

        }

    }

    when (requisitionViewModel.materialIdResponse.value) {
        ApiState.Loading -> {

        }

        is ApiState.Success -> {
            val response =
                (requisitionViewModel.materialIdResponse.value as ApiState.Success<MaterialResponse>).data
            materialList.clear()
            materialList = response.toMutableList()

        }

        is ApiState.Failure -> {
            val response = (requisitionViewModel.materialIdResponse.value as ApiState.Failure).msg

        }

        else -> {

        }

    }

    when (requisitionViewModel.unitTypeResponse.value) {
        ApiState.Loading -> {

        }

        is ApiState.Success -> {
            val response =
                (requisitionViewModel.unitTypeResponse.value as ApiState.Success<UnitTypeResponse>).data
            unityTypeList.clear()
            unityTypeList.addAll(response)

        }

        is ApiState.Failure -> {
            val response = (requisitionViewModel.unitTypeResponse.value as ApiState.Failure).msg

        }

        else -> {

        }

    }
    when (requisitionViewModel.getPlantsResponse.value) {
        ApiState.Loading -> {

        }

        is ApiState.Success -> {
            val response =
                (requisitionViewModel.getPlantsResponse.value as ApiState.Success<PlantResponse>).data
            plantLocationList.clear()
            plantLocationList.addAll(response)

        }

        is ApiState.Failure -> {
            val response = (requisitionViewModel.getPlantsResponse.value as ApiState.Failure).msg


        }

        else -> {

        }

    }

    when (requisitionViewModel.approveStatusResponse.value) {
        ApiState.Loading -> {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.padding(4.dp)
            )
        }

        is ApiState.Success -> {
            val response =
                (requisitionViewModel.approveStatusResponse.value as ApiState.Success<RequisitionApproveStatusResponse>).data
            isStatusApproved = response.status

            requisitionViewModel.getRequisitionList()
        }

        is ApiState.Failure -> {
            val response = (requisitionViewModel.approveStatusResponse.value as ApiState.Failure).msg


        }

        else -> {

        }

    }
    if(isStatusApproved == "New") {
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier.padding(16.dp)
                    .verticalScroll(rememberScrollState()),


                ) {
                Text(
                    text = "Welcome,\n${authViewModel.getUser().name}",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = Kefa,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(8.dp))

                if (requisitionList.isNotEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(2.dp, BlueDark, RoundedCornerShape(8))
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {


                        Spacer(modifier = Modifier.padding(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Tracking ID: \n ${
                                    requisitionList.get(
                                        requisitionRequestIndex
                                    ).id
                                }",
                                color = BlueDark,
                                fontWeight = FontWeight.Bold
                            )
                            Row {
                                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                                Text(text = "1-${requisitionList.size - 1}")
                                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                                Button(
                                    shape = RoundedCornerShape(0), onClick = {
                                        if (requisitionRequestIndex >= 0) {
                                            --requisitionRequestIndex
                                        }

                                    }, colors = ButtonDefaults.buttonColors(
                                        disabledContainerColor = Color.Green,
                                        containerColor = Color(0xFFD2D2D2)

                                    )
                                ) {
                                    Text(text = "<", fontSize = 19.sp)
                                }
                                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                                Button(
                                    shape = RoundedCornerShape(0), onClick = {
                                        if (requisitionRequestIndex <= requisitionList.size) {
                                            ++requisitionRequestIndex
                                        }

                                    }, colors = ButtonDefaults.buttonColors(
                                        disabledContainerColor = Color.Green,
                                        containerColor = Color(0xFFD2D2D2)

                                    )
                                ) {
                                    Text(text = ">", fontSize = 19.sp)
                                }


                            }
                        }

                        RequisitionText(
                            title = "Material Id :",
                            value = requisitionList.get(requisitionRequestIndex).materialId.toString()
                        )

                        RequisitionText(
                            title = "Short Text :", value = materialList.find {
                                requisitionList.get(requisitionRequestIndex).materialId == it.id
                            }?.shortText ?: ""
                        )
                        RequisitionText(
                            title = "Material Group :", value = materialList.find {
                                requisitionList.get(requisitionRequestIndex).materialId == it.id
                            }?.materialGroup ?: ""
                        )

                        /*   */
                        RequisitionText(
                            title = "Quantity :",
                            value = "${requisitionList.get(requisitionRequestIndex).quantity.toString()} ${
                                unityTypeList.find {
                                    requisitionList.get(requisitionRequestIndex).unitsId == it.id
                                }?.name ?: ""
                            }"
                        )


                        RequisitionText(
                            title = "Delivery Date :", value = Utilis.convertDateTimeToString(
                                requisitionList.get(
                                    requisitionRequestIndex
                                ).deliveryDate
                            )
                        )

                        RequisitionText(
                            title = "Plant Location :", value = plantLocationList.find {
                                requisitionList.get(requisitionRequestIndex).plantId == it.id
                            }?.plantName ?: ""
                        )


                        LaunchedEffect(key1 = Unit) {
                            requisitionViewModel.getStorageLocation(
                                requisitionList.get(
                                    requisitionRequestIndex
                                ).plantId
                            )
                        }


                        when (requisitionViewModel.getStorageLocationResponse.value) {
                            ApiState.Loading -> {
                            }

                            is ApiState.Success -> {

                                val response =
                                    (requisitionViewModel.getStorageLocationResponse.value as ApiState.Success<StorageLocationResponse>).data

                                RequisitionText(
                                    title = "Storage Location :", value = response.find {
                                        requisitionList.get(requisitionRequestIndex).storageLocationId == it.id
                                    }?.name ?: ""
                                )


                            }

                            is ApiState.Failure -> {

                            }

                            else -> {

                            }

                        }

                        RequisitionText(
                            title = "Status : ",
                            value = "Approval Pending",
                            valueColor = Color(0xFF851D1D)
                        )

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.5f)
                                    .padding(horizontal = 10.dp), onClick = {
                                    requisitionViewModel.changeRequisitionRequestStatus(
                                        requisitionId = requisitionList[requisitionRequestIndex].id, approver = authViewModel.getUser().name, status = 3
                                    )
                                    isStatusApproved = "Rejected"


                                }, colors = ButtonDefaults.buttonColors(
                                    disabledContainerColor = Color.Red,
                                    containerColor = Color(0xFF851D1D)
                                ), shape = RoundedCornerShape(12)

                            ) {
                                Text(text = "Cancel", color = Color.White)
                            }
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(0.5f)
                                    .padding(horizontal = 10.dp), onClick = {

                                    requisitionViewModel.changeRequisitionRequestStatus(
                                        requisitionId = requisitionList[requisitionRequestIndex].id, approver = authViewModel.getUser().name, status = 2
                                    )
                                    isStatusApproved = "Approved"
                                }, colors = ButtonDefaults.buttonColors(
                                    disabledContainerColor = Color.Green,
                                    containerColor = Color(0xFF38851D)
                                ), shape = RoundedCornerShape(12)

                            ) {
                                Text(text = "Approve", color = Color.White)
                            }
                        }

                    }
                }

            }


        }
    }else{
        Box(modifier = Modifier.fillMaxSize()) {


            Column(
                modifier = Modifier.padding(
                    16.dp
                )
            ) {


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if(isStatusApproved == "Approved") {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(92.dp)
                                .background(
                                    Color.Green,
                                    shape = CircleShape
                                )
                        ) {

                            Image(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Check",
                                modifier = Modifier.size(72.dp),
                                colorFilter = ColorFilter.tint(Color.White)
                            )

                        }
                    }else{
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(92.dp)
                                .background(
                                    Color.Red,
                                    shape = CircleShape
                                )
                        ) {
                            Image(
                                imageVector = Icons.Default.Cancel,
                                contentDescription = "Cancel",
                                modifier = Modifier.size(72.dp),
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                        }
                }

                    Spacer(modifier = Modifier.padding(16.dp))
                    if(isStatusApproved == "Approved") {
                        Text(
                            text = "Requisition Approved",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontFamily = Kefa,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }else{
                        Text(
                            text = "Requisition Reported",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontFamily = Kefa,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            LaunchedEffect(key1 = Unit) {
                delay(3000)
                navController.navigate(DashBoardNavigationRoute.Home.route)
            }

        }
    }

}



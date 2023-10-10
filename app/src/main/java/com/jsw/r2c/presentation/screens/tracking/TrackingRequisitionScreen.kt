package com.jsw.r2c.presentation.screens.tracking

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jsw.r2c.R
import com.jsw.r2c.base.Utilis
import com.jsw.r2c.presentation.screens.dashboard.role.RequisitionRequest
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa
import com.jsw.r2c.presentation.viewmodels.features.auth.AuthViewModel
import com.jsw.r2c.presentation.viewmodels.features.requisition.RequisitionViewModel
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponseItem
import com.jsw.r2c.retrofit.response.tracking.TrackingResponse
import com.jsw.r2c.retrofit.utlis.ApiState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrackingRequisitionScreen(
    navController: NavController,
    requisitionViewModel: RequisitionViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    var requisitionList = rememberSaveable {
        mutableListOf<RequisitionListResponseItem>()
    }

    var trackingList = rememberSaveable {
        mutableListOf<TrackingResponse>()
    }

    LaunchedEffect(key1 = Unit) {
        requisitionViewModel.getRequisitionList()


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
    when (requisitionViewModel.trackingResponse.value) {
        ApiState.Loading -> {
        }

        is ApiState.Success -> {
            val response =
                (requisitionViewModel.trackingResponse.value as ApiState.Success<TrackingResponse>).data
            trackingList.clear()
            trackingList.add(response)


        }

        is ApiState.Failure -> {
            val response =
                (requisitionViewModel.trackingResponse.value as ApiState.Failure).msg


        }

        else -> {

        }

    }






    Box(modifier = Modifier.fillMaxSize()) {
        val requisitionRequest = remember {
            mutableListOf<RequisitionRequest>()
        }
        var selectedRequisitionIndex by remember {
            mutableStateOf(0)
        }

        if (requisitionList.isNotEmpty() && trackingList.isEmpty()) {
            requisitionViewModel.getTrackingDetails(
                requisitionList[selectedRequisitionIndex].id
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    16.dp
                )
        ) {
            Text(
                text = "Request Tracking",
                fontSize = 20.sp,
                color = Color.Black,
                fontFamily = Kefa,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(8.dp))

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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = "Tracking ID: \n ${
                            if (requisitionList.isNotEmpty()) {
                                requisitionList.get(selectedRequisitionIndex).id
                            } else {
                                ""
                            }
                        }",
                        color = BlueDark,
                        fontWeight = FontWeight.Bold, textAlign = TextAlign.Start
                    )
                    Row {
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        Text(text = "1-${requisitionList.size - 1}")

                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        Button(
                            shape = RoundedCornerShape(0),
                            onClick = {

                                    requisitionViewModel.getTrackingDetails(
                                        requisitionList.get(
                                            selectedRequisitionIndex
                                        ).id
                                    )
                                if(selectedRequisitionIndex >0) {
                                    --selectedRequisitionIndex
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
                            shape = RoundedCornerShape(0),
                            onClick = {
                                requisitionViewModel.getTrackingDetails(
                                    requisitionList.get(
                                        selectedRequisitionIndex
                                    ).id
                                )
                                if(selectedRequisitionIndex < requisitionList.size-1) {
                                    ++selectedRequisitionIndex
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                disabledContainerColor = Color.Green,
                                containerColor = Color(0xFFD2D2D2)

                            )
                        ) {
                            Text(text = ">", fontSize = 19.sp)
                        }


                    }
                }


                Spacer(modifier = Modifier.padding(8.dp))
//                LazyColumn(
//                    contentPadding = PaddingValues(8.dp),
//                ) {
//                    if (trackingList.isNotEmpty()) {
//                        items(trackingList.get(0).tracking.size) { index ->
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                            ) {
//                                Column {
//                                    Image(
//                                        painter = painterResource(id = R.drawable.requestion_1),
//                                        contentDescription = "Requisition",
//                                        modifier = Modifier
//
//                                            .size(46.dp)
//                                            .background(
//                                                Color(
//                                                    0xFF6C91FF
//                                                ), shape = CircleShape
//                                            )
//                                            .padding(12.dp)
//                                    )
//                                    VerticalDivider(
//                                        modifier = Modifier
//                                            .height(24.dp)
//                                            .padding(start = 21.dp)
//                                            .width(width = 4.dp),
//                                        color = Color(
//                                            0xFFE1E1E1
//                                        )
//                                    )
//                                }
//                                Spacer(modifier = Modifier.padding(16.dp))
//                                Column() {
//                                    Text(
//                                        text = "${trackingList.get(0).tracking.get(index).actionBy}",
//                                        fontSize = 16.sp,
//                                        color = Color.DarkGray, fontWeight = FontWeight.Bold
//                                    )
//                                    Row {
//                                        Text(
//                                            text = "${trackingList.get(0).tracking.get(index).actionDate}",
//                                            fontSize = 14.sp,
//                                            color = Color.DarkGray
//                                        )
//                                        Spacer(modifier = Modifier.padding(16.dp))
//                                        Text(
//                                            text = "${trackingList.get(0).tracking.get(index).actionDate}",
//                                            fontSize = 14.sp,
//                                            color = Color.DarkGray,
//                                        )
//                                    }
//                                }
//                            }
//
//                        }
//
//                    }
//
//                }
                val dateSS = if (trackingList.isNotEmpty() && trackingList.size-1>=selectedRequisitionIndex) {
                    Utilis.convertDateTimeToZString(trackingList[0].tracking[selectedRequisitionIndex].actionDate)
                } else {
                    "DD-MM-YY HH:mm"
                }
                val actionDate = dateSS.split(" ")[0]
                val actionTime = dateSS.split(" ")[1]
                Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFF6C91FF
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Requisition Request Generated",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.stamp_),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFF1B6A2C
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Approved by Manager",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.stamp_),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFF1B6A2C
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Approved by Production Head",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.stamp_),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFF1B6A65
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Supervisor Assigned",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFF1B6A65
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Packing Supervisor",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFF1B6A65
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Goods Issue Note Generated",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFFBBBBBB
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "T/T Assigned for Pickup",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.scan_qr),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFFBBBBBB
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "T/T Gate Entry Scan Complete",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFFBBBBBB
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "T/T Reached Store",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFFBBBBBB
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Goods Loaded by T/T",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFFD0DCFF
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "T/T Exit Gate Pass Scanned",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFFD0DCFF
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Gatekeeper Scan Complete",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFF272727
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Reached Point A",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFF272727
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Reached Point B",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFFBBBBBB
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Goods Unloaded",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFFD0DCFF
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Goods Receipt Note",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.requestion_1),
                                contentDescription = "Requisition",
                                modifier = Modifier

                                    .size(46.dp)

                                    .background(
                                        Color(
                                            0xFF1B6A2C
                                        ), shape = CircleShape
                                    )
                                    .padding(12.dp)
                            )
                            VerticalDivider(
                                modifier = Modifier
                                    .height(24.dp)
                                    .padding(start = 21.dp)
                                    .width(width = 4.dp),
                                color = Color(
                                    0xFFE1E1E1
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Column() {
                            Text(
                                text = "Requisition Complete",
                                fontSize = 15.sp,
                                color = Color.DarkGray, fontWeight = FontWeight.Bold
                            )
                            Row {
                                Text(
                                    text = actionDate,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.padding(16.dp))
                                Text(
                                    text = actionTime,
                                    fontSize = 12.sp,
                                    color = Color.DarkGray,
                                )
                            }
                        }
                    }

                }
            }


        }
    }

}

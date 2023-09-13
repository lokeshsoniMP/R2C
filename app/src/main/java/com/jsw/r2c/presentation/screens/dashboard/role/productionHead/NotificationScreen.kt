package com.jsw.r2c.presentation.screens.dashboard.role.productionHead

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jsw.r2c.R
import com.jsw.r2c.presentation.screens.dashboard.role.RequisitionRequest
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa
import com.jsw.r2c.presentation.viewmodels.features.requisition.RequisitionViewModel
import com.jsw.r2c.retrofit.response.notification.NotificationResponse
import com.jsw.r2c.retrofit.response.notification.NotificationResponseItem
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponseItem
import com.jsw.r2c.retrofit.utlis.ApiState

@Composable
fun NotificationScreen(
    navController: NavController,
    requisitionViewModel: RequisitionViewModel = hiltViewModel(),
) {
    var notificationList = rememberSaveable {
        mutableListOf<NotificationResponseItem>()
    }

    LaunchedEffect(key1 = Unit) {
        requisitionViewModel.getNotificationResponse()
    }



    Column(
        modifier = Modifier.padding(
            16.dp
        )
    ) {
        Text(
            text = "Notification",
            fontSize = 24.sp,
            color = Color.Black,
            fontFamily = Kefa,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Column(
            /* modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, BlueDark, RoundedCornerShape(8))
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start*/
        )

        {

            val requisitionRequest = remember {
                mutableListOf<RequisitionRequest>()
            }
            var selectedRequisitionIndex by remember {
                mutableStateOf(0)
            }

            requisitionRequest.add(
                RequisitionRequest(
                    "10000",
                    "Provide me this",
                    "Hon",
                    "1000 kg",
                    "Kg",
                    "11-jan 2023",
                    "Banglore",
                    "Banglore",
                    0
                )
            )

            requisitionRequest.add(
                RequisitionRequest(
                    "10000",
                    "Provide me this",
                    "Hon",
                    "1000 kg",
                    "Kg",
                    "11-jan 2023",
                    "Banglore",
                    "Banglore",
                    0
                )
            )
        }

        when (requisitionViewModel.getNotification.value) {
            ApiState.Loading -> {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.padding(4.dp)
                )
            }

            is ApiState.Success -> {
                val response =
                    (requisitionViewModel.getNotification.value as ApiState.Success<NotificationResponse>).data
                notificationList.clear()
                notificationList = response.toMutableList()

            }

            is ApiState.Failure -> {
                val response =
                    (requisitionViewModel.getNotification.value as ApiState.Failure).msg


            }

            else -> {

            }

        }

        LazyColumn(
            contentPadding = PaddingValues(8.dp)
        ) {
            items(notificationList.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
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
                    }
                    Column() {
                        Text(
                            text = "${notificationList.get(index).notificationText}",
                            fontSize = 18.sp,
                            color = Color.DarkGray, fontWeight = FontWeight.Bold
                        )
                        Row {
                            Text(
                                text = "28-APR-23",
                                fontSize = 16.sp,
                                color = Color.DarkGray
                            )
                            Spacer(modifier = Modifier.padding(16.dp))
                            Text(
                                text = "17:50",
                                fontSize = 16.sp,
                                color = Color.DarkGray,
                            )
                        }
                    }
                }

            }

        }
    }


}




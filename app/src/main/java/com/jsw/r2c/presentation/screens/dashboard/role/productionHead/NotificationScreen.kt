package com.jsw.r2c.presentation.screens.dashboard.role.productionHead

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import com.jsw.r2c.R
import com.jsw.r2c.presentation.screens.dashboard.role.RequisitionRequest
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponseItem

@Composable
fun NotificationScreen(navController: NavController) {
    var requisitionList = rememberSaveable {
        mutableListOf<RequisitionListResponseItem>()
    }

    /*LaunchedEffect(key1 = Unit) {
        requisitionViewModel.getRequisitionList()
    }*/

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


            data class AndroidVersion(
                @DrawableRes val imageResourceId: Int,
                val name: String,
                val release: String
            )

            val androidNameList = listOf(

                AndroidVersion(R.drawable.scan_qr, "Marshmallow", "October 5, 2015"),
                AndroidVersion(R.drawable.scan_qr, "Nougat", "August 22, 2016"),
                AndroidVersion(R.drawable.scan_qr, "Oreo", "August 21, 2017"),
                AndroidVersion(R.drawable.scan_qr, "Pie", "August 6, 2018"),
                AndroidVersion(R.drawable.scan_qr, "Android 10", "September 3, 2019"),
                AndroidVersion(R.drawable.scan_qr, "Android 11", "September 8, 2020")
            )


            LazyColumn(
                contentPadding = PaddingValues(8.dp)
            ) {
                items(androidNameList) { item ->

                    Column(
                        Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .size(64.dp)
                                .padding(8.dp)
                                .clip(RoundedCornerShape(50)),
                            contentScale = ContentScale.Crop,
                            painter = painterResource(id = item.imageResourceId),
                            contentDescription = null
                        )
                        Text(text = item.name)
                    }
                }

            }
        }




}




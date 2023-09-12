package com.jsw.r2c.presentation.screens.dashboard.role.productionHead

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jsw.r2c.presentation.viewmodels.features.requisition.RequisitionViewModel
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponseItem
import com.jsw.r2c.retrofit.utlis.ApiState

@Composable
fun RequisitionDashboardProductionHead(requisitionViewModel: RequisitionViewModel = hiltViewModel()) {

    var requisitionList = rememberSaveable {
        mutableListOf<RequisitionListResponseItem>()
    }

    LaunchedEffect(key1 = Unit) {
        requisitionViewModel.getRequisitionList()
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        when (requisitionViewModel.getRequisitionListResponse.value) {
            ApiState.Loading -> {
                CircularProgressIndicator(
                    color = Color.DarkGray,
                    modifier = Modifier.padding(4.dp)
                )
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
                Log.e("Failure", response.toString())

            }

            else -> {

            }

        }
        LazyColumn() {
            item {
                RequisitionListHeader()
            }
            items(requisitionList.size) {
                RequisitionListBodyItem(
                    trackingId = requisitionList.get(it).id.toString(),
                    status = requisitionList.get(it).status
                )
            }
        }
    }

}

@Composable
fun RequisitionListHeader() {
    Column {
        Row(
            modifier = Modifier
                .background(color = Color(0xFFEFEFEF))
                .border(1.dp, Color.LightGray)
                .fillMaxWidth()
                .height(52.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .border(1.dp, Color.LightGray)
                    .fillMaxSize()
                    .padding(8.dp)
                    .weight(0.5f), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Tracking ID:", textAlign = TextAlign.Start, fontWeight = FontWeight.Bold,    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            Column(
                modifier = Modifier
                    .border(1.dp, Color.LightGray)
                    .fillMaxSize()
                    .padding(8.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .weight(0.5f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Status ", textAlign = TextAlign.Start, fontWeight = FontWeight.Bold,    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }


        }
    }
}

@Composable
fun RequisitionListBodyItem(trackingId: String, status: String) {
    Column {
        Row(
            modifier = Modifier
                .border(1.dp, Color.LightGray)
                .fillMaxWidth()
                .height(52.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .border(1.dp, Color.LightGray)
                    .fillMaxSize()
                    .padding(8.dp)
                    .weight(0.5f), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = trackingId,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            Column(
                modifier = Modifier
                    .border(1.dp, Color.LightGray)
                    .fillMaxSize()
                    .padding(8.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .weight(0.5f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = status,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }


        }
    }
}

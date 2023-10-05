package com.jsw.r2c.presentation.screens.dashboard.role.transport

import android.R
import android.annotation.SuppressLint
import android.view.View.OnClickListener
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.jsw.r2c.presentation.screens.dashboard.role.RequisitionRequest
import com.jsw.r2c.presentation.screens.dashboard.role.RequisitionText
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa
import com.jsw.r2c.presentation.viewmodels.features.auth.AuthViewModel
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TransportPersonDashboardScreen(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {

    var isGetPassSubmitted by remember {
        mutableStateOf(false)
    }
    if(isGetPassSubmitted) {
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

                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = "GATE PASS APPROVED",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontFamily = Kefa,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            LaunchedEffect(key1 = Unit) {
                delay(2000)
                isGetPassSubmitted = false
            }

        }
    }
    else{
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier.padding(
                    16.dp
                )
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

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, BlueDark, RoundedCornerShape(8))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val requisitionRequest = remember {
                        mutableListOf<RequisitionRequest>()
                    }
                    var selectedRequisitionIndex by remember {
                        mutableStateOf(0)
                    }
                    Text(
                        text = "Tracking Id:1000",
                        color = BlueDark,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(8.dp))


                    val barcodeEncoder = BarcodeEncoder()
                    val bitmap = barcodeEncoder.encodeBitmap("content", BarcodeFormat.QR_CODE, 500, 500)
                    Image(bitmap = bitmap.asImageBitmap(), contentDescription = "",
                        modifier = Modifier.clickable {
//                            isGetPassSubmitted = true
                        } )


                }
                Spacer(modifier = Modifier.padding(4.dp))
                Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = "Gate Pass", fontSize = 20.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, BlueDark, RoundedCornerShape(8))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ){
                    RequisitionText(
                        title = "Pickup Location: ",
                        value = "StorageLocation1"
                    )

                    RequisitionText(
                        title = "Drop Location: ",
                        value = "Production Point"
                    )
                    RequisitionText(
                        title = "Quantity: ",
                        value = "400 Kg"
                    )
                    RequisitionText(
                        title = "Material Group: ",
                        value = "Battery"
                    )
                }


            }


        }

    }
}

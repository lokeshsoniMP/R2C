package com.jsw.r2c.presentation.screens.dashboard.role.transport

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.jsw.r2c.presentation.screens.dashboard.role.RequisitionRequest
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TransportPersonDashboardScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.padding(
                16.dp
            )
        ) {
            Text(
                text = "Welcome\n Driver  Name",
                fontSize = 24.sp,
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
                Image(bitmap = bitmap.asImageBitmap(), contentDescription = "")

                Text(text = "Gate Pass", fontSize = 24.sp)
            }
        }


    }

}

package com.jsw.r2c.presentation.screens.dashboard.role.gatekeeper

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.jsw.r2c.R
import com.jsw.r2c.presentation.screens.dashboard.role.RequisitionRequest
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GateKeeperDashboardScreen(navController: NavController) {
// Register the launcher and result handler
    // Register the launcher and result handler

    val context = LocalContext.current
    val isGatePassApproved = remember {
        mutableStateOf(false)
    }
    val isGatePassCancelled = remember {
        mutableStateOf(true)
    }


    val barcodeLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        if (result.contents == null) {
            isGatePassApproved.value = true
            isGatePassCancelled.value = true
        } else {
            isGatePassApproved.value = true
            isGatePassCancelled.value = false
            /*Toast.makeText(context, "Scanned :${result.contents}", Toast.LENGTH_LONG).show()
     */
        }
    }

    if (!isGatePassApproved.value) {
        Box(modifier = Modifier.fillMaxSize()) {
            val options = ScanOptions()
            options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            options.setPrompt("Scan a QR Code")
            options.setCameraId(0) // Use a specific camera of the device
            options.setBeepEnabled(false)
            options.setBarcodeImageEnabled(true)
            Column(
                modifier = Modifier.padding(
                    16.dp
                )
            ) {
                Text(
                    text = "Welcome\n GateKeeper  Name",
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
                    Image(
                        painter = painterResource(id = R.drawable.scan_qr),
                        contentDescription = "scan_qr",
                        modifier = Modifier
                            .size(240.dp)
                            .clickable {
                                /*           val options = ScanOptions()
                                           options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
                                           options.setPrompt("Scan a barcode")
                                           options.setCameraId(0) // Use a specific camera of the device
                                           options.setBeepEnabled(false)
                                           options.setBarcodeImageEnabled(true)
                                           options.setOrientationLocked(false);*/
                                barcodeLauncher.launch(ScanOptions())
                            }
                    )

                    Text(text = "Gate Pass", fontSize = 24.sp)
                }
            }


        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier.padding(
                    16.dp
                )
            ) {
                Text(
                    text = "Scan to GO",
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontFamily = Kefa,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(8.dp))

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
                            .size(240.dp)
                            .background(
                                if (!isGatePassCancelled.value) Color.Green else Color.Red,
                                shape = CircleShape
                            )
                    ) {
                        Image(
                            imageVector = if (!isGatePassCancelled.value) Icons.Filled.Check else Icons.Filled.Close,
                            contentDescription = "Check",
                            modifier = Modifier.size(72.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }
            }


        }
        LaunchedEffect(key1 = Unit) {
            delay(3000)
            isGatePassApproved.value = false
        }
    }

    //  bitmap.value = it


}


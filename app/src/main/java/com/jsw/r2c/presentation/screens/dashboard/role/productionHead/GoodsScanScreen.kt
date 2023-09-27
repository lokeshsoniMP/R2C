package com.jsw.r2c.presentation.screens.dashboard.role.productionHead

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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
import com.jsw.r2c.retrofit.request.requisition.CreateGINRequest
import com.jsw.r2c.retrofit.utlis.ApiState
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GoodsScanScreen(navController: NavController) {
// Register the launcher and result handler
    // Register the launcher and result handler

    val context = LocalContext.current
    val isScanDone = rememberSaveable {
        mutableStateOf(false)
    }
    var isRequisitionFormSubmitted by remember {
        mutableStateOf(false)
    }
    //  bitmap.value = it
    val barcodeLauncher = rememberLauncherForActivityResult(ScanContract()) { result ->
        isScanDone.value = true
    }


    if (isScanDone.value) {
        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(
                modifier = Modifier
                    .padding(
                        16.dp
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(R.string.goods_receipt_note),
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontFamily = Kefa,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Tracking ID: ",
                            color = BlueDark,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "223344 ",
                            color = BlueDark,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Material ID: ",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "12333355353",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Short Text: ",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Raw Material",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Material Group: ",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Battery",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Quantity: ",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "400 kg",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Delivery Date: ",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "24 November 2023",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Plant Location: ",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Dolvi",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Storage Location: ",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Store 10",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }

                Spacer(modifier = Modifier.padding(12.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        isScanDone.value = false
                        isRequisitionFormSubmitted = true
//                    requisitionViewModel.createGINRequisitionViewModel(requisitionId = requisitionList[requisitionRequestIndex].id,
//                        CreateGINRequest(quantity= quantity.value, unitsId = unityType.value, documentDate = documentDate.value,
//                            ginStorageLocationId = storageLocationId.value)
//                    )
//                    isRequisitionFormSubmitted = true
//                    requisitionViewModel.createRequisitionResponse.value = ApiState.Empty
                    },
                    colors = ButtonDefaults.buttonColors(
                        disabledContainerColor = BlueDark,
                        containerColor = BlueDark
                    ), shape = RoundedCornerShape(12)

                ) {
                    Text(text = stringResource(R.string.confirm_entry), color = Color.White)
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
                Text(
                    text = "Scan Goods Issue Notes",
                    fontSize = 20.sp,
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

                    Spacer(modifier = Modifier.padding(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.scan_qr),
                        contentDescription = "scan_qr",
                        modifier = Modifier
                            .size(240.dp)
                            .clickable {
                                barcodeLauncher.launch(ScanOptions())
                            }
                    )


                }
                Text(text = "Scan the QR for GIN confirmation", fontSize = 15.sp)
            }
        }
    }
    if (isRequisitionFormSubmitted) {
        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {


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
                        text = "Requisition Completed\n" +
                                "Successfully",
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
                delay(3000)
                isRequisitionFormSubmitted = false
            }

        }
    }

}

@Preview
@Composable
fun GoodsReceiptNoteScreen() {


}


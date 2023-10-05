package com.jsw.r2c.presentation.screens.dashboard.role.gatekeeper

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jsw.r2c.presentation.screens.dashboard.navigation.DashBoardNavigationRoute
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GatePassTTGoodsDetailsScreen(navController: NavController) {
// Register the launcher and result handler
    // Register the launcher and result handler

    val context = LocalContext.current
    val isGatePassApproved = remember {
        mutableStateOf(false)
    }
    val isGatePassCancelled = remember {
        mutableStateOf(true)
    }
    val isActionPerformed = remember {
        mutableStateOf(false)
    }
    //  bitmap.value = it

    if (!isActionPerformed.value) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(
                    16.dp
                )
            ) {
                Text(
                    text = "T/T Goods Details",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontFamily = Kefa,
                    textAlign = TextAlign.Start,
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
                    horizontalAlignment = Alignment.Start
                ) {
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
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            shape = RoundedCornerShape(8),
                            onClick = {
                                isActionPerformed.value = true
                                isGatePassApproved.value = false
                                isGatePassCancelled.value = true
                            }, colors = ButtonDefaults.buttonColors(
                                disabledContainerColor = Color.Red,
                                containerColor = Color.Red
                            )
                        ) {
                            Text(text = "Stop", fontSize = 16.sp)
                        }
                        Button(
                            shape = RoundedCornerShape(8),
                            onClick = {
                                isActionPerformed.value = true
                                isGatePassApproved.value = true
                                isGatePassCancelled.value = false
                            }, colors = ButtonDefaults.buttonColors(
                                disabledContainerColor = Color.Green,
                                containerColor = Color.Green

                            )
                        ) {
                            Text(text = "Pass", fontSize = 16.sp)
                        }

                    }
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
                            modifier = Modifier.size(45.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                if(isGatePassApproved.value) {
                    Text(
                        text = "Gate Pass Approved",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontFamily = Kefa,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }else{
                    Text(
                        text = "Stopped / Reported",
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

    }
    LaunchedEffect(key1 = Unit) {
        if (isActionPerformed.value) {
            delay(3000)
            isGatePassApproved.value = false
            isGatePassCancelled.value = false
            isActionPerformed.value = false
            navController.navigate(DashBoardNavigationRoute.Home.route)
        }
    }


}


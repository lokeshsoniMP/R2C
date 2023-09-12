package com.jsw.r2c.presentation.screens.tracking

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jsw.r2c.presentation.screens.dashboard.role.RequisitionRequest
import com.jsw.r2c.presentation.screens.dashboard.role.RequisitionText
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa
import com.jsw.r2c.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrackingRequisitionScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {
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
                1
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    16.dp
                )
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Request Tracking",
                fontSize = 24.sp,
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
                        text = "Track ID: \n 232323",
                        color = BlueDark,
                        fontWeight = FontWeight.Bold, textAlign = TextAlign.Start
                    )
                    Row {
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        Text(text = "1-5")
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        Button(
                            shape = RoundedCornerShape(0),
                            onClick = {
                                --selectedRequisitionIndex
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
                            onClick = { ++selectedRequisitionIndex },
                            colors = ButtonDefaults.buttonColors(
                                disabledContainerColor = Color.Green,
                                containerColor = Color(0xFFD2D2D2)

                            )
                        ) {
                            Text(text = ">", fontSize = 19.sp)
                        }


                    }
                }

                Spacer(modifier = Modifier.padding(16.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()) {
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
                        Divider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 21.dp)
                                .width(width = 4.dp),
                            color =  Color(
                                0xFFE1E1E1
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Column() {
                        Text(
                            text = "Requisition Request Generated",
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
                Row(modifier = Modifier
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
                        Divider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 21.dp)
                                .width(width = 4.dp),
                            color =  Color(
                                0xFFE1E1E1
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Column() {
                        Text(
                            text = "Approved by Manager",
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

                Row(modifier = Modifier
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
                        Divider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 21.dp)
                                .width(width = 4.dp),
                            color =  Color(
                                0xFFE1E1E1
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Column() {
                        Text(
                            text = "Approved by Production Head",
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


                Row(modifier = Modifier
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
                        Divider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 21.dp)
                                .width(width = 4.dp),
                            color =  Color(
                                0xFFE1E1E1
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Column() {
                        Text(
                            text = "Supervisor Assigned",
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


                Row(modifier = Modifier
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
                        Divider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 21.dp)
                                .width(width = 4.dp),
                            color =  Color(
                                0xFFE1E1E1
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Column() {
                        Text(
                            text = "T/T Reached Store",
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

                Row(modifier = Modifier
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
                        Divider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 21.dp)
                                .width(width = 4.dp),
                            color =  Color(
                                0xFFE1E1E1
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Column() {
                        Text(
                            text = "Goods Collected by T/T",
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

                Row(modifier = Modifier
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
                        Divider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 21.dp)
                                .width(width = 4.dp),
                            color =  Color(
                                0xFFE1E1E1
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Column() {
                        Text(
                            text = "Gatekeeper Scan Complete",
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


                Row(modifier = Modifier
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
                        Divider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 21.dp)
                                .width(width = 4.dp),
                            color =  Color(
                                0xFFE1E1E1
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Column() {
                        Text(
                            text = "Reached Point A",
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

                Row(modifier = Modifier
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
                        Divider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 21.dp)
                                .width(width = 4.dp),
                            color =  Color(
                                0xFFE1E1E1
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Column() {
                        Text(
                            text = "Reached Point B",
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
                Row(modifier = Modifier
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
                        Divider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 21.dp)
                                .width(width = 4.dp),
                            color =  Color(
                                0xFFE1E1E1
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Column() {
                        Text(
                            text = "Goods Unloaded",
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
                Row(modifier = Modifier
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
                        Divider(
                            modifier = Modifier
                                .height(24.dp)
                                .padding(start = 21.dp)
                                .width(width = 4.dp),
                            color =  Color(
                                0xFFE1E1E1
                            )
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Column() {
                        Text(
                            text = "Requisition Complete",
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

package com.jsw.r2c.presentation.screens.dashboard.role

import android.annotation.SuppressLint
import android.provider.CalendarContract
import android.provider.CalendarContract.Colors
import android.widget.Toast
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.sp
import com.jsw.r2c.presentation.customviews.TopAppBarR2C
import com.jsw.r2c.presentation.screens.dashboard.navigation.DashBoardNavigationRoute

import com.jsw.r2c.presentation.screens.dashboard.navigation.NavigationItem
import kotlinx.coroutines.launch
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jsw.r2c.R
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PackagingSuperVisorDashBoardScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.padding(
                16.dp
            )
        ) {
            Text(
                text = "Welcome\n Packaging Supervisor  Name",
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
                    .padding(16.dp)
                  ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {

                val requisitionRequest = remember {
                    mutableListOf<RequisitionRequest>()
                }
                var selectedRequisitionIndex by remember {
                    mutableStateOf(0)
                }

                Spacer(modifier = Modifier.padding(8.dp))
                Row() {
                    Text(
                        text = "Requisition Requests:",
                        color = BlueDark,
                        fontWeight = FontWeight.Bold
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
                RequisitionText(
                    title = "Material Id :",
                    value = requisitionRequest.get(selectedRequisitionIndex).materialId
                )
                RequisitionText(
                    title = "Short  Text :",
                    value = requisitionRequest.get(selectedRequisitionIndex).shortText
                )
                RequisitionText(
                    title = "Material Group :",
                    value = requisitionRequest.get(selectedRequisitionIndex).materialGroup
                )
                RequisitionText(
                    title = "Quantity :",
                    value = requisitionRequest.get(selectedRequisitionIndex).quantity
                )
                RequisitionText(
                    title = "Delivery Date :",
                    value = requisitionRequest.get(selectedRequisitionIndex).deliveryDate
                )
                RequisitionText(
                    title = "Plant Location  :",
                    value = requisitionRequest.get(selectedRequisitionIndex).plantLocation
                )
                RequisitionText(
                    title = "Store Location   :",
                    value = requisitionRequest.get(selectedRequisitionIndex).storageLocation
                )
                RequisitionText(
                    title = "Status : ",
                    value = if (requisitionRequest.get(selectedRequisitionIndex).status.toInt() == 0) {
                        "Approval Pending"
                    } else {
                        "Approved"
                    },
                    valueColor =  if (requisitionRequest.get(selectedRequisitionIndex).status.toInt() == 0) {
                        Color(0xFF851D1D)
                    } else {
                        Color(0xFF38851D)
                    }
                )

                Row(modifier = Modifier.fillMaxWidth()) {

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        onClick = {

                        },
                        colors = ButtonDefaults.buttonColors(
                            disabledContainerColor = Color.Green,
                            containerColor = BlueDark
                        ), shape = RoundedCornerShape(12)

                    ) {
                        Text(text = "Assign Supervisor", color = Color.White)
                    }
                }

            }
        }


    }

}

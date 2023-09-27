package com.jsw.r2c.presentation.screens.dashboard.role.managerApproval

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jsw.r2c.R
import com.jsw.r2c.presentation.screens.dashboard.navigation.DashBoardNavigationRoute
import com.jsw.r2c.presentation.theme.Kefa
import com.jsw.r2c.presentation.viewmodels.features.auth.AuthViewModel

@Composable
fun ManagerNotificationScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {

            Row(
                modifier = Modifier.padding(
                    24.dp
                )
            ) {
                Text(
                    text = "Welcome,\n${authViewModel.getUser().name}",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = Kefa,
                    fontWeight = FontWeight.Bold
                )

            }
            Row {
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items(5) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(DashBoardNavigationRoute.ManagerDashaBoardScreen.route)
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround,

                            ) {
                            Column(
                                modifier = Modifier.padding(8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.requestion_1),
                                    contentDescription = "Requisition",
                                    modifier = Modifier
                                        .size(46.dp)
                                        .padding(12.dp)
                                )
                            }
                            Column() {
                                Text(
                                    text = "Tracking ID: 10$index",
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
                                        text = "${17 + index}:50",
                                        fontSize = 16.sp,
                                        color = Color.DarkGray,
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier.padding(4.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.End
                            ) {
                                Image(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                                    contentDescription = "Requisition",
                                    modifier = Modifier
                                        .size(46.dp)
                                        .padding(12.dp)
                                )
                            }

                        }
                        HorizontalDivider(
                            modifier = Modifier
                                .height(4.dp)
                                .padding(start = 21.dp)
                                .fillMaxWidth(),
                            color = Color(
                                0x99E1E1E1
                            )
                        )

                    }

                }
            }

        }
    }

}

@Preview
@Composable
fun NotificationList() {

//    LazyColumn(
//        contentPadding = PaddingValues(8.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        items(5) { index ->
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
////                        navController.navigate(DashBoardNavigationRoute.ManagerDashaBoardScreen.route)
//                    },
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceAround,
//
//                ) {
//                Column(modifier = Modifier.padding(8.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.requestion_1),
//                        contentDescription = "Requisition",
//                        modifier = Modifier
//                            .size(46.dp)
//                            .padding(12.dp)
//                    )
//                }
//                Column() {
//                    Text(
//                        text = "Tracking ID: 10$index",
//                        fontSize = 18.sp,
//                        color = Color.DarkGray, fontWeight = FontWeight.Bold
//                    )
//                    Row {
//                        Text(
//                            text = "28-APR-23",
//                            fontSize = 16.sp,
//                            color = Color.DarkGray
//                        )
//                        Spacer(modifier = Modifier.padding(16.dp))
//                        Text(
//                            text = "${17+index}:50",
//                            fontSize = 16.sp,
//                            color = Color.DarkGray,
//                        )
//                    }
//                }
//                Column(modifier = Modifier.padding(4.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.End
//                ) {
//                    Image(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowRight,
//                        contentDescription = "Requisition",
//                        modifier = Modifier
//                            .size(46.dp)
//                            .padding(12.dp)
//                    )
//                }
//
//            }
//            HorizontalDivider(
//                modifier = Modifier
//                    .height(4.dp)
//                    .padding(start = 21.dp)
//                    .fillMaxWidth(),
//                color = Color(
//                    0xFFE1E1E1
//                )
//            )
//
//        }
//
//    }

}
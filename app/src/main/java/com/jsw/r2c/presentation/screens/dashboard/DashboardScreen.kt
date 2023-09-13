package com.jsw.r2c.presentation.screens.dashboard

import android.annotation.SuppressLint
import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jsw.r2c.R
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa
import com.jsw.r2c.presentation.viewmodels.features.auth.AuthViewModel
import com.jsw.r2c.room.dao.AuthDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashBoardScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {


    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.padding(
                16.dp
            )
        ) {
            Text(
                text = "Welcome",
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
                    .clickable {
                        navController.navigate(DashBoardNavigationRoute.CreateRequisition.route)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(12.dp))
                Image(
                    painter = painterResource(id = R.drawable.requisition),
                    contentDescription = "requisition",
                    modifier = Modifier.size(42.dp)
                )
                Spacer(modifier = Modifier.padding(12.dp))
                Text(text = "Click to Generate New Requisition Request")
            }
            Spacer(modifier = Modifier.padding(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .border(2.dp, BlueDark, RoundedCornerShape(8))
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(DashBoardNavigationRoute.CreateRequisition.route)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(12.dp))
                Image(
                    painter = painterResource(id = R.drawable.scan_qr),
                    contentDescription = "requisition",
                    modifier = Modifier.size(42.dp)
                        .clickable {
                            navController.navigate(DashBoardNavigationRoute.GoodsScanScreen.route)
                        },
                )
                Spacer(modifier = Modifier.padding(12.dp))
                Text(text = "Scan Goods Issue Note")
            }
        }


    }

}


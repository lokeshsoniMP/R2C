package com.jsw.r2c.presentation.activities.gatekeeper

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

import com.jsw.r2c.presentation.customviews.TopAppBarR2C
import com.jsw.r2c.presentation.screens.dashboard.DashBoardMainScreen
import com.jsw.r2c.presentation.screens.dashboard.DashBoardScreen
import com.jsw.r2c.presentation.screens.dashboard.navigation.DashBoardNavigationRoute

import com.jsw.r2c.presentation.screens.dashboard.navigation.NavigationItem
import com.jsw.r2c.presentation.screens.dashboard.role.managerApproval.ManagerMainDashBoardScreen
import com.jsw.r2c.presentation.screens.dashboard.role.productionHead.DashBoardProductionMainScreen
import com.jsw.r2c.presentation.screens.dashboard.role.storeIncharge.StoreInChargeMainDashBoardScreen

import com.jsw.r2c.presentation.theme.R2CTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardGateKeeperActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            R2CTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StoreInChargeMainDashBoardScreen()

                 }
            }
        }
    }
}

package com.jsw.r2c.presentation.screens.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import com.jsw.r2c.presentation.customviews.TopAppBarR2C
import com.jsw.r2c.presentation.screens.dashboard.navigation.DashBoardNavigationRoute
import com.jsw.r2c.presentation.screens.dashboard.navigation.NavigationItem
import kotlinx.coroutines.launch
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jsw.r2c.presentation.activities.productionhead.DashboardProductionHeadActivity
import com.jsw.r2c.presentation.screens.dashboard.role.ManagerDashBoardScreen
import com.jsw.r2c.presentation.screens.dashboard.role.PackagingSuperVisorDashBoardScreen
import com.jsw.r2c.presentation.screens.dashboard.role.ProductionHeadDashBoardScreen
import com.jsw.r2c.presentation.screens.dashboard.role.productionHead.RequisitionScreen
import com.jsw.r2c.presentation.screens.dashboard.role.gatekeeper.GateKeeperDashboardScreen
import com.jsw.r2c.presentation.screens.dashboard.role.storeIncharge.StoreInchargeDashBoardScreen
import com.jsw.r2c.presentation.screens.dashboard.role.transport.TransportPersonDashboardScreen
import com.jsw.r2c.presentation.screens.tracking.TrackingRequisitionScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashBoardMainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val systemUiController: SystemUiController = rememberSystemUiController()

    val scope = rememberCoroutineScope()
    val items = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = DashBoardNavigationRoute.Home.route
        ),
        NavigationItem(
            title = "Requisition",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = DashBoardNavigationRoute.CreateRequisition.route
        )
        ,
        NavigationItem(
            title = "Manager Role",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = DashBoardNavigationRoute.ManagerDashaBoardScreen.route
        ),
        NavigationItem(
            title = "Production Head Role",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = DashBoardNavigationRoute.ProductionHeadDashBoardScreen.route
        ),
        NavigationItem(
            title = "Store Incharge Role",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = DashBoardNavigationRoute.StoreInChargeDashBoardScreen.route
        ),
        NavigationItem(
            title = " Packaging Supervisor Role",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = DashBoardNavigationRoute.PackagingSuperVisorDashBoardScreen.route
        )
        ,
        NavigationItem(
            title = " Tracking Request",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = DashBoardNavigationRoute.TrackingRequisitionScreen.route
        )
        ,
        NavigationItem(
            title = "Transport",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = DashBoardNavigationRoute.TransportPersonScreen.route
        )
        ,
        NavigationItem(
            title = "GateKeeper",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = DashBoardNavigationRoute.GateKeeperDashboardScreen.route
        )
    )
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                items.forEachIndexed { index, navigationItem ->
                    NavigationDrawerItem(
                        label = { Text(text = navigationItem.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(navigationItem.route)
                            navigationItem.badgeCount
                            scope.launch {
                                drawerState.close()

                            }

                        })
                }

            }

        }, drawerState = drawerState
    ) {

        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBarR2C(onClickHamBurger = {
                scope.launch {
                    drawerState.open()
                }
            })

            TopAppBarR2C(onClickIcon = {
                scope.launch {
                   /* val intent =
                        Intent(this, TrackingRequisitionScreen::class.java)
                    startActivity(intent)
                    finish()*/
                }
            })


        }) {
            systemUiController.isNavigationBarVisible = true
            systemUiController.setStatusBarColor(color = Color.White)
            systemUiController.statusBarDarkContentEnabled = true
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = DashBoardNavigationRoute.Home.route
                ) {
                    composable(DashBoardNavigationRoute.Home.route) {
                        DashBoardScreen(navController)
                    }
                    composable(DashBoardNavigationRoute.CreateRequisition.route) {
                        RequisitionScreen()
                    }
                    composable(DashBoardNavigationRoute.ManagerDashaBoardScreen.route) {
                        ManagerDashBoardScreen(navController)
                    }
                    composable(DashBoardNavigationRoute.PackagingSuperVisorDashBoardScreen.route) {
                        PackagingSuperVisorDashBoardScreen(navController)
                    }
                    composable(DashBoardNavigationRoute.ProductionHeadDashBoardScreen.route) {
                        ProductionHeadDashBoardScreen(navController)
                    }
                    composable(DashBoardNavigationRoute.StoreInChargeDashBoardScreen.route) {
                        StoreInchargeDashBoardScreen(navController)
                    }
                    composable(DashBoardNavigationRoute.TrackingRequisitionScreen.route) {
                        TrackingRequisitionScreen(navController)
                    }
                    composable(DashBoardNavigationRoute.GateKeeperDashboardScreen.route) {
                        GateKeeperDashboardScreen(navController)
                    }
                    composable(DashBoardNavigationRoute.TransportPersonScreen.route) {
                        TransportPersonDashboardScreen(navController)
                    }
                }
            }
        }
    }


}
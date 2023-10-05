package com.jsw.r2c.presentation.screens.dashboard.role.transport

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jsw.r2c.presentation.activities.MainActivity
import com.jsw.r2c.presentation.customviews.TopAppBarR2C
import com.jsw.r2c.presentation.screens.auth.LoginScreen
import com.jsw.r2c.presentation.screens.dashboard.navigation.DashBoardNavigationRoute
import com.jsw.r2c.presentation.screens.dashboard.navigation.NavigationItem
import com.jsw.r2c.presentation.screens.dashboard.role.productionHead.NotificationScreen
import com.jsw.r2c.presentation.screens.dashboard.role.productionHead.RequisitionDashboardProductionHead
import com.jsw.r2c.presentation.screens.dashboard.role.productionHead.RequisitionScreen
import com.jsw.r2c.presentation.screens.tracking.TrackingRequisitionScreen
import com.jsw.r2c.presentation.viewmodels.features.auth.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun TransPortMainDashBoardScreen(authViewModel: AuthViewModel = hiltViewModel()) {


    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current

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
            title = "Tracking",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = DashBoardNavigationRoute.TrackingRequisitionScreen.route
        ),
        NavigationItem(
            title = "Logout",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = DashBoardNavigationRoute.Logout.route
        )

    )
    var isNotificationClicked by remember {
        mutableStateOf(false)
    }
    if (isNotificationClicked) {
        navController.navigate(DashBoardNavigationRoute.NotificationScreen.route)
    } else {
        navController.popBackStack()
    }
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
            }, onClickNotification = {
                isNotificationClicked = !isNotificationClicked
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
                        TransportPersonDashboardScreen(navController)
                    }
                    composable(DashBoardNavigationRoute.TrackingRequisitionScreen.route) {
                        TrackingRequisitionScreen(navController)
                    }
                    composable(DashBoardNavigationRoute.NotificationScreen.route) {
                        NotificationScreen(navController)
                    }
                    composable(DashBoardNavigationRoute.Logout.route) {

                        LaunchedEffect(key1 = Unit) {
                            authViewModel.removeUserAppLoginPref()
                            val intent = Intent(context, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            context.startActivity(intent)
                            (context as Activity).finish()
                        }

                    }


                }
            }
        }
    }


}
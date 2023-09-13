package com.jsw.r2c.presentation.screens.dashboard.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jsw.r2c.presentation.screens.dashboard.DashBoardScreen


sealed class DashBoardNavigationRoute(val route: String) {
    object Home : DashBoardNavigationRoute("home_screen")
    object Logout : DashBoardNavigationRoute("Logout_screen")
    object ManagerDashaBoardScreen : DashBoardNavigationRoute("manager_dashboard_screen")
    object CreateRequisition : DashBoardNavigationRoute("requisition_screen")
    object ProductionHeadDashBoardScreen : DashBoardNavigationRoute("production_head_dashboard_screen")
    object PackagingSuperVisorDashBoardScreen : DashBoardNavigationRoute("packaging_supervisor_dashboard_screen")
    object StoreInChargeDashBoardScreen : DashBoardNavigationRoute("storeincharge_dashboard_screen")
    object TrackingRequisitionScreen: DashBoardNavigationRoute("tracking_requisition_dashboard_screen")
    object TransportPersonScreen: DashBoardNavigationRoute("transportPerson_dashboard_screen")
    object GatePassApprovalScreen: DashBoardNavigationRoute("gateKeeper_pass_screen")
    object RequisitionDashBoardScreen: DashBoardNavigationRoute("requisition_dashboard_screen")
    object GateKeeperDashboardScreen: DashBoardNavigationRoute("gateKeeper_dashboard_screen")
    object NotificationScreen: DashBoardNavigationRoute("notification_dashboard_screen")
    object TransportNotificationScreen: DashBoardNavigationRoute("transport_notification_screen")
    object GoodsScanScreen: DashBoardNavigationRoute("goods_scan_screen")
}
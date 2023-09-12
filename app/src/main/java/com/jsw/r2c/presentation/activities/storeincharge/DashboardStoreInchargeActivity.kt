package com.jsw.r2c.presentation.activities.storeincharge

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import com.jsw.r2c.presentation.screens.dashboard.role.storeIncharge.StoreInChargeMainDashBoardScreen

import com.jsw.r2c.presentation.theme.R2CTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardStoreInchargeActivity : ComponentActivity() {
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

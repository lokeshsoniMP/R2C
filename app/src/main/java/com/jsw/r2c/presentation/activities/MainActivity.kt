package com.jsw.r2c.presentation.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.jsw.r2c.base.UserType
import com.jsw.r2c.presentation.activities.gatekeeper.DashboardGateKeeperActivity
import com.jsw.r2c.presentation.activities.manager.DashboardManagerApprovalactivity
import com.jsw.r2c.presentation.activities.packagingsupervisor.DashboardPackagingSupervisorActivity
import com.jsw.r2c.presentation.activities.productionhead.DashboardProductionHeadActivity
import com.jsw.r2c.presentation.activities.productionsupervisor.DashboardProductionSupervisorActivity
import com.jsw.r2c.presentation.activities.storeincharge.DashboardStoreInchargeActivity
import com.jsw.r2c.presentation.activities.transportperson.DashboardTransportPersonActivity

import com.jsw.r2c.presentation.screens.auth.LoginScreen
import com.jsw.r2c.presentation.theme.R2CTheme
import com.jsw.r2c.presentation.viewmodels.features.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val auth: AuthViewModel by viewModels()


    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            R2CTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isLoggedIn = auth.authDataStore.getUserLogInState.collectAsState(false)
                    val context= LocalContext.current
                    if (isLoggedIn.value) {
                        when (auth.getUser().role) {
                            UserType.PRODUCTION_HEAD -> {
                                val intent =
                                    Intent(this, DashboardProductionHeadActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                            UserType.AS_MANAGER_HEAD -> {
                                val intent =
                                    Intent(this, DashboardManagerApprovalactivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                            UserType.AS_STOREINCHAREGE -> {
                                val intent =
                                    Intent(this, DashboardStoreInchargeActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                            UserType.AS_PACKAGINGSUPERVISOR -> {
                                val intent =
                                    Intent(this, DashboardPackagingSupervisorActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                            UserType.AS_TRASPORTPERSON -> {
                                val intent =
                                    Intent(this, DashboardTransportPersonActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                            UserType.AS_GATEKEEPER -> {
                                val intent =
                                    Intent(this, DashboardGateKeeperActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                            UserType.AS_PRODUCTIONSUPERVISOR -> {
                                val intent = Intent(this, DashboardProductionSupervisorActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                        }

                    } else {
                      LoginScreen()
                    }


                }
            }
        }
    }


    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed()
        finish()
    }


}




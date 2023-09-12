package com.jsw.r2c.presentation.screens.auth

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.auth0.android.jwt.JWT
import com.jsw.r2c.R
import com.jsw.r2c.base.UserType
import com.jsw.r2c.presentation.activities.gatekeeper.DashboardGateKeeperActivity
import com.jsw.r2c.presentation.activities.manager.DashboardManagerApprovalactivity
import com.jsw.r2c.presentation.activities.packagingsupervisor.DashboardPackagingSupervisorActivity
import com.jsw.r2c.presentation.activities.productionhead.DashboardProductionHeadActivity
import com.jsw.r2c.presentation.activities.productionsupervisor.DashboardProductionSupervisorActivity
import com.jsw.r2c.presentation.activities.storeincharge.DashboardStoreInchargeActivity
import com.jsw.r2c.presentation.activities.transportperson.DashboardTransportPersonActivity
import com.jsw.r2c.presentation.theme.BlueLight
import com.jsw.r2c.presentation.theme.Kefa
import com.jsw.r2c.presentation.theme.LoginBackground
import com.jsw.r2c.presentation.viewmodels.features.auth.AuthViewModel
import com.jsw.r2c.retrofit.request.auth.LoginRequest
import com.jsw.r2c.retrofit.response.auth.LoginResponse
import com.jsw.r2c.retrofit.utlis.ApiState
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(viewModel: AuthViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        val isFormValid by rememberSaveable { mutableStateOf(true) }
        val coroutine = rememberCoroutineScope()

        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LoginBackground)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(18.dp))
            Image(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
                    .padding(24.dp),
                painter = painterResource(id = R.drawable.login_mascot),
                contentDescription = "Login mascot",
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 72.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = Kefa
            )
            Text(
                text = stringResource(R.string.app_desc),
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                fontFamily = Kefa
            )
            Spacer(modifier = Modifier.padding(16.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text(text = stringResource(R.string.enter_your_email_address))
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                ),
                label = { Text(text = stringResource(R.string.enter_your_email_address)) },
                shape = RoundedCornerShape(16)
            )
            Spacer(modifier = Modifier.padding(16.dp))

            var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(text = stringResource(R.string.enter_your_pass))
                }, colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                ),
                label = { Text(text = stringResource(R.string.enter_your_pass)) },
                shape = RoundedCornerShape(16),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "password-toggle", tint = Color.DarkGray
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                var isRememberPassword by rememberSaveable {
                    mutableStateOf(false)
                }
                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Checkbox(
                        checked = isRememberPassword,
                        onCheckedChange = {
                            isRememberPassword = it
                        },
                        colors = CheckboxDefaults.colors(BlueLight),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically),
                        text = stringResource(R.string.remember_me),
                        color = Color.White,
                        textAlign = TextAlign.Center, fontSize = 16.sp,

                        )
                }
                Text(
                    text = stringResource(R.string.forgot_password),
                    color = Color.White,
                    textAlign = TextAlign.Center, fontSize = 16.sp
                )

            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    coroutine.launch {
                        viewModel.login(LoginRequest(userName = email, password = password))

                    }
                },
                enabled = isFormValid,
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = BlueLight,
                    containerColor = BlueLight
                ), shape = RoundedCornerShape(16)

            ) {
                Text(text = stringResource(R.string.login), color = Color.White)
                when (viewModel.loginResponse.value) {
                    ApiState.Empty -> {

                    }

                    ApiState.Loading -> {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                    is ApiState.Success -> {
                        val response =
                            (viewModel.loginResponse.value as ApiState.Success<LoginResponse>).data
                        viewModel.saveUser(response.token)
                        val role = JWT(response.token).getClaim("role").asString()
                        when (role) {
                            UserType.PRODUCTION_HEAD -> {
                                val intent =
                                    Intent(context, DashboardProductionHeadActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                            UserType.AS_MANAGER_HEAD -> {
                                val intent =
                                    Intent(context, DashboardManagerApprovalactivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                            UserType.AS_STOREINCHAREGE -> {
                                val intent =
                                    Intent(context, DashboardStoreInchargeActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                            UserType.AS_PACKAGINGSUPERVISOR -> {
                                val intent =
                                    Intent(context, DashboardPackagingSupervisorActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                            UserType.AS_TRASPORTPERSON -> {
                                val intent =
                                    Intent(context, DashboardTransportPersonActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                            UserType.AS_GATEKEEPER -> {
                                val intent =
                                    Intent(context, DashboardGateKeeperActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }

                            UserType.AS_PRODUCTIONSUPERVISOR -> {
                                val intent =
                                    Intent(context, DashboardProductionSupervisorActivity::class.java)
                                context.startActivity(intent)
                                (context as Activity).finish()
                            }


                        }


                    }

                    is ApiState.Failure -> {
                        val response =
                            (viewModel.loginResponse.value as ApiState.Failure).msg

                    }

                    else -> {

                    }

                }

            }


        }
    }
}
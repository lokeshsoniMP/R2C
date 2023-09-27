package com.jsw.r2c.presentation.screens.dashboard.role.storeIncharge

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jsw.r2c.R
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa
import com.jsw.r2c.presentation.viewmodels.features.auth.AuthViewModel
import com.jsw.r2c.presentation.viewmodels.features.requisition.RequisitionViewModel
import com.jsw.r2c.retrofit.request.requisition.AssignPackagingSupervisorRequest
import com.jsw.r2c.retrofit.response.material.MaterialResponse
import com.jsw.r2c.retrofit.response.material.MaterialResponseItem
import com.jsw.r2c.retrofit.response.plant.PlantResponse
import com.jsw.r2c.retrofit.response.plant.PlantResponseItem
import com.jsw.r2c.retrofit.response.requisition.AssignPackagingSupervisorResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionResponse
import com.jsw.r2c.retrofit.response.storage.StorageLocationResponse
import com.jsw.r2c.retrofit.response.storage.StorageLocationResponseItem
import com.jsw.r2c.retrofit.utlis.ApiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AssignSuperviser(navController: NavController, viewModel: RequisitionViewModel = hiltViewModel(), authViewModel: AuthViewModel = hiltViewModel()) {
    val materialId = rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val shortTextID = rememberSaveable {
        mutableStateOf("")
    }
    val materialGroupId = rememberSaveable {
        mutableStateOf("")
    }
    val quantity = rememberSaveable {
        mutableStateOf(0)
    }
    val unityType = rememberSaveable {
        mutableStateOf(1)
    }
    val deliveryDate = rememberSaveable {
        mutableStateOf("")
    }
    val plantLocationId = rememberSaveable {
        mutableStateOf(0)
    }
    val storageLocationId = rememberSaveable {
        mutableStateOf(0)
    }
    var storageLocationList = rememberSaveable {
        mutableListOf<StorageLocationResponseItem>()
    }
    val coroutine = rememberCoroutineScope()

    var isRequisitionFormSubmitted by remember {
        mutableStateOf(false)
    }


    val currentDate = remember { LocalDate.now() }
    val selectedDate by remember { mutableStateOf(currentDate) }

    // Declaring integer values
    // for year, month and day

    when (viewModel.assignPackagingSupervisorResponse.value) {
        ApiState.Loading -> {
        }

        is ApiState.Success -> {

            val response =
                (viewModel.assignPackagingSupervisorResponse.value as ApiState.Success<AssignPackagingSupervisorResponse>).data

            isRequisitionFormSubmitted = true
        }

        is ApiState.Failure -> {
            val response = (viewModel.assignPackagingSupervisorResponse.value as ApiState.Failure).msg

        }

        else -> {

        }

    }
    // Create a date picker dialog

    if (!isRequisitionFormSubmitted) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    text = "Assign Packaging Supervisor",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(8.dp)
                ) {

                    // Plant Location
                    PlantLocationDialogTextInputWithDropDown(
                        title = "Plant Location",
                        hint = "Select Plant Location",
                        onPlantLocationSelected = {
                            plantLocationId.value = it.id
                            viewModel.getStorageLocation(it.id)


                        }
                    )
                    when (viewModel.getStorageLocationResponse.value) {
                        ApiState.Loading -> {
                        }

                        is ApiState.Success -> {

                            val response =
                                (viewModel.getStorageLocationResponse.value as ApiState.Success<StorageLocationResponse>).data

                            storageLocationList.clear()
                            storageLocationList.addAll(response)

                        }

                        is ApiState.Failure -> {
                            val response = (viewModel.getStorageLocationResponse.value as ApiState.Failure).msg

                        }

                        else -> {

                        }

                    }

                    StorageLocationDialogTextInputWithDropDown(title = "Storage Location",
                        hint = "Select Storage Location", storageLocationList = storageLocationList,
                        onStorageLocationSelected = {
                            storageLocationId.value = it.id


                        }
                    )
                    // material Id
                    PackagingSupervisorDialogTextInputWithDropDown(
                        title = "PackagingSupervisor:",
                        hint = "Search /PackagingSupervisor",
                        onMaterialSelected = {
                            coroutine.launch {
                                materialId.value = it.id.toString()
                            }
                        }
                    )

                    // short text
                    TextField(
                        value = shortTextID.value,
                        onValueChange = {
                            shortTextID.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = {
                            Text(text = "Enter Short Text")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                        )
                    )



                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            coroutine.launch {
                                viewModel.assignPackageSupervisor(requisitionId = storageLocationId.value,
                                    AssignPackagingSupervisorRequest(
                                        plantId = plantLocationId.value,
                                        storageId = storageLocationId.value,
                                        packagingSupervisor = materialId.value,
                                        shortText =shortTextID.value
                                    )
                                )
                                isRequisitionFormSubmitted = true

                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            disabledContainerColor = BlueDark,
                            containerColor = BlueDark
                        ), shape = RoundedCornerShape(12)

                    ) {
                        Text(text = stringResource(R.string.Confirm_supervise), color = Color.White)
                    }

                }
                when (viewModel.createRequisitionResponse.value) {
                    ApiState.Loading -> {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                    is ApiState.Success -> {
                        val response =
                            (viewModel.createRequisitionResponse.value as ApiState.Success<RequisitionResponse>).data
                        isRequisitionFormSubmitted = true
                    }

                    is ApiState.Failure -> {
                        val response =
                            (viewModel.createRequisitionResponse.value as ApiState.Failure).msg
                        Log.e("Failure", response.toString())

                    }

                    else -> {

                    }

                }


            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {


            Column(
                modifier = Modifier.padding(
                    16.dp
                )
            ) {


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(92.dp)
                            .background(
                                Color.Green,
                                shape = CircleShape
                            )
                    ) {
                        Image(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Check",
                            modifier = Modifier.size(72.dp),
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }

                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text = "Supervisor Assigned\n" +
                                "Successfully",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontFamily = Kefa,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            LaunchedEffect(key1 = Unit) {
                delay(2000)
                isRequisitionFormSubmitted = false
            }

        }

    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantLocationDialogTextInputWithDropDown(
    title: String = "",
    hint: String = "",
    inputValue: String = "",
    onPlantLocationSelected: (PlantResponseItem) -> Unit,
    viewModel: RequisitionViewModel = hiltViewModel()

) {
    var mInputValue by rememberSaveable {
        mutableStateOf(hint)
    }
    var selectedItem by rememberSaveable {
        mutableStateOf(hint)
    }
    val context = LocalContext.current
    val plantLocationList = rememberSaveable {
        mutableListOf<PlantResponseItem>()
    }
    var PopControll by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getPlants()

    }
    when (viewModel.getPlantsResponse.value) {
        ApiState.Loading -> {

        }

        is ApiState.Success -> {
            val response =
                (viewModel.getPlantsResponse.value as ApiState.Success<PlantResponse>).data
            plantLocationList.clear()
            plantLocationList.addAll(response)

        }

        is ApiState.Failure -> {
            val response =
                (viewModel.getPlantsResponse.value as ApiState.Failure).msg


        }

        else -> {

        }

    }
    // material Id
    Text(
        text = title,
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.padding(4.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(8))
            .clickable {
                PopControll = !PopControll
                // load api
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = selectedItem, modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
                .padding(16.dp), color = Color.DarkGray
        )

        Image(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "Arrow down", modifier = Modifier.weight(0.2f)
        )
    }

    if (PopControll) {
        Popup(
            alignment = Alignment.Center,
            properties = PopupProperties(focusable = true)
        ) {

            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.5f))
                    .fillMaxSize()
                    .clickable {
                        PopControll = !PopControll
                    },
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .defaultMinSize(minHeight = 200.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(4)),
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(12)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = mInputValue,
                            onValueChange = {
                                mInputValue = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.8f),
                            label = {
                                Text(text = hint)
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                            )
                        )
                        Image(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Arrow down",
                            modifier = Modifier.weight(0.2f)
                        )
                    }
                    LazyColumn(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        )
                    ) {
                        items(plantLocationList.size) {
                            Text(
                                text = plantLocationList.get(it).plantName,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        selectedItem = plantLocationList.get(it).plantName
                                        onPlantLocationSelected.invoke(plantLocationList.get(it))
                                        PopControll = !PopControll
                                        Toast
                                            .makeText(
                                                context,
                                                plantLocationList.get(it).plantName + " Selected",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                            )
                        }
                    }

                }

            }


        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageLocationDialogTextInputWithDropDown(
    title: String = "",
    hint: String = "",
    storageLocationList: MutableList<StorageLocationResponseItem>,
    onStorageLocationSelected: (StorageLocationResponseItem) -> Unit

) {
    var mInputValue by rememberSaveable {
        mutableStateOf(hint)
    }
    var selectedItem by rememberSaveable {
        mutableStateOf(hint)
    }
    val context = LocalContext.current

    var PopControll by rememberSaveable {
        mutableStateOf(false)
    }


    // material Id
    Text(
        text = title,
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.padding(4.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(8))
            .clickable {
                PopControll = !PopControll
                // load api
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = selectedItem, modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
                .padding(16.dp), color = Color.DarkGray
        )

        Image(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "Arrow down", modifier = Modifier.weight(0.2f)
        )
    }

    if (PopControll) {
        Popup(
            alignment = Alignment.Center,
            properties = PopupProperties(focusable = true)
        ) {

            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.5f))
                    .fillMaxSize()
                    .clickable {
                        PopControll = !PopControll
                    },
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .defaultMinSize(minHeight = 200.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(4)),
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(12)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = mInputValue,
                            onValueChange = {
                                mInputValue = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.8f),
                            label = {
                                Text(text = hint)
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                            )
                        )
                        Image(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Arrow down",
                            modifier = Modifier.weight(0.2f)
                        )
                    }
                    LazyColumn(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        )
                    ) {
                        items(storageLocationList.size) {
                            Text(
                                text = storageLocationList.get(it).name,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        selectedItem = storageLocationList.get(it).name
                                        onStorageLocationSelected.invoke(storageLocationList.get(it))
                                        PopControll = !PopControll
                                        Toast
                                            .makeText(
                                                context,
                                                storageLocationList.get(it).name + " Selected",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                            )
                        }
                    }

                }

            }


        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackagingSupervisorDialogTextInputWithDropDown(
    title: String = "",
    hint: String = "",
    inputValue: String = "",
    onMaterialSelected: (MaterialResponseItem) -> Unit,
    viewModel: RequisitionViewModel = hiltViewModel()

) {
    var mInputValue by rememberSaveable {
        mutableStateOf(inputValue)
    }
    val context = LocalContext.current
    var materialList = rememberSaveable {
        mutableListOf<MaterialResponseItem>()
    }
    var selectedItem by rememberSaveable {
        mutableStateOf(hint)
    }
    var materialIdPopControll by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getMaterial()
    })

    when (viewModel.materialIdResponse.value) {
        ApiState.Loading -> {

        }

        is ApiState.Success -> {
            val response =
                (viewModel.materialIdResponse.value as ApiState.Success<MaterialResponse>).data
            materialList.clear()
            materialList = response.toMutableList()

        }

        is ApiState.Failure -> {
            val response =
                (viewModel.materialIdResponse.value as ApiState.Failure).msg
            Log.e("Failure", response.toString())

        }

        else -> {

        }

    }

    // material Id
    Text(
        text = title,
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.padding(4.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(8))
            .clickable {
                materialIdPopControll = !materialIdPopControll
                // load api
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = selectedItem, modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
                .padding(16.dp), color = Color.DarkGray
        )

        Image(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "Arrow down", modifier = Modifier.weight(0.2f)
        )
    }

    if (materialIdPopControll) {
        Popup(
            alignment = Alignment.Center,
            properties = PopupProperties(focusable = true)
        ) {

            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.5f))
                    .fillMaxSize()
                    .clickable {
                        materialIdPopControll = !materialIdPopControll
                    },
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .defaultMinSize(minHeight = 200.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(4)),
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                            .border(1.dp, Color.Gray, RoundedCornerShape(12)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextField(
                            value = mInputValue,
                            onValueChange = {
                                mInputValue = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.8f),
                            label = {
                                Text(text = hint)
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                            )
                        )
                        Image(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Arrow down",
                            modifier = Modifier.weight(0.2f)
                        )
                    }
                    LazyColumn(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        )
                    ) {
                        items(materialList.size) {
                            Text(
                                text = materialList.get(it).shortText,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        selectedItem = materialList.get(it).shortText
                                        onMaterialSelected.invoke(materialList.get(it))
                                        materialIdPopControll = !materialIdPopControll
                                        Toast
                                            .makeText(
                                                context,
                                                materialList.get(it).shortText + " Selected",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                            )
                        }
                    }

                }

            }


        }
    }


}





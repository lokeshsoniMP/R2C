package com.jsw.r2c.presentation.screens.dashboard.role.packagingsupervisor

import android.annotation.SuppressLint
import android.app.Activity
import android.text.Layout
import android.text.Layout.Directions
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.sp

import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jsw.r2c.R
import com.jsw.r2c.base.Utilis
import com.jsw.r2c.presentation.screens.dashboard.navigation.DashBoardNavigationRoute
import com.jsw.r2c.presentation.screens.dashboard.role.RequisitionText
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa
import com.jsw.r2c.presentation.viewmodels.features.auth.AuthViewModel
import com.jsw.r2c.presentation.viewmodels.features.requisition.RequisitionViewModel
import com.jsw.r2c.retrofit.request.requisition.CreateGINRequest
import com.jsw.r2c.retrofit.response.material.MaterialResponse
import com.jsw.r2c.retrofit.response.material.MaterialResponseItem
import com.jsw.r2c.retrofit.response.plant.PlantResponse
import com.jsw.r2c.retrofit.response.plant.PlantResponseItem
import com.jsw.r2c.retrofit.response.requisition.RequisitionGINResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponse
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponseItem
import com.jsw.r2c.retrofit.response.storage.StorageLocationResponse
import com.jsw.r2c.retrofit.response.storage.StorageLocationResponseItem
import com.jsw.r2c.retrofit.response.unit.UnitTypeResponse
import com.jsw.r2c.retrofit.response.unit.UnitTypeResponseItem
import com.jsw.r2c.retrofit.utlis.ApiState
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PackageSupervisorGoodsConfirmGIN(
    navController: NavController,
    requisitionViewModel: RequisitionViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()
    var requisitionList = rememberSaveable {
        mutableListOf<RequisitionListResponseItem>()
    }
    var materialList = rememberSaveable {
        mutableListOf<MaterialResponseItem>()
    }
    var plantLocationList = rememberSaveable {
        mutableListOf<PlantResponseItem>()
    }
    var unityTypeList = rememberSaveable {
        mutableListOf<UnitTypeResponseItem>()
    }
    var requisitionRequestIndex by remember {
        mutableStateOf(0)
    }
    var storageLocationList = rememberSaveable {
        mutableListOf<StorageLocationResponseItem>()
    }
    val quantity = rememberSaveable {
        mutableStateOf(0)
    }
    val unityType = rememberSaveable {
        mutableStateOf(1)
    }
    var isRequisitionFormSubmitted by remember {
        mutableStateOf(false)
    }
    val documentDate = rememberSaveable {
        mutableStateOf("")
    }
    val storageLocationId = rememberSaveable {
        mutableStateOf(0)
    }
    BackHandler() {
        navController.popBackStack()
        (context as Activity).finish()

    }
    LaunchedEffect(key1 = Unit) {
        requisitionViewModel.getRequisitionList()
        requisitionViewModel.getMaterial()
        requisitionViewModel.getMaterialUnitType()
        requisitionViewModel.getPlants()


    }
    when (requisitionViewModel.getRequisitionListResponse.value) {
        ApiState.Loading -> {
        }

        is ApiState.Success -> {
            val response =
                (requisitionViewModel.getRequisitionListResponse.value as ApiState.Success<RequisitionListResponse>).data
            requisitionList.clear()
            requisitionList = response.toMutableList()

        }

        is ApiState.Failure -> {
            val response =
                (requisitionViewModel.getRequisitionListResponse.value as ApiState.Failure).msg


        }

        else -> {

        }

    }

    when (requisitionViewModel.materialIdResponse.value) {
        ApiState.Loading -> {

        }

        is ApiState.Success -> {
            val response =
                (requisitionViewModel.materialIdResponse.value as ApiState.Success<MaterialResponse>).data
            materialList.clear()
            materialList = response.toMutableList()

        }

        is ApiState.Failure -> {
            val response = (requisitionViewModel.materialIdResponse.value as ApiState.Failure).msg

        }

        else -> {

        }

    }

    when (requisitionViewModel.unitTypeResponse.value) {
        ApiState.Loading -> {

        }

        is ApiState.Success -> {
            val response =
                (requisitionViewModel.unitTypeResponse.value as ApiState.Success<UnitTypeResponse>).data
            unityTypeList.clear()
            unityTypeList.addAll(response)

        }

        is ApiState.Failure -> {
            val response = (requisitionViewModel.unitTypeResponse.value as ApiState.Failure).msg

        }

        else -> {

        }

    }
    when (requisitionViewModel.getPlantsResponse.value) {
        ApiState.Loading -> {

        }

        is ApiState.Success -> {
            val response =
                (requisitionViewModel.getPlantsResponse.value as ApiState.Success<PlantResponse>).data
            plantLocationList.clear()
            plantLocationList.addAll(response)


        }

        is ApiState.Failure -> {
            val response = (requisitionViewModel.getPlantsResponse.value as ApiState.Failure).msg


        }

        else -> {

        }

    }
    when (requisitionViewModel.getStorageLocationResponse.value) {
        ApiState.Loading -> {
        }

        is ApiState.Success -> {

            val response =
                (requisitionViewModel.getStorageLocationResponse.value as ApiState.Success<StorageLocationResponse>).data

            storageLocationList.clear()
            storageLocationList.addAll(response)

        }

        is ApiState.Failure -> {
            val response =
                (requisitionViewModel.getStorageLocationResponse.value as ApiState.Failure).msg

        }

        else -> {

        }

    }
    when (requisitionViewModel.createGINRequisitionResponse.value) {
        ApiState.Loading -> {
        }

        is ApiState.Success -> {

            val response =
                (requisitionViewModel.createGINRequisitionResponse.value as ApiState.Success<RequisitionGINResponse>).data

            isRequisitionFormSubmitted = true

        }

        is ApiState.Failure -> {
            val response =
                (requisitionViewModel.createGINRequisitionResponse.value as ApiState.Failure).msg

        }

        else -> {

        }

    }

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .padding(
                    16.dp
                )
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.goods_issue_note),
                fontSize = 20.sp,
                color = Color.Black,
                fontFamily = Kefa,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(8.dp))

            if (requisitionList.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, BlueDark, RoundedCornerShape(8))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {


                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Tracking ID: ",
                            color = BlueDark,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "223344 ",
                            color = BlueDark,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Material ID: ",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "12333355353",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Short Text: ",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Raw Material",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Material Group: ",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Battery",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Plant Location: ",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Dolvi",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }


                }
                Spacer(modifier = Modifier.padding(8.dp))

                RequisitionQuantityAndUnitTypeInputField(
                    onSelectedQuantity = {
                        quantity.value =
                            if (it.isNotEmpty()) it.toInt() else return@RequisitionQuantityAndUnitTypeInputField
                    },
                    onUnitTypeSelected = {
                        unityType.value = it.id
                    })
                Spacer(modifier = Modifier.padding(8.dp))
                DeliveryDate(
                    onDateSelected = {
                        documentDate.value = it
                    })
                Spacer(modifier = Modifier.padding(8.dp))
                if (plantLocationList.isNotEmpty() && storageLocationList.isEmpty()) {

                    requisitionViewModel.getStorageLocation(plantLocationList[0].id)
                }
                StorageLocationDialogTextInputWithDropDown(title = "Storage Location",
                    hint = "Select Storage Location",
                    storageLocationList = storageLocationList,
                    onStorageLocationSelected = {
                        storageLocationId.value = it.id

                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        requisitionViewModel.createGINRequisitionViewModel(
                            requisitionId = requisitionList[requisitionRequestIndex].id,
                            CreateGINRequest(
                                quantity = quantity.value,
                                unitsId = unityType.value,
                                documentDate = documentDate.value,
                                ginStorageLocationId = storageLocationId.value
                            )
                        )
                        isRequisitionFormSubmitted = true
                        requisitionViewModel.createRequisitionResponse.value = ApiState.Empty
                    },
                    colors = ButtonDefaults.buttonColors(
                        disabledContainerColor = BlueDark,
                        containerColor = BlueDark
                    ), shape = RoundedCornerShape(12)

                ) {
                    Text(text = stringResource(R.string.confirm_gin), color = Color.White)
                }
            }


        }


    }

    previewDesign(isRequisitionFormSubmitted = isRequisitionFormSubmitted)
    if(isRequisitionFormSubmitted) {
        LaunchedEffect(key1 = Unit) {
            delay(2000)
            navController.navigate(DashBoardNavigationRoute.Home.route)
        }

    }
}

//@Preview(showBackground = true)
@Composable
fun previewDesign(isRequisitionFormSubmitted: Boolean) {
    if (isRequisitionFormSubmitted) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White
                )
        ) {


            Spacer(modifier = Modifier.padding(16.dp))
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
                        text = "Goods Issue Note\n" +
                                "Created Successfully",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontFamily = Kefa,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }



        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequisitionQuantityAndUnitTypeInputField(
    onSelectedQuantity: (String) -> Unit,
    onUnitTypeSelected: (UnitTypeResponseItem) -> Unit,
    viewModel: RequisitionViewModel = hiltViewModel()
) {

    var quantityValue by rememberSaveable {
        mutableStateOf(
            ""
        )
    }
    val context = LocalContext.current
    var unityType by rememberSaveable {
        mutableStateOf(
            "Kg"
        )
    }
    val unityTypeList = rememberSaveable {
        mutableListOf<UnitTypeResponseItem>()
    }
    var PopControll by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getMaterialUnitType()

    }
    when (viewModel.unitTypeResponse.value) {
        ApiState.Loading -> {

        }

        is ApiState.Success -> {
            val response =
                (viewModel.unitTypeResponse.value as ApiState.Success<UnitTypeResponse>).data
            unityTypeList.clear()
            unityTypeList.addAll(response)

        }

        is ApiState.Failure -> {
            val response =
                (viewModel.unitTypeResponse.value as ApiState.Failure).msg
            Log.e("Failure", response.toString())

        }

        else -> {

        }

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

                    LazyColumn(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        )
                    ) {
                        items(unityTypeList.size) {
                            Text(
                                text = unityTypeList.get(it).name,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        unityType = unityTypeList.get(it).name
                                        onUnitTypeSelected.invoke(unityTypeList.get(it))
                                        PopControll = !PopControll
                                        Toast
                                            .makeText(
                                                context,
                                                unityTypeList.get(it).name + " Selected",
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Quantity
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Quantity",
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, RoundedCornerShape(8)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = quantityValue,
                        onValueChange = {
                            quantityValue = it
                            onSelectedQuantity.invoke(quantityValue)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier =
                        Modifier
                            .fillMaxWidth(),
                        label = {
                            Text(text = "Enter Quantity")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                        )
                    )
                }
            }

        }
        // Quantity
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Column() {
                Text(
                    text = "Unit",
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, RoundedCornerShape(8)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = unityType,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(16.dp)
                    )
                    Image(

                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Arrow down",
                        modifier = Modifier

                            .weight(0.3f)
                            .clickable {

                                PopControll = !PopControll
                            }
                    )
                }
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryDate(
    onDateSelected: (String) -> Unit,
) {
    val date = Date()
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.set(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DATE)
    )

    var selected by remember {
        mutableStateOf("Select Document Date")
    }
    val datePickerState =
        rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
    val showDialog = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Document Date:",
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(8))
                .clickable {
                    showDialog.value = !showDialog.value
                }, verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selected,
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
                    .padding(16.dp), color = Color.DarkGray
            )
            Image(
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = "Calender", modifier = Modifier.weight(0.2f)
            )
        }
        if (showDialog.value) {
            DatePickerDialog(
                onDismissRequest = { showDialog.value = false },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog.value = false
                        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)
                        selected = formatter.format(datePickerState.selectedDateMillis?.let {
                            Date(
                                it
                            )
                        }!!)

                        val formatter2 =
                            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", Locale.ROOT)
//                        val formatter2 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sssZ", Locale.ROOT)
                        onDateSelected(formatter2.format(datePickerState.selectedDateMillis?.let {
                            Date(
                                it
                            )
                        }!!))
                    }) {
                        Text("Ok")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog.value = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)

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
        fontWeight = FontWeight.SemiBold,
        color = Color.DarkGray
    )
    Spacer(modifier = Modifier.padding(4.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(12))
            .clickable {
                PopControll = !PopControll
                // load api
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = selectedItem, fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
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
                                        selectedItem = storageLocationList[it].name
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

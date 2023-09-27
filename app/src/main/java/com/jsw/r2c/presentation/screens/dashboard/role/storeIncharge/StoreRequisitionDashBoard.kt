package com.jsw.r2c.presentation.screens.dashboard.role.storeIncharge

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jsw.r2c.R
import com.jsw.r2c.presentation.viewmodels.features.requisition.RequisitionViewModel
import com.jsw.r2c.retrofit.response.requisition.RequisitionListResponseItem
import java.lang.Integer.min

@Composable
fun StoreRequisitionDashboard(requisitionViewModel: RequisitionViewModel = hiltViewModel()) {

    var requisitionList = rememberSaveable {
        mutableListOf<RequisitionListResponseItem>()
    }

    LaunchedEffect(key1 = Unit) {
        requisitionViewModel.getRequisitionList()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
//        Spacer(modifier = Modifier.padding(25.dp))

        val trackIDArray = mutableListOf<Long>(123456, 245566, 43434344, 4522333, 121233)
        val trackStatusArray = mutableListOf<String>(
            "Approval Pending",
            "Packing In Progress",
            "Requisition Complete",
            "T/T In Progress",
            "Goods Loading"
        )


        RequisitionListBodyItem(
            trackingId = trackIDArray,
            status = trackStatusArray
        )

        Spacer(modifier = Modifier.padding(25.dp))

//            LazyColumn() {
//                item {
//                    RequisitionListHeader()
//                }
//                items(trackIDArray.size) {
//                    RequisitionListBodyItem(
//                        trackingId = trackIDArray[it].toString(),
//                        status = trackStatusArray[it]
//                    )
//                }
//            }
    }

//    BarChartRequisition()
//    PieChartScreen()
//    showPieChart()
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Search/Select Tracking ID") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Cyan,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.LightGray,
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}
@Composable
fun RequisitionListHeader() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .background(color = Color(0xFFEFEFEF))
                .border(1.dp, Color.LightGray)
                .fillMaxWidth()
                .height(52.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .border(1.dp, Color.LightGray)
                    .fillMaxSize()
                    .padding(8.dp)
                    .weight(0.5f), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Tracking ID:",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            Column(
                modifier = Modifier
                    .border(1.dp, Color.LightGray)
                    .fillMaxSize()
                    .padding(8.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .weight(0.5f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Status ",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }


        }
    }

}

@Composable
fun RequisitionListBodyItem(trackingId: MutableList<Long>, status: MutableList<String>) {

    Column {
        Row {
            SearchBar()
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row {
            Text(
                text = "Dashboard",
                textAlign = TextAlign.Start,
                fontSize = TextUnit(18f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier
                .background(color = Color(0xFFEFEFEF))
                .border(1.dp, Color.LightGray)
                .fillMaxWidth()
                .height(52.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .border(1.dp, Color.LightGray)
                    .fillMaxSize()
                    .padding(8.dp)
                    .weight(0.4f), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Tracking ID:",
                    textAlign = TextAlign.Start,
                    fontSize = TextUnit(16f, TextUnitType.Sp),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            Column(
                modifier = Modifier
                    .border(1.dp, Color.LightGray)
                    .fillMaxSize()
                    .padding(8.dp)
                    .align(alignment = Alignment.CenterVertically)
                    .weight(0.5f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Status:",
                    textAlign = TextAlign.Start,
                    fontSize = TextUnit(16f, TextUnitType.Sp),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }


        }
        for (trackingD in 0..4) {
            Row(
                modifier = Modifier
                    .border(1.dp, Color.LightGray)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier
//                        .border(1.dp, Color.LightGray)
                        .fillMaxSize()
                        .padding(8.dp)
                        .align(alignment = Alignment.CenterVertically)
                        .weight(0.4f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = trackingId[trackingD].toString(),
                        textAlign = TextAlign.Start,
                        fontSize = TextUnit(14f, TextUnitType.Sp),
                        fontWeight = FontWeight.Normal,
                        color = Color.Blue,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .border(1.dp, Color.LightGray)
                        .fillMaxSize()
                        .padding(8.dp)
                        .align(alignment = Alignment.CenterVertically)
                        .weight(0.5f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = status[trackingD],
                        textAlign = TextAlign.Start,
                        fontSize = TextUnit(14f, TextUnitType.Sp),
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }


            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row {
            Text(
                text = "Overview Chart:",
                textAlign = TextAlign.Start,
                fontSize = TextUnit(18f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clipToBounds()
            ) {
            Image(
                painter = painterResource(id = R.drawable.chart),
                contentDescription = "pie_chart",
                modifier = Modifier
                    .height(230.dp)
                    .fillMaxWidth()

            )
        }
    }

}

@Composable
fun showPieChart() {
    Spacer(modifier = Modifier.padding(25.dp))
    Box(modifier = Modifier) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.chart),
                contentDescription = "scan_qr",
                modifier = Modifier
                    .fillMaxSize()

            )
        }

    }
}

@Composable
internal fun PieChart(
    modifier: Modifier = Modifier,
    colors: List<Color>,
    inputValues: List<Float>,
    textColor: Color = MaterialTheme.colorScheme.primary,
    animated: Boolean = true,
    enableClickInfo: Boolean = true
) {
    val chartDegrees = 360f // circle shape

    // start drawing clockwise (top to right)
    var startAngle = 270f

    // calculate each input percentage
    val proportions = inputValues.map {
        it * 100 / inputValues.sum()
    }

    // calculate each input slice degrees
    val angleProgress = proportions.map { prop ->
        chartDegrees * prop / 100
    }

    // clicked slice index
    var clickedItemIndex by remember {
        mutableStateOf(inputValues)
    }

    // calculate each slice end point in degrees, for handling click position
    val progressSize = mutableListOf<Float>()

    LaunchedEffect(angleProgress) {
        progressSize.add(angleProgress.first())
        for (x in 1 until angleProgress.size) {
            progressSize.add(angleProgress[x] + progressSize[x - 1])
        }
    }
    BoxWithConstraints(modifier = modifier, contentAlignment = Alignment.CenterEnd) {

        val canvasSize = min(constraints.maxWidth, constraints.maxHeight)
        val size = Size(canvasSize.toFloat(), canvasSize.toFloat())
        val canvasSizeDp = with(LocalDensity.current) { canvasSize.toDp() }

        Canvas(
            modifier = Modifier
                .size(canvasSizeDp)
                .align(Alignment.BottomCenter)
        ) {

            angleProgress.forEachIndexed { index, angle ->
                drawArc(
                    color = colors[index],
                    startAngle = startAngle,
                    sweepAngle = angle,
                    useCenter = true,
                    size = size / 1.3f,
                    style = Fill
                )
                startAngle += angle
            }

        }

    }
}

@Composable
internal fun PieChartScreen() {

    val chartColors = listOf(
        Color.Cyan,
        Color.Magenta,
        Color.LightGray,
        Color.Yellow,
        Color.Blue,
        Color.Green
    )

    val chartValues = listOf(23f, 20f, 17f, 3f, 4f, 13f)

    PieChart(
        modifier = Modifier.padding(10.dp),
        colors = chartColors,
        inputValues = chartValues,
        textColor = Color.White
    )

}

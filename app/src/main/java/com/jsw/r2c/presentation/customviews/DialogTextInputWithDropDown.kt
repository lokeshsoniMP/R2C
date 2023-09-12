package com.jsw.r2c.presentation.customviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogTextInputWithDropDown(title: String = "", hint: String = "", inputValue: String) {
    var mInputValue by rememberSaveable {
        mutableStateOf(inputValue)
    }
    var materialIdPopControll by rememberSaveable {
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
                materialIdPopControll = !materialIdPopControll
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = hint, modifier = Modifier
                .fillMaxSize()
                .weight(0.8f), color = Color.LightGray
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
                        .height(200.dp)
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
                        items(10) {
                            Text(text = "12346", modifier = Modifier.padding(8.dp))
                        }
                    }

                }

            }


        }
    }

}
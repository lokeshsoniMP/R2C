package com.jsw.r2c.presentation.customviews

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jsw.r2c.R
import com.jsw.r2c.presentation.theme.BlueDark
import com.jsw.r2c.presentation.theme.Kefa

@Composable
fun TopAppBarR2C(onClickHamBurger: () -> Unit = {}) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(82.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(0.2f)

                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.hamburger),
                contentDescription = "TopApp Bar",
                modifier = Modifier
                    .weight(0.2f)
                    .clickable {
                        onClickHamBurger.invoke()
                    }
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(0.6f)

                .fillMaxWidth()

        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 42.sp,
                color = BlueDark,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = Kefa,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Image(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "Notification Icon",
                modifier = Modifier
                    .size(28.dp),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Image(
                painter = painterResource(id = R.drawable.memo),
                modifier = Modifier.size(28.dp),
                contentDescription = "Notification Icon",

                )
        }

    }
}
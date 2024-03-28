package com.example.odoo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odoo.ui.theme.OdooTheme
import com.example.odoo.ui.theme.interFont

@Composable
fun TimeSheets(onClick: () -> Unit) {

    Background()
    Column {
        HeadLine(onClick)
        TimerList()
    }


}

@Composable
fun TimerList() {
    LazyColumn(Modifier.fillMaxSize()) {

    }
}

@Composable
fun HeadLine(onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 84.dp, end = 16.dp, bottom = 8.dp),

        ) {
        Text(
            text = "TimeSheets", color = Color.White, fontFamily =
            interFont,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold

        )

        val interactionSource = remember { MutableInteractionSource() }

        Image(
            painter = painterResource(id = R.drawable.btn_add),
            contentDescription = "CreateTimer",
            modifier = Modifier
                .size(48.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = { onClick() }
                )
        )
    }
}

@Composable
fun Background() {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0xFF0C1D4D),
                        Color(0xFF214ECC)
                    )
                )
            )
            .fillMaxSize()
    )
}

@Preview
@Composable
fun TimeSheetsPreview() {
    OdooTheme {
        TimeSheets {}
    }
}



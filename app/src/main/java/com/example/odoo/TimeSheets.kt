package com.example.odoo

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.odoo.data.entity.Timer
import com.example.odoo.ui.theme.interFont
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Preview
@Composable
fun TimeSheets(onClick: () -> Unit = {}) {
    var timeInSeconds by remember { mutableIntStateOf(0) }
    Background()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeadLine(onClick)
        TimerList()

    }
}

//@Preview
@Composable
fun TimerList(
    timerViewModel: TimerViewModel = hiltViewModel()
) {

    val timersState by timerViewModel.getTimers.collectAsState(initial = emptyList())

    Log.d("TimerList", "Timers: $timersState")

    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        itemsIndexed(timersState) { index, timer ->
            if (timer.favorite) {
                TimerItem(timer = timer, resId = R.drawable.favorite_icon)
            } else TimerItem(timer = timer, resId = R.drawable.emptyfavorite_icon)
        }
    }

}


@Composable
fun TimerItem(
    timer: Timer,
    resId: Int,
    timerViewModel: TimerViewModel = hiltViewModel()
) {
    val gray = Color.White.copy(alpha = 0.16f)

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(shape = RoundedCornerShape(8.dp), color = gray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.rectangle
            ),
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.width(220.dp)
        ) {
            ProjectText(project = timer.project, resId)
            SecondaryText(timer.task, resId = R.drawable.task_icon)
            SecondaryText(timer.description, resId = R.drawable.time_icon)
        }
        TimerInc(timer = timer,
            onTimeUpdate = { newTimeInSeconds ->
                timerViewModel.viewModelScope.launch(Dispatchers.IO) {
                    timerViewModel.updateTimer(timer = timer, timeInSeconds = newTimeInSeconds)
                }
            }
        )
    }
}


@Composable
fun TimerInc(
    timer: Timer,
    onTimeUpdate: (Int) -> Unit
) {

    var isClicked by remember { mutableStateOf(false) }
    var timeInSeconds by rememberSaveable { mutableIntStateOf(timer.time) }

    val backgroundColor = if (isClicked) Color.White else Color.White.copy(alpha = 0.16f)
    val contentColor = if (isClicked) Color.Black else Color.White

    val iconResource = if (isClicked) R.drawable.pause_icon else R.drawable.pause_white_icon

    Row(
        modifier = Modifier
            .width(104.dp)
            .height(64.dp)
            .padding(8.dp, 8.dp, 8.dp, 16.dp)
            .clip(RoundedCornerShape(64.dp))
            .background(backgroundColor)
            .clickable { isClicked = !isClicked },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = formatTime(timeInSeconds),
            fontFamily = interFont,
            fontWeight = FontWeight.Medium,
            color = contentColor
        )
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = null,
            tint = contentColor
        )
    }
    if (isClicked) {
        LaunchedEffect(Unit) {
            while (isClicked) {
                delay(1000)
                timeInSeconds++
                onTimeUpdate(timeInSeconds)
            }
        }
    }
}

@SuppressLint("DefaultLocale")
private fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}

@Composable
fun SecondaryText(text: String, resId: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            modifier = Modifier.size(16.dp),
        )
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Text(
            fontFamily = interFont,
            fontWeight = FontWeight.Light,
            color = Color.White,
            text = text
        )
    }
}

@Composable
fun ProjectText(project: String, resId: Int) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 4.dp)
                .size(16.dp),
        )
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Text(
            fontFamily = interFont,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            fontSize = 16.sp,
            text = project,
        )
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

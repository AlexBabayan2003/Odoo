package com.example.odoo

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.odoo.ui.theme.interFont
@Preview
@Composable
fun CreateTimer(onClick: () -> Unit = {}) {
    GradientBackground()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Headline(onClick)

        DropDownMenu(
            "Project",
            listOf("Android app development", "Other")
        )
        DropDownMenu(
            "Task",
            listOf("Make a Odoo app")
        )
        Description()

        MakeFavorite()
        CreateTimerButton(onClick)
    }

}

@Composable
fun CreateTimerButton(onClick: () -> Unit = {}) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp, 20.dp, 14.dp, 20.dp),

        ) {
        Button(
            onClick = {
                onClick()
            },
            shape = RoundedCornerShape(12.dp),

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(48.dp)
                .widthIn(max = 361.dp)
            ,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White.copy(alpha = 0.16f)
            )
        ) {

            Text(
                text = "Create Timer",
                fontFamily = interFont,
                fontWeight = FontWeight.Medium
            )

        }
    }
}

@Composable
fun Headline(onClick: () -> Unit = {}) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(start = 16.dp, top = 84.dp, end = 16.dp, bottom = 8.dp),
    ) {


        val interactionSource = remember { MutableInteractionSource() }

        Image(
            painter = painterResource(id = R.drawable.btn_back),
            contentDescription = "BackToTimesheet",
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = { onClick() },
                )
        )


        Spacer(modifier = Modifier.padding(horizontal = 38.dp))

        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            text = "Create Timer", color = Color.White, fontFamily =
            interFont,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MakeFavorite() {
    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .width(361.dp)
            .height(40.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.btn_down),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Make Favorite",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(category: String, items: List<String>) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }
    var categoryChosen by remember { mutableStateOf(false) }
    val gray = Color.White.copy(alpha = 0.16f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier
                .width(361.dp)
                .height(56.dp)
                .border(2.dp, gray, RoundedCornerShape(8.dp))
                .background(Color.Transparent),
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                placeholder = {
                    Text(text = category, color = Color.White)
                    Spacer(modifier = Modifier.padding(566.dp))
                },

                trailingIcon = {
                    Box(
                    ) {
                        Icon(
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                            ,
                            painter = painterResource(id = R.drawable.btn_down),
                            contentDescription = null
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = Color.White,
                    cursorColor = Color.White,
                    unfocusedTrailingIconColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedSupportingTextColor = Color.White
                ),
                modifier = Modifier.menuAnchor()
                    .clickable { expanded = true }
                    .fillMaxWidth()

            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { selectionOption ->
                    DropdownMenuItem(
                        {
                            Text(
                                text = selectionOption, fontFamily = interFont,
                                fontWeight = FontWeight.Normal,
                                        modifier = Modifier
//                                        .height(56.dp)
//                                    .padding(vertical = 8.dp)
                            )
                        },
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                            categoryChosen = true
                        },
                        interactionSource = remember { MutableInteractionSource() }
                    )
                }
            }
        }
    }
}

@Composable
fun Description() {

    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 8.dp)
            .width(361.dp)
            .height(56.dp)
            .border(2.dp, Color.White.copy(alpha = 0.16f), RoundedCornerShape(8.dp)),
        value = text,
        onValueChange = { text = it },
        colors = OutlinedTextFieldDefaults.colors(
            disabledTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedSupportingTextColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        readOnly = false,
        placeholder = {
            Text(
                text = "Description",
                color = Color.White,
                fontFamily = interFont
            )
        }
    )

}

@Composable
fun GradientBackground() {
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


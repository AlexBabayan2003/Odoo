package com.example.odoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.dark(
                    android.graphics.Color.TRANSPARENT
                )
            )
            Navigation()
        }
    }

    @Preview
    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            "TimeSheets"
        ) {
            composable("TimeSheets") {
                TimeSheets {
                    navController.navigate("CreateTimer")
                }
            }
            composable("CreateTimer") {
                CreateTimer {
                    navController.navigate("TimeSheets")
                }
            }
        }
    }
}


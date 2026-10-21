package com.example.examenpractico2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.examenpractico2.presentation.navigation.AppNavigation
import com.example.examenpractico2.ui.theme.ExamenPractico2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenPractico2Theme {
                AppNavigation()
            }
        }
    }
}

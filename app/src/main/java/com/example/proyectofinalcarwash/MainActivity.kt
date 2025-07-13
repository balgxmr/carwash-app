package com.example.proyectofinalcarwash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.proyectofinalcarwash.login.LoginScreen
import com.example.proyectofinalcarwash.ui.theme.ProyectofinalcarwashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectofinalcarwashTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    LoginScreen(
                        modifier = Modifier.padding(paddingValues),
                        onLoginClick = { user, pass -> },
                        onRegisterClick = { }
                    )
                }
            }
        }
    }
}

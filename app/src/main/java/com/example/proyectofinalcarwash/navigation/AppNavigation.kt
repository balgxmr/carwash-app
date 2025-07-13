package com.example.proyectofinalcarwash.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalcarwash.authScreens.LoginScreen
import com.example.proyectofinalcarwash.authScreens.RegisterScreen
import com.example.proyectofinalcarwash.ui.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("login") {
            LoginScreen(
                onLoginClick = { _, _ -> },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            RegisterScreen(
                onLoginClick = { _, _ -> },
                onRegisterClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

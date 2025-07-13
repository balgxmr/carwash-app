package com.example.proyectofinalcarwash.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalcarwash.authScreens.LoginScreen
import com.example.proyectofinalcarwash.authScreens.RegisterScreen
import com.example.proyectofinalcarwash.home.HomeScreen
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
                onLoginClick = { _, _ ->
                    navController.navigate("home")
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }
        composable("home") {
            HomeScreen(
                // se coloca temporalmente lambdas vacíos
                onAgendarCita = {},
                onMisVehiculos = {},
                onVerServicios = {},
                onHistorialCitas = {}
            )
        }
        composable("register") {
            RegisterScreen(
                onLoginClick = { _, _ -> },
                onRegisterClick = { username, password ->
                    // Aquí sería lo que quieras con los datos, por ahora solo regresa:
                    navController.popBackStack()
                }
            )
        }
    }
}
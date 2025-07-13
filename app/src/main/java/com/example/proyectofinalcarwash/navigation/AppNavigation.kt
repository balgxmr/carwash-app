package com.example.proyectofinalcarwash.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalcarwash.authScreens.LoginScreen
import com.example.proyectofinalcarwash.authScreens.RegisterScreen
import com.example.proyectofinalcarwash.home.HomeScreen
import com.example.proyectofinalcarwash.ui.splash.SplashScreen
import com.example.proyectofinalcarwash.screens.ServicesScreen
import com.example.proyectofinalcarwash.screens.Servicio

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
            HomeScreen(navController = navController)
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
        composable("services") {
            // Lista dummy temporal
            val serviciosDummy = listOf(
                Servicio(1, "Lavado Básico", "Incluye lavado exterior con agua y jabón", 8.00),
                Servicio(2, "Lavado Completo", "Exterior, interior, cera y aromatizante", 15.00),
                Servicio(3, "Lavado Premium", "Lavado completo + tapicería y motor", 25.00)
            )
            ServicesScreen(servicios = serviciosDummy)
        }
    }
}
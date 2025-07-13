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
import com.example.proyectofinalcarwash.screens.AppointmentScreen
import com.example.proyectofinalcarwash.screens.Servicio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.example.proyectofinalcarwash.ui.components.MainLayout

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
        composable("register") {
            RegisterScreen(
                onLoginClick = { _, _ -> },
                onRegisterClick = { username, password ->
                    // Aquí sería lo que quieras con los datos, por ahora solo regresa:
                    navController.popBackStack()
                }
            )
        }
        composable("home") {
            val currentDestination = navController.currentBackStackEntryAsState().value?.destination
            MainLayout(navController, currentDestination) { innerPadding ->
                HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController
                )
            }
        }
        composable("services") {
            val currentDestination = navController.currentBackStackEntryAsState().value?.destination
            MainLayout(navController, currentDestination) { innerPadding ->
                ServicesScreen(
                    modifier = Modifier.padding(innerPadding),
                    /* DATOS DE PRUEBA UNICAMENTE !!!!!!!!! */
                    servicios = listOf(
                        Servicio(1, "Lavado completo", "Incluye lavado exterior e interior del vehículo", 15.0),
                        Servicio(2, "Pulido de pintura", "Mejora el brillo de la pintura y elimina rayones leves", 25.0),
                        Servicio(3, "Cambio de aceite", "Incluye cambio de aceite y filtro", 40.0)
                    )
                )
            }
        }
        composable("agendarCita") {
            val currentDestination = navController.currentBackStackEntryAsState().value?.destination
            MainLayout(navController, currentDestination) { innerPadding ->
                AppointmentScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController
                )
            }
        }
    }
}
package com.example.proyectofinalcarwash.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Vehiculo(
    val id: Int,
    val marca: String,
    val modelo: String,
    val placa: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisVehiculosScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // Lista de vehículos (puedes reemplazar con ViewModel)
    //var vehiculos by remember { mutableStateOf(listOf<Vehiculo>()) }

    // LISTA DE VEHICULOS TEMPORAL -- BORRAR !!!!!!!!!!!!!!!!!
    var vehiculos by remember {
        mutableStateOf(
            listOf(
                Vehiculo(
                    id = 1,
                    marca = "Toyota",
                    modelo = "Corolla",
                    placa = "ABC-123"
                )
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mis Vehículos") })
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (vehiculos.isEmpty()) {
                item {
                    AddVehicleCard(
                        onClick = {
                            navController.navigate("agregarVehiculo")
                        }
                    )
                }
            } else {
                items(vehiculos) { vehiculo ->
                    VehiculoCard(vehiculo)
                }
                item {
                    AddVehicleCard(
                        onClick = {
                            navController.navigate("agregarVehiculo")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun VehiculoCard(vehiculo: Vehiculo) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.DirectionsCar,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("${vehiculo.marca} ${vehiculo.modelo}", fontSize = 18.sp)
                Text("Placa: ${vehiculo.placa}", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun AddVehicleCard(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir vehículo",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Añadir vehículo",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

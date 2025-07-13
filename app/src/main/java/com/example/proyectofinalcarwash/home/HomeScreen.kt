package com.example.proyectofinalcarwash.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinalcarwash.home.HomeScreen

@Composable
fun HomeScreen(
    onAgendarCita: () -> Unit,
    onMisVehiculos: () -> Unit,
    onVerServicios: () -> Unit,
    onHistorialCitas: () -> Unit
) {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = listOf(
                    BottomNavItem("Inicio", Icons.Default.Home),
                    BottomNavItem("Servicios", Icons.Default.Build),
                    BottomNavItem("Perfil", Icons.Default.Person)
                )
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // 🧾 Bienvenida
            Text(
                text = "Bienvenido, user",
                style = MaterialTheme.typography.headlineSmall
            )

            // 🔹 Próxima cita
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Próxima cita", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("📅 Fecha: 2025-07-20")
                    Text("🕒 Hora: 10:30 AM")
                    Text("🚗 Vehículo: Toyota Corolla")
                    Text("🧽 Servicio: Lavado completo")
                    Text("📌 Estado: Confirmada")
                }
            }

            // 🔹 Acciones rápidas
            Text("Acciones rápidas", style = MaterialTheme.typography.titleMedium)

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = onAgendarCita, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Agendar nueva cita")
                }
                Button(onClick = onMisVehiculos, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.DirectionsCar, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Mis vehículos")
                }
                Button(onClick = onVerServicios, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.Build, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Ver servicios")
                }
                Button(onClick = onHistorialCitas, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.History, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Historial de citas")
                }
            }
        }
    }
}

data class BottomNavItem(val label: String, val icon: ImageVector)

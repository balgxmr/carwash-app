package com.example.proyectofinalcarwash.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val hoy = LocalDate.now()

    // Citas de ejemplo
    val citas = listOf(
        Cita(hoy.minusDays(1), "08:00", 30, "Lavado interior", "Nissan Versa"),
        Cita(hoy.minusDays(2), "15:00", 60, "Encerado", "Ford Focus"),
        Cita(hoy.minusDays(3), "10:00", 45, "Cambio de aceite", "Toyota Corolla"),
        Cita(hoy.minusDays(4), "11:30", 60, "Pulido de pintura", "Honda Civic"),
        Cita(hoy.minusDays(2), "11:30", 60, "Pulido de pintura", "Kia K3"),
        Cita(hoy.minusDays(5), "11:30", 60, "Pulido de pintura", "Acura RSX"),
        Cita(hoy.minusDays(6), "11:30", 60, "Pulido de pintura", "Honda Accord")
    )

    val citasPasadas = citas.sortedByDescending { it.fecha }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historial de Citas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (citasPasadas.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay citas anteriores.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(citasPasadas) { cita ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    "detalleCita/${cita.fecha}/${cita.hora}/${cita.servicio}/${cita.vehiculo}/${cita.duracionMin}"
                                )
                            },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "📅 ${cita.fecha}",
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(Modifier.height(4.dp))
                            Text("🕒 ${cita.hora} — ${cita.duracionMin} min")
                            Text("🧽 Servicio: ${cita.servicio}")
                            Text("🚗 Vehículo: ${cita.vehiculo}")
                        }
                    }
                }

                // Último ítem: Botón Volver
                item {
                    Spacer(Modifier.height(24.dp))
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text("Volver")
                    }
                }
            }
        }
    }
}

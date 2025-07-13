package com.example.proyectofinalcarwash.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Servicio(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicesScreen(
    servicios: List<Servicio>
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Servicios disponibles") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(servicios) { servicio ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CleaningServices,
                                contentDescription = null,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = servicio.nombre,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = servicio.descripcion)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Precio: $${servicio.precio}",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
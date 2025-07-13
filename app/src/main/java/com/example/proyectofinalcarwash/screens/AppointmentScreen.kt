package com.example.proyectofinalcarwash.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // Datos de prueba (deberías obtenerlos desde ViewModel o repositorio en producción)
    val servicios = listOf("Lavado completo", "Cambio de aceite", "Pulido de pintura")
    val vehiculos = listOf("Toyota Corolla", "Honda Civic", "Mazda 3")

    var servicioSeleccionado by remember { mutableStateOf<String?>(null) }
    var vehiculoSeleccionado by remember { mutableStateOf<String?>(null) }
    var fecha by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            fecha = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            hora = String.format("%02d:%02d", hourOfDay, minute)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Agendar nueva cita") })
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            SimpleDropdownMenu(
                label = "Selecciona un vehículo",
                options = vehiculos,
                selectedOption = vehiculoSeleccionado,
                onOptionSelected = { vehiculoSeleccionado = it }
            )

            SimpleDropdownMenu(
                label = "Selecciona un servicio",
                options = servicios,
                selectedOption = servicioSeleccionado,
                onOptionSelected = { servicioSeleccionado = it }
            )

            DateSelector(
                label = "Selecciona una fecha",
                date = fecha,
                onDateSelected = { fecha = it }
            )

            TimeSelector(
                label = "Selecciona una hora",
                time = hora,
                onTimeSelected = { hora = it }
            )

            Button(
                onClick = {
                    // Aquí puedes guardar la cita
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirmar cita")
            }
        }
    }
}

@Composable
fun SimpleDropdownMenu(
    label: String,
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = selectedOption ?: "",
        onValueChange = {},
        label = { Text(label) },
        readOnly = true,
        enabled = true,
        trailingIcon = {
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.fillMaxWidth()
    ) {
        options.forEach { option ->
            DropdownMenuItem(
                text = { Text(option) },
                onClick = {
                    onOptionSelected(option)
                    expanded = false
                }
            )
        }
    }
}

@Composable
fun DateSelector(
    label: String,
    date: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    OutlinedTextField(
        value = date,
        onValueChange = {},
        label = { Text(label) },
        readOnly = true,
        enabled = true,
        trailingIcon = { Icon(Icons.Default.CalendarToday, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        onDateSelected("%04d-%02d-%02d".format(year, month + 1, day))
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
    )
}


@Composable
fun TimeSelector(
    label: String,
    time: String,
    onTimeSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    OutlinedTextField(
        value = time,
        onValueChange = {},
        label = { Text(label) },
        readOnly = true,
        enabled = true,
        trailingIcon = { Icon(Icons.Default.AccessTime, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        onTimeSelected("%02d:%02d".format(hourOfDay, minute))
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                ).show()
            }
    )
}

package com.example.examenpractico2.presentation.ui.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examenpractico2.presentation.viewmodel.FavoritosViewModel

@Composable
fun FavoritesScreen(viewModel: FavoritosViewModel = viewModel()) {
    val favoritos by viewModel.favoritos.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Razas favoritas", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        if (favoritos.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Sin elementos guardados")
            }
        } else {
            LazyColumn {
                items(favoritos) { raza ->
                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(raza.name.replaceFirstChar { it.uppercase() }, style = MaterialTheme.typography.titleMedium)
                                Text("Subrazas: ${raza.subbreedCount}", style = MaterialTheme.typography.bodySmall)
                            }
                            Button(
                                onClick = { viewModel.quitarFavorita(raza.name) },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                            ) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}

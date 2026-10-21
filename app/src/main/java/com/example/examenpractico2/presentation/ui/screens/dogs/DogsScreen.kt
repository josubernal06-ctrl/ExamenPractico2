package com.example.examenpractico2.presentation.ui.screens.dogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examenpractico2.domain.model.DogBreed
import com.example.examenpractico2.presentation.ui.state.UiState
import com.example.examenpractico2.presentation.viewmodel.DogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsScreen(
    viewModel: DogViewModel = viewModel(),
    onNavigateToDetail: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Razas de perros", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { 
                searchQuery = it
                viewModel.buscarRazas(it)
            },
            label = { Text("Buscar raza") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        when (val state = uiState) {
            is UiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Success -> {
                val breeds = state.data
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(breeds) { breed ->
                        DogBreedItem(breed = breed, onClick = { onNavigateToDetail(breed.name) })
                    }
                }
            }
            is UiState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.reintentar() }) {
                        Text("Reintentar")
                    }
                }
            }
        }
    }
}

@Composable
fun DogBreedItem(breed: DogBreed, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = breed.name.replaceFirstChar { it.uppercase() }, style = MaterialTheme.typography.titleLarge)
            Text(text = "Subrazas: ${breed.subbreedCount}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

package com.example.examenpractico2.presentation.ui.screens.dogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examenpractico2.presentation.viewmodel.DogViewModel
import com.example.examenpractico2.presentation.viewmodel.FavoritosViewModel

@Composable
fun DogDetailScreen(
    breedName: String,
    dogViewModel: DogViewModel,
    favoritosViewModel: FavoritosViewModel = viewModel()
) {
    val breed = dogViewModel.getBreedByName(breedName)

    if (breed == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text("Raza no encontrada")
        }
        return
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = breed.name.replaceFirstChar { it.uppercase() }, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { favoritosViewModel.marcarFavorita(breed) }) {
            Text("Marcar favorita")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (breed.subbreeds.isEmpty()) {
            Text("No tiene subrazas", style = MaterialTheme.typography.bodyLarge)
        } else {
            Text("Subrazas:", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(breed.subbreeds) { subbreed ->
                    Text(text = "- $subbreed", modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        }
    }
}

package com.example.examenpractico2.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.examenpractico2.presentation.ui.screens.catfacts.CatFactsScreen
import com.example.examenpractico2.presentation.ui.screens.dogs.DogDetailScreen
import com.example.examenpractico2.presentation.ui.screens.dogs.DogsScreen
import com.example.examenpractico2.presentation.ui.screens.favorites.FavoritesScreen
import com.example.examenpractico2.presentation.ui.screens.weather.WeatherScreen
import com.example.examenpractico2.presentation.viewmodel.DogViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val dogViewModel: DogViewModel = viewModel()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                NavigationBarItem(
                    icon = { Text("🐶") },
                    label = { Text("Razas") },
                    selected = currentRoute == "dogs" || currentRoute?.startsWith("dog_detail") == true,
                    onClick = {
                        navController.navigate("dogs") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Text("⛅") },
                    label = { Text("Clima") },
                    selected = currentRoute == "weather",
                    onClick = {
                        navController.navigate("weather") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Text("🐱") },
                    label = { Text("Gatos") },
                    selected = currentRoute == "catfacts",
                    onClick = {
                        navController.navigate("catfacts") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Text("⭐") },
                    label = { Text("Favoritos") },
                    selected = currentRoute == "favorites",
                    onClick = {
                        navController.navigate("favorites") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "dogs",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("dogs") {
                DogsScreen(
                    viewModel = dogViewModel,
                    onNavigateToDetail = { breedName ->
                        navController.navigate("dog_detail/$breedName")
                    }
                )
            }
            composable("dog_detail/{breedName}") { backStackEntry ->
                val breedName = backStackEntry.arguments?.getString("breedName") ?: ""
                DogDetailScreen(
                    breedName = breedName,
                    dogViewModel = dogViewModel
                )
            }
            composable("weather") {
                WeatherScreen()
            }
            composable("catfacts") {
                CatFactsScreen()
            }
            composable("favorites") {
                FavoritesScreen()
            }
        }
    }
}

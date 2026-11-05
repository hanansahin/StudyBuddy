package com.example.studybuddy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.studybuddy.nav.Screen
import com.example.studybuddy.ui.screens.*
import com.example.studybuddy.viewmodels.TaskViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()

    // Bottom bar items (unchanged)
    val items = listOf(Screen.Timer, Screen.Tasks, Screen.Progress, Screen.Motivation)

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { screen ->
                    val icon = when (screen) {
                        Screen.Timer -> Icons.Default.Timer
                        Screen.Tasks -> Icons.AutoMirrored.Filled.List
                        Screen.Progress -> Icons.AutoMirrored.Filled.ShowChart
                        Screen.Motivation -> Icons.Default.Lightbulb
                    }

                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = false,
                        onClick = {
                            navController.navigate(screen.route) {
                                navController.graph.startDestinationRoute?.let { r ->
                                    popUpTo(r) { saveState = true }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            // ⭐ NEW: Start on the Start Screen instead of Timer
            startDestination = "start",
            modifier = Modifier.padding(padding)
        ) {

            // ⭐ NEW: Start Screen Route
            composable("start") {
                StartScreen(
                    onStartClick = {
                        // Navigate to Timer and remove start from backstack
                        navController.navigate(Screen.Timer.route) {
                            popUpTo("start") { inclusive = true }
                        }
                    }
                )
            }

            // Existing screens
            composable(Screen.Timer.route) { TimerScreen() }

            composable(Screen.Tasks.route) { TasksScreen(taskViewModel) }

            composable(Screen.Progress.route) { ProgressScreen(taskViewModel) }

            composable(Screen.Motivation.route) { MotivationScreen() }
        }
    }
}

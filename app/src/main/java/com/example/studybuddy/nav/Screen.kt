package com.example.studybuddy.nav

sealed class Screen(val route: String, val title: String) {
    object Timer : Screen("timer", "Timer")
    object Tasks : Screen("tasks", "Tasks")
    object Progress : Screen("progress", "Progress")
    object Motivation : Screen("motivation", "Motivation")
}

package com.example.compasstest.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes (val title: String, val icon: ImageVector, val route: String){

    sealed class Home {
        object HomeScreen: Routes("Home", Icons.Filled.Home, "homeScreen")
    }
}
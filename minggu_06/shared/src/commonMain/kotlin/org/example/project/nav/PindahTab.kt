package org.example.project.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

fun pindahTab(navController: NavController, rute: Any) {
    navController.navigate(rute) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
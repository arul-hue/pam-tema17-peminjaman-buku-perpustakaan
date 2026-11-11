package org.example.project.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import org.example.project.nav.RuteAsisten
import org.example.project.nav.RuteBeranda
import org.example.project.nav.RuteProfil
import org.example.project.nav.pindahTab

private data class ItemMenu(val label: String, val ikon: ImageVector, val rute: Any)

private val DAFTAR_MENU = listOf(
    ItemMenu("Beranda", Icons.Default.Home, RuteBeranda),
    ItemMenu("Asisten", Icons.Default.Face, RuteAsisten),
    ItemMenu("Profil", Icons.Default.Person, RuteProfil)
)

@Composable
fun MenuBawah(navController: NavController) {
    val entry by navController.currentBackStackEntryAsState()
    NavigationBar {
        DAFTAR_MENU.forEach { item ->
            NavigationBarItem(
                selected = entry?.destination?.hasRoute(item.rute::class) == true,
                onClick = { pindahTab(navController, item.rute) },
                icon = { Icon(item.ikon, contentDescription = null) },
                label = { Text(item.label) }
            )
        }
    }
}
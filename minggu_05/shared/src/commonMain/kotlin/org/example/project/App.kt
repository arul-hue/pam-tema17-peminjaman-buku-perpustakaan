package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.nav.RuteBeranda
import org.example.project.nav.RuteForm
import org.example.project.ui.DaftarPeminjamanScreen
import org.example.project.ui.FormPeminjamanScreen

@Composable
fun App() {
    MaterialTheme {
        val nav = rememberNavController()

        NavHost(
            navController = nav,
            startDestination = RuteBeranda
        ) {
            composable<RuteBeranda> {
                DaftarPeminjamanScreen(
                    onTambah = { nav.navigate(RuteForm) }
                )
            }

            composable<RuteForm> {
                FormPeminjamanScreen(
                    onSelesai = { nav.popBackStack() },
                    onBatal = { nav.popBackStack() }
                )
            }
        }
    }
}

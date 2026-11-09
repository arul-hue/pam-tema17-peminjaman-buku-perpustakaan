package org.example.project

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.example.project.nav.RuteAsisten
import org.example.project.nav.RuteBeranda
import org.example.project.nav.RuteDetail
import org.example.project.nav.RuteForm
import org.example.project.nav.RuteProfil
import org.example.project.ui.AsistenScreen
import org.example.project.ui.DaftarPeminjamanScreen
import org.example.project.ui.DetailPeminjamanScreen
import org.example.project.ui.FormPeminjamanScreen
import org.example.project.ui.MenuBawah
import org.example.project.ui.ProfilScreen

@Composable
fun App() {
    MaterialTheme {
        val nav = rememberNavController()
        val entry by nav.currentBackStackEntryAsState()
        val tujuan = entry?.destination
        val tampilkanMenu = tujuan?.hasRoute<RuteBeranda>() == true ||
                tujuan?.hasRoute<RuteAsisten>() == true ||
                tujuan?.hasRoute<RuteProfil>() == true

        Scaffold(
            bottomBar = {
                if (tampilkanMenu) {
                    MenuBawah(navController = nav)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = nav,
                startDestination = RuteBeranda,
                modifier = Modifier
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
            ) {
                composable<RuteBeranda> {
                    DaftarPeminjamanScreen(
                        onTambah = { nav.navigate(RuteForm) },
                        onBukaDetail = { id -> nav.navigate(RuteDetail(id)) }
                    )
                }

                composable<RuteDetail> { detailEntry ->
                    val rute = detailEntry.toRoute<RuteDetail>()
                    DetailPeminjamanScreen(
                        id = rute.id,
                        onKembali = { nav.popBackStack() }
                    )
                }

                composable<RuteForm> {
                    FormPeminjamanScreen(
                        onSelesai = { nav.popBackStack() },
                        onBatal = { nav.popBackStack() }
                    )
                }

                composable<RuteAsisten> {
                    AsistenScreen()
                }

                composable<RuteProfil> {
                    ProfilScreen()
                }
            }
        }
    }
}

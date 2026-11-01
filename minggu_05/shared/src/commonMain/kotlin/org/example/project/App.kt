package org.example.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.example.project.ui.DaftarPeminjamanScreen
import org.example.project.ui.FormPeminjamanScreen

@Composable
fun App() {
    MaterialTheme {
        // rememberSaveable: form tidak menutup sendiri saat layar diputar
        var tampilkanForm by rememberSaveable { mutableStateOf(false) }

        if (tampilkanForm) {
            FormPeminjamanScreen(
                onSelesai = { tampilkanForm = false },
                onBatal = { tampilkanForm = false }
            )
        } else {
            DaftarPeminjamanScreen(onTambah = { tampilkanForm = true })
        }
    }
}
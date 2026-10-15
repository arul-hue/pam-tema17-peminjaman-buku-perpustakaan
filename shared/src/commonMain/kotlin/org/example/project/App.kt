
package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Nama tema aplikasi
            Text("Peminjaman Buku Perpustakaan")

            // Nama platform otomatis
            Text("Platform: ${getPlatform().name}")

            //Spacer(modifier = Modifier.padding(8.dp))
            Spacer(modifier = Modifier.height(16.dp))

            // Bagian Tentang
            Text("Tentang Anggota")

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Anggota 1: Syahrul Afwan - 124140096")
                Text("Anggota 2: Hafidz Raihan Putra Anfa - 124140108              ")
                Text("Anggota 3: NAMA - NIM")
            }
        }
    }
}

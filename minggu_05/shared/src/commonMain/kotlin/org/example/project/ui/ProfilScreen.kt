package org.example.project.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfilScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Profil", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Data contoh, bukan data pengguna sebenarnya", color = Color.Gray)

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("Pengguna", fontWeight = FontWeight.Bold)
                Text("Nama: Pengguna Contoh")
                Text("Peran: Anggota Perpustakaan")
                Text("Email: pengguna@contoh.ac.id")
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("Tentang Aplikasi", fontWeight = FontWeight.Bold)
                Text("Peminjaman Buku Perpustakaan")
                Text("Tema 17, Pengembangan Aplikasi Mobile", color = Color.Gray)
                Text("Dikembangkan dengan Compose Multiplatform", color = Color.Gray)
            }
        }
    }
}
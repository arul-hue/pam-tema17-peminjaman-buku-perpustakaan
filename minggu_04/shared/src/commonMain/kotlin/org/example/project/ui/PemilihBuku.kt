package org.example.project.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.example.project.model.Buku

@Composable
fun PemilihBuku(
    pilihan: List<Buku>,
    terpilih: Buku?,
    onPilih: (Buku) -> Unit
) {
    var terbuka by remember { mutableStateOf(false) }
// agar saat tombol diklik menu muncul tepat di bawahnya
    Box {
        OutlinedButton(onClick = { terbuka = true }) {
            Text(terpilih?.judul ?: "Pilih buku")
        }
//agar saat tombol diklik, daftar buku tampil menggulir ke bawah dan bisa dipilih
        DropdownMenu(expanded = terbuka, onDismissRequest = { terbuka = false }) {
            pilihan.forEach { buku ->
                DropdownMenuItem(
                    text = { Text(buku.judul) },
                    onClick = {
                        onPilih(buku)
                        terbuka = false
                    }
                )
            }
        }
    }
}
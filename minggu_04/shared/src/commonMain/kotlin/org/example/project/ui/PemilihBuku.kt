package org.example.project.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.example.project.model.Buku

// Buku yang dipilih datang dari parent. Hanya buka/tutup menu yang disimpan di sini
@Composable
fun PemilihBuku(
    pilihan: List<Buku>,
    terpilih: Buku?,
    onPilih: (Buku) -> Unit,
    modifier: Modifier = Modifier
) {
    var terbuka by remember { mutableStateOf(false) }

    // Box membuat menu muncul tepat di bawah kolom
    Box(modifier) {
        // Tampil seperti kolom isian lain, tapi isinya hanya bisa dipilih dari menu
        OutlinedTextField(
            value = terpilih?.judul ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Buku") },
            trailingIcon = { Text("▾") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        // Lapisan transparan di atas kolom: klik di mana saja membuka menu
        Box(
            Modifier
                .matchParentSize()
                .clickable { terbuka = true }
        )

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

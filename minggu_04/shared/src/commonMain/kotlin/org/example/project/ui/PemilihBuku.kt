package org.example.project.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.project.model.Buku

/**
 * Pemilik: Hafidz. Dropdown pilihan buku, TANPA state dari ViewModel.
 * Tanda tangan DIBEKUKAN.
 */
@Composable
fun PemilihBuku(
    pilihan: List<Buku>,
    terpilih: Buku?,
    onPilih: (Buku) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO Hafidz: dropdown (ExposedDropdownMenuBox), tampilkan judul buku
    Text("TODO Hafidz: PemilihBuku", modifier = modifier)
}

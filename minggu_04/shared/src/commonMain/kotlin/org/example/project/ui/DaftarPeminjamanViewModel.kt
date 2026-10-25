package org.example.project.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import org.example.project.data.SumberDaftarPeminjaman
import org.example.project.data.sumberDaftarPeminjaman
import org.example.project.model.Peminjaman

/** Pemilik: Syahrul. Layar daftar hanya membaca [daftar]. */
class DaftarPeminjamanViewModel(
    sumber: SumberDaftarPeminjaman = sumberDaftarPeminjaman
) : ViewModel() {
    val daftar: StateFlow<List<Peminjaman>> = sumber.daftar
}

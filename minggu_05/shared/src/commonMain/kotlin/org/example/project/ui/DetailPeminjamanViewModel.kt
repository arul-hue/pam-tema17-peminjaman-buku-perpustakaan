package org.example.project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.example.project.data.SumberDaftarPeminjaman
import org.example.project.data.sumberDaftarPeminjaman
import org.example.project.model.Peminjaman

/** Tiga kondisi layar Detail. */
sealed interface DetailUiState {
    data object Memuat : DetailUiState
    data class Ditemukan(val peminjaman: Peminjaman) : DetailUiState
    data class TidakDitemukan(val id: Int) : DetailUiState
}

/**
 * Pemilik: Jundi. Mengambil peminjaman berdasarkan ID dari sumber bersama
 * [sumberDaftarPeminjaman], sehingga isinya sama dengan kartu yang diklik
 * dan data baru dari Form juga bisa dibuka.
 */
class DetailPeminjamanViewModel(
    id: Int,
    sumber: SumberDaftarPeminjaman = sumberDaftarPeminjaman
) : ViewModel() {

    val uiState: StateFlow<DetailUiState> = sumber.pantau(id)
        .map { peminjaman ->
            if (peminjaman != null) DetailUiState.Ditemukan(peminjaman)
            else DetailUiState.TidakDitemukan(id)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailUiState.Memuat
        )
}

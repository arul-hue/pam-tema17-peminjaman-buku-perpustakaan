package org.example.project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.data.SumberDaftarPeminjaman
import org.example.project.data.sumberDaftarPeminjaman
import org.example.project.model.Buku

class FormPeminjamanViewModel(
    private val sumber: SumberDaftarPeminjaman = sumberDaftarPeminjaman
) : ViewModel() {

    private val _uiState = MutableStateFlow(FormPeminjamanUiState())
    val uiState: StateFlow<FormPeminjamanUiState> = _uiState.asStateFlow()

    fun ubahBuku(buku: Buku) =
        _uiState.update { it.copy(bukuDipilih = buku) }

    fun ubahPeminjam(teks: String) =
        _uiState.update { it.copy(peminjam = teks.take(50)) }

    fun ubahTanggal(teks: String) =
        _uiState.update { it.copy(tanggalPinjam = teks.take(10)) }

    fun simpan() {
        val state = _uiState.value
        val buku = state.bukuDipilih ?: return
        if (!state.bisaSimpan) return

        _uiState.update { it.copy(sedangMenyimpan = true) }
        viewModelScope.launch {
            delay(800) // simulasi proses kirim, minggu 6 diganti Ktor
            sumber.tambah(buku, state.peminjam.trim(), state.tanggalPinjam)
            _uiState.update { it.copy(sedangMenyimpan = false, tersimpan = true) }
        }
    }

    fun reset() {
        _uiState.value = FormPeminjamanUiState()
    }
}
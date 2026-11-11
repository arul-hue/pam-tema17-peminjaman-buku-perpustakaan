package org.example.project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.CancellationException
import org.example.project.data.sumberDaftarPeminjaman
import org.example.project.model.Buku

class FormPeminjamanViewModel(
    private val simpanPeminjaman: suspend (Buku, String, String) -> Unit =
        { buku, peminjam, tanggalPinjam ->
            delay(800)
            sumberDaftarPeminjaman.tambah(buku, peminjam, tanggalPinjam)
        }
) : ViewModel() {

    private val _uiState = MutableStateFlow(FormPeminjamanUiState())
    val uiState: StateFlow<FormPeminjamanUiState> = _uiState.asStateFlow()

    // Disimpan supaya proses simpan bisa dibatalkan kalau form di-reset
    private var jobSimpan: Job? = null

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

        _uiState.update { it.copy(sedangMenyimpan = true, pesan = null) }

        jobSimpan = viewModelScope.launch {
            try {
                simpanPeminjaman(buku, state.peminjam.trim(), state.tanggalPinjam)
                _uiState.update { it.copy(sedangMenyimpan = false, tersimpan = true) }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        sedangMenyimpan = false,
                        pesan = "Peminjaman gagal disimpan. Coba lagi."
                    )
                }
            }
        }
    }

    fun reset() {
        jobSimpan?.cancel() // batal di tengah proses simpan: data tidak jadi ditambah
        _uiState.value = FormPeminjamanUiState()
    }
}
package org.example.project.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.data.SumberDaftarPeminjaman
import org.example.project.data.sumberDaftarPeminjaman
import org.example.project.model.Buku

/**
 * KONTRAK, tanda tangan fungsi DIBEKUKAN. Pemilik: Syahrul (isi fungsi menyusul).
 * Hafidz memanggil fungsi di bawah dari layar form.
 */
class FormPeminjamanViewModel(
    private val sumber: SumberDaftarPeminjaman = sumberDaftarPeminjaman
) : ViewModel() {

    private val _uiState = MutableStateFlow(FormPeminjamanUiState())
    val uiState: StateFlow<FormPeminjamanUiState> = _uiState.asStateFlow()

    fun ubahBuku(buku: Buku) { /* TODO Syahrul */ }
    fun ubahPeminjam(teks: String) { /* TODO Syahrul */ }
    fun ubahTanggal(teks: String) { /* TODO Syahrul */ }

    /** Menyimpan hanya bila bisaSimpan. Setelah berhasil, tersimpan = true. */
    fun simpan() { /* TODO Syahrul */ }

    /**
     * Mengosongkan form. WAJIB dipanggil layar setelah tersimpan dan saat Batal,
     * karena ViewModel tidak ikut hilang saat layar form ditutup.
     */
    fun reset() { /* TODO Syahrul */ }
}

package org.example.project.ui

import org.example.project.data.daftarBukuContoh
import org.example.project.model.Buku

private val formatTanggal = Regex("""\d{4}-\d{2}-\d{2}""")

/**
 * KONTRAK, DIBEKUKAN. Pemilik: Syahrul.
 * Hafidz hanya MEMBACA state ini. bisaSimpan dan tanggalValid dihitung, tidak disimpan.
 */
data class FormPeminjamanUiState(
    val bukuDipilih: Buku? = null,
    val peminjam: String = "",
    val tanggalPinjam: String = "",
    val pilihanBuku: List<Buku> = daftarBukuContoh,
    val sedangMenyimpan: Boolean = false,
    val tersimpan: Boolean = false,
    val pesan: String? = null
) {
    /** Format tanggal yyyy-MM-dd, contoh 2026-10-25. */
    val tanggalValid: Boolean
        get() = formatTanggal.matches(tanggalPinjam)

    val bisaSimpan: Boolean
        get() = bukuDipilih != null &&
                peminjam.isNotBlank() &&
                tanggalValid &&
                !sedangMenyimpan
}

package org.example.project.data

import kotlinx.coroutines.flow.StateFlow
import org.example.project.model.Buku
import org.example.project.model.Peminjaman

/**
 * KONTRAK, DIBEKUKAN. Pemilik: Syahrul.
 * Jangan ubah tanda tangan tanpa kabar ke grup, karena ViewModel form,
 * ViewModel daftar, dan tes bergantung padanya.
 */
interface SumberDaftarPeminjaman {
    /** Daftar peminjaman, terbaru di atas. Setiap perubahan otomatis sampai ke layar. */
    val daftar: StateFlow<List<Peminjaman>>

    /**
     * Menambah satu peminjaman baru:
     * id = id terbesar + 1, status = Diajukan, tanggalKembali = null,
     * lalu daftar diurutkan lagi dari tanggalPinjam terbaru.
     */
    fun tambah(buku: Buku, peminjam: String, tanggalPinjam: String)
}

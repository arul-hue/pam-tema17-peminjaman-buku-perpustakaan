package org.example.project.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.model.Buku
import org.example.project.model.Peminjaman

/**
 * Pemilik: Jundi. Satu-satunya file data yang diisi Jundi untuk sumber daftar.
 * Jangan ubah SumberPeminjamanPalsu.kt milik minggu 3.
 */
class SumberDaftarPeminjamanPalsu(
    awal: List<Peminjaman> = daftarPeminjamanContoh
) : SumberDaftarPeminjaman {

    private val _daftar = MutableStateFlow(awal)
    override val daftar: StateFlow<List<Peminjaman>> = _daftar.asStateFlow()

    override fun tambah(buku: Buku, peminjam: String, tanggalPinjam: String) {
        // TODO Jundi: id berikutnya (maxOf id + 1), status Diajukan, tanggalKembali null,
        //  _daftar.update { ... }, urutkan lagi dari tanggalPinjam terbaru.
    }
}

/**
 * Satu instance bersama untuk seluruh aplikasi (belum ada dependency injection).
 * Form dan daftar HARUS memakai instance ini, kalau tidak data baru tidak muncul di daftar.
 * Nama properti ini DIBEKUKAN.
 */
val sumberDaftarPeminjaman: SumberDaftarPeminjaman = SumberDaftarPeminjamanPalsu()

package org.example.project.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.project.model.Buku
import org.example.project.model.Peminjaman
import org.example.project.model.StatusPeminjaman

/**
 * Pemilik: Jundi. Satu-satunya file data yang diisi Jundi untuk sumber daftar..
 * Jangan ubah SumberPeminjamanPalsu.kt milik minggu 3.
 */
class SumberDaftarPeminjamanPalsu(
    awal: List<Peminjaman> = daftarPeminjamanContoh
) : SumberDaftarPeminjaman {

    private val _daftar = MutableStateFlow(awal)
    override val daftar: StateFlow<List<Peminjaman>> = _daftar.asStateFlow()

    override fun tambah(buku: Buku, peminjam: String, tanggalPinjam: String) {
        // update { } aman dipanggil berulang: lambda diulang otomatis kalau ada perubahan bersamaan.
        _daftar.update { sekarang ->
            // id terbesar + 1 (bukan size + 1, supaya tetap unik walau id tidak berurutan)
            val idBaru = (sekarang.maxOfOrNull { it.id } ?: 0) + 1

            val baru = Peminjaman(
                id = idBaru,
                buku = buku,
                peminjam = peminjam,
                tanggalPinjam = tanggalPinjam,
                tanggalKembali = null,
                status = StatusPeminjaman.Diajukan
            )

            // Data baru ditaruh di depan sebelum diurutkan. sortedByDescending itu stabil,
            // jadi kalau tanggalnya sama dengan data lain, data baru tetap di atas.
            (listOf(baru) + sekarang).sortedByDescending { it.tanggalPinjam }
        }
    }
}

/**
 * Satu instance bersama untuk seluruh aplikasi (belum ada dependency injection).
 * Form dan daftar HARUS memakai instance ini, kalau tidak data baru tidak muncul di daftar.
 * Nama properti ini DIBEKUKAN.
 */
val sumberDaftarPeminjaman: SumberDaftarPeminjaman = SumberDaftarPeminjamanPalsu()

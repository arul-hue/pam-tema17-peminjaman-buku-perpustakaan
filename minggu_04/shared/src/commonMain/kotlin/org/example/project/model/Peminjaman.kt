package org.example.project.model

data class Peminjaman(
    val id: Int,
    val buku: Buku,
    val peminjam: String,
    val tanggalPinjam: String,
    val tanggalKembali: String?,   // satu-satunya nullable: kosong sampai buku dikembalikan
    val status: StatusPeminjaman = StatusPeminjaman.Diajukan
)
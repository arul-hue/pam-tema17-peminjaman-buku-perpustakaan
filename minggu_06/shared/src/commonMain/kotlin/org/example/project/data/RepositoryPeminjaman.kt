package org.example.project.data

import org.example.project.model.Buku
import org.example.project.model.Peminjaman

interface RepositoryPeminjaman {
    suspend fun semua(): List<Peminjaman>

    suspend fun ambil(id: Int): Peminjaman

    suspend fun tambah(
        buku: Buku,
        peminjam: String,
        tanggalPinjam: String
    ): Peminjaman
}
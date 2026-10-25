package org.example.project.model

sealed class StatusPeminjaman {
    data object Diajukan : StatusPeminjaman()
    data class Dipinjam(
        val petugas: String
    ) : StatusPeminjaman()
    data class Dikembalikan(
        val catatan: String
    ) : StatusPeminjaman()
}
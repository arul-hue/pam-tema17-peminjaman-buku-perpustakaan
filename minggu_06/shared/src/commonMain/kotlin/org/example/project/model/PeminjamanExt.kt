package org.example.project.model

fun StatusPeminjaman.label(): String = when (this) {
    is StatusPeminjaman.Diajukan -> "Menunggu persetujuan petugas"
    is StatusPeminjaman.Dipinjam -> "Dipinjamkan oleh $petugas"
    is StatusPeminjaman.Dikembalikan -> "Dikembalikan: $catatan"
}

fun Peminjaman.ringkas(): String = "#$id ${buku.judul}, dipinjam $peminjam"
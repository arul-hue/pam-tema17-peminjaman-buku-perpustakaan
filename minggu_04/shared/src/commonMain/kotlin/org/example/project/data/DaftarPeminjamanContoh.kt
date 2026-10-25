package org.example.project.data

import org.example.project.model.Buku
import org.example.project.model.Peminjaman
import org.example.project.model.StatusPeminjaman

// Data buku yang tersedia di perpustakaan
private val daftarBuku = listOf(
    Buku(1, "Pemrograman Kotlin", "Muhammad Habib Algifari", 2024),
    Buku(2, "Clean Code", "Robert C. Martin", 2008),
    Buku(3, "Design Patterns", "Gang of Four", 1994),
    Buku(4, "The Pragmatic Programmer", "Hunt & Thomas", 1999),
    Buku(5, "Refactoring", "Martin Fowler", 2018),
    Buku(6, "Introduction to Algorithms", "Cormen et al.", 2009),
    Buku(7, "Database Design", "C.J. Date", 2019),
    Buku(8, "Web Development with Django", "Wes McKinney", 2017),
    Buku(9, "Machine Learning Basics", "Andrew Ng", 2020),
    Buku(10, "Mobile Development Guide", "Satya Nadella", 2021)
)

// Pertemuan 4: daftar buku dibuka untuk pilihan buku di form dan sumber data baru.
// Hanya ini perubahan di file ini. Isi daftarBuku tidak diubah.
val daftarBukuContoh: List<Buku> get() = daftarBuku

// Data peminjaman dummy dengan variasi status
private fun buatDaftarPeminjamanDummy() = listOf(
    Peminjaman(
        id = 1,
        buku = daftarBuku[0],
        peminjam = "Jundi Lamtara",
        tanggalPinjam = "2026-10-20",
        tanggalKembali = null,
        status = StatusPeminjaman.Diajukan
    ),
    Peminjaman(
        id = 2,
        buku = daftarBuku[1],
        peminjam = "Syahrul Afwan",
        tanggalPinjam = "2026-10-19",
        tanggalKembali = null,
        status = StatusPeminjaman.Dipinjam("Bu Rina")
    ),
    Peminjaman(
        id = 3,
        buku = daftarBuku[2],
        peminjam = "Hafidz Raihan Putra Anfa",
        tanggalPinjam = "2026-10-18",
        tanggalKembali = "2026-10-25",
        status = StatusPeminjaman.Dikembalikan("Dikembalikan tepat waktu")
    ),
    Peminjaman(
        id = 4,
        buku = daftarBuku[3],
        peminjam = "Muhammad Rizki",
        tanggalPinjam = "2026-10-17",
        tanggalKembali = null,
        status = StatusPeminjaman.Dipinjam("Pak Ahmad")
    ),
    Peminjaman(
        id = 5,
        buku = daftarBuku[4],
        peminjam = "Ani Wijaya",
        tanggalPinjam = "2026-10-16",
        tanggalKembali = "2026-10-23",
        status = StatusPeminjaman.Dikembalikan("Buku dalam kondisi baik")
    ),
    Peminjaman(
        id = 6,
        buku = daftarBuku[5],
        peminjam = "Budi Santoso",
        tanggalPinjam = "2026-10-21",
        tanggalKembali = null,
        status = StatusPeminjaman.Diajukan
    ),
    Peminjaman(
        id = 7,
        buku = daftarBuku[6],
        peminjam = "Citra Dewi",
        tanggalPinjam = "2026-10-15",
        tanggalKembali = "2026-10-22",
        status = StatusPeminjaman.Dikembalikan("Ada catatan kecil halaman 45")
    ),
    Peminjaman(
        id = 8,
        buku = daftarBuku[7],
        peminjam = "Doni Hermawan",
        tanggalPinjam = "2026-10-14",
        tanggalKembali = null,
        status = StatusPeminjaman.Dipinjam("Bu Rina")
    ),
    Peminjaman(
        id = 9,
        buku = daftarBuku[8],
        peminjam = "Eka Putri",
        tanggalPinjam = "2026-10-13",
        tanggalKembali = "2026-10-24",
        status = StatusPeminjaman.Dikembalikan("Dikembalikan")
    ),
    Peminjaman(
        id = 10,
        buku = daftarBuku[9],
        peminjam = "Fajar Rahman",
        tanggalPinjam = "2026-10-12",
        tanggalKembali = null,
        status = StatusPeminjaman.Diajukan
    ),
    Peminjaman(
        id = 11,
        buku = daftarBuku[0],
        peminjam = "Gita Sari",
        tanggalPinjam = "2026-10-22",
        tanggalKembali = null,
        status = StatusPeminjaman.Diajukan
    ),
    Peminjaman(
        id = 12,
        buku = daftarBuku[2],
        peminjam = "Hendra Gunawan",
        tanggalPinjam = "2026-10-11",
        tanggalKembali = "2026-10-21",
        status = StatusPeminjaman.Dikembalikan("Buku agak kusut, dimohon perbaikan")
    )
)

/**
 * Daftar peminjaman yang sudah diolah dan siap ditampilkan.
 * Diurutkan berdasarkan tanggalPinjam terbaru di atas (sortedByDescending).
 * Ini memastikan peminjaman terbaru tampil di atas layar.
 */
val daftarPeminjamanContoh: List<Peminjaman> = buatDaftarPeminjamanDummy()
    .sortedByDescending { it.tanggalPinjam }
    .mapIndexed { index, peminjaman -> peminjaman.copy(id = index + 1) }
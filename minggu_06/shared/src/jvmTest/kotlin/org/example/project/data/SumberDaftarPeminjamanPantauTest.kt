package org.example.project.data

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.example.project.model.Buku
import org.example.project.model.Peminjaman
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * Tes pantau(id). Flow dibaca dengan first(), jadi perlu coroutine;
 * ditaruh di jvmTest memakai runBlocking supaya tidak perlu menambah library tes baru.
 * Setiap tes membuat instance sendiri, bukan singleton sumberDaftarPeminjaman.
 */
class SumberDaftarPeminjamanPantauTest {

    private val buku = Buku(1, "Pemrograman Kotlin", "Penulis A", 2024)
    private val bukuLain = Buku(2, "Clean Code", "Robert C. Martin", 2008)

    private fun pinjam(id: Int, tanggal: String) = Peminjaman(
        id = id,
        buku = buku,
        peminjam = "Peminjam $id",
        tanggalPinjam = tanggal,
        tanggalKembali = null
    )

    /** Id sengaja tidak berurutan: 3, 7, 5. */
    private fun sumberAwal() = SumberDaftarPeminjamanPalsu(
        awal = listOf(
            pinjam(3, "2026-10-20"),
            pinjam(7, "2026-10-15"),
            pinjam(5, "2026-10-10")
        )
    )

    @Test
    fun pantauMenemukanPeminjamanBerdasarkanId() = runBlocking {
        val hasil = sumberAwal().pantau(7).first()

        assertNotNull(hasil)
        assertEquals(7, hasil.id)
        assertEquals("Peminjam 7", hasil.peminjam)
    }

    @Test
    fun pantauMemakaiIdBukanPosisi() = runBlocking {
        val sumber = sumberAwal()

        // id 5 ada di posisi ke-3 (indeks 2), id 3 di indeks 0. Posisi 5 tidak ada.
        assertEquals("Peminjam 5", sumber.pantau(5).first()?.peminjam)
        assertEquals("Peminjam 3", sumber.pantau(3).first()?.peminjam)
        assertNull(sumber.pantau(0).first())
        assertNull(sumber.pantau(1).first())
    }

    @Test
    fun idTidakAdaMenghasilkanNullTanpaDataPengganti() = runBlocking {
        val sumber = sumberAwal()
        val sebelum = sumber.daftar.value

        assertNull(sumber.pantau(99).first())
        assertEquals(sebelum, sumber.daftar.value)
    }

    @Test
    fun peminjamanBaruDariTambahBisaDibukaDetailnya() = runBlocking {
        val sumber = sumberAwal()

        sumber.tambah(bukuLain, "Jundi", "2026-10-25")

        val hasil = sumber.pantau(8).first() // id terbesar 7, jadi id baru 8
        assertNotNull(hasil)
        assertEquals("Jundi", hasil.peminjam)
        assertEquals(bukuLain, hasil.buku)
    }

    @Test
    fun idTidakBerubahSetelahDaftarDiurutkanUlang() = runBlocking {
        val sumber = sumberAwal()
        val sebelum = sumber.pantau(7).first()

        // tanggal lebih baru memaksa daftar diurutkan ulang
        sumber.tambah(bukuLain, "Jundi", "2026-10-30")

        assertEquals(sebelum, sumber.pantau(7).first())
    }

    @Test
    fun sumberDataContohMembukaKartuYangSama() = runBlocking {
        val sumber = SumberDaftarPeminjamanPalsu()

        // setiap kartu di daftar harus bisa dibuka dengan id-nya dan isinya identik
        sumber.daftar.value.forEach { kartu ->
            assertEquals(kartu, sumber.pantau(kartu.id).first())
        }
    }
}

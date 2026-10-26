package org.example.project.data

import org.example.project.model.Buku
import org.example.project.model.Peminjaman
import org.example.project.model.StatusPeminjaman
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * Tes untuk SumberDaftarPeminjamanPalsu.tambah()..
 * Setiap tes membuat instance sendiri (BUKAN singleton sumberDaftarPeminjaman),
 * supaya tes tidak saling memengaruhi. tambah() bukan suspend, jadi tanpa coroutine.
 */
class SumberDaftarPeminjamanPalsuTest {

    private val buku = Buku(1, "Pemrograman Kotlin", "Penulis A", 2024)
    private val bukuLain = Buku(2, "Clean Code", "Robert C. Martin", 2008)

    private fun pinjam(id: Int, tanggal: String) = Peminjaman(
        id = id,
        buku = buku,
        peminjam = "Peminjam $id",
        tanggalPinjam = tanggal,
        tanggalKembali = null
    )

    /** Daftar awal terurut menurun dengan id sengaja tidak berurutan: 3, 7, 5. */
    private fun sumberAwal() = SumberDaftarPeminjamanPalsu(
        awal = listOf(
            pinjam(3, "2026-10-20"),
            pinjam(7, "2026-10-15"),
            pinjam(5, "2026-10-10")
        )
    )

    @Test
    fun idBerikutnyaAdalahIdTerbesarTambahSatu() {
        val sumber = sumberAwal()

        sumber.tambah(bukuLain, "Jundi", "2026-10-25")

        // id terbesar 7, jadi 8 (bukan size + 1 = 4)
        assertEquals(8, sumber.daftar.value.maxOf { it.id })
        assertEquals(8, sumber.daftar.value.first { it.peminjam == "Jundi" }.id)
    }

    @Test
    fun idPertamaPadaDaftarKosongAdalahSatu() {
        val sumber = SumberDaftarPeminjamanPalsu(awal = emptyList())

        sumber.tambah(buku, "Jundi", "2026-10-25")

        assertEquals(1, sumber.daftar.value.single().id)
    }

    @Test
    fun idTetapUnikSetelahBeberapaKaliTambah() {
        val sumber = sumberAwal()

        repeat(5) { sumber.tambah(buku, "Peminjam baru $it", "2026-10-2$it") }

        val ids = sumber.daftar.value.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
        assertEquals(8, ids.size)
    }

    @Test
    fun dataBaruAdaDiUrutanPalingAtas() {
        val sumber = sumberAwal()

        sumber.tambah(bukuLain, "Jundi", "2026-10-25")

        val pertama = sumber.daftar.value.first()
        assertEquals("Jundi", pertama.peminjam)
        assertEquals("2026-10-25", pertama.tanggalPinjam)
    }

    @Test
    fun dataBaruDenganTanggalSamaTetapDiAtasDataLama() {
        val sumber = sumberAwal()

        // tanggal sama dengan data teratas yang sudah ada (2026-10-20)
        sumber.tambah(bukuLain, "Jundi", "2026-10-20")

        assertEquals("Jundi", sumber.daftar.value.first().peminjam)
    }

    @Test
    fun daftarTetapTerurutDariTanggalTerbaruWalauTanggalBaruLebihLama() {
        val sumber = sumberAwal()

        // tanggal paling lama, harus jatuh di urutan terakhir
        sumber.tambah(bukuLain, "Jundi", "2026-10-01")

        val tanggal = sumber.daftar.value.map { it.tanggalPinjam }
        assertEquals(tanggal.sortedDescending(), tanggal)
        assertEquals("Jundi", sumber.daftar.value.last().peminjam)
    }

    @Test
    fun jumlahDaftarBertambahSatu() {
        val sumber = sumberAwal()
        val sebelum = sumber.daftar.value.size

        sumber.tambah(buku, "Jundi", "2026-10-25")

        assertEquals(sebelum + 1, sumber.daftar.value.size)
    }

    @Test
    fun peminjamanBaruBerstatusDiajukanDanBelumDikembalikan() {
        val sumber = sumberAwal()

        sumber.tambah(bukuLain, "Jundi", "2026-10-25")

        val baru = sumber.daftar.value.first()
        assertEquals(StatusPeminjaman.Diajukan, baru.status)
        assertNull(baru.tanggalKembali)
        assertEquals(bukuLain, baru.buku)
    }

    @Test
    fun dataLamaTidakBerubah() {
        val sumber = sumberAwal()
        val lama = sumber.daftar.value

        sumber.tambah(buku, "Jundi", "2026-10-25")

        assertTrue(sumber.daftar.value.containsAll(lama))
    }
}

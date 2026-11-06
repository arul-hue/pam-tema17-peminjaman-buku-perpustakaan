package org.example.project.data

import org.example.project.model.StatusPeminjaman
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** Pertemuan 5: pengurutan data contoh tidak boleh mengganti id peminjaman. */
class DaftarPeminjamanContohTest {

    @Test
    fun idUnikDanTetapSatuSampaiDuaBelas() {
        val ids = daftarPeminjamanContoh.map { it.id }
        assertEquals(12, ids.size)
        assertEquals((1..12).toSet(), ids.toSet())
    }

    @Test
    fun idAsliTidakBerubahSetelahPengurutan() {
        // id 1 tetap milik Jundi (buku Pemrograman Kotlin), bukan data yang kebetulan tampil paling atas
        val satu = daftarPeminjamanContoh.first { it.id == 1 }
        assertEquals("Jundi Lamtara", satu.peminjam)
        assertEquals("Pemrograman Kotlin", satu.buku.judul)
        assertEquals(StatusPeminjaman.Diajukan, satu.status)

        val tiga = daftarPeminjamanContoh.first { it.id == 3 }
        assertEquals("Hafidz Raihan Putra Anfa", tiga.peminjam)
        assertEquals("Design Patterns", tiga.buku.judul)
    }

    @Test
    fun daftarTetapTerurutDariTanggalTerbaru() {
        val tanggal = daftarPeminjamanContoh.map { it.tanggalPinjam }
        assertEquals(tanggal.sortedDescending(), tanggal)
        // id tidak lagi berurutan di layar, dan itu wajar
        assertTrue(daftarPeminjamanContoh.map { it.id } != (1..12).toList())
    }
}

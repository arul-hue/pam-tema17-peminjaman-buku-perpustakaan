package org.example.project.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.project.model.Buku
import org.example.project.model.Peminjaman
import org.example.project.model.StatusPeminjaman

fun contohPeminjaman(id: Int) = Peminjaman(
    id = id,
    buku = Buku(
        id = 1,
        judul = "Pemrograman Kotlin",
        penulis = "Penulis A",
        tahunTerbit = 2024
    ),
    peminjam = "Syehabudin",
    tanggalPinjam = "2026-10-20",
    tanggalKembali = null
)

class SumberPeminjamanPalsu : Sumber<Peminjaman> {

    override suspend fun ambil(id: Int): Peminjaman {
        delay(1000)
        return contohPeminjaman(id)
    }

    override fun pantau(id: Int): Flow<Peminjaman> = flow {
        val awal = contohPeminjaman(id)

        emit(awal)

        delay(1500)
        emit(awal.copy(status = StatusPeminjaman.Dipinjam("Bu Rina")))

        delay(2000)
        emit(
            awal.copy(
                tanggalKembali = "2026-10-27",
                status = StatusPeminjaman.Dikembalikan("Dikembalikan tepat waktu")
            )
        )
    }
}

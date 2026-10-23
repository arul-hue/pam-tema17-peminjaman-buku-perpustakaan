# Tema 17: Peminjaman Buku Perpustakaan

## Deskripsi Singkat Tema

Aplikasi peminjaman buku perpustakaan membantu mahasiswa mengajukan peminjaman
buku dan memantau statusnya, sehingga tidak perlu bertanya langsung ke petugas.
Setiap peminjaman melewati tiga tahap: **diajukan**, **dipinjam** (disetujui
petugas), lalu **dikembalikan**.

## Fokus Pengerjaan Pertemuan 2: Model Data, Coroutines, dan Flow

Membuat model data peminjaman (`Buku`, `Peminjaman`, dan status), lalu sumber data
palsu dengan Coroutines dan Flow untuk mensimulasikan perubahan status, dibuktikan
lewat keluaran konsol. Data masih berupa data palsu (dummy), belum terhubung ke server.

## Anggota Kelompok

| No  | Nama                     | NIM       | Peran          |
| --- | ------------------------ | --------- | -------------- |
| 1   | Syahrul Afwan            | 124140096 | Ketua Kelompok |
| 2   | Hafidz Raihan Putra Anfa | 124140108 | Anggota        |
| 3   | Jundi Lamtara            | 124140190 | Anggota        |

## Pembagian Kerja

| Bagian                                                     | Penanggung jawab         |
| ---------------------------------------------------------- | ------------------------ |
| Model data: 2 data class dan 1 sealed class status         | Syahrul Afwan            |
| Sumber data palsu dengan `delay` dan Flow perubahan status | Hafidz Raihan Putra Anfa |
| Bukti berjalan: keluaran konsol dan README                 | Jundi Lamtara            |

## 1. Model Data

Lokasi: `shared/src/commonMain/kotlin/org/example/project/model/`

| File                  | Isi                                            |
| --------------------- | ---------------------------------------------- |
| `Buku.kt`             | `data class Buku` (entitas pendukung)          |
| `Peminjaman.kt`       | `data class Peminjaman` (entitas utama)        |
| `StatusPeminjaman.kt` | `sealed class StatusPeminjaman` dengan 3 nilai |
| `PeminjamanExt.kt`    | extension function `label()` dan `ringkas()`   |

### Entitas

**Buku** (pendukung): `id: Int`, `judul: String`, `penulis: String`, `tahunTerbit: Int`

**Peminjaman** (utama): `id: Int`, `buku: Buku`, `peminjam: String`,
`tanggalPinjam: String`, `tanggalKembali: String?`, `status: StatusPeminjaman`

`tanggalKembali` adalah satu-satunya kolom nullable, karena buku yang belum
dikembalikan memang belum punya tanggal kembali.

### Status Peminjaman (sealed class)

| Status         | Data yang dibawa  | Arti                                   |
| -------------- | ----------------- | -------------------------------------- |
| `Diajukan`     | tidak ada         | Menunggu persetujuan petugas           |
| `Dipinjam`     | `petugas: String` | Disetujui dan dipinjamkan oleh petugas |
| `Dikembalikan` | `catatan: String` | Buku sudah dikembalikan                |

Alur status: `Diajukan` -> `Dipinjam` -> `Dikembalikan`.

## 2. Sumber Data Palsu

Lokasi: `shared/src/commonMain/kotlin/org/example/project/data/`

| File                       | Isi                                                                                     |
| -------------------------- | --------------------------------------------------------------------------------------- |
| `Sumber.kt`                | `interface Sumber<T>` dengan `suspend fun ambil(id)` dan `fun pantau(id): Flow<T>`      |
| `SumberPeminjamanPalsu.kt` | Implementasi `Sumber<Peminjaman>` dengan data palsu, plus fungsi `contohPeminjaman(id)` |

| Fungsi       | Perilaku                                                                                                                                                                          |
| ------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `ambil(id)`  | `delay` 1 detik, lalu mengembalikan satu peminjaman contoh                                                                                                                        |
| `pantau(id)` | Flow yang memancarkan tiga status bertahap: `Diajukan` (langsung), `Dipinjam` oleh Bu Rina (setelah 1,5 detik), lalu `Dikembalikan` dengan tanggal kembali (setelah 2 detik lagi) |

## 3. Bukti Berjalan

Lokasi: `DemoStatus.kt` (`shared/.../data/`) dan `DemoMain.kt` (`shared/src/jvmMain/`).
`main` memanggil `demoStatus()` di dalam `runBlocking`, yang meng-collect Flow
`pantau` dan mencetak `label()` tiap status.

### Contoh Keluaran Konsol

Format keluaran (tiap baris muncul bertahap karena ada `delay`):

```
Sebelum collect
Menunggu persetujuan petugas              <- status Diajukan (langsung)
Dipinjamkan oleh Bu Rina                  <- status Dipinjam (sekitar 1,5 detik kemudian)
Dikembalikan: Dikembalikan tepat waktu    <- status Dikembalikan (sekitar 2 detik kemudian)
Sesudah collect
```

| Baris keluaran                 | Berasal dari                                                  |
| ------------------------------ | ------------------------------------------------------------- |
| `Menunggu persetujuan petugas` | `StatusPeminjaman.Diajukan.label()`                           |
| `Dipinjamkan oleh ...`         | `StatusPeminjaman.Dipinjam(petugas).label()`                  |
| `Dikembalikan: ...`            | `StatusPeminjaman.Dikembalikan(catatan).label()`              |
| `Sesudah collect`              | tercetak setelah Flow selesai, karena `collect` ikut menunggu |

**Keluaran asli dari hasil menjalankan program:**

```
Sebelum collect
Menunggu persetujuan petugas
Dipinjamkan oleh Bu Rina
Dikembalikan: Dikembalikan tepat waktu
Sesudah collect
```

## Cara Menjalankan

Buka proyek di Android Studio, lalu jalankan di terminal:

```powershell
.\gradlew.bat :shared:compileKotlinJvm
```

Pastikan dependency coroutines ada di `commonMain` modul `shared`:

```kotlin
implementation(libs.kotlinx.coroutines.core)
```

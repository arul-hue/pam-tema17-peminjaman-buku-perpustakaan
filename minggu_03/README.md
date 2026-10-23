# Tema 17: Peminjaman Buku Perpustakaan

## Deskripsi Singkat Tema

Aplikasi peminjaman buku perpustakaan membantu mahasiswa mengajukan peminjaman
buku dan memantau statusnya. Setiap peminjaman melewati tiga tahap: **diajukan**,
**dipinjam** (disetujui petugas), lalu **dikembalikan**. Pada checkpoint ini
data masih berupa data palsu (dummy), belum terhubung ke server.

## Anggota Kelompok

| No | Nama                    | NIM       | Peran           |
|----|-------------------------|-----------|-----------------|
| 1  | Syahrul Afwan           | 124140096 | Ketua Kelompok  |
| 2  | Hafidz Raihan Putra Anfa | 124140108 | Anggota         |
| 3  | Jundi Lamtara           | 124140190 | Anggota         |

## Pembagian Kerja

| Checkpoint | Bagian | Penanggung jawab | Status |
|------------|--------|------------------|--------|
| 1 | Model data tema: 2 data class + 1 sealed class status di `commonMain/model` | Syahrul Afwan | Selesai |
| 2 | Sumber data palsu: implementasi `Sumber` untuk entitas utama, dengan `delay` dan Flow perubahan status | Hafidz Raihan Putra Anfa | Belum |
| 3 | Bukti berjalan: fungsi `main` yang mencetak perubahan status ke konsol, dan README | Jundi Lamtara | Belum |

## Checkpoint 1: Model Data

Lokasi: `shared/src/commonMain/kotlin/org/example/project/model/`

| File | Isi |
|------|-----|
| `Buku.kt` | `data class Buku` (entitas pendukung) |
| `Peminjaman.kt` | `data class Peminjaman` (entitas utama) |
| `StatusPeminjaman.kt` | `sealed class StatusPeminjaman` dengan 3 nilai |
| `PeminjamanExt.kt` | extension function `label()` dan `ringkas()` |

### Entitas

**Buku** (pendukung): `id: Int`, `judul: String`, `penulis: String`, `tahunTerbit: Int`

**Peminjaman** (utama): `id: Int`, `buku: Buku`, `peminjam: String`,
`tanggalPinjam: String`, `tanggalKembali: String?`, `status: StatusPeminjaman`

`tanggalKembali` adalah satu-satunya kolom nullable, karena buku yang belum
dikembalikan memang belum punya tanggal kembali.

### Status Peminjaman (sealed class)

| Status | Data yang dibawa | Arti |
|--------|------------------|------|
| `Diajukan` | tidak ada | Menunggu persetujuan petugas |
| `Dipinjam` | `petugas: String` | Disetujui dan dipinjamkan oleh petugas |
| `Dikembalikan` | `catatan: String` | Buku sudah dikembalikan |

Alur status: `Diajukan` -> `Dipinjam` -> `Dikembalikan`.

## Checkpoint 2: Sumber Data Palsu

_Belum dikerjakan. Akan diisi oleh Hafidz Raihan Putra Anfa._

Rencana: `interface Sumber<T>` dan `SumberPeminjamanPalsu` di package
`org.example.project.data`, memakai `delay` dan Flow yang memancarkan
perubahan status peminjaman.

## Checkpoint 3: Bukti Berjalan

_Belum dikerjakan. Akan diisi oleh Jundi Lamtara._

### Contoh Keluaran Konsol

_Template di bawah adalah format yang diharapkan, berdasarkan fungsi `label()`
di model. Setelah `Main.kt` selesai dan dijalankan, ganti dengan keluaran konsol
yang asli._

Format yang diharapkan (tiap baris muncul bertahap karena ada `delay`):

```
Sebelum collect
Menunggu persetujuan petugas              <- status Diajukan (langsung)
Dipinjamkan oleh <nama petugas>           <- status Dipinjam (sekitar 1,5 detik kemudian)
Dikembalikan: <isi catatan>               <- status Dikembalikan (sekitar 2 detik kemudian)
Sesudah collect
```

Keterangan:

| Baris keluaran | Berasal dari |
|----------------|--------------|
| `Menunggu persetujuan petugas` | `StatusPeminjaman.Diajukan.label()` |
| `Dipinjamkan oleh ...` | `StatusPeminjaman.Dipinjam(petugas).label()` |
| `Dikembalikan: ...` | `StatusPeminjaman.Dikembalikan(catatan).label()` |
| `Sesudah collect` | tercetak setelah Flow selesai, karena `collect` ikut menunggu |

**Keluaran asli dari hasil menjalankan program:**

```
(tempel keluaran konsol yang asli di sini)
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
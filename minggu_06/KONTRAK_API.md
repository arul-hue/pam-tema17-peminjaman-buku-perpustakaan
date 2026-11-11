# Kontrak API Peminjaman Buku — Minggu 6

Aplikasi memakai mock REST API untuk mengambil daftar, membuka detail, dan menyimpan peminjaman. Request dan respons memakai JSON. Base URL diisi setelah mock API siap.

## Endpoint

| Method | Endpoint | Respons |
|---|---|---|
| `GET` | `/peminjaman` | `200 OK` dengan array peminjaman. Jika kosong, responsnya `[]`. |
| `GET` | `/peminjaman/{id}` | `200 OK` dengan satu peminjaman. Jika ID transaksi tidak ditemukan, responsnya `404 Not Found`. |
| `POST` | `/peminjaman` | `201 Created` dengan data lengkap transaksi yang baru disimpan. Jika isian tidak valid atau ID buku tidak dikenal, responsnya `400 Bad Request`. |

## Request untuk tambah peminjaman

Form mengirim `bukuId`, `peminjam`, dan `tanggalPinjam` dengan format `yyyy-MM-dd`.

```json
{
  "bukuId": 1,
  "peminjam": "Mahasiswa Contoh",
  "tanggalPinjam": "2026-11-11"
}
```

`bukuId` mengikuti ID buku yang dipilih di Form. Server membuat ID transaksi dan menyimpan peminjaman baru dengan status `Diajukan` serta `tanggalKembali` bernilai `null`.

## Contoh respons satu peminjaman

```json
{
  "id": 13,
  "buku": {
    "id": 1,
    "judul": "Pemrograman Kotlin",
    "penulis": "Penulis A",
    "tahunTerbit": 2024
  },
  "peminjam": "Mahasiswa Contoh",
  "tanggalPinjam": "2026-11-11",
  "tanggalKembali": null,
  "status": "Diajukan"
}
```

- `id` di bagian luar adalah ID peminjaman. Daftar memakai ID ini saat membuka `RuteDetail(id)`.
- `buku.id` adalah ID buku. Buku yang sama bisa muncul di beberapa transaksi dengan ID peminjaman berbeda.
- `status` berisi `Diajukan`, `Dipinjam`, atau `Dikembalikan`. Respons menyertakan `petugas` untuk status `Dipinjam` dan `catatan` untuk status `Dikembalikan`. Kedua field berada sejajar dengan `status` dan boleh dihilangkan jika tidak relevan.

## Pemakaian di aplikasi

- Daftar memakai `RepositoryPeminjaman.semua()`.
- Detail memakai `RepositoryPeminjaman.ambil(id)`.
- Form memakai `RepositoryPeminjaman.tambah(buku, peminjam, tanggalPinjam)`.

Setelah `POST` berhasil, aplikasi kembali ke Daftar dan memuat ulang data. Transaksi yang baru disimpan harus bisa dibaca dari `GET /peminjaman` dan `GET /peminjaman/{id}`.
# Tema 17: Peminjaman Buku Perpustakaan

Proyek kelompok mata kuliah **Pengembangan Aplikasi Mobile (IF25-22017)**,
Teknik Informatika, Institut Teknologi Sumatera (ITERA).

## Deskripsi Singkat Tema

Aplikasi ini bertujuan membantu mahasiswa meminjam buku perpustakaan tanpa antre
dan tanpa bertanya berulang kali ke petugas. Pengguna dapat melihat daftar buku,
mengajukan peminjaman, dan memantau statusnya sampai buku dikembalikan.

## Anggota Kelompok

| No  | Nama                     | NIM       | Peran          |
| --- | ------------------------ | --------- | -------------- |
| 1   | Syahrul Afwan            | 124140096 | Ketua Kelompok |
| 2   | Hafidz Raihan Putra Anfa | 124140108 | Anggota        |
| 3   | Jundi Lamtara            | 124140190 | Anggota        |

## Progres Pengerjaan Proyek

| Minggu | Topik                                                | Hasil                                                                           | Detail                                 |
| :----: | ---------------------------------------------------- | ------------------------------------------------------------------------------- | -------------------------------------- |
|   1    | Setup Proyek KMP dan Tampilan Informasi Kelompok     | Proyek KMP berjalan di Android dan Desktop, menampilkan tema dan platform       | [README Minggu 1](minggu_01/README.md) |
|   2    | Model Data Peminjaman dan Perubahan Status           | Model `Buku`, `Peminjaman`, dan status; sumber data palsu dengan Flow           | [README Minggu 2](minggu_02/README.md) |
|   3    | Dasar Compose Multiplatform: Layar Daftar Peminjaman | Layar daftar peminjaman dengan 12 data contoh dan label status berwarna         | [README Minggu 3](minggu_03/README.md) |
|   4    | State dan MVVM: Form Tambah Peminjaman               | Form tambah dengan ViewModel dan StateFlow; data baru langsung muncul di daftar | [README Minggu 4](minggu_04/README.md) |

Pembagian kerja, tangkapan layar, dan cara menjalankan aplikasi ada di README
folder minggu masing-masing.

## Teknologi

Kotlin Multiplatform dan Compose Multiplatform, dengan target Android dan Desktop (JVM).
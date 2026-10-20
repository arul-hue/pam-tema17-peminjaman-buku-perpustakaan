# Peminjaman Buku Perpustakaan

**Tema 17** | Proyek kelompok mata kuliah **Pengembangan Aplikasi Mobile (IF25-22017)**,
Teknik Informatika, Institut Teknologi Sumatera (ITERA).

Dibangun dengan **Kotlin Multiplatform (KMP)** dan **Compose Multiplatform**
untuk platform Android dan Desktop.

---

## Tentang Tema

**Nama tema:** Peminjaman Buku Perpustakaan

Aplikasi ini membantu mahasiswa meminjam buku perpustakaan tanpa antre dan tanpa
bertanya berulang kali ke petugas. Pengguna bisa melihat daftar buku, mengajukan
peminjaman, lalu memantau statusnya sampai buku dikembalikan.

Setiap peminjaman melewati tiga status:

| Status       | Arti                                              |
| ------------ | ------------------------------------------------- |
| Diajukan     | Pengajuan masuk, menunggu persetujuan petugas     |
| Dipinjam     | Disetujui dan dipinjamkan oleh petugas tertentu   |
| Dikembalikan | Buku sudah dikembalikan, ada catatan dari petugas |

Data utama aplikasi: **Peminjaman** (siapa meminjam, kapan, statusnya) dan
**Buku** (judul, penulis, tahun terbit).

---

## Anggota Kelompok

| No  | Nama                     | NIM       | Peran          |
| --- | ------------------------ | --------- | -------------- |
| 1   | Syahrul Afwan            | 124140096 | Ketua Kelompok |
| 2   | Hafidz Raihan Putra Anfa | 124140108 | Anggota        |
| 3   | Jundi Lamtara            | 124140190 | Anggota        |

---

## Progres Pengerjaan Proyek

| Minggu | Topik / Fitur                                  | Detail                        |
| :----: | ---------------------------------------------- | ----------------------------- |
|   1    | Setup Proyek KMP & Tampilan Informasi Kelompok | [README](minggu_01/README.md) |
|   2    | Model Data Peminjaman & Perubahan Status       | [README](minggu_02/README.md) |

Pembagian kerja dan bukti berjalan tiap pertemuan ada di README folder minggu
masing-masing.

---

## Tampilan Aplikasi

|                    Android                    |                   Desktop                    |
| :-------------------------------------------: | :------------------------------------------: |
| ![Android Screenshot](asset/ss1_android.jpeg) | ![Desktop Screenshot](asset/ss2_desktop.png) |

---

## Cara Menjalankan Proyek

Jalankan dari folder root proyek. Di Windows (PowerShell) pakai `.\gradlew.bat`,
di Mac atau Linux pakai `./gradlew`.

### 1. Desktop (JVM)

```powershell
.\gradlew.bat :desktopApp:run
```

### 2. Android

Jalankan modul `androidApp` lewat Android Studio, atau lewat terminal:

```powershell
.\gradlew.bat :androidApp:installDebug
```

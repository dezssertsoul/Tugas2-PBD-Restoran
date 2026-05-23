# 🍳 Aplikasi Manajemen & Pemesanan Restoran - Mamah Inah

Aplikasi berbasis karakter (Console/Terminal) yang dibangun menggunakan bahasa pemrograman **Java**.
Proyek ini dibuat untuk memenuhi **Tugas Praktik 2** pada mata kuliah **Pemrograman Berbasis Dekstop (STSI4201)** di Universitas Terbuka Jakarta.

Program ini dirancang secara modular menggunakan *methods* serta menerapkan manipulasi struktur data Array secara dinamis untuk menyimulasikan sistem transaksi pelanggan dan pengelolaan menu oleh pemilik restoran (*owner*).

---

## 📌 Identitas Mahasiswa
* **Nama:** Reza Rahmawati
* **NIM:** 053149057
* **Program Studi:** Sistem Informasi
* **Fakultas:** Sains dan Teknologi
* **UPBJJ:** UT Jakarta

---

## 🚀 Fitur Utama Sistem

### 👤 1. Menu Pelanggan (Pemesanan)
* **Pemesanan Tanpa Batas:** Pelanggan dapat memasukkan pesanan secara berulang sampai mengetik kata kunci `selesai`.
* **Validasi Input (Anti-Crash):** Dilengkapi dengan blok penanganan eror (*Exception Handling* `try-catch`) sehingga jika terjadi kesalahan input (seperti typo huruf atau nomor menu tidak terdaftar), sistem tidak akan *crash*, melainkan memunculkan peringatan.
* **Kalkulasi Promo Otomatis:** * Potongan **Diskon 10%** otomatis aktif jika subtotal belanja di atas Rp 100.000,-
  * Penawaran khusus **Beli 1 Gratis 1 Minuman** otomatis aktif jika total belanja di atas Rp 50.000,-
* **Format Struk Rapi:** Nota belanja dicetak menggunakan pembatas ribuan standar (`Rp 20.000`) agar mudah dibaca.

### 🔑 2. Menu Pengelolaan (Dashboard Owner)
* **Tambah Menu Baru:** Menambahkan item makanan/minuman baru ke dalam slot array secara dinamis.
* **Ubah Harga Menu:** Memperbarui tarif harga menu berdasarkan nomor indeks pilihan.
* **Hapus Menu (*Delete*):** Menghapus menu dari daftar menggunakan algoritma pergeseran indeks array manual (*Shifting Array Data*).
* **Sistem Konfirmasi:** Operasi sensitif (ubah harga & hapus) dilindungi konfirmasi validasi `Ya/Tidak` sebelum dieksekusi ke sistem.

---

## 🛠️ Struktur Berkas & Alur Logika Kode

Berkas utama program terletak pada file [Main.java](./Main.java) dengan pembagian struktur fungsi sebagai berikut:

| Nama Fungsi (*Method*) | Deskripsi Tanggung Jawab / Logika |
| :--- | :--- |
| `class Menu` | *Blueprint* objek untuk merepresentasikan item menu (Nama, Harga, Kategori). |
| `main()` | Titik awal program untuk memuat data awal (*load 8 menu*) dan memanggil menu utama. |
| `menuUtama()` | Mengendalikan perulangan `while(true)` dan navigasi bercabang (Pelanggan vs Admin). |
| `cetakDaftarMenu()` | Menampilkan seluruh menu aktif yang dikelompokkan berdasarkan kategori secara rapi. |
| `prosesPemesanan()` | Menampung transaksi belanja pelanggan dan mengamankan input dari typo. |
| `hitungStrukAkhir()` | Melakukan kalkulasi subtotal, diskon, pajak 10%, biaya pelayanan Rp 20.000, lalu mencetak nota. |
| `kelolaMenuAdmin()` | Menampilkan dashboard CRUD khusus untuk pemilik restoran. |
| `tambahMenu()` | Memasukkan item baru dan menaikkan *counter* jumlah data aktif di array. |
| `editHarga()` & `deleteMenu()` | Memanipulasi isi data array setelah melewati validasi input kata `Ya`. |

---

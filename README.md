# Responsi 2 Struktur Data dan Algoritma

## Nama Anggota Kelompok 4:

1. Reyhan Diandra Alvaro L0125029
2. Muhammad Naufal Albany L0125053
3. Yura Aling Vatsabel Putri Andicha L0125093

---

## Sistem Pemutar Musik Berbasis GUI

Sebuah pemutar musik berbasis **Java GUI** yang dikembangkan sebagai implementasi mata kuliah Struktur Data dan Algoritma. Sistem ini dirancang untuk membantu pengguna dalam mengelola koleksi lagu, melakukan pengelompokan data, serta memutar musik melalui antarmuka grafis yang sederhana dan mudah digunakan.

---

## Deskripsi Proyek

**Judul Proyek** : ALGORYTHM

Aplikasi ini merupakan sistem pemutar musik berbasis desktop yang memanfaatkan konsep struktur data untuk mengelola data lagu secara efisien. Pengguna dapat menambahkan, mengubah, menghapus, mengelompokkan, serta memutar lagu melalui antarmuka grafis (GUI). Struktur data seperti **ArrayList**, **HashMap**, dan **TreeMap** digunakan agar proses pengelolaan data lebih cepat, terstruktur, dan mudah dipelihara.

---

## Fitur Utama

1. Manajemen Data Lagu
   Sistem menyediakan fitur untuk mengelola koleksi lagu yang tersimpan di dalam aplikasi.

Fitur yang tersedia:

- Menampilkan seluruh koleksi lagu.
- Menambahkan lagu baru.

2. Pengelompokan Lagu

Data lagu dapat dikelompokkan berdasarkan:

- Genre
- Penyanyi
- Album

Hasil pengelompokan ditampilkan secara terurut sehingga memudahkan pengguna dalam melihat koleksi musik.

3. Sistem Pemutar Musik

Fitur pemutar musik memungkinkan pengguna untuk:

- Memutar lagu.
- Memutar lagu sebelumnya.
- Memutar lagu setelahnya.
- Menambahkan lagu ke dalam antrean.
- Melihat daftar putar lagu (history).

4. Antarmuka Grafis (GUI)

Aplikasi menggunakan Java Swing sehingga seluruh fitur dapat diakses melalui tampilan visual yang mudah dipahami tanpa harus menggunakan terminal.

---

## Struktur Data yang Digunakan

**ArrayList<Lagu>**
Fungsi : Menyimpan seluruh daftar lagu
Kompleksitas : O(1)
Alasan pemilihan : Mudah digunakan untuk menyimpan data secara berurutan dan mendukung proses pengurutan.

**HashMap<String, Lagu>**
Fungsi : Membantu pencarian lagu
Kompleksitas : O(1) (rata-rata)
Alasan pemilihan : Memanfaatkan konsep key-value sehingga proses pencarian data lebih cepat dibanding pencarian linear.

**TreeMap<String, List<Lagu>>**
Fungsi : Mengelompokkan lagu berdasarkan genre, penyanyi, dan album
Kompleksitas : O(log n)
Alasan pemilihan : Menyimpan data dalam keadaan terurut sehingga hasil pengelompokan menjadi lebih rapi.

---

# Algoritma yang Digunakan

Sorting: Digunakan untuk mengurutkan daftar lagu agar informasi ditampilkan secara lebih terstruktur.

---

## Cara Menjalankan Program

## Persyaratan

- Java Development Kit (JDK) versi 11 atau lebih baru.
- Visual Studio Code.

## Langkah-langkah

Buka VS Code.
Tekan Ctrl + Shift + P.
Ketik Git: Clone lalu pilih. 4. Tempel URL repository GitHub -> https://github.com/reyvarvar/Responsi_SDA_Kelompok-4 5. Pilih lokasi penyimpanan di komputer. 6. Tunggu proses clone selesai. 7. Akan muncul notifikasi "Open Repository", lalu klik Open.

---

## Alur Penggunaan

1. Jalankan aplikasi.
2. GUI utama akan ditampilkan.
3. Tambahkan data lagu apabila diinginkan.
4. Pilih lagu yang ingin diputar.
5. Menambahkan lagu ke antrean.
6. Kelola data lagu sesuai kebutuhan.

---

## Library yang Digunakan

Program dikembangkan menggunakan library standar Java tanpa library eksternal.

java.util.List -> Menyimpan kumpulan data lagu yang digunakan dalam aplikasi. 2. java.util.Map -> Menyimpan data lagu dalam bentuk pasangan _key-value_ untuk mempermudah pengelolaan dan pengelompokan data. 3. java.util.Random -> Menghasilkan nilai acak, misalnya untuk memilih lagu secara acak atau fitur lain yang memerlukan proses randomisasi. 4. javax.swing -> Membangun antarmuka GUI. 5. java.awt -> Komponen tampilan GUI.

---

## Tujuan Pengembangan

- Mengimplementasikan konsep Struktur Data pada aplikasi desktop.
- Menerapkan penggunaan ArrayList, HashMap, dan TreeMap.
- Mengembangkan aplikasi pemutar musik berbasis GUI.
- Memberikan pengalaman pengguna dalam mengelola koleksi lagu secara lebih mudah.

---

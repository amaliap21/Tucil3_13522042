# Tucil3_13522042

Penyelesaian Permainan Word Ladder Menggunakan Algoritma UCS, Greedy Best First Search, dan A\*

## Daftar Isi

- [Informasi Umum](#informasi-umum)
- [Techstack](#techstack)
- [Features](#features)
- [Screenshots](#screenshots)
- [Setup dan Penggunaan](#setup-dan-penggunaan)
- [Penulis](#penulis)
- [Program Structure](#program-structure)

## Informasi Umum

Word ladder (juga dikenal sebagai Doublets, word-links, change-the-word puzzles, paragrams, laddergrams, atau word golf) adalah salah satu permainan kata yang terkenal bagi seluruh kalangan. Word ladder ditemukan oleh Lewis Carroll, seorang penulis dan matematikawan, pada tahun 1877. Pada permainan ini, pemain diberikan dua kata yang disebut sebagai start word dan end word. Untuk memenangkan permainan, pemain harus menemukan rantai kata yang dapat menghubungkan antara start word dan end word. Banyaknya huruf pada start word dan end word selalu sama. Tiap kata yang berdekatan dalam rantai kata tersebut hanya boleh berbeda satu huruf saja. Pada permainan ini, diharapkan solusi optimal, yaitu solusi yang meminimalkan banyaknya kata yang dimasukkan pada rantai kata. Berikut adalah ilustrasi serta aturan permainan.

## Techstack

Programming Language: `Java`

## Features

| **No.** |                                                               **Poin**                                                               | **Ya** | **Tidak** |
| :-----: | :----------------------------------------------------------------------------------------------------------------------------------: | :----: | --------- |
|   1.    |                                                     Program berhasil dijalankan                                                      |   ✓    |           |
|   2.    |           Program dapat menemukan rangkaian kata dari start word ke end word sesuai aturan permainan dengan algoritma UCS            |   ✓    |           |
|   3.    |                                           Solusi yang diberikan pada algoritma UCS optimal                                           |   ✓    |           |
|   4.    | Program dapat menemukan rangkaian kata dari start word ke end word sesuai aturan permainan dengan algoritma Greedy Best First Search |   ✓    |           |
|   5.    |           Program dapat menemukan rangkaian kata dari start word ke end word sesuai aturan permainan dengan algoritma A\*            |   ✓    |           |
|   6.    |                                           Solusi yang diberikan pada algoritma A\* optimal                                           |   ✓    |           |
|   7.    |                                                [Bonus]: Program memiliki tampilan GUI                                                |   ✓    |           |

## Screenshots

Iteration/tolerance : 1
![Example screenshot](./test/Comparison0.png)
![Example screenshot](./test/Comparison1.png)

## Setup dan Penggunaan

1. Clone repositori ke lokal

   ```
   git clone https://github.com/amaliap21/Tucil2_13522010_13522042.git`
   ```

2. Jalankan program

   - Windows, pada current directory `./Tucil3_13522042` jalankan file `run.bat` dengan perintah berikut:

   ```
   ./run.bat
   ```

   - Linux, pada current directory `./Tucil3_13522042` jalankan file `run.sh` dengan perintah berikut:

   ```
   ./run.sh
   ```

   `ATAU`

   ```
   make all
   ```

## Penulis

| **NIM**  |   **Nama**   |
| :------: | :----------: |
| 13522042 | Amalia Putri |

## Program Structure

```
.
└── Tucil3_13522042
   ├── bin
   │ └── ...
   |
   ├── doc
   │ └── Tucil3_13522042.pdf
   |
   ├── src
   │ ├── AStarWordLadder.java
   | ├── .java
   │ ├── .java
   │ └── .java
   |
   ├── test
   │ ├── Output
   │ | ├── Brute Force
   | | | └── ...
   │ | └── Divide and Conquer
   | |   └── ...
   | |
   | └── Input
   |   └── ...
   |
   └── README.md
```

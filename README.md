# Penyelesaian Permainan Word Ladder Menggunakan Algoritma UCS, Greedy Best First Search, dan A*

## Deskripsi Tugas

Word ladder (juga dikenal sebagai Doublets, word-links, change-the-word puzzles, paragrams, laddergrams, atau word golf) adalah salah satu permainan kata yang terkenal bagi seluruh kalangan. Word ladder ditemukan oleh Lewis Carroll, seorang penulis dan matematikawan, pada tahun 1877. Pada permainan ini, pemain diberikan dua kata yang disebut sebagai start word dan end word. Untuk memenangkan permainan, pemain harus menemukan rantai kata yang dapat menghubungkan antara start word dan end word. Banyaknya huruf pada start word dan end word selalu sama. Tiap kata yang berdekatan dalam rantai kata tersebut hanya boleh berbeda satu huruf saja. Pada permainan ini, diharapkan solusi optimal, yaitu solusi yang meminimalkan banyaknya kata yang dimasukkan pada rantai kata.

## Penjelasan Program

Program ini adalah sebuah solver untuk permainan Word Ladder yang memanfaatkan algoritma UCS, GBFS, dan A* untuk mendapatkan path yang mungkin dari start word ke end word. Program solver Word Ladder ini dibuat menggunakan bahasa pemrograman Java dan menggunakan Java Swing untuk membuat GUI nya. Program ini menerima input dari pengguna berupa start word dan end word yang akan dicari pathnya. Pengguna dapat menentukan algoritma apa yang akan digunakan untuk melakukan pencarian.

## Cara Kompilasi

Program ini dikompilasi dengan menggunakan maven. Proses instalasi maven dapat dilihat pada [link](https://maven.apache.org/install.html) berikut.

Setelah menginstall maven, kompilasi program dapat dilakukan dengan mengikuti langkah-langkah berikut:

1. Clone repository lalu masuk ke dalam directory nya

    ```bash
    git clone https://github.com/SandWithCheese/Tucil3_13522005

    cd Tucil3_13522005
    ```

2. Build project dengan command berikut

    ```bash
    mvn package
    ```

3. Jalankan program dengan command berikut

    ```bash
    java -cp bin/tucil-3-1.0-SNAPSHOT.jar com.sandwicheese.app.App
    ```

4. Program sudah dapat digunakan

## Anggota

| NAMA ANGGOTA         | NIM      |
|----------------------|----------|
| Ahmad Naufal Ramadan | 13522005 |

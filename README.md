# Algeo01-13520022

Team Member:

1. Primanda Adyatma Hafiz (13520022)
2. Vincent Prasetiya Atmadja (13520099)
3. Gerald Abraham Sianturi (13520138)

Repository ini dibuat dalam rangka memenuhi Tugas Besar 1 Mata Kuliah Aljabar Linear dan Geometri Teknik Informatika 2020

## Table of Content

- [Struktur Repository](#struktur-repository)
- [How to Use](#how-to-use)

## Struktur Repository

Struktur repository dibuat dengan mengikuti panduan yang diberikan dengan sedikit improvisasi, yaitu dibagi menjadi 5 folder dengan spesifikasinya sebagai berikut.

1. Folder *bin*, berisi *java bytecode* (*.*class*)
2. Folder *src*, berisi *source code* dari program Java
3. Folder *test*, akan berisi 2 folder lagi
    1. Input, folder yang berisi file .txt sebagai input program
    2. Output, folder yang berisi file .txt sebagai output apabila dituliskan dalam file.
4. Folder *lib*, berisi file *.jar* yang berupa library yang kami buat untuk menyelesaikan persoalan aljabar linier.
5. Folder *doc*, berisi laporan

## How to Use

Kami sangat menyarankan untuk mencoba merun program di vscode yang telah dilengkapi dengan ekstensi Java. Hal ini karena kami mendevelop program ini menggunakan vscode dan terdapat beberapa hal seperti inputSourcePath java yang kami atur menggunakan file json di folder .vscode.

Untuk merun program dapat mengikuti petunjuk singkat berikut ini.

1. Buka *mainFile.java* di folder src.
2. Klik kanan di editor atau arahkan kursor ke kanan atas, ke logo berbentuk segitiga.
3. Pilih opsi run Java atau debug Java
4. Ikuti petunjuk yang ada dalam programnya untuk cara penggunaan.

Beberapa catatan tentang program

1. Program akan terus berjalan kecuali force stop (Ctrl + C) atau memilih opsi 6 di Service pertama.
2. Untuk pilihan service, metode penyelesaian, metode input, dan metode output, silahkan pilih angka yang sesuai dengan pilihan yang ada.
3. Untuk pilihan yang terdapat pada poin 2, terdapat error handle hanya untuk input tidak sesuai tipe (*InputMismatchException*), tidak terdapat handle untuk force close.
4. Untuk masukan SPL dari terminal, mengikuti pola yang terdapat pada spesifikasi, dengan asumsi m adalah banyak baris, n adalah banyak kolom. Untuk m baris berikutnya, tiap baris akan berisi a1 ... an diikuti dengan b1.
5. Untuk masukan SPL dari file, asumsi yang juga sama dengan spesifikasi, yang mana hanya berupa baris dan kolom. Kolom terakhir tiap barisnya, diasumsikan adalah nilai b.
6. Untuk masukan determinan dan invers dari terminal, mengikuti pola yang terdapat pada spesifikasi yaitu bilangan n diikuti dengan nilai aij(a baris i kolom j).
7. Untuk masukan determinan dan invers dari file akan sama dengan terminal, dengan pengecualian akhir bisa bukan b
8. Untuk masukan interpolasi dari terminal, input mengikuti pola yang terdapat pada spesifikasi, yang mana diawali sebuah integer n, diikuti xn dan yn, kemudian x yang akan ditaksir.
9. Untuk masukan interpolasi dari file, input akan berupa baris yang terdiri dari pasangan x dan y. NIlai x yang akan ditaksir akan digenerate seara random dengan memperhatiakan batas atas dan batas bawah dari x yang diinput.
10. Untuk masukan regresi dari terminal, input akan memprompting dua tahap masukan, pertama untuk variabel bebas (dependen), kedua (variabel terikat). User memasukan banyak baris (banyak data) dan kolom (banyak peubah x), lalu menginput variabel bebas dan terikat. User juga memasukkan xpredict untuk tiap variabel bebas xi..xn dimana n adalah banyak kolom
11. Output dari setiap servis yang ditampilkan ke layar akan berupa string  dan double (untuk kasus matriks invers), sementara output yang dituliskan ke dalam file akan berupa string.
12. Nama file output akan mengikuti nama file input dengan format {file_input}_output.txt. Apabila input berasal dari terminal, maka nama file output adalah temp{x}_output.txt dengan x diawali 0 dan akan terus bertambah.


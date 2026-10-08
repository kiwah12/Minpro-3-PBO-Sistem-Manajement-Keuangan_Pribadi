# Sistem Manajemen Keuangan Pribadi

----

| Keterangan | Isi |
|---|---|
| Nama | **Muhammad Risky Alpianur** |
| NIM | **2509116101** |
| Prodi | **Sistem Informasi** |
| Mata Kuliah | Pemrograman Berorientasi Objek |
| Studi Kasus | Manajemen Keuangan Pribadi |

---

## 1. **Deskripsi Singkat Program**

Sistem Manajemen Keuangan Pribadi merupakan program berbasis Java yang digunakan untuk membantu pengguna dalam mencatat dan mengelola transaksi keuangan pribadi.

Program menyediakan beberapa fitur utama, yaitu:
1. Menambahkan data pemasukan.
2. Menambahkan data pengeluaran.
3. Menampilkan seluruh transaksi.
4. Mengubah data transaksi.
5. Menghapus data transaksi.
6. Menampilkan ringkasan keuangan.
7. Menghitung total pemasukan.
8. Menghitung total pengeluaran.
9. Menghitung saldo akhir.

Program ini merupakan pengembangan dari Mini Project sebelumnya dengan menerapkan beberapa konsep Pemrograman Berorientasi Objek, yaitu encapsulation, inheritance, polymorphism, abstraction, serta struktur proyek MVC.
Data transaksi disimpan menggunakan ArrayList sehingga data pemasukan dan pengeluaran dapat dikelola dalam satu struktur data.

## **2. Alur Program**

Ketika program dijalankan, sistem akan menampilkan **Menu Utama Sistem Manajemen Keuangan Pribadi**. Pada saat program pertama kali dijalankan, 
sistem juga secara otomatis menyediakan beberapa data transaksi awal agar pengguna dapat langsung melihat data tanpa harus memasukkan transaksi terlebih dahulu.
Pengguna dapat memilih menu dengan memasukkan angka sesuai dengan pilihan yang tersedia.



### **A. Menu Tambah Pemasukan**

Menu **Tambah Pemasukan** digunakan untuk menambahkan data pemasukan ke dalam sistem.
Pengguna perlu memasukkan beberapa data, yaitu:

1. **Tanggal**
   Pengguna memasukkan tanggal transaksi dengan format **dd-mm-yyyy**.
2. **Kategori**
   Pengguna memasukkan kategori pemasukan, seperti Gaji atau Bonus.
3. **Keterangan**
   Pengguna memasukkan keterangan mengenai pemasukan yang dicatat.
4. **Jumlah**
   Pengguna memasukkan jumlah uang yang diterima.
5. **Sumber Dana**
   Pengguna memasukkan sumber dana dari pemasukan tersebut.
   
Setelah seluruh data valid, sistem akan membuat objek **Pemasukan** dan menyimpannya ke dalam `ArrayList<Transaksi>`. Data pemasukan akan menambah jumlah saldo karena nilai pemasukan dihitung sebagai nilai positif.

### **B. Menu Tambah Pengeluaran**

Menu **Tambah Pengeluaran** digunakan untuk mencatat transaksi pengeluaran yang dilakukan oleh pengguna.
Pengguna perlu memasukkan beberapa data, yaitu:

1. **Tanggal**
   Pengguna memasukkan tanggal transaksi dengan format **dd-mm-yyyy**.
2. **Kategori**
   Pengguna memasukkan kategori pengeluaran, seperti Makanan atau Transportasi.
3. **Keterangan**
   Pengguna memasukkan keterangan mengenai pengeluaran.
4. **Jumlah**
   Pengguna memasukkan jumlah uang yang dikeluarkan. Sistem akan memastikan jumlah yang dimasukkan lebih besar dari 0.
5. **Metode Pembayaran**
   Pengguna memasukkan metode pembayaran yang digunakan, seperti Tunai, Transfer, atau Kartu.
   
Setelah data valid, sistem akan membuat objek **Pengeluaran** dan menyimpannya ke dalam `ArrayList<Transaksi>`. Berbeda dengan pemasukan, pengeluaran akan mengurangi saldo karena dihitung sebagai nilai negatif.

### **C. Menu Lihat Semua Transaksi**

Menu Lihat Semua Transaksi digunakan untuk menampilkan seluruh transaksi yang tersimpan.Program melakukan perulangan terhadap:
`ArrayList<Transaksi>` Meskipun tipe data list adalah Transaksi, list tersebut dapat menyimpan objek:
1. **Pemasukan**
2. **Pengeluaran**
Hal tersebut dapat dilakukan karena kedua class tersebut merupakan turunan dari Transaksi.

### **D. Menu Update Transaksi**

Menu **Update Transaksi** digunakan untuk mengubah data transaksi yang sudah tersimpan.

1. Sistem terlebih dahulu menampilkan seluruh transaksi.
2. Pengguna memasukkan **ID transaksi** yang ingin diubah.
3. Sistem mencari transaksi berdasarkan ID tersebut.
4. Jika transaksi ditemukan, pengguna dapat memasukkan **keterangan baru** dan **jumlah baru**.
5. Sistem akan memperbarui data transaksi tersebut.
6. Jika ID tidak ditemukan, sistem akan menampilkan pesan bahwa data tidak ditemukan.

Sistem juga melakukan validasi agar keterangan tidak boleh kosong dan jumlah transaksi harus lebih besar dari 0.

### **E. Menu Hapus Transaksi**

Menu **Hapus Transaksi** digunakan untuk menghapus data transaksi yang sudah tersimpan.

1. Sistem menampilkan seluruh data transaksi.
2. Pengguna memasukkan **ID transaksi** yang ingin dihapus.
3. Sistem mencari transaksi berdasarkan ID.
4. Jika ID ditemukan, data transaksi akan dihapus dari `ArrayList`.
5. Jika ID tidak ditemukan, sistem akan menampilkan pesan bahwa data tersebut tidak tersedia.

### **F. Menu Lihat Ringkasan Keuangan**

Menu **Lihat Ringkasan Keuangan** digunakan untuk menampilkan kondisi keuangan berdasarkan seluruh transaksi yang tersimpan.
Sistem akan menampilkan tiga informasi utama:

1. **Total Pemasukan**
   Menampilkan jumlah seluruh transaksi pemasukan.
2. **Total Pengeluaran**
   Menampilkan jumlah seluruh transaksi pengeluaran.
3. **Saldo Akhir**
   Menampilkan hasil perhitungan total pemasukan dikurangi total pengeluaran.
   
Perhitungan saldo memanfaatkan method `hitungPengaruhSaldo()`. Pada objek **Pemasukan**, nilai transaksi dihitung positif, sedangkan pada objek **Pengeluaran**, nilai transaksi dihitung negatif. Dengan demikian, sistem dapat menghitung saldo dari seluruh transaksi yang tersimpan.

### **G. Menu Keluar**

Jika pengguna memilih menu **7. Keluar**, sistem akan menghentikan perulangan menu utama dan menampilkan pesan:

**"Sampai jumpa rworrrrrr"**

Setelah itu, program akan berhenti dan `Scanner` yang digunakan untuk menerima input pengguna akan ditutup.

## **3. Dokumentasi Dan Implementasi Program**

### **Menu Utama**

Berikut merupakan screenshot tampilan Menu Utama pada Sistem Manajemen Keuangan Pribadi yang menyediakan beberapa fitur untuk mengelola data  Tambah Pemasukan, Tambah Pengeluaran, Lihat Semua Transaksi, Update Transaksi, Hapus Transaksi, Lihat Ringkasan Keuangan, dan Keluar

<img width="758" height="215" alt="image" src="https://github.com/user-attachments/assets/4f605b33-36ea-4419-a187-78d1cb87f7e9" />

### **Tambah Pemasukan**

Berikut merupakan screenshot tampilan Menu Tambah Pemasukan pada Sistem Manajemen Keuangan Pribadi yang digunakan untuk mencatat dan menyimpan data pemasukan ke dalam sistem. 

<img width="1222" height="463" alt="image" src="https://github.com/user-attachments/assets/c9e2d622-4562-485e-8ebc-e287c041c6eb" />

### **Tambah Pengeluaran**

Berikut merupakan screenshot tampilan **Menu Tambah Pengeluaran** pada Sistem Manajemen Keuangan Pribadi yang digunakan untuk mencatat dan menyimpan data pengeluaran ke dalam sistem.

<img width="1305" height="505" alt="image" src="https://github.com/user-attachments/assets/2e798df6-692e-4a2c-b63e-6cf0bc5d2b6a" />

### **Lihat Semua Transaksi**

Berikut merupakan screenshot tampilan Menu Lihat Semua Transaksi pada Sistem Manajemen Keuangan Pribadi yang digunakan untuk menampilkan seluruh data pemasukan dan pengeluaran yang telah tersimpan di dalam sistem. 

<img width="1498" height="470" alt="image" src="https://github.com/user-attachments/assets/44d48737-e239-4b8f-b83b-904d499527e2" />

### **Update Transaksi**

Berikut merupakan screenshot tampilan Menu Update Transaksi pada Sistem Manajemen Keuangan Pribadi yang digunakan untuk mengubah data transaksi yang telah tersimpan di dalam sistem. 

<img width="1417" height="497" alt="image" src="https://github.com/user-attachments/assets/574829fc-c6e3-4602-840e-fc6bff57bdc6" />

### **Hapus Transaksi**

Berikut merupakan screenshot tampilan Menu Hapus Transaksi pada Sistem Manajemen Keuangan Pribadi yang digunakan untuk menghapus data transaksi yang telah tersimpan di dalam sistem. 

<img width="1598" height="451" alt="image" src="https://github.com/user-attachments/assets/90dc6986-6256-4757-87fe-2284152ab9a9" />

### **Lihat Ringkasan Keuangan**

Berikut merupakan screenshot tampilan Menu Lihat Ringkasan Keuangan pada Sistem Manajemen Keuangan Pribadi yang digunakan untuk menampilkan ringkasan kondisi keuangan berdasarkan transaksi yang telah tersimpan di dalam sistem. 

<img width="1663" height="325" alt="image" src="https://github.com/user-attachments/assets/3e36dafa-37ce-4914-acd1-4d617aebaa5c" />

### **Keluar**

Berikut merupakan screenshot tampilan Menu Keluar pada Sistem Manajemen Keuangan Pribadi yang digunakan untuk mengakhiri penggunaan program.

<img width="1542" height="243" alt="image" src="https://github.com/user-attachments/assets/e907d262-8772-462c-9e18-6a35b7aece10" />


## **4. Access Modifier**

Access modifier digunakan untuk mengatur hak akses terhadap class, atribut, dan method yang terdapat dalam program. Pada Sistem Manajemen Keuangan Pribadi, access modifier digunakan untuk membatasi akses langsung terhadap data dan mengatur bagian program yang dapat digunakan oleh class lain.
Program menggunakan beberapa access modifier, yaitu:

* **private** digunakan pada atribut dalam class agar data tidak dapat diakses secara langsung dari luar class.
* **public** digunakan pada constructor dan method yang perlu diakses oleh class lain.
* **protected** dapat digunakan pada bagian tertentu yang membutuhkan akses dari class turunan.

Penggunaan access modifier membantu membuat struktur program menjadi lebih teratur karena setiap data dan method memiliki batasan akses sesuai dengan kebutuhannya,
berikut adalah screenshot untuk kode dari access modifier.

untuk Private:

<img width="475" height="107" alt="image" src="https://github.com/user-attachments/assets/1c60320c-5c76-4b48-85b0-cb7840432e37" />

untuk protected:

<img width="1036" height="177" alt="image" src="https://github.com/user-attachments/assets/4755daa7-6656-4f6d-ac49-805aa9c5a7da" />

## **5. Encapsulation + Getter/Setter**

Encapsulation merupakan konsep yang digunakan untuk melindungi data dalam sebuah class dengan membatasi akses langsung terhadap atribut. Pada Sistem Manajemen Keuangan Pribadi, 
encapsulation diterapkan melalui penggunaan getter dan setter untuk mengakses serta mengubah nilai atribut.

* **Getter** digunakan untuk mengambil atau mendapatkan nilai dari atribut yang terdapat dalam object.
* **Setter** digunakan untuk memberikan atau mengubah nilai atribut dalam object.

Dengan menggunakan getter dan setter, data dalam object dapat diakses dan diubah melalui method yang telah disediakan oleh class, sehingga pengelolaan data menjadi lebih terkontrol, berikut adalah screenshot untuk kode dari Encapsulation + Getter/Setter.

<img width="717" height="186" alt="image" src="https://github.com/user-attachments/assets/77430d8d-db81-4210-82ff-d2dddbfe095a" />

## **6. Inheritance**

Inheritance merupakan konsep pewarisan yang memungkinkan sebuah class turunan mewarisi atribut dan method dari class induk. Pada Sistem Manajemen Keuangan Pribadi, inheritance digunakan untuk membuat class **Pemasukan** dan **Pengeluaran** sebagai turunan dari class **Transaksi**.

Penerapan inheritance terdiri dari:

* **Transaksi** sebagai superclass yang menjadi class induk dan menyediakan atribut serta method yang dapat digunakan oleh class turunannya.
* **Pemasukan** sebagai subclass yang mewarisi sifat dan method dari class Transaksi serta digunakan untuk mengelola data pemasukan.
* **Pengeluaran** sebagai subclass yang mewarisi sifat dan method dari class Transaksi serta digunakan untuk mengelola data pengeluaran.

Dengan menggunakan inheritance, class Pemasukan dan Pengeluaran dapat menggunakan kembali atribut dan method yang terdapat pada class Transaksi tanpa harus menuliskannya kembali,berikut adalh screenshot untuk kode superclass dan subclass

<img width="802" height="45" alt="image" src="https://github.com/user-attachments/assets/260d48ef-cd04-4dd3-9ed5-08c75104716e" />

---

<img width="891" height="65" alt="image" src="https://github.com/user-attachments/assets/b7536d11-2b90-45cb-8222-2191148c1c28" />

---

<img width="582" height="52" alt="image" src="https://github.com/user-attachments/assets/1de60adc-f52b-41d1-a9dd-9b63a009fbaa" />

## **7. Dummy Data**

Dummy data digunakan sebagai data awal yang sudah tersedia ketika program pertama kali dijalankan. Pada Sistem Manajemen Keuangan Pribadi, dummy data dimasukkan ke dalam `ArrayList<Transaksi>` sehingga pengguna dapat langsung melihat data transaksi tanpa harus memasukkan data secara manual terlebih dahulu, berikut adalah screenshot kode Dummy Data.

<img width="1277" height="97" alt="image" src="https://github.com/user-attachments/assets/3fc65e52-b713-4240-9151-5783cc9b95ca" />

## **8. Polymorphism**

Polymorphism merupakan konsep yang memungkinkan satu method atau referensi digunakan untuk menangani object dengan bentuk yang berbeda. Pada Sistem Manajemen Keuangan Pribadi polymorphism diterapkan melalui **method overriding** dan penggunaan `ArrayList<Transaksi>` yang dapat menyimpan object dari class **Pemasukan** dan **Pengeluaran**.

Penerapan polymorphism dapat dilihat pada:

* **Method toString()** di-override pada class turunan untuk menampilkan informasi sesuai dengan jenis transaksi.
* **Method hitungPengaruhSaldo()** di-override pada class turunan sehingga menghasilkan nilai yang berbeda, yaitu positif untuk `Pemasukan` dan negatif untuk `Pengeluaran`.
* **ArrayList<Transaksi>** digunakan untuk menyimpan object dari class `Pemasukan` dan `Pengeluaran` dalam satu list.
  
Dengan menggunakan polymorphism, program dapat memanggil method yang sama pada object yang berbeda dan menghasilkan perilaku sesuai dengan jenis object tersebut. Penerapan ini membuat pengelolaan berbagai jenis transaksi menjadi lebih fleksibel, berikut screenshot untuk kode Polymorphism.

---

<img width="931" height="326" alt="image" src="https://github.com/user-attachments/assets/54185b9d-e25c-4a69-b95c-38c7255a6b77" />

---

<img width="845" height="321" alt="image" src="https://github.com/user-attachments/assets/d1d43dd0-39be-486a-a2f8-15fc383cedc9" />

## **9. MVC (Model-View-Controller)**

MVC (Model-View-Controller) merupakan pola perancangan yang digunakan untuk memisahkan bagian data, tampilan, dan proses pengendalian program. Pada Sistem Manajemen Keuangan Pribadi, struktur MVC digunakan agar setiap bagian program memiliki fungsi dan tanggung jawab yang berbeda.
Penerapan MVC terdiri dari:

* **Model** digunakan untuk mengelola data dan struktur object transaksi, seperti class `Transaksi`, `Pemasukan`, dan `Pengeluaran`.
* **View** digunakan untuk menampilkan menu dan menerima input dari pengguna, seperti `MenuView`.
* **Controller** digunakan untuk mengatur proses dan logika program, seperti `TransaksiController` yang mengelola penambahan, penampilan, perubahan, penghapusan, dan perhitungan transaksi.

Dengan menggunakan MVC, program menjadi lebih terstruktur karena bagian data, tampilan, dan proses program dipisahkan sesuai dengan fungsinya. Pemisahan tersebut juga memudahkan proses pengembangan dan pemeliharaan program, berikut screenshot untuk MVC.

<img width="411" height="357" alt="image" src="https://github.com/user-attachments/assets/5d2c5017-7d10-4d72-8e30-3b4e76b2d91e" />

## **10. Abstraction**

Abstraction digunakan untuk menyembunyikan detail implementasi dan hanya menentukan bagian penting yang harus dimiliki oleh suatu objek.
Pada program ini, abstraction diterapkan melalui abstract class:

<img width="486" height="27" alt="image" src="https://github.com/user-attachments/assets/56d232eb-42ef-4eb4-a089-b296ddb48027" />

Class Transaksi tidak dibuat sebagai objek secara langsung, tetapi digunakan sebagai dasar untuk class Pemasukan dan Pengeluaran.

## **11. Interface**

Interface merupakan sebuah struktur yang digunakan untuk menentukan method yang harus dimiliki oleh class atau object yang mengimplementasikannya. Pada Sistem Manajemen Keuangan Pribadi, interface digunakan sebagai salah satu penerapan nilai tambah untuk membuat struktur program menjadi lebih terorganisir.
Penerapan interface terdiri dari:

**Interface Katagori** digunakan untuk menentukan method yang berkaitan dengan kategori transaksi, yaitu getNama() dan isLainnya().

<img width="448" height="41" alt="image" src="https://github.com/user-attachments/assets/dfd335a3-e9c6-4226-815d-13ef9b14d6b3" />

**KatagoriPemasukan** digunakan untuk mengimplementasikan interface Katagori dan mengatur kategori yang digunakan pada transaksi pemasukan.

<img width="670" height="21" alt="image" src="https://github.com/user-attachments/assets/b77724e7-78de-431e-8ad0-a2478af1d158" />

**KatagoriPengeluaran** digunakan untuk mengimplementasikan interface Katagori dan mengatur kategori yang digunakan pada transaksi pengeluaran.

<img width="640" height="21" alt="image" src="https://github.com/user-attachments/assets/18c9f6eb-5525-4323-b0e1-189d71343d4f" />

Dengan menggunakan interface, program dapat menentukan method yang harus tersedia pada bagian yang mengimplementasikannya. Penerapan interface juga membantu membuat struktur program menjadi lebih terorganisir dan memisahkan aturan method dari implementasinya. 

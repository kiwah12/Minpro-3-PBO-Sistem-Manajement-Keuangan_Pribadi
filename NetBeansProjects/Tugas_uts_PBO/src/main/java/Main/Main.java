/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Makanan;
import model.Minuman;
import model.MinumanBersoda;
import model.Produk;
import model.Rokok;

/**
 * @author MSI THIN 15
 */
public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final DataToko toko =
            new DataToko("TOKO MADURA BAROKAH 24 JAM", "Jl. Ir. H. Juanda No. 12, Samarinda");

    private record ItemBelanja(Produk produk, int jumlah, double diskon) {
        double subtotal() {
            return produk.hitungHargaJual(diskon) * jumlah;
        }
    }

    public static void main(String[] args) {
        isiDataAwal();
        boolean jalan = true;

        while (jalan) {
            tampilkanMenu();
            int pilihan = bacaInt("Pilih menu [0-9] : ");
            System.out.println();

            switch (pilihan) {
                case 1 -> tambahProduk();
                case 2 -> tampilkanSemua();
                case 3 -> lihatDetail();
                case 4 -> cariProduk();
                case 5 -> ubahProduk();
                case 6 -> hapusProduk();
                case 7 -> transaksiPenjualan();
                case 8 -> laporanToko();
                case 9 -> restockProduk();
                case 0 -> {
                    jalan = false;
                    System.out.println("Terima kasih, toko ditutup. Sampai jumpa!");
                }
                default -> System.out.println("[!] Menu tidak tersedia, silakan pilih 0-9.");
            }

            if (jalan) {
                System.out.print("\nTekan ENTER untuk kembali ke menu...");
                input.nextLine();
            }
        }
        input.close();
    }

    // ==================== TAMPILAN ====================
    private static void tampilkanMenu() {
        garis();
        System.out.println("   " + toko.getNamaToko());
        System.out.println("   " + toko.getAlamat());
        garis();
        System.out.println("  1. Tambah Produk");
        System.out.println("  2. Daftar Seluruh Produk");
        System.out.println("  3. Lihat Detail Produk");
        System.out.println("  4. Cari Produk");
        System.out.println("  5. Ubah Data Produk");
        System.out.println("  6. Hapus Produk");
        System.out.println("  7. Transaksi Penjualan");
        System.out.println("  8. Laporan Toko");
        System.out.println("  9. Restock Produk");
        System.out.println("  0. Keluar");
        garis();
    }

    private static void garis() {
        System.out.println("=".repeat(72));
    }

    private static void header() {
        System.out.printf("%-8s %-22s %-10s %14s %7s  %-8s%n",
                "KODE", "NAMA PRODUK", "KATEGORI", "HARGA JUAL", "STOK", "STATUS");
        System.out.println("-".repeat(72));
    }

    private static void cetakTabel(List<Produk> data) {
        if (data.isEmpty()) {
            System.out.println("[i] Tidak ada data produk.");
            return;
        }
        header();
        for (Produk p : data) {
            System.out.println(p.toBarisTabel()); 
        }
        System.out.println("-".repeat(72));
        System.out.println("Total data : " + data.size() + " produk");
    }

    private static void tambahProduk() {
        System.out.println(">> TAMBAH PRODUK BARU");
        System.out.println("   1. Makanan   2. Minuman   3. Rokok   4. Minuman Bersoda");
        int jenis = bacaInt("Pilih kategori [1-4] : ");
        if (jenis < 1 || jenis > 4) {
            System.out.println("[!] Kategori tidak valid.");
            return;
        }

        String kode = bacaString("Kode produk        : ").toUpperCase();
        if (kode.isEmpty()) {
            System.out.println("[!] Kode tidak boleh kosong.");
            return;
        } else if (toko.cariByKode(kode) != null) {
            System.out.println("[!] Gagal: kode " + kode + " sudah dipakai produk lain.");
            return;
        }
        String nama = bacaString("Nama produk        : ");
        double hargaBeli = bacaDouble("Harga beli (Rp)    : ");
        int stok = bacaInt("Jumlah stok        : ");
        if (nama.isEmpty() || hargaBeli <= 0 || stok < 0) {
            System.out.println("[!] Gagal: nama wajib diisi, harga beli > 0, stok >= 0.");
            return;
        }

        Produk produkBaru;
        if (jenis == 1) {
            String exp = bacaString("Tgl kadaluarsa     : ");
            boolean kemasan = bacaYaTidak("Makanan kemasan?   (y/t) : ");
            produkBaru = new Makanan(kode, nama, hargaBeli, stok, exp, kemasan);
        } else if (jenis == 2) {
            int volume = bacaInt("Volume (ml)        : ");
            boolean dingin = bacaYaTidak("Disimpan di kulkas? (y/t) : ");
            produkBaru = new Minuman(kode, nama, hargaBeli, stok, volume, dingin);
        } else if (jenis == 3) {
            String merek = bacaString("Merek              : ");
            int batang = bacaInt("Isi per bungkus    : ");
            produkBaru = new Rokok(kode, nama, hargaBeli, stok, merek, batang);
        } else {
            int volume = bacaInt("Volume (ml)        : ");
            boolean dingin = bacaYaTidak("Disimpan di kulkas? (y/t) : ");
            int gula = bacaInt("Kadar gula (gram) : ");
            produkBaru = new MinumanBersoda(kode, nama, hargaBeli, stok, volume, dingin, gula);
        }

        if (toko.tambahProduk(produkBaru)) {
            System.out.println("\n[OK] Produk berhasil ditambahkan.");
            System.out.println("     Harga jual otomatis: " + Produk.rupiah(produkBaru.hitungHargaJual()));
        } else {
            System.out.println("\n[!] Produk gagal ditambahkan.");
        }
    }

    private static void tampilkanSemua() {
        System.out.println(">> DAFTAR SELURUH PRODUK");
        cetakTabel(toko.getSemuaProduk());
    }

    private static void lihatDetail() {
        System.out.println(">> DETAIL PRODUK");
        String kode = bacaString("Masukkan kode produk : ").toUpperCase();
        Produk p = toko.cariByKode(kode);
        if (p == null) {
            System.out.println("[!] Produk dengan kode " + kode + " tidak ditemukan.");
            return;
        }
        System.out.println();
        p.tampilkanDetail(); 
    }

    private static void cariProduk() {
        System.out.println(">> CARI PRODUK");
        System.out.println("   1. Berdasarkan nama/kode   2. Berdasarkan rentang harga");
        int mode = bacaInt("Pilih cara cari [1-2] : ");

        List<Produk> hasil;
        if (mode == 1) {
            String kunci = bacaString("Kata kunci (nama/kode) : ");
            hasil = toko.cariProduk(kunci);            
        } else if (mode == 2) {
            double min = bacaDouble("Harga jual minimum (Rp) : ");
            double maks = bacaDouble("Harga jual maksimum (Rp): ");
            hasil = toko.cariProduk(min, maks);     
        } else {
            System.out.println("[!] Pilihan tidak valid.");
            return;
        }
        System.out.println();
        cetakTabel(hasil);
    }

    private static void ubahProduk() {
        System.out.println(">> UBAH DATA PRODUK");
        String kode = bacaString("Masukkan kode produk : ").toUpperCase();
        Produk p = toko.cariByKode(kode);
        if (p == null) {
            System.out.println("[!] Produk tidak ditemukan.");
            return;
        }

        System.out.println("\nData saat ini:");
        p.tampilkanDetail();
        System.out.println("\n(Kosongkan / isi 0 jika tidak ingin mengubah)");

        String namaBaru = bacaString("Nama baru          : ");
        double hargaBaru = bacaDouble("Harga beli baru    : ");
        int stokBaru = bacaIntOpsional("Stok baru (ENTER = tetap) : ", -1);

        boolean sukses = toko.ubahProduk(kode, namaBaru, hargaBaru, stokBaru);

        if (sukses) {
            
            if (p instanceof Makanan m) {
                String exp = bacaString("Tgl kadaluarsa baru : ");
                if (!exp.isBlank()) m.setTanggalKadaluarsa(exp);
            } else if (p instanceof Minuman mn) {
                if (bacaYaTidak("Ubah status dingin? (y/t) : ")) {
                    mn.setDingin(!mn.isDingin());
                }
                if (mn instanceof MinumanBersoda ms) {
                    int gula = bacaInt("Kadar gula baru (0 = tetap) : ");
                    if (gula > 0) ms.setKadarGulaGram(gula);
                }
            } else if (p instanceof Rokok r) {
                int batang = bacaInt("Isi per bungkus baru (0 = tetap) : ");
                if (batang > 0) r.setJumlahBatang(batang);
            }
            System.out.println("\n[OK] Data produk berhasil diperbarui:");
            p.tampilkanDetail();
        } else {
            System.out.println("\n[!] Gagal memperbarui data.");
        }
    }

    private static void hapusProduk() {
        System.out.println(">> HAPUS PRODUK");
        String kode = bacaString("Masukkan kode produk : ").toUpperCase();
        Produk p = toko.cariByKode(kode);
        if (p == null) {
            System.out.println("[!] Produk tidak ditemukan.");
            return;
        }
        System.out.println("Produk terpilih : " + p);
        if (bacaYaTidak("Yakin ingin menghapus? (y/t) : ")) {
            if (toko.hapusProduk(kode)) {
                System.out.println("[OK] Produk " + kode + " berhasil dihapus.");
            }
        } else {
            System.out.println("[i] Penghapusan dibatalkan.");
        }
    }

    private static void restockProduk() {
        System.out.println(">> RESTOCK PRODUK");
        String kode = bacaString("Masukkan kode produk : ").toUpperCase();
        Produk p = toko.cariByKode(kode);
        if (p == null) {
            System.out.println("[!] Produk tidak ditemukan.");
            return;
        }
        int jumlah = bacaInt("Jumlah tambahan stok : ");
        if (jumlah <= 0) {
            System.out.println("[!] Jumlah harus lebih dari 0.");
            return;
        }
        String catatan = bacaString("Catatan (ENTER = kosong) : ");
        if (catatan.isEmpty()) {
            p.tambahStok(jumlah);                 // overload versi 1 parameter
        } else {
            p.tambahStok(jumlah, catatan);        // overload versi 2 parameter
        }
        System.out.println("[OK] Stok " + p.getNama() + " sekarang: "
                + p.getStok() + " (" + p.getStatusStok() + ")");
    }

    private static void transaksiPenjualan() {
        System.out.println(">> TRANSAKSI PENJUALAN");
        cetakTabel(toko.getSemuaProduk());
        System.out.println();

        List<ItemBelanja> keranjang = new ArrayList<>();
        int usia = -1;
        
        do {
            String kode = bacaString("Kode produk    : ").toUpperCase();
            Produk p = toko.cariByKode(kode);

            if (p == null) {
                System.out.println("[!] Produk tidak ditemukan.");
            } else {
                boolean lolos = true;
                if (p instanceof Rokok r) {
                    if (usia < 0) usia = bacaInt("Usia pembeli   : ");
                    if (!r.bolehDibeli(usia)) {
                        System.out.println("[!] Ditolak. Pembeli di bawah "
                                + Rokok.BATAS_USIA + " tahun.");
                        lolos = false;
                    }
                }

                if (lolos) {
                    int jumlah = bacaInt("Jumlah beli    : ");
                    int sudahDiKeranjang = hitungDiKeranjang(keranjang, p);
                    if (jumlah <= 0 || jumlah + sudahDiKeranjang > p.getStok()) {
                        System.out.println("[!] Jumlah tidak valid / stok kurang. Stok tersisa "
                                + (p.getStok() - sudahDiKeranjang) + ".");
                    } else {
                        double diskon = 0;
                        if (p instanceof Rokok) {
                            System.out.println("    (Rokok tidak boleh didiskon)");
                        } else {
                            diskon = bacaDouble("Diskon % (0 = tidak) : ");
                        }
                        keranjang.add(new ItemBelanja(p, jumlah, diskon));
                        System.out.println("[OK] Ditambahkan ke keranjang.");
                    }
                }
            }
        } while (bacaYaTidak("Tambah barang lain? (y/t) : "));

        if (keranjang.isEmpty()) {
            System.out.println("[i] Keranjang kosong, transaksi dibatalkan.");
            return;
        }

        double total = 0;
        for (ItemBelanja item : keranjang) {
            total += item.subtotal();
        }

        System.out.println("\nTotal belanja : " + Produk.rupiah(total));
        double bayar = bacaDouble("Uang dibayarkan (0 = batal): ");
        while (bayar != 0 && bayar < total) {
            System.out.println("[!] Uang kurang. Total belanja " + Produk.rupiah(total));
            bayar = bacaDouble("Uang dibayarkan (0 = batal): ");
        }
        if (bayar == 0) {
            System.out.println("[i] Transaksi dibatalkan, stok tidak berubah.");
            return;
        }

        for (ItemBelanja item : keranjang) {
            if (item.diskon() > 0) {
                toko.jualProduk(item.produk().getKode(), item.jumlah(), item.diskon());
            } else {
                toko.jualProduk(item.produk().getKode(), item.jumlah());
            }
        }
        cetakStruk(keranjang, total, bayar);
    }

    private static int hitungDiKeranjang(List<ItemBelanja> keranjang, Produk p) {
        int jumlah = 0;
        for (ItemBelanja item : keranjang) {
            if (item.produk() == p) jumlah += item.jumlah();
        }
        return jumlah;
    }

    private static void cetakStruk(List<ItemBelanja> keranjang, double total, double bayar) {
        System.out.println("\n" + "=".repeat(48));
        System.out.println("        STRUK " + toko.getNamaToko());
        System.out.println("=".repeat(48));
        for (ItemBelanja item : keranjang) {
            String label = item.produk().getNama();
            if (label.length() > 22) label = label.substring(0, 20) + "..";
            System.out.printf("%-22s %3d x %10s%n", label, item.jumlah(),
                    Produk.rupiah(item.produk().hitungHargaJual(item.diskon())));
            if (item.diskon() > 0) {
                System.out.printf("   (diskon %.0f%%)%n", item.diskon());
            }
            System.out.printf("%48s%n", Produk.rupiah(item.subtotal()));
        }
        System.out.println("-".repeat(48));
        System.out.printf("%-30s %17s%n", "TOTAL", Produk.rupiah(total));
        System.out.printf("%-30s %17s%n", "TUNAI", Produk.rupiah(bayar));
        System.out.printf("%-30s %17s%n", "KEMBALI", Produk.rupiah(bayar - total));
        System.out.println("=".repeat(48));
        System.out.println("            Terima kasih, selamat belanja!");
    }

    private static void laporanToko() {
        System.out.println(">> LAPORAN TOKO");
        garis();
        System.out.println("  Jumlah jenis produk   : " + toko.getJumlahProduk());
        System.out.println("   - Makanan            : " + toko.hitungKategori("Makanan"));
        System.out.println("   - Minuman            : " + toko.hitungKategori("Minuman"));
        System.out.println("   - Rokok              : " + toko.hitungKategori("Rokok"));
        System.out.println("  Nilai persediaan      : " + Produk.rupiah(toko.getTotalNilaiPersediaan()));
        System.out.println("  Pendapatan sesi ini   : " + Produk.rupiah(toko.getTotalPendapatan()));
        garis();
        System.out.println("  Produk yang perlu di-restock (stok <= 5):");
        List<Produk> menipis = toko.getProdukStokMenipis();
        if (menipis.isEmpty()) {
            System.out.println("   - Tidak ada, semua stok aman.");
        } else {
            for (Produk p : menipis) {
                System.out.println("   - " + p + " | sisa " + p.getStok());
            }
        }
        garis();
    }

    private static void isiDataAwal() {
        toko.tambahProduk(new Makanan("MK001", "Indomie Goreng", 3000, 40, "12-08-2027", true));
        toko.tambahProduk(new Makanan("MK002", "Roti Sisir Mentega", 8000, 12, "28-09-2026", true));
        toko.tambahProduk(new Makanan("MK003", "Gorengan Tempe", 1000, 5, "21-09-2026", false));
        toko.tambahProduk(new Minuman("MN001", "Teh Botol Sosro 450ml", 4500, 24, 450, true));
        toko.tambahProduk(new Minuman("MN002", "Aqua Botol 600ml", 3000, 36, 600, false));
        toko.tambahProduk(new Minuman("MN003", "Kopi Susu Kaleng", 7000, 4, 240, true));
        toko.tambahProduk(new MinumanBersoda("MN004", "Coca-Cola 390ml", 4500, 20, 390, true, 39));
        toko.tambahProduk(new Rokok("RK001", "Sampoerna Mild 16", 25000, 10, "Sampoerna", 16));
        toko.tambahProduk(new Rokok("RK002", "Djarum Super 12", 20000, 3, "Djarum", 12));
    }

    private static String bacaString(String pesan) {
        System.out.print(pesan);
        return input.nextLine().trim();
    }

    private static int bacaInt(String pesan) {
        return bacaIntOpsional(pesan, 0);
    }

    private static int bacaIntOpsional(String pesan, int nilaiDefault) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim();
            if (teks.isEmpty()) return nilaiDefault;
            try {
                return Integer.parseInt(teks);
            } catch (NumberFormatException e) {
                System.out.println("[!] Masukkan berupa angka bulat.");
            }
        }
    }

    private static double bacaDouble(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = input.nextLine().trim().replace(",", ".");
            if (teks.isEmpty()) return 0;
            try {
                return Double.parseDouble(teks);
            } catch (NumberFormatException e) {
                System.out.println("[!] Masukkan berupa angka.");
            }
        }
    }

    private static boolean bacaYaTidak(String pesan) {
        System.out.print(pesan);
        String jawab = input.nextLine().trim().toLowerCase();
        return jawab.startsWith("y");
    }
}


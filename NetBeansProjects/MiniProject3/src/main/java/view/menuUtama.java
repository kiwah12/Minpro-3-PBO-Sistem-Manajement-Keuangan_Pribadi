package view;

import controller.TransaksiService;
import controller.ProsesTransaksi;
import java.util.ArrayList;
import java.util.Scanner;
import model.Kategori;
import model.KategoriPemasukan;
import model.KategoriPengeluaran;
import model.Pemasukan;
import model.Pengeluaran;
import model.Transaksi;

/**
 * VIEW: semua tampilan menu dan input/output ke console.
 */
public class menuUtama {
    private static final String GARIS =
            "===================================================================================";

    private Scanner scanner;
    private inputHealper input;
    private TransaksiService controller; // bergantung pada interface, bukan class konkret

    public menuUtama() {
        this.scanner = new Scanner(System.in);
        this.input = new inputHealper(scanner);
        this.controller = new ProsesTransaksi();
    }

    public void mulai() {
        boolean berjalan = true;

        System.out.println("=====================================================");
        System.out.println("   SISTEM MANAJEMEN KEUANGAN PRIBADI");
        System.out.println("=====================================================");

        while (berjalan) {
            tampilkanMenu();
            int pilihan = input.inputInt("Pilih menu (1-7): ", 1, 7);

            switch (pilihan) {
                case 1:
                    tambahPemasukan();
                    break;
                case 2:
                    tambahPengeluaran();
                    break;
                case 3:
                    lihatTransaksi();
                    break;
                case 4:
                    updateData();
                    break;
                case 5:
                    hapusData();
                    break;
                case 6:
                    tampilkanRingkasan();
                    break;
                case 7:
                    berjalan = false;
                    System.out.println(" Sampai jumpa rworrrrrrrr");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }

    private void tampilkanMenu() {
        System.out.println("-----------------------------------------------------");
        System.out.println("MENU:");
        System.out.println("1. Tambah Pemasukan");
        System.out.println("2. Tambah Pengeluaran");
        System.out.println("3. Lihat Transaksi");
        System.out.println("4. Update Transaksi");
        System.out.println("5. Hapus Transaksi");
        System.out.println("6. Lihat Ringkasan Keuangan");
        System.out.println("7. Keluar");
        System.out.println("-----------------------------------------------------");
    }

    /**
     * Menampilkan pilihan kategori bernomor, lalu mengembalikan nama kategori.
     * Jika user memilih "Lainnya", user diminta mengetik kategori sendiri.
     * Parameter bertipe interface Kategori, jadi bisa dipakai untuk pemasukan maupun pengeluaran.
     */
    private String pilihKategori(Kategori[] pilihan) {
        System.out.println("Kategori:");
        for (int i = 0; i < pilihan.length; i++) {
            System.out.println("  " + (i + 1) + ". " + pilihan[i].getNama());
        }
        int nomor = input.inputInt("Pilih kategori (1-" + pilihan.length + "): ", 1, pilihan.length);
        Kategori dipilih = pilihan[nomor - 1];

        if (dipilih.isLainnya()) {
            return input.inputStringWajibIsi("Tulis kategori sesuai kebutuhan: ");
        }
        return dipilih.getNama();
    }

    private void tambahPemasukan() {
        System.out.println("--- Tambah Pemasukan ---");
        String tanggal = input.inputTanggal("Tanggal (dd-mm-yyyy): ");
        String kategori = pilihKategori(KategoriPemasukan.values());
        String keterangan = input.inputStringWajibIsi("Keterangan: ");
        double jumlah = input.inputDoublePositif("Jumlah (Rp): ");
        String sumberDana = input.inputStringWajibIsi("Sumber dana: ");

        Pemasukan p = new Pemasukan(controller.getNextId(), tanggal, kategori, keterangan, jumlah, sumberDana);
        controller.tambahTransaksi(p);
        System.out.println(">> Data pemasukan berhasil ditambahkan.");
    }

    private void tambahPengeluaran() {
        System.out.println("--- Tambah Pengeluaran ---");
        String tanggal = input.inputTanggal("Tanggal (dd-mm-yyyy): ");
        String kategori = pilihKategori(KategoriPengeluaran.values());
        String keterangan = input.inputStringWajibIsi("Keterangan: ");
        double jumlah = input.inputDoublePositif("Jumlah (Rp): ");
        String metode = input.inputStringWajibIsi("Metode pembayaran (Tunai/Transfer/Kartu): ");

        Pengeluaran p = new Pengeluaran(controller.getNextId(), tanggal, kategori, keterangan, jumlah, metode);
        controller.tambahTransaksi(p);
        System.out.println(">> Data pengeluaran berhasil ditambahkan.");
    }

    private void lihatTransaksi() {
        System.out.println("--- Lihat Transaksi ---");
        System.out.println("1. Semua");
        System.out.println("2. Pemasukan saja");
        System.out.println("3. Pengeluaran saja");
        int pilihan = input.inputInt("Pilih (1-3): ", 1, 3);

        switch (pilihan) {
            case 1:
                cetakDaftar(controller.getDaftarTransaksi());
                break;
            case 2:
                cetakDaftar(controller.getDaftarTransaksi("Pemasukan"));
                break;
            case 3:
                cetakDaftar(controller.getDaftarTransaksi("Pengeluaran"));
                break;
        }
    }

    private void cetakDaftar(ArrayList<Transaksi> data) {
        if (data.isEmpty()) {
            System.out.println(">> Tidak ada data untuk ditampilkan.");
            return;
        }
        System.out.println(GARIS);
        for (Transaksi t : data) {
            System.out.println(t); // polymorphism: toString milik Pemasukan / Pengeluaran
        }
        System.out.println(GARIS);
    }

    private void updateData() {
        System.out.println("--- Update Transaksi ---");
        cetakDaftar(controller.getDaftarTransaksi());
        int id = input.inputInt("Masukkan ID transaksi yang ingin diupdate: ");

        Transaksi t = controller.cariTransaksi(id);
        if (t == null) {
            System.out.println(">> Data dengan ID tersebut tidak ditemukan.");
            return;
        }

        System.out.println("Data ditemukan: " + t);
        String keterangan = input.inputStringWajibIsi("Keterangan baru: ");
        double jumlah = input.inputDoublePositif("Jumlah baru (Rp): ");

        boolean sukses = controller.updateTransaksi(id, keterangan, jumlah);
        System.out.println(sukses ? ">> Transaksi berhasil diupdate." : ">> Gagal mengupdate transaksi.");
    }

    private void hapusData() {
        System.out.println("--- Hapus Transaksi ---");
        cetakDaftar(controller.getDaftarTransaksi());
        int id = input.inputInt("Masukkan ID transaksi yang ingin dihapus: ");

        boolean sukses = controller.hapusTransaksi(id);
        System.out.println(sukses ? ">> Transaksi berhasil dihapus." : ">> Data dengan ID tersebut tidak ditemukan.");
    }

    private void tampilkanRingkasan() {
        System.out.println("--- Ringkasan Keuangan ---");
        System.out.printf("Total Pemasukan   : Rp%,.2f%n", controller.hitungTotalPemasukan());
        System.out.printf("Total Pengeluaran : Rp%,.2f%n", controller.hitungTotalPengeluaran());
        System.out.printf("Saldo Akhir       : Rp%,.2f%n", controller.hitungSaldo());
    }
}

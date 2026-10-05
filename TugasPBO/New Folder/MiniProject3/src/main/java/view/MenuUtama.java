/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ProsesTransaksi;
import model.Transaksi;
import model.Pemasukan;
import model.Pengeluaran;
import java.util.Scanner;
/**
 *
 * @author MSI THIN 15
 */
public class MenuUtama { 
    private Scanner scanner;
    private InputHelper input;
    private ProsesTransaksi controller;
    
    public MenuUtama() {
        this.scanner = new Scanner(System.in);
        this.input = new InputHelper(scanner);
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
                    controller.tampilkanSemuaTransaksi();
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
        System.out.println("3. Lihat Semua Transaksi");
        System.out.println("4. Update Transaksi");
        System.out.println("5. Hapus Transaksi");
        System.out.println("6. Lihat Ringkasan Keuangan");
        System.out.println("7. Keluar");
        System.out.println("-----------------------------------------------------");
    }
 
    private void tambahPemasukan() {
        System.out.println("--- Tambah Pemasukan ---");
        String tanggal = input.inputTanggal("Tanggal (dd-mm-yyyy): ");
        String kategori = input.inputStringWajibIsi("Kategori (misal: Gaji, Bonus): ");
        String keterangan = input.inputStringWajibIsi("Keterangan: ");
        double jumlah = input.inputDoublePositif("Jumlah (Rp): ");
        String sumberDana = input.inputStringWajibIsi("Sumber dana: ");
 
        Pemasukan p = new Pemasukan(controller.getNextId(), tanggal, kategori, keterangan, jumlah, sumberDana);
        controller.tambahTransaksi(p);
    }
 
    private void tambahPengeluaran() {
        System.out.println("--- Tambah Pengeluaran ---");
        String tanggal = input.inputTanggal("Tanggal (dd-mm-yyyy): ");
        String kategori = input.inputStringWajibIsi("Kategori (misal: Makanan, Transportasi): ");
        String keterangan = input.inputStringWajibIsi("Keterangan: ");
        double jumlah = input.inputDoublePositif("Jumlah (Rp): ");
        String metode = input.inputStringWajibIsi("Metode pembayaran (Tunai/Transfer/Kartu): ");
 
        Pengeluaran p = new Pengeluaran(controller.getNextId(), tanggal, kategori, keterangan, jumlah, metode);
        controller.tambahTransaksi(p);
    }
 
    private void updateData() {
        System.out.println("--- Update Transaksi ---");
        controller.tampilkanSemuaTransaksi();
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
        controller.tampilkanSemuaTransaksi();
        int id = input.inputInt("Masukkan ID transaksi yang ingin dihapus: ");
 
        boolean sukses = controller.hapusTransaksi(id);
        System.out.println(sukses ? ">> Transaksi berhasil dihapus." : ">> Data dengan ID tersebut tidak ditemukan.");
    }
 
    private void tampilkanRingkasan() {
        double pemasukan = controller.hitungTotalPemasukan();
        double pengeluaran = controller.hitungTotalPengeluaran();
        double saldo = controller.hitungSaldo();
 
        System.out.println("--- Ringkasan Keuangan ---");
        System.out.printf("Total Pemasukan   : Rp%,.2f%n", pemasukan);
        System.out.printf("Total Pengeluaran : Rp%,.2f%n", pengeluaran);
        System.out.printf("Saldo Akhir       : Rp%,.2f%n", saldo);
    }
}    

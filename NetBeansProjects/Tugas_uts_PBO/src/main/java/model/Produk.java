/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author MSI THIN 15
 */
public abstract class Produk {

    private String kode;
    private String nama;
    private double hargaBeli;
    private int stok;

    public Produk(String kode, String nama, double hargaBeli, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.hargaBeli = hargaBeli;
        this.stok = stok;
    }

    public abstract String getKategori();
    public abstract double hitungHargaJual();
    public abstract String getInfoTambahan();

    public double hitungHargaJual(double diskonPersen) {
        if (diskonPersen <= 0) {
            return hitungHargaJual();
        }
        if (diskonPersen > 100) {
            diskonPersen = 100;
        }
        double harga = hitungHargaJual() * (1 - diskonPersen / 100.0);
        return Math.ceil(harga / 100.0) * 100;
    }

    /** Tambah stok biasa. */
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            stok += jumlah;
        }
    }

    /** Tambah stok + cetak catatan restock (overloading dengan parameter berbeda). */
    public void tambahStok(int jumlah, String catatan) {
        if (jumlah > 0) {
            stok += jumlah;
            System.out.println("  [Restock] " + nama + " +" + jumlah + " (" + catatan + ")");
        }
    }

    public double getTotalNilaiStok() {
        return hitungHargaJual() * stok;
    }

    public boolean kurangiStok(int jumlah) {
        if (jumlah <= 0 || jumlah > stok) {
            return false;
        }
        stok -= jumlah;
        return true;
    }

    public String getStatusStok() {
        if (stok == 0) return "HABIS";
        if (stok <= 5) return "MENIPIS";
        return "AMAN";
    }

    public String toBarisTabel() {
        return String.format("%-8s %-22s %-10s %14s %7d  %-8s",
                kode, potong(nama, 22), getKategori(),
                rupiah(hitungHargaJual()), stok, getStatusStok());
    }

    public void tampilkanDetail() {
        System.out.println("  Kode            : " + kode);
        System.out.println("  Nama Produk     : " + nama);
        System.out.println("  Kategori        : " + getKategori());
        System.out.println("  Harga Beli      : " + rupiah(hargaBeli));
        System.out.println("  Harga Jual      : " + rupiah(hitungHargaJual()));
        System.out.println("  Stok            : " + stok + " (" + getStatusStok() + ")");
        System.out.println("  Info Tambahan   : " + getInfoTambahan());
        System.out.println("  Nilai Stok      : " + rupiah(getTotalNilaiStok()));
    }

    @Override
    public String toString() {
        return kode + " - " + nama + " (" + getKategori() + ")";
    }

    public String getKode() {
        return kode; }

    public String getNama() { 
        return nama; }
    
    public void setNama(String nama) { 
        this.nama = nama; }

    public double getHargaBeli() {
        return hargaBeli; }
    
    public void setHargaBeli(double hargaBeli) { 
        this.hargaBeli = hargaBeli; }

    public int getStok() { 
        return stok; }
    
    public void setStok(int stok) { 
        this.stok = stok; }

    public static String rupiah(double nilai) {
        DecimalFormatSymbols simbol = new DecimalFormatSymbols(Locale.ROOT);
        simbol.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0", simbol);
        return "Rp" + df.format(nilai);
    }

    protected static String potong(String teks, int panjang) {
        if (teks == null) return "";
        return teks.length() <= panjang ? teks : teks.substring(0, panjang - 2) + "..";
    }
}

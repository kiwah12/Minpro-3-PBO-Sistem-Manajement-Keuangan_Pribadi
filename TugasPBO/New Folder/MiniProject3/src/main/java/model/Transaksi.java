/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MSI THIN 15
 */
public abstract class Transaksi {
    protected int id;
    protected String tanggal;
    protected String kategori;
    protected String keterangan;
    protected double jumlah;
 
    public Transaksi(int id, String tanggal, String kategori, String keterangan, double jumlah) {
        this.id = id;
        this.tanggal = tanggal;
        this.kategori = kategori;
        this.keterangan = keterangan;
        setJumlah(jumlah); 
    }

    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getTanggal() {
        return tanggal;
    }
 
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
 
    public String getKategori() {
        return kategori;
    }
 
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
 
    public String getKeterangan() {
        return keterangan;
    }
 
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
 
    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        if (jumlah < 0) {
            throw new IllegalArgumentException("Jumlah tidak boleh negatif.");
        }
        this.jumlah = jumlah;
    }

    public abstract double hitungPengaruhSaldo();
    
    public abstract String getJenis();

    @Override
    public String toString() {
        return String.format("%-4d %-12s %-12s %-15s %-20s Rp%,.2f",
                id, tanggal, getJenis(), kategori, keterangan, jumlah);
    }
}
 

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MSI THIN 15
 */
public class Pengeluaran extends Transaksi {
private String metodePembayaran;
 
    public Pengeluaran(int id, String tanggal, String kategori, String keterangan, double jumlah, String metodePembayaran) {
        super(id, tanggal, kategori, keterangan, jumlah);
        this.metodePembayaran = metodePembayaran;
    }
 
    public String getMetodePembayaran() {
        return metodePembayaran;
    }
 
    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    @Override
    public double hitungPengaruhSaldo() {
        return -jumlah;
    }
 
    @Override
    public String getJenis() {
        return "Pengeluaran";
    }
 
    @Override
    public String toString() {
        return super.toString() + String.format("  [Bayar: %s]", metodePembayaran);
    }
}
 

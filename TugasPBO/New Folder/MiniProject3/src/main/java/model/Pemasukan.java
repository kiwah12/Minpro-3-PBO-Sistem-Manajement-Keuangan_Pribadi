/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MSI THIN 15
 */
public class Pemasukan extends Transaksi {
private String sumberDana;
 
    public Pemasukan(int id, String tanggal, String kategori, String keterangan, double jumlah, String sumberDana) {
        super(id, tanggal, kategori, keterangan, jumlah); 
        this.sumberDana = sumberDana;
    }
 
    public String getSumberDana() {
        return sumberDana;
    }
 
    public void setSumberDana(String sumberDana) {
        this.sumberDana = sumberDana;
    }

    @Override
    public double hitungPengaruhSaldo() {
        return jumlah;
    }
 
    @Override
    public String getJenis() {
        return "Pemasukan";
    }

    @Override
    public String toString() {
        return super.toString() + String.format("  [Sumber: %s]", sumberDana);
    }
}
 


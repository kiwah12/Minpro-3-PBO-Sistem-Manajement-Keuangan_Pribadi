/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.Pemasukan;
import model.Pengeluaran;
import model.Transaksi;

/**
 *
 * @author MSI THIN 15
 */
public interface PengelolaTransaksi {
    int getNextId();

    void tambahTransaksi(Pemasukan p);
    void tambahTransaksi(Pengeluaran p);

    ArrayList<Transaksi> getDaftarTransaksi(); 
    ArrayList<Transaksi> getDaftarTransaksi(String jenis);

    Transaksi cariTransaksi(int id);
    boolean updateTransaksi(int id, String keterangan, double jumlah);
    boolean hapusTransaksi(int id);

    double hitungSaldo();
    double hitungTotalPemasukan();
    double hitungTotalPengeluaran();
}
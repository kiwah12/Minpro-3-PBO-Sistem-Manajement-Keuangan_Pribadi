/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.Transaksi;
import model.Pemasukan;
import model.Pengeluaran;
/**
 *
 * @author MSI THIN 15
 */
public class ProsesTransaksi {
    private ArrayList<Transaksi> daftarTransaksi;
    private int idCounter;
 
    public ProsesTransaksi() {
        this.daftarTransaksi = new ArrayList<>();
        this.idCounter = 1;
        isiDummyData();
    }

    private void isiDummyData() {
        daftarTransaksi.add(new Pemasukan(idCounter++, "01-09-2026", "Gaji", "Gaji bulan September", 5000000, "Kantor"));
        daftarTransaksi.add(new Pengeluaran(idCounter++, "05-09-2026", "Makanan", "Makan siang", 35000, "Tunai"));
    }
 
    public int getNextId() {
        return idCounter;
    }
    
    public void tambahTransaksi(Pemasukan p) {
        daftarTransaksi.add(p);
        idCounter++;
        System.out.println(">> Data pemasukan berhasil ditambahkan.");
    }
 
    public void tambahTransaksi(Pengeluaran p) {
        daftarTransaksi.add(p);
        idCounter++;
        System.out.println(">> Data pengeluaran berhasil ditambahkan.");
    }

    public void tampilkanSemuaTransaksi() {
        cetakDaftar(daftarTransaksi);
    }
 
    public void tampilkanTransaksi(String filterJenis) {
        ArrayList<Transaksi> hasil = new ArrayList<>();
        for (Transaksi t : daftarTransaksi) {
            if (t.getJenis().equalsIgnoreCase(filterJenis)) {
                hasil.add(t);
            }
        }
        cetakDaftar(hasil);
    }
 
    private void cetakDaftar(ArrayList<Transaksi> data) {
        if (data.isEmpty()) {
            System.out.println(">> Tidak ada data untuk ditampilkan.");
            return;
        }
        System.out.println("===================================================================================");

        for (Transaksi t : data) {

            System.out.println(t);
        }
        System.out.println("===================================================================================");
    }
 
    public Transaksi cariTransaksi(int id) {
        for (Transaksi t : daftarTransaksi) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
 
    public boolean updateTransaksi(int id, String keterangan, double jumlah) {
        Transaksi t = cariTransaksi(id);
        if (t == null) return false;
        t.setKeterangan(keterangan);
        t.setJumlah(jumlah);
        return true;
    }
 
    public boolean hapusTransaksi(int id) {
        for (int i = 0; i < daftarTransaksi.size(); i++) {
            if (daftarTransaksi.get(i).getId() == id) {
                daftarTransaksi.remove(i);
                return true;
            }
        }
        return false;
    }
 
    public double hitungSaldo() {
        double saldo = 0;
        for (Transaksi t : daftarTransaksi) {
            saldo += t.hitungPengaruhSaldo();
        }
        return saldo;
    }
 
    public double hitungTotalPemasukan() {
        double total = 0;
        for (Transaksi t : daftarTransaksi) {
            if (t instanceof Pemasukan) {
                total += t.hitungPengaruhSaldo();
            }
        }
        return total;
    }
 
    public double hitungTotalPengeluaran() {
        double total = 0;
        for (Transaksi t : daftarTransaksi) {
            if (t instanceof Pengeluaran) {
                total += Math.abs(t.hitungPengaruhSaldo());
            }
        }
        return total;
    }
 
    public ArrayList<Transaksi> getDaftarTransaksi() {
        return daftarTransaksi;
    }
}

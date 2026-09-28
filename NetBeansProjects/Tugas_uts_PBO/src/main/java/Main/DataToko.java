/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import model.Produk;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MSI THIN 15
 */
public class DataToko {

    private String namaToko;
    private String alamat;
    private final List<Produk> daftarProduk = new ArrayList<>();
    private double totalPendapatan = 0;

    public DataToko(String namaToko, String alamat) {
        this.namaToko = namaToko;
        this.alamat = alamat;
    }

    public boolean tambahProduk(Produk produk) {
        if (produk == null || cariByKode(produk.getKode()) != null) {
            return false;
        }
        return daftarProduk.add(produk);
    }

    public List<Produk> getSemuaProduk() {
        return daftarProduk;
    }

    public Produk cariByKode(String kode) {
        for (Produk p : daftarProduk) {
            if (p.getKode().equalsIgnoreCase(kode)) {
                return p;
            }
        }
        return null;
    }

    public List<Produk> cariProduk(String kataKunci) {
        List<Produk> hasil = new ArrayList<>();
        String kunci = kataKunci.toLowerCase();
        for (Produk p : daftarProduk) {
            if (p.getNama().toLowerCase().contains(kunci)
                    || p.getKode().toLowerCase().contains(kunci)) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    public List<Produk> cariProduk(double hargaMin, double hargaMax) {
        List<Produk> hasil = new ArrayList<>();
        for (Produk p : daftarProduk) {
            double harga = p.hitungHargaJual();
            if (harga >= hargaMin && harga <= hargaMax) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    public List<Produk> filterByKategori(String kategori) {
        List<Produk> hasil = new ArrayList<>();
        for (Produk p : daftarProduk) {
            if (p.getKategori().equalsIgnoreCase(kategori)) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    public boolean ubahProduk(String kode, String namaBaru, double hargaBeliBaru, int stokBaru) {
        Produk p = cariByKode(kode);
        if (p == null) return false;
        if (namaBaru != null && !namaBaru.isBlank()) p.setNama(namaBaru);
        if (hargaBeliBaru > 0) p.setHargaBeli(hargaBeliBaru);
        if (stokBaru >= 0) p.setStok(stokBaru);
        return true;
    }

    public boolean hapusProduk(String kode) {
        Produk p = cariByKode(kode);
        return p != null && daftarProduk.remove(p);
    }

    public double jualProduk(String kode, int jumlah) {
        return jualProduk(kode, jumlah, 0);
    }

    public double jualProduk(String kode, int jumlah, double diskonPersen) {
        Produk p = cariByKode(kode);
        if (p == null || !p.kurangiStok(jumlah)) {
            return -1;
        }
        double total = p.hitungHargaJual(diskonPersen) * jumlah;
        totalPendapatan += total;
        return total;
    }

    public double getTotalNilaiPersediaan() {
        double total = 0;
        for (Produk p : daftarProduk) {
            total += p.getTotalNilaiStok();
        }
        return total;
    }

    public List<Produk> getProdukStokMenipis() {
        List<Produk> hasil = new ArrayList<>();
        for (Produk p : daftarProduk) {
            if (p.getStok() <= 5) hasil.add(p);
        }
        return hasil;
    }

    public int hitungKategori(String kategori) {
        return filterByKategori(kategori).size();
    }

    public int getJumlahProduk() { return daftarProduk.size(); }
    public double getTotalPendapatan() { return totalPendapatan; }
    public String getNamaToko() { return namaToko; }
    public String getAlamat() { return alamat; }
}


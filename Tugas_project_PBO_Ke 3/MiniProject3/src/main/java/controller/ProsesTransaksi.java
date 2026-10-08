package controller;

import java.util.ArrayList;
import model.Pemasukan;
import model.Pengeluaran;
import model.Transaksi;

/**
 *
 * @author MSI THIN 15
 */
public class ProsesTransaksi implements TransaksiService {
    private ArrayList<Transaksi> daftarTransaksi;
    private int idCounter;

    public ProsesTransaksi() {
        this.daftarTransaksi = new ArrayList<>();
        this.idCounter = 1;
        isiDummyData();
    }

    private void isiDummyData() {
        daftarTransaksi.add(new Pemasukan(idCounter++, "01-09-2026", "Gaji", "Gaji bulan September", 5000000, "Kantor"));
        daftarTransaksi.add(new Pengeluaran(idCounter++, "05-09-2026", "Makan", "Makan siang", 35000, "Tunai"));
    }

    @Override
    public int getNextId() {
        return idCounter;
    }

    @Override
    public void tambahTransaksi(Pemasukan p) {
        daftarTransaksi.add(p);
        idCounter++;
    }

    @Override
    public void tambahTransaksi(Pengeluaran p) {
        daftarTransaksi.add(p);
        idCounter++;
    }

    @Override
    public ArrayList<Transaksi> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    @Override
    public ArrayList<Transaksi> getDaftarTransaksi(String jenis) {
        ArrayList<Transaksi> hasil = new ArrayList<>();
        for (Transaksi t : daftarTransaksi) {
            if (t.getJenis().equalsIgnoreCase(jenis)) {
                hasil.add(t);
            }
        }
        return hasil;
    }

    @Override
    public Transaksi cariTransaksi(int id) {
        for (Transaksi t : daftarTransaksi) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    @Override
    public boolean updateTransaksi(int id, String keterangan, double jumlah) {
        Transaksi t = cariTransaksi(id);
        if (t == null) return false;
        t.setKeterangan(keterangan);
        t.setJumlah(jumlah);
        return true;
    }

    @Override
    public boolean hapusTransaksi(int id) {
        Transaksi t = cariTransaksi(id);
        if (t == null) return false;
        daftarTransaksi.remove(t);
        return true;
    }

    @Override
    public double hitungSaldo() {
        double saldo = 0;
        for (Transaksi t : daftarTransaksi) {
            saldo += t.hitungPengaruhSaldo();
        }
        return saldo;
    }

    @Override
    public double hitungTotalPemasukan() {
        double total = 0;
        for (Transaksi t : daftarTransaksi) {
            if (t instanceof Pemasukan) {
                total += t.hitungPengaruhSaldo();
            }
        }
        return total;
    }

    @Override
    public double hitungTotalPengeluaran() {
        double total = 0;
        for (Transaksi t : daftarTransaksi) {
            if (t instanceof Pengeluaran) {
                total += Math.abs(t.hitungPengaruhSaldo());
            }
        }
        return total;
    }
}

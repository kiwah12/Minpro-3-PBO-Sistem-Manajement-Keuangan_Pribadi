package model;

/**
 *
 * @author MSI THIN 15
 */
public enum KategoriPengeluaran implements Kategori {
    MAKAN("Makan", false),
    TRANSPORTASI("Transportasi", false),
    LAINNYA("Lainnya", true);

    private final String nama;
    private final boolean lainnya;

    KategoriPengeluaran(String nama, boolean lainnya) {
        this.nama = nama;
        this.lainnya = lainnya;
    }

    @Override
    public String getNama() {
        return nama;
    }

    @Override
    public boolean isLainnya() {
        return lainnya;
    }
}

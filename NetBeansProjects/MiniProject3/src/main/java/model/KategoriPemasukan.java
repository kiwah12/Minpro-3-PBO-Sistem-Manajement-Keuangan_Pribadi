package model;

/**
 *
 * @author MSI THIN 15
 */
public enum KategoriPemasukan implements Kategori {
    GAJI("Gaji", false),
    BONUS("Bonus", false),
    LAINNYA("Lainnya", true);

    private final String nama;
    private final boolean lainnya;

    KategoriPemasukan(String nama, boolean lainnya) {
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

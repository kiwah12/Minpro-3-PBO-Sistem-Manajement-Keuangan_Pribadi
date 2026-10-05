/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MSI THIN 15
 */
public enum KatagoriPengeluaran implements Katagori {
    MAKAN("Makan", false),
    TRANSPORTASI("Transportasi", false),
    LAINNYA("Lainnya", true);

    private final String nama;
    private final boolean lainnya;

    KatagoriPengeluaran(String nama, boolean lainnya) {
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
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MSI THIN 15
 */
public class MinumanBersoda extends Minuman {

    public static final double CUKAI_BERPEMANIS = 500;
    public static final int BATAS_GULA_TINGGI = 20; 

    private int kadarGulaGram;

    public MinumanBersoda(String kode, String nama, double hargaBeli, int stok,
                          int volumeMl, boolean dingin, int kadarGulaGram) {
        super(kode, nama, hargaBeli, stok, volumeMl, dingin);
        this.kadarGulaGram = kadarGulaGram;
    }

    @Override
    public double hitungHargaJual() {
        return super.hitungHargaJual() + CUKAI_BERPEMANIS;
    }

    @Override
    public String getInfoTambahan() {
        String info = super.getInfoTambahan() + ", gula " + kadarGulaGram + " g";
        if (kadarGulaGram > BATAS_GULA_TINGGI) {
            info += " (TINGGI)";
        }
        return info;
    }

    public int getKadarGulaGram() { return kadarGulaGram; }
    public void setKadarGulaGram(int kadarGulaGram) { this.kadarGulaGram = kadarGulaGram; }
}


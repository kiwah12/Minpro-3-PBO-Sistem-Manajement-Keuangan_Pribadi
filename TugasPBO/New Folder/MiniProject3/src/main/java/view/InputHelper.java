/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;

/**
 *
 * @author MSI THIN 15
 */
public class InputHelper {
    private Scanner scanner;
 
    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }
 
    public int inputInt(String pesan) {
        System.out.print(pesan);
        while (!scanner.hasNextInt()) {
            System.out.print(">> Input harus berupa angka bulat, coba lagi: ");
            scanner.next();
        }
        int nilai = scanner.nextInt();
        scanner.nextLine();
        return nilai;
    }
 
    public int inputInt(String pesan, int min, int max) {
        int nilai;
        while (true) {
            nilai = inputInt(pesan);
            if (nilai >= min && nilai <= max) {
                break;
            }
            System.out.println(">> Input harus di antara " + min + " - " + max + ", coba lagi.");
        }
        return nilai;
    }
 
    public double inputDouble(String pesan) {
        System.out.print(pesan);
        while (!scanner.hasNextDouble()) {
            System.out.print(">> Input harus berupa angka, coba lagi: ");
            scanner.next();
        }
        double nilai = scanner.nextDouble();
        scanner.nextLine();
        return nilai;
    }

    public double inputDoublePositif(String pesan) {
        double nilai;
        while (true) {
            nilai = inputDouble(pesan);
            if (nilai > 0) {
                break;
            }
            System.out.println(">> Jumlah harus lebih besar dari 0, coba lagi.");
        }
        return nilai;
    }
 
    public String inputString(String pesan) {
        System.out.print(pesan);
        return scanner.nextLine();
    }
 
    public String inputStringWajibIsi(String pesan) {
        String nilai;
        while (true) {
            nilai = inputString(pesan);
            if (!nilai.trim().isEmpty()) {
                break;
            }
            System.out.println(">> Input tidak boleh kosong, coba lagi.");
        }
        return nilai;
    }
 
    public String inputTanggal(String pesan) {
        String nilai;
        while (true) {
            nilai = inputString(pesan);
            if (nilai.matches("\\d{2}-\\d{2}-\\d{4}")) {
                break;
            }
            System.out.println(">> Format tanggal harus dd-mm-yyyy, contoh: 21-09-2026. Coba lagi.");
        }
        return nilai;
    }
}

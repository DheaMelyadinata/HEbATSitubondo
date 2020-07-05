package com.example.hebatsitubondo.Admin;

public class ModelItem {
    String judul, gambar;
    public ModelItem(String judul, String gambar){
        this.judul = judul;
        this.gambar = gambar;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}

package com.example.hebatsitubondo.Orangtua;

public class AgendaKegiatanTM {
    private String judulkegiatan;
    private int imgKegiatan;

    public AgendaKegiatanTM(){
    }

    public AgendaKegiatanTM(String judulkegiatan, int imgKegiatan) {
        this.judulkegiatan = judulkegiatan;
        this.imgKegiatan = imgKegiatan;
    }

    public String getJudulkegiatan() {
        return judulkegiatan;
    }

    public void setJudulkegiatan(String judulkegiatan) {
        this.judulkegiatan = judulkegiatan;
    }

    public int getImgKegiatan() {
        return imgKegiatan;
    }

    public void setImgKegiatan(int imgKegiatan) {
        this.imgKegiatan = imgKegiatan;
    }
}

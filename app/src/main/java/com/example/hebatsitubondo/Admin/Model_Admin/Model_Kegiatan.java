package com.example.hebatsitubondo.Admin.Model_Admin;

public class Model_Kegiatan {

    private Integer idKegiatan;
    private String jenisKegiatan;
    private String namaKegiatan;
    private String waktu_kegiatan;
    private String tanggal_pelaksanaan;
    private String tempat;
    private String photoKegiatan;

    public Model_Kegiatan() {
    }

    public Model_Kegiatan(Integer idKegiatan, String jenisKegiatan, String namaKegiatan, String waktu_kegiatan,
                          String tanggal_pelaksanaan, String tempat, String photoKegiatan) {
        this.idKegiatan = idKegiatan;
        this.jenisKegiatan = jenisKegiatan;
        this.namaKegiatan = namaKegiatan;
        this.waktu_kegiatan = waktu_kegiatan;
        this.tanggal_pelaksanaan = tanggal_pelaksanaan;
        this.tempat = tempat;
        this.photoKegiatan = photoKegiatan;
    }

    public String getTanggal_pelaksanaan() {
        return tanggal_pelaksanaan;
    }

    public void setTanggal_pelaksanaan(String tanggal_pelaksanaan) {
        this.tanggal_pelaksanaan = tanggal_pelaksanaan;
    }

    public String getWaktu_kegiatan() {
        return waktu_kegiatan;
    }

    public void setWaktu_kegiatan(String waktu_kegiatan) {
        this.waktu_kegiatan = waktu_kegiatan;
    }

    public Integer getIdKegiatan() {
        return idKegiatan;
    }

    public void setIdKegiatan(Integer idKegiatan) {
        this.idKegiatan = idKegiatan;
    }

    public String getJenisKegiatan() {
        return jenisKegiatan;
    }

    public void setJenisKegiatan(String jenisKegiatan) {
        this.jenisKegiatan = jenisKegiatan;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getPhotoKegiatan() {
        return photoKegiatan;
    }

    public void setPhotoKegiatan(String photoKegiatan) {
        this.photoKegiatan = photoKegiatan;
    }
}

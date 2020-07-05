package com.example.hebatsitubondo.Admin.Model_Admin;

public class Model_Dokumentasi_Kegiatan {

    private Integer id_Dokumentasi;
    private String namaKegiatan;
    private String photoDokumentasi;
    private String deskripsiKegiatan;

    public Model_Dokumentasi_Kegiatan() {
    }

    public Model_Dokumentasi_Kegiatan(Integer id_Dokumentasi,String namaKegiatan, String photoDokumentasi, String deskripsiKegiatan) {
        this.id_Dokumentasi = id_Dokumentasi;
        this.namaKegiatan = namaKegiatan;
        this.photoDokumentasi = photoDokumentasi;
        this.deskripsiKegiatan = deskripsiKegiatan;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public Integer getId_Dokumentasi() {
        return id_Dokumentasi;
    }

    public void setId_Dokumentasi(Integer id_Dokumentasi) {
        this.id_Dokumentasi = id_Dokumentasi;
    }

    public String getPhotoDokumentasi() {
        return photoDokumentasi;
    }

    public void setPhotoDokumentasi(String photoDokumentasi) {
        this.photoDokumentasi = photoDokumentasi;
    }

    public String getDeskripsiKegiatan() {
        return deskripsiKegiatan;
    }

    public void setDeskripsiKegiatan(String deskripsiKegiatan) {
        this.deskripsiKegiatan = deskripsiKegiatan;
    }
}

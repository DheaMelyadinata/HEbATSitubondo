package com.example.hebatsitubondo.Admin.Model_Admin;

public class Model_Keluarga {
    private Integer id_keluarga_meta;
    private Integer id_keluarga;
    private Integer id_status_keluarga;
    private String nama;
    private String nama_status;
    private String nama_kepala_keluarga;
    private String alamat_keluarga;
    private String ttl;
    private String pekerjaan;
    private String no_handphone;

    public Model_Keluarga() {
    }

    public Model_Keluarga(Integer id_keluarga_meta, Integer id_keluarga, Integer id_status_keluarga, String nama, String nama_status,
                          String nama_kepala_keluarga, String alamat_keluarga, String ttl, String pekerjaan, String no_handphone) {
        this.id_keluarga_meta = id_keluarga_meta;
        this.id_keluarga = id_keluarga;
        this.id_status_keluarga = id_status_keluarga;
        this.nama = nama;
        this.nama_status = nama_status;
        this.nama_kepala_keluarga = nama_kepala_keluarga;
        this.alamat_keluarga = alamat_keluarga;
        this.ttl = ttl;
        this.pekerjaan = pekerjaan;
        this.no_handphone = no_handphone;
    }

    public Integer getId_keluarga_meta() {
        return id_keluarga_meta;
    }

    public void setId_keluarga_meta(Integer id_keluarga_meta) {
        this.id_keluarga_meta = id_keluarga_meta;
    }

    public Integer getId_keluarga() {
        return id_keluarga;
    }

    public void setId_keluarga(Integer id_keluarga) {
        this.id_keluarga = id_keluarga;
    }

    public Integer getId_status_keluarga() {
        return id_status_keluarga;
    }

    public void setId_status_keluarga(Integer id_status_keluarga) {
        this.id_status_keluarga = id_status_keluarga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama_status() {
        return nama_status;
    }

    public void setNama_status(String nama_status) {
        this.nama_status = nama_status;
    }

    public String getNama_kepala_keluarga() {
        return nama_kepala_keluarga;
    }

    public void setNama_kepala_keluarga(String nama_kepala_keluarga) {
        this.nama_kepala_keluarga = nama_kepala_keluarga;
    }

    public String getAlamat_keluarga() {
        return alamat_keluarga;
    }

    public void setAlamat_keluarga(String alamat_keluarga) {
        this.alamat_keluarga = alamat_keluarga;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getNo_handphone() {
        return no_handphone;
    }

    public void setNo_handphone(String no_handphone) {
        this.no_handphone = no_handphone;
    }
}
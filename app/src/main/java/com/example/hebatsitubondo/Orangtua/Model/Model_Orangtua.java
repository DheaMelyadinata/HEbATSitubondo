package com.example.hebatsitubondo.Orangtua.Model;

public class Model_Orangtua {
    private Integer id_orangtua;
    private String nama_ayah;
    private String pekerjaan_ayah;
    private String no_handphone_ayah;
    private String nama_ibu;
    private String pekerjaan_ibu;
    private String no_handphone_ibu;

    public Model_Orangtua() {
    }

    public Model_Orangtua(Integer id_orangtua, String nama_ayah, String pekerjaan_ayah,
                          String no_handphone_ayah, String nama_ibu, String pekerjaan_ibu, String no_handphone_ibu) {
        this.id_orangtua = id_orangtua;
        this.nama_ayah = nama_ayah;
        this.pekerjaan_ayah = pekerjaan_ayah;
        this.no_handphone_ayah = no_handphone_ayah;
        this.nama_ibu = nama_ibu;
        this.pekerjaan_ibu = pekerjaan_ibu;
        this.no_handphone_ibu = no_handphone_ibu;
    }

    public Integer getId_orangtua() {
        return id_orangtua;
    }

    public void setId_orangtua(Integer id_orangtua) {
        this.id_orangtua = id_orangtua;
    }

    public String getNama_ayah() {
        return nama_ayah;
    }

    public void setNama_ayah(String nama_ayah) {
        this.nama_ayah = nama_ayah;
    }

    public String getPekerjaan_ayah() {
        return pekerjaan_ayah;
    }

    public void setPekerjaan_ayah(String pekerjaan_ayah) {
        this.pekerjaan_ayah = pekerjaan_ayah;
    }

    public String getNo_handphone_ayah() {
        return no_handphone_ayah;
    }

    public void setNo_handphone_ayah(String no_handphone_ayah) {
        this.no_handphone_ayah = no_handphone_ayah;
    }

    public String getNama_ibu() {
        return nama_ibu;
    }

    public void setNama_ibu(String nama_ibu) {
        this.nama_ibu = nama_ibu;
    }

    public String getPekerjaan_ibu() {
        return pekerjaan_ibu;
    }

    public void setPekerjaan_ibu(String pekerjaan_ibu) {
        this.pekerjaan_ibu = pekerjaan_ibu;
    }

    public String getNo_handphone_ibu() {
        return no_handphone_ibu;
    }

    public void setNo_handphone_ibu(String no_handphone_ibu) {
        this.no_handphone_ibu = no_handphone_ibu;
    }
}

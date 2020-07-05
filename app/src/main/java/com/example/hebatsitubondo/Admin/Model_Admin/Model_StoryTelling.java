package com.example.hebatsitubondo.Admin.Model_Admin;

public class Model_StoryTelling {
    private Integer id_storytelling;
    private String nama_tema;
    private String nama_anak;
    private String judul_tema;
    private String cerita;

    public Model_StoryTelling() {
    }

    public Model_StoryTelling(Integer id_storytelling, String nama_tema, String nama_anak, String judul_tema, String cerita) {
        this.id_storytelling = id_storytelling;
        this.nama_tema = nama_tema;
        this.nama_anak = nama_anak;
        this.judul_tema = judul_tema;
        this.cerita = cerita;
    }

    public Integer getId_storytelling() {
        return id_storytelling;
    }

    public void setId_storytelling(Integer id_storytelling) {
        this.id_storytelling = id_storytelling;
    }

    public String getNama_tema() {
        return nama_tema;
    }

    public void setNama_tema(String nama_tema) {
        this.nama_tema = nama_tema;
    }

    public String getNama_anak() {
        return nama_anak;
    }

    public void setNama_anak(String nama_anak) {
        this.nama_anak = nama_anak;
    }

    public String getJudul_tema() {
        return judul_tema;
    }

    public void setJudul_tema(String judul_tema) {
        this.judul_tema = judul_tema;
    }

    public String getCerita() {
        return cerita;
    }

    public void setCerita(String cerita) {
        this.cerita = cerita;
    }
}

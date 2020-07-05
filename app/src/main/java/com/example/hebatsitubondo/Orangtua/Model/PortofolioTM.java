package com.example.hebatsitubondo.Orangtua.Model;

public class PortofolioTM {
    private Integer id_tema_meta;
    private String namaTema;
    private String namaAnak;
    private String usia;
    private String waktuKegiatan;
    private String lokasiKegiatan;
    private String aspekPerkembangan;

    public PortofolioTM() {
    }

    public PortofolioTM(Integer id_tema_meta, String namaTema, String namaAnak, String usia,
                        String waktuKegiatan, String lokasiKegiatan, String aspekPerkembangan) {
        this.id_tema_meta = id_tema_meta;
        this.namaTema = namaTema;
        this.namaAnak = namaAnak;
        this.usia = usia;
        this.waktuKegiatan = waktuKegiatan;
        this.lokasiKegiatan = lokasiKegiatan;
        this.aspekPerkembangan = aspekPerkembangan;
    }

    public Integer getId_tema_meta() {
        return id_tema_meta;
    }

    public void setId_tema_meta(Integer id_tema_meta) {
        this.id_tema_meta = id_tema_meta;
    }
}

package com.example.hebatsitubondo.Orangtua.Model;

import android.content.Context;
import android.widget.TextView;

import com.example.hebatsitubondo.R;

import java.io.Serializable;
import java.util.List;

public class TemaPortofolioTM  {
    private Integer idTema;
    private String namaTema;
    private String judulTema;
    private String tujuan;
    private String kegiatanTema;
    private String catatan;
    private String gambar_url;

    public TemaPortofolioTM(Integer idTema, String namaTema, String judulTema, String tujuan, String kegiatanTema, String catatan, String gambar_url) {
        this.idTema = idTema;
        this.namaTema = namaTema;
        this.judulTema = judulTema;
        this.tujuan = tujuan;
        this.kegiatanTema = kegiatanTema;
        this.catatan = catatan;
        this.gambar_url = gambar_url;
    }

    public TemaPortofolioTM() {

    }

    public Integer getIdTema() {
        return idTema;
    }

    public void setIdTema(Integer idTema) {
        this.idTema = idTema;
    }

    public String getNamaTema() {
        return namaTema;
    }

    public void setNamaTema(String namaTema) {
        this.namaTema = namaTema;
    }

    public String getJudulTema() {
        return judulTema;
    }

    public void setJudulTema(String judulTema) {
        this.judulTema = judulTema;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getKegiatanTema() {
        return kegiatanTema;
    }

    public void setKegiatanTema(String kegiatanTema) {
        this.kegiatanTema = kegiatanTema;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getGambar_url() {
        return gambar_url;
    }

    public void setGambar_url(String gambar_url) {
        this.gambar_url = gambar_url;
    }
}

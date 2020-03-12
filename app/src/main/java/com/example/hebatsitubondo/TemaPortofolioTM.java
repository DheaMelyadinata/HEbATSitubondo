package com.example.hebatsitubondo;

public class TemaPortofolioTM {
    private String tema, deskripsiTema;
    private int imgTema;

    public TemaPortofolioTM(){
    }

    public TemaPortofolioTM(String tema, String deskripsiTema, int imgTema) {
        this.tema = tema;
        this.deskripsiTema = deskripsiTema;
        this.imgTema = imgTema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDeskripsiTema() {
        return deskripsiTema;
    }

    public void setDeskripsiTema(String deskripsiTema) {
        this.deskripsiTema = deskripsiTema;
    }

    public int getImgTema() {
        return imgTema;
    }

    public void setImgTema(int imgTema) {
        this.imgTema = imgTema;
    }
}

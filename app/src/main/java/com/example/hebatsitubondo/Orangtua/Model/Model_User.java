package com.example.hebatsitubondo.Orangtua.Model;

public class Model_User {

    private Integer id_user;
    private String username;
    private String email;
    private String no_handphone;
    private String photo;
    private String status;

    public Model_User() {
    }

    public Model_User(Integer id_user, String username, String email, String no_handphone, String photo, String status) {
        this.id_user = id_user;
        this.username = username;
        this.email = email;
        this.no_handphone = no_handphone;
        this.photo = photo;
        this.status = status;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_handphone() {
        return no_handphone;
    }

    public void setNo_handphone(String no_handphone) {
        this.no_handphone = no_handphone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


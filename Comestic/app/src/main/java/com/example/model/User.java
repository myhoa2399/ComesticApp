package com.example.model;

import java.io.Serializable;

public class User implements Serializable {

    private String ma;
    private String ten;
    private String diaChi;
    private String email;
    private String sdt;


    public String getMa() {
        return ma;
    }

    public User(String ten, String diaChi, String email, String sdt) {
        this.ten = ten;
        this.diaChi = diaChi;
        this.email = email;
        this.sdt = sdt;
    }



    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }



    public User(){

    }

    public User(String ma, String ten, String diaChi, String email, String sdt) {
        this.ma = ma;
        this.ten = ten;
        this.diaChi = diaChi;
        this.email = email;
        this.sdt = sdt;
    }









}

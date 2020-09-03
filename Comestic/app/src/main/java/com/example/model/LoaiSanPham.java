package com.example.model;

import java.io.Serializable;

public class LoaiSanPham implements Serializable {
    private int maLoaiSP;
    private String tenLoaiSP;
    private String img;

    public LoaiSanPham(){

    }

    public LoaiSanPham(int maLoaiSP, String tenLoaiSP, String img) {
        this.maLoaiSP = maLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
        this.img = img;
    }

    public int getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(int maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

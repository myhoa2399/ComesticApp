package com.example.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int maSP;
    private int maLoaiSP;

    private String tenSP;
    private double gia;
    private String spURL;
    private String moTaSP;

    public SanPham(){

    }

    public SanPham(String tenSP, double gia, String moTaSP) {
        this.tenSP = tenSP;
        this.gia = gia;
        this.moTaSP = moTaSP;
    }

    public SanPham(int maSP, int maLoaiSP, String tenSP, double gia, String spURL, String moTaSP) {
        this.maSP = maSP;
        this.maLoaiSP = maLoaiSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.spURL = spURL;
        this.moTaSP = moTaSP;
    }


    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(int maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getSpURL() {
        return spURL;
    }

    public void setSpURL(String spURL) {
        this.spURL = spURL;
    }

    public String getMoTaSP() {
        return moTaSP;
    }

    public void setMoTaSP(String moTaSP) {
        this.moTaSP = moTaSP;
    }








}

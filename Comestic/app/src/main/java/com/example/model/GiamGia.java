package com.example.model;

import java.io.Serializable;
import java.util.Date;

public class GiamGia implements Serializable {
    private String maGiamGia;
    private String ngayBatDau;
    private String ngayKetThuc;
    private double phanTram;
    private String img;
    private String tieuDe;

    public GiamGia() {
    }

    public GiamGia(String maGiamGia, String ngayBatDau, String ngayKetThuc, double phanTram, String img, String tieuDe) {
        this.maGiamGia = maGiamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.phanTram = phanTram;
        this.img = img;
        this.tieuDe = tieuDe;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public double getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(double phanTram) {
        this.phanTram = phanTram;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

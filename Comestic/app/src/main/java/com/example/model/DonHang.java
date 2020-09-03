package com.example.model;

import java.util.Date;

public class DonHang {
    private String maDH;
    private String maKH;
    private String ngayDatHang;
    private double tongTienHang;
    private String maGiamGia;
    private double tienVanChuyen;
    private double tienGiamGia;
    private double tienThanhToan;
    private String diaChi;
    private int daGiao; // chưa giao là 0 , đã giao là 1

    public DonHang(){

    }

    public DonHang(String maDH, String maKH, String ngayDatHang,
                   double tongTienHang, String maGiamGia, double tienVanChuyen, double tienGiamGia, double tienThanhToan, String diaChi, int daGiao) {
        this.maDH = maDH;
        this.maKH = maKH;
        this.ngayDatHang = ngayDatHang;
        this.tongTienHang = tongTienHang;
        this.maGiamGia = maGiamGia;
        this.tienVanChuyen = tienVanChuyen;
        this.tienGiamGia = tienGiamGia;
        this.tienThanhToan = tienThanhToan;
        this.diaChi = diaChi;
        this.daGiao = daGiao;
    }

    public DonHang(String maKH, String ngayDatHang, double tongTienHang, String maGiamGia, double tienVanChuyen, double tienGiamGia, double tienThanhToan, String diaChi) {
        this.maKH = maKH;
        this.ngayDatHang = ngayDatHang;
        this.tongTienHang = tongTienHang;
        this.maGiamGia = maGiamGia;
        this.tienVanChuyen = tienVanChuyen;
        this.tienGiamGia = tienGiamGia;
        this.tienThanhToan = tienThanhToan;
        this.diaChi = diaChi;
        this.daGiao =0;
    }

    public DonHang(String maKH, String ngayDatHang, double tongTienHang, String maGiamGia, double tienVanChuyen, double tienGiamGia, double tienThanhToan) {
        this.maKH = maKH;
        this.ngayDatHang = ngayDatHang;
        this.tongTienHang = tongTienHang;
        this.maGiamGia = maGiamGia;
        this.tienVanChuyen = tienVanChuyen;
        this.tienGiamGia = tienGiamGia;
        this.tienThanhToan = tienThanhToan;
    }

    public DonHang(String maKH, String ngayDatHang, double tongTien, String maGiamGia, double tienVanChuyen) {
        this.maKH = maKH;
        this.ngayDatHang = ngayDatHang;
        this.tongTienHang = tongTien;
        this.maGiamGia = maGiamGia;
        this.tienVanChuyen = tienVanChuyen;
    }

    public DonHang(String maKH, String ngayDatHang, double tongTien, String maGiamGia) {
        this.maKH = maKH;
        this.ngayDatHang = ngayDatHang;
        this.tongTienHang = tongTien;
        this.maGiamGia = maGiamGia;
    }




    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(String ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public double getTongTienHang() {
        return tongTienHang;
    }

    public void setTongTienHang(double tongTienHang) {
        this.tongTienHang = tongTienHang;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public double getTienVanChuyen() {
        return tienVanChuyen;
    }

    public void setTienVanChuyen(double tienVanChuyen) {
        this.tienVanChuyen = tienVanChuyen;
    }

    public double getTienGiamGia() {
        return tienGiamGia;
    }

    public void setTienGiamGia(double tienGiamGia) {
        this.tienGiamGia = tienGiamGia;
    }

    public double getTienThanhToan() {
        return tienThanhToan;
    }

    public void setTienThanhToan(double tienThanhToan) {
        this.tienThanhToan = tienThanhToan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getDaGiao() {
        return daGiao;
    }

    public void setDaGiao(int daGiao) {
        this.daGiao = daGiao;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class HopDong {

    private String maHD, maND, maPT, ghiChu;
    private Date ngayTao, ngayBatDau, ngayHetHan;
    private double tienCuoc, tienThang, traTruocCon, tongTien;

    public HopDong() {
    }

    public HopDong(String maHD, String maND, String maPT, String ghiChu,
            Date ngayTao, Date ngayBatDau, Date ngayHetHan, double tienCuoc,
            double tienThang, double traTruocCon, double tongTien) {
        this.maHD = maHD;
        this.maND = maND;
        this.maPT = maPT;
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
        this.tienCuoc = tienCuoc;
        this.tienThang = tienThang;
        this.traTruocCon = traTruocCon;
        this.tongTien = tongTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getMaPT() {
        return maPT;
    }

    public void setMaPT(String maPT) {
        this.maPT = maPT;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public double getTienCuoc() {
        return tienCuoc;
    }

    public void setTienCuoc(double tienCuoc) {
        this.tienCuoc = tienCuoc;
    }

    public double getTienThang() {
        return tienThang;
    }

    public void setTienThang(double tienThang) {
        this.tienThang = tienThang;
    }

    public double getTraTruocCon() {
        return traTruocCon;
    }

    public void setTraTruocCon(double traTruocCon) {
        this.traTruocCon = traTruocCon;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}

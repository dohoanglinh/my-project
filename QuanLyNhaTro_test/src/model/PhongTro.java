/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.Date;

public class PhongTro {
    private String maPT,maLN,diaChi,moTa,lienHe;
    private float dienTich;
    private double giaPhong;
    private Date ngayDang;

    public PhongTro() {
    }

    public PhongTro(String maPT, String maLN, String diaChi, String moTa, 
            String lienHe, float dienTich, double giaPhong, 
            Date ngayDang) {
        this.maPT = maPT;
        this.maLN = maLN;
        this.diaChi = diaChi;
        this.moTa = moTa;
        this.lienHe = lienHe;
        this.dienTich = dienTich;
        this.giaPhong = giaPhong;
        this.ngayDang = ngayDang;
    }

    public String getMaPT() {
        return maPT;
    }

    public void setMaPT(String maPT) {
        this.maPT = maPT;
    }

    public String getMaLN() {
        return maLN;
    }

    public void setMaLN(String maLN) {
        this.maLN = maLN;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getLienHe() {
        return lienHe;
    }

    public void setLienHe(String lienHe) {
        this.lienHe = lienHe;
    }

    public float getDienTich() {
        return dienTich;
    }

    public void setDienTich(float dienTich) {
        this.dienTich = dienTich;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }
}

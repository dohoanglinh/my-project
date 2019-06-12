/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class NguoiThue {
    private String maND;
    private String tenND,diaChi,dienThoai,email,cmnd,hinh;
    private boolean gioiTinh;
    private int tuoi;

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public NguoiThue() {
    }

    public NguoiThue(String maND, String tenND, String diaChi,
            String dienThoai, String email, String cmnd,
            boolean gioiTinh, int tuoi) {
        this.maND = maND;
        this.tenND = tenND;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.email = email;
        this.cmnd = cmnd;
        this.gioiTinh = gioiTinh;
        this.tuoi = tuoi;
    }

    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getTenND() {
        return tenND;
    }
    
    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }
    
    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    } 
}

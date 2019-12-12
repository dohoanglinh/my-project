/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class NguoiQuanLy {
    private String maNQL;
    private String matKhau;
    private String tenNQL;
    private boolean vaiTro;
    public NguoiQuanLy() {
    }

    public NguoiQuanLy(String maNQL, String matKhau, String tenNQL, boolean vaiTro) {
        this.maNQL = maNQL;
        this.matKhau = matKhau;
        this.tenNQL = tenNQL;
        this.vaiTro = vaiTro;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }
   
    public String getMaNQL() {
        return maNQL;
    }

    public void setMaNQL(String maNQL) {
        this.maNQL = maNQL;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenNQL() {
        return tenNQL;
    }

    public void setTenNQL(String tenNQL) {
        this.tenNQL = tenNQL;
    }  
}

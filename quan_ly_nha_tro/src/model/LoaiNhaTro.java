/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class LoaiNhaTro {
    private String maLN;
    private String loaiNha;
    private String ghiChu;

    public LoaiNhaTro(String maLN, String loaiNha, String ghiChu) {
        this.maLN = maLN;
        this.loaiNha = loaiNha;
        this.ghiChu = ghiChu;
    }

    public LoaiNhaTro() {
    }

    public String getMaLN() {
        return maLN;
    }

    public void setMaLN(String maLN) {
        this.maLN = maLN;
    }

    public String getLoaiNha() {
        return loaiNha;
    }

    public void setLoaiNha(String loaiNha) {
        this.loaiNha = loaiNha;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}

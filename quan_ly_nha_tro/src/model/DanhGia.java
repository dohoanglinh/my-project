/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class DanhGia {

    private int STT;
    private String maND, maPT, danhGia, tenND;
    private boolean yeuThich;

    public DanhGia(int STT, String maND, String maPT, String danhGia,
            String tenND, boolean yeuThich) {
        this.STT = STT;
        this.maND = maND;
        this.maPT = maPT;
        this.danhGia = danhGia;
        this.tenND = tenND;
        this.yeuThich = yeuThich;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public DanhGia() {
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
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

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public boolean isYeuThich() {
        return yeuThich;
    }

    public void setYeuThich(boolean yeuThich) {
        this.yeuThich = yeuThich;
    }
}

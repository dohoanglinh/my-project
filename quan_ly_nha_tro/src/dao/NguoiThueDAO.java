/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import help.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.NguoiThue;

public class NguoiThueDAO {

    public static void AddNQL(NguoiThue nt) {
        String sql = "insert into NguoiDung values(?,?,?,?,?,?,?,?,?)";
        Jdbc.executeUpdate(sql,
                nt.getMaND(),
                nt.getTenND(),
                nt.isGioiTinh(),
                nt.getTuoi(),
                nt.getDiaChi(),
                nt.getDienThoai(),
                nt.getEmail(),
                nt.getCmnd(),
                nt.getHinh());
    }

    public static void deleteNQL(String maND) {
        String sql = "delete from NguoiDung where MaND=?";
        Jdbc.executeUpdate(sql, maND);

    }

    public static void UpdateNQL(NguoiThue nt) {
        String sql = "Update NguoiDung set TenND=?,GioiTinh=?,Tuoi=?,DiaChi=?,DienThoai=?,Email=?,CMND=?, Hinh=? where MaND=?";
        Jdbc.executeUpdate(sql,
                nt.getTenND(),
                nt.isGioiTinh(),
                nt.getTuoi(),
                nt.getDiaChi(),
                nt.getDienThoai(),
                nt.getEmail(),
                nt.getCmnd(),
                nt.getHinh(),
                nt.getMaND());
    }

    public static ArrayList<NguoiThue> select() throws Exception {
        ArrayList<NguoiThue> ds = new ArrayList<>();
        try (ResultSet rs = Jdbc.executeQuery("Select * from NguoiDung");) {

            while (rs.next()) {
                NguoiThue nt = new NguoiThue();
                nt.setMaND(rs.getString("MaND"));
                nt.setTenND(rs.getString("TenND"));
                nt.setGioiTinh(rs.getBoolean("GioiTinh"));
                nt.setTuoi(rs.getInt("Tuoi"));
                nt.setDiaChi(rs.getString("DiaChi"));
                nt.setDienThoai(rs.getString("DienThoai"));
                nt.setEmail(rs.getString("Email"));
                nt.setCmnd(rs.getString("CMND"));
                nt.setHinh(rs.getString("Hinh"));
                ds.add(nt);
            }
        }
        return ds;
    }
}

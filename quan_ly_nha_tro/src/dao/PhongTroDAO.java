/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import help.General;
import help.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.PhongTro;

public class PhongTroDAO {

    public static void AddPT(PhongTro pt) {
        String sql = "insert into PhongTro values(?,?,?,?,?,?,?,?)";
        Jdbc.executeUpdate(sql,
                pt.getMaPT(),
                pt.getMaLN(),
                pt.getDienTich(),
                pt.getGiaPhong(),
                pt.getDiaChi(),
                pt.getMoTa(),
                General.convertFromJAVADateToSQLDate(pt.getNgayDang()),
                pt.getLienHe()
        );
    }

    public static void deletePT(String maPT) {
        String sql = "delete from PhongTro where MaPT=?";
        Jdbc.executeUpdate(sql, maPT);
    }

    public static void UpdatePT(PhongTro pt) {
        String sql = "Update PhongTro set MaLoaiNha=?,DienTich=?,GiaPhong=?,DiaChi=?,MoTa=?,NgayDangTin=?,LienHe=? where MaPT=?";
        Jdbc.executeUpdate(sql,
                pt.getMaLN(),
                pt.getDienTich(),
                pt.getGiaPhong(),
                pt.getDiaChi(),
                pt.getMoTa(),
                General.convertFromJAVADateToSQLDate(pt.getNgayDang()),
                pt.getLienHe(),
                pt.getMaPT()
        );
    }

    public static ArrayList<PhongTro> select() throws Exception {
        ArrayList<PhongTro> ds = new ArrayList<>();
        try (ResultSet rs = Jdbc.executeQuery("Select * from PhongTro");) {

            while (rs.next()) {
                PhongTro ql = new PhongTro();
                ql.setMaPT(rs.getString("MaPT"));
                ql.setMaLN(rs.getString("MaLoaiNha"));
                ql.setDienTich(rs.getFloat("DienTich"));
                ql.setGiaPhong(rs.getDouble("GiaPhong"));
                ql.setDiaChi(rs.getString("DiaChi"));
                ql.setMoTa(rs.getString("MoTa"));
                ql.setNgayDang(rs.getDate("NgayDangTin"));
                ql.setLienHe(rs.getString("LienHe"));
                ds.add(ql);
            }
        }
        return ds;
    }

}

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
import model.HopDong;

public class HopDongDAO {

    public static void AddHD(HopDong nt) {
        String sql = "insert into HopDong values(?,?,?,?,?,?,?,?,?,?,?)";
        Jdbc.executeUpdate(sql,
                nt.getMaHD(),
                nt.getMaND(),
                nt.getMaPT(),
                General.convertFromJAVADateToSQLDate(nt.getNgayTao()),
                General.convertFromJAVADateToSQLDate(nt.getNgayBatDau()),
                General.convertFromJAVADateToSQLDate(nt.getNgayHetHan()),
                nt.getTienCuoc(),
                nt.getTienThang(),
                nt.getTraTruocCon(),
                nt.getTongTien(),
                nt.getGhiChu());
    }

    public static void deleteHD(String maHD) {
        String sql = "delete from HopDong where MaHD=?";
        Jdbc.executeUpdate(sql, maHD);
    }

    public static void UpdateHD(HopDong nt) {
        String sql = "Update HopDong set MaND=?,MaPT=?,NgayTao=?,NgayBatDau=?,NgayHetHan=?,TienCuoc=?,"
                + "TienTraTheoThang=?,TraTruocCon=?,TongTien=?,GhiChu=? where MaHD=?";
        Jdbc.executeUpdate(sql,
                nt.getMaND(),
                nt.getMaPT(),
                General.convertFromJAVADateToSQLDate(nt.getNgayTao()),
                General.convertFromJAVADateToSQLDate(nt.getNgayBatDau()),
                General.convertFromJAVADateToSQLDate(nt.getNgayHetHan()),
                nt.getTienCuoc(),
                nt.getTienThang(),
                nt.getTraTruocCon(),
                nt.getTongTien(),
                nt.getGhiChu(),
                nt.getMaHD());
    }

    public static ArrayList<HopDong> select(String MaPT) throws Exception {
        ArrayList<HopDong> ds = new ArrayList<>();
        String sql = "Select * from HopDong";
        if (MaPT.length() != 0) {
            sql = sql + " where MaPT like '" + MaPT + "'";
        }
        try (ResultSet rs = Jdbc.executeQuery(sql);) {

            while (rs.next()) {
                HopDong nt = new HopDong();
                nt.setMaHD(rs.getString("MaHD"));
                nt.setMaND(rs.getString("MaND"));
                nt.setMaPT(rs.getString("MaPT"));
                nt.setNgayTao(rs.getDate("NgayTao"));
                nt.setNgayBatDau(rs.getDate("NgayBatDau"));
                nt.setNgayHetHan(rs.getDate("NgayHetHan"));
                nt.setTienCuoc(rs.getDouble("TienCuoc"));
                nt.setTienThang(rs.getDouble("TienTraTheoThang"));
                nt.setTraTruocCon(rs.getDouble("TraTruocCon"));
                nt.setTongTien(rs.getDouble("TongTien"));
                nt.setGhiChu(rs.getString("GhiChu"));
                ds.add(nt);
            }
        }
        return ds;
    }

    public static HopDong selectByMaPT(String MaPT) throws Exception {
        return select(MaPT).get(0);
    }

    public static double autoFill(String MaPT) throws Exception {
        String sql = "Select GiaPhong from PhongTro where MaPT=?";
        ResultSet rs = Jdbc.execute(sql, MaPT);
        rs.next();
        return rs.getDouble("GiaPhong");
    }

}

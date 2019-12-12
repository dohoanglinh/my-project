/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import help.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DanhGia;

public class DanhGiaDAO {

    public static void AddDG(DanhGia nt) {
        String sql = "insert into DanhGia values(?,?,?,?)";
        Jdbc.executeUpdate(sql,
                nt.getMaND(),
                nt.getMaPT(),
                nt.isYeuThich(),
                nt.getDanhGia());
    }

    public static ArrayList<DanhGia> select(String MaPT) throws Exception {
        ArrayList<DanhGia> ds = new ArrayList<>();
        try (ResultSet rs = Jdbc.execute("Select A.MaND,A.DanhGia,A.YeuThich,B.TenND from DanhGia A "
                + "join NguoiDung B on A.MaND=B.MaND where A.MaPT=?", MaPT)) {
            while (rs.next()) {
                DanhGia ln = new DanhGia();
                ln.setMaND(rs.getString("MaND"));
                ln.setDanhGia(rs.getString("DanhGia"));
                if (rs.getBoolean("YeuThich") == true) {
                    ln.setYeuThich(true);
                };
                ln.setTenND(rs.getString("TenND"));
                ds.add(ln);
            }
        }
        return ds;
    }

    public static void updateDG(DanhGia dg) {
        String sql = "Update DanhGia set YeuThich=?,DanhGia=? where MaND=? and MaPT=?";
        Jdbc.executeUpdate(sql,
                dg.isYeuThich(),
                dg.getDanhGia(),
                dg.getMaND(),
                dg.getMaPT()
        );
    }

    public static void deleteDG(String maPT, String maND) {
        String sql = "Delete from DanhGia where MaND=? and MaPT=?";
        Jdbc.executeUpdate(sql,
                maND,
                maPT
        );
    }
}

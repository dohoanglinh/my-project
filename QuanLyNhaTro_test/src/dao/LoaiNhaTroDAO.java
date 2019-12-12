/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import help.Jdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.LoaiNhaTro;

public class LoaiNhaTroDAO {

    public static void AddLNT(LoaiNhaTro ln) {
        String sql = "insert into LoaiNha values(?,?,?)";
        Jdbc.executeUpdate(sql,
                ln.getMaLN(),
                ln.getLoaiNha(),
                ln.getGhiChu()
        );
    }

    public static void deleteLNT(String maLNT) {
        String sql = "delete from LoaiNha where MaLoaiNha=?";
        Jdbc.executeUpdate(sql, maLNT);
    }

    public static void UpdateLNT(LoaiNhaTro ln) {
        String sql = "Update LoaiNha set LoaiNhaTro=?,GhiChu=? where MaLoaiNha=?";
        Jdbc.executeUpdate(sql,
                ln.getMaLN(),
                ln.getGhiChu(),
                ln.getMaLN()
        );
    }

    public static ArrayList<LoaiNhaTro> select() throws Exception {
        ArrayList<LoaiNhaTro> ds = new ArrayList<>();
        try (ResultSet rs = Jdbc.executeQuery("Select * from LoaiNha");) {

            while (rs.next()) {
                LoaiNhaTro ln = new LoaiNhaTro();
                ln.setMaLN(rs.getString("MaLoaiNha"));
                ln.setLoaiNha(rs.getString("LoaiNhaTro"));
                ln.setGhiChu(rs.getString("GhiChu"));
                ds.add(ln);
            }
        }
        return ds;
    }

    public static String autoFill(String lnt) throws Exception {
        String sql = "Select MaLoaiNha from LoaiNha where LoaiNhaTro=?";
        ResultSet rs = Jdbc.execute(sql, lnt);
        rs.next();
        return rs.getString(1);
    }
}

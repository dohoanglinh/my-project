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
import model.NguoiQuanLy;

public class NguoiQuanLyDAO {

    public static void AddNQL(NguoiQuanLy ql) {
        String sql = "insert into NguoiQuanLy values(?,?,?,?)";
        Jdbc.executeUpdate(sql,
                ql.getMaNQL(),
                General.encoding(ql.getMatKhau()),
                ql.getTenNQL(),
                ql.isVaiTro()
        );
    }

    public static void deleteNQL(String maNQL) {
        String sql = "delete from NguoiQuanLy where MaNQL=?";
        Jdbc.executeUpdate(sql, maNQL);
    }

    public static void UpdateNQL(NguoiQuanLy ql) {
        String sql = "Update NguoiQuanLy set MatKhau=?,TenNQL=?, VaiTro=? where MaNQL=?";
        Jdbc.executeUpdate(sql,
                General.encoding(ql.getMatKhau()),
                ql.getTenNQL(),
                ql.isVaiTro(),
                ql.getMaNQL()
                
        );
    }

    public static ArrayList<NguoiQuanLy> select() throws Exception {
        ArrayList<NguoiQuanLy> ds = new ArrayList<>();
        try (ResultSet rs = Jdbc.executeQuery("Select * from NguoiQuanLy");) {
            while (rs.next()) {
                NguoiQuanLy ql = new NguoiQuanLy();
                ql.setMaNQL(rs.getString("MaNQL"));
                ql.setMatKhau(rs.getString("MatKhau"));
                ql.setTenNQL(rs.getString("TenNQL"));
                ql.setVaiTro(rs.getBoolean("VaiTro"));
                ds.add(ql);
            }
        }
        return ds;
    }
    
}

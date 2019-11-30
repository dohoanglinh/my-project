/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.HopDongDAO;
import dao.NguoiThueDAO;
import dao.PhongTroDAO;
import help.General;
import help.inWord;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.HopDong;
import model.NguoiThue;
import model.PhongTro;

/**
 *
 * @author Admin
 */
public class HopDongFrame extends javax.swing.JFrame {

    String tittle[] = {"MaHD", "MaND", "MaPT", "NgayTao", "NgayBatDau", "NgayHetHan", "TienCoc", "TienThang", "TraCon", "TongTien", "GhiChu"};
    DefaultTableModel model = new DefaultTableModel(tittle, 0);
    int index = 0;
    String TenND = null;
    String NgaySinh = null;
    String CMND = null;
    String diachi = null;
    String sdt = null;

    public HopDongFrame() {
        initComponents();
        setIconImage(General.AppIcon());
        init();
        showHD(0);
        showOnTable();
        fillCboPT();
        fillCboND();
    }

    void init() {
        setLocationRelativeTo(this);

        

    }

    public void showHD(int index) {
        try {
            ArrayList<HopDong> ds = HopDongDAO.select("");
            if (ds.size() == 1) {
                btnBack.setEnabled(false);
                btnNext.setEnabled(false);
            } else if (index <= 0) {
                btnBack.setEnabled(false);
                btnNext.setEnabled(true);
                index = 0;
            } else if (index >= ds.size() - 1) {
                btnBack.setEnabled(true);
                btnNext.setEnabled(false);
                index = ds.size() - 1;
            } else {
                btnBack.setEnabled(true);
                btnNext.setEnabled(true);
            }
            setModel(ds.get(index));
            autoFillTienThang();
            txtTongTien.setText(General.formatNumber(autoFillTongtien()));
            txtTongTien.setToolTipText(autoFillTongtien() + "");
            setStatus(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showOnTable() {
        model.setRowCount(0);
        try {
            ArrayList<HopDong> ds = HopDongDAO.select("");
            for (HopDong hd : ds) {
                Object row[] = {
                    hd.getMaHD(),
                    hd.getMaND(),
                    hd.getMaPT(),
                    hd.getNgayTao(),
                    hd.getNgayBatDau(),
                    hd.getNgayHetHan(),
                    General.formatNumber(hd.getTienCuoc()),
                    General.formatNumber(hd.getTienThang()),
                    General.formatNumber(hd.getTraTruocCon()),
                    General.formatNumber(hd.getTongTien()),
                    hd.getGhiChu()
                };
                model.addRow(row);
            }
            tblHD.setModel(model);
        } catch (Exception e) {
        }
    }

    public void fillCboPT() {
        try {
            ArrayList<PhongTro> ds = PhongTroDAO.select();
            for (PhongTro pt : ds) {
                cboMaPT.addItem(pt.getMaPT());
            }
        } catch (Exception e) {
        }
    }

    public void fillCboND() {
        try {
            ArrayList<NguoiThue> ds = NguoiThueDAO.select();
            for (NguoiThue nt : ds) {
                cboMaND1.addItem(nt.getMaND());
            }
        } catch (Exception e) {
        }
    }

    public void setModel(HopDong hd) {
        txtMaHD.setText(hd.getMaHD());
        cboMaND1.setSelectedItem(hd.getMaND());
        cboMaPT.setSelectedItem(hd.getMaPT());
        dchNgayTao.setDate(hd.getNgayTao());
        dchNgayBatDau.setDate(hd.getNgayBatDau());
        dchNgayHetHan.setDate(hd.getNgayHetHan());
        txtTienCoc.setText(General.formatNumber(hd.getTienCuoc()));
        txtTienCoc.setToolTipText(hd.getTienCuoc() + "");
        txtTienThang.setText(General.formatNumber(hd.getTienThang()));
        txtTienThang.setToolTipText(hd.getTienThang() + "");
        txtTraCon.setText(General.formatNumber(hd.getTraTruocCon()));
        txtTraCon.setToolTipText(hd.getTraTruocCon() + "");
        txtTongTien.setText(General.formatNumber(hd.getTienCuoc() + hd.getTienThang() + hd.getTraTruocCon()));
        txtTongTien.setToolTipText(hd.getTienCuoc() + hd.getTienThang() + hd.getTraTruocCon() + "");
    }

    public HopDong getModel() {
        HopDong hd = new HopDong();
        hd.setMaHD(txtMaHD.getText());
        hd.setMaND(cboMaND1.getSelectedItem() + "");
        hd.setMaPT(cboMaPT.getSelectedItem() + "");
        hd.setNgayTao(dchNgayTao.getDate());
        hd.setNgayBatDau(dchNgayBatDau.getDate());
        hd.setNgayHetHan(dchNgayHetHan.getDate());
        hd.setTienCuoc(Double.parseDouble(txtTienCoc.getText()));
        hd.setTienThang(Double.parseDouble(txtTienThang.getToolTipText()));
        hd.setTraTruocCon(Double.parseDouble(txtTraCon.getToolTipText()));
        hd.setTongTien(Double.parseDouble(txtTongTien.getToolTipText()));
        hd.setGhiChu(txtGhiChu.getText());
        return hd;
    }

    public void autoFillTienThang() {
        try {
            double money = HopDongDAO.autoFill(cboMaPT.getSelectedItem() + "");
            txtTienThang.setText(General.formatNumber(money));
            txtTienThang.setToolTipText(money + "");
        } catch (Exception e) {
        }
    }

    public void autoND() {
        try {
            ArrayList<NguoiThue> ds = NguoiThueDAO.selectauto(cboMaND1.getSelectedItem() + "");
            for (NguoiThue nt : ds) {
                Object[] row = {
                    nt.getMaND(),
                    nt.getTenND(),
                    nt.isGioiTinh() ? "Nam" : "Nữ",
                    nt.getTuoi(),
                    nt.getDiaChi(),
                    nt.getDienThoai(),
                    nt.getEmail(),
                    nt.getCmnd(),
                    nt.getHinh()
                };
                TenND = nt.getTenND();
                NgaySinh = String.valueOf(nt.getTuoi());
                CMND = nt.getCmnd();
                diachi = nt.getDiaChi();
                sdt = nt.getDienThoai();
            }
        } catch (Exception e) {
        }
    }

    public double autoFillTongtien() {
        double money = 0;
        try {
            Date batDau = dchNgayBatDau.getDate();
            Date ketThuc = dchNgayHetHan.getDate();
            double tienThang = Double.parseDouble(txtTienThang.getToolTipText());

//            if (ketThuc.getYear() - batDau.getYear() < 2) {
//                if (ketThuc.getMonth() - batDau.getMonth() < 2) {
//                    if (ketThuc.getMonth() - batDau.getMonth() == 1) {
//                        money = tienThang;
//                    } else {
//                        int songay = (ketThuc.getDate() - batDau.getDate());
//                        money = (tienThang / 30) * songay;
//                    }
//
//                }
//                else if (ketThuc.getMonth() - batDau.getMonth() >= 2) {
//                    int sothang = (ketThuc.getMonth()- batDau.getMonth());
//                 int songay = (ketThuc.getDate() - batDau.getDate());
//                        money = (tienThang / 30) * songay * sothang;
//
//                }
//            }
            if (ketThuc.getYear() - batDau.getYear() < 2) {
                if (ketThuc.getDate() - batDau.getDate() < 2) {
                    money = (ketThuc.getMonth() - batDau.getMonth() + 13) * tienThang;
                } else {
                    money = (ketThuc.getMonth() - batDau.getMonth() + 12) * tienThang;
                }
            } else {
                if (ketThuc.getMonth() < batDau.getMonth()) {
                    money = (((ketThuc.getYear() - batDau.getYear()) * 12 - (batDau.getMonth() - ketThuc.getMonth())) * tienThang);
                } else {
                    money = (((ketThuc.getYear() - batDau.getYear()) * 12 + (ketThuc.getMonth() - batDau.getMonth())) * tienThang);
                }
            }
        } catch (Exception e) {
        }
        return money;
    }

    public void addHD() {
        try {
            HopDong hd = getModel();
            HopDongDAO.AddHD(hd);
            showOnTable();
            JOptionPane.showMessageDialog(this, "Tạo hợp đồng thành công!");
            index = tblHD.getRowCount();
            showHD(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeHD() {
        try {
            String maHD = txtMaHD.getText();
            HopDongDAO.deleteHD(maHD);
            showOnTable();
            JOptionPane.showMessageDialog(this, "Xóa hợp đồng thành công!");
            index--;
            showHD(index);
        } catch (Exception e) {
        }
    }

    public void updateHD() {
        try {
            HopDong hd = getModel();
            HopDongDAO.UpdateHD(hd);
            showOnTable();
            JOptionPane.showMessageDialog(this, "Cập nhật hợp đồng thành công!");
        } catch (Exception e) {
        }
    }

    public void setStatus(boolean isEnable) {
        btnAdd.setEnabled(isEnable);
        btnDelete.setEnabled(!isEnable);
        btnUpdate.setEnabled(!isEnable);
        txtMaHD.setEnabled(isEnable);
        txtMaHD.setBackground(Color.white);
        dchNgayBatDau.setBackground(Color.white);
        dchNgayHetHan.setBackground(Color.white);
        dchNgayTao.setBackground(Color.white);
        txtTienCoc.setBackground(Color.white);

    }

    public boolean validateForm(boolean isUpdated) {
        boolean checkError = true;
        String maHD = txtMaHD.getText();
        Date batDau = dchNgayBatDau.getDate();
        Date ketThuc = dchNgayHetHan.getDate();
        String reMaHD = "HD[0-9]{3}";
        /////////////////////////////////////////////////////////////////////
        if (isUpdated) {
        } else if (maHD.isEmpty()) {
            checkError = focus(txtMaHD);
        } else if (!maHD.matches(reMaHD)) {
            checkError = focus(txtMaHD);
            JOptionPane.showMessageDialog(this, "Wrong MaHD pattern! (For instance: HD001)", "Error", 0);
        } else {
            try {
                ArrayList<HopDong> ds = HopDongDAO.select("");
                for (HopDong hd : ds) {
                    if (hd.getMaHD().equals(maHD)) {
                        checkError = focus(txtMaHD);
                        JOptionPane.showMessageDialog(this, "MaHD: " + maHD + " is already created!", "Error", 0);
                        break;
                    } else {
                        checkError = unfocus(txtMaHD);
                    }
                }

            } catch (Exception e) {
            }
        }
        //////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////
        if (cboMaPT.getSelectedIndex() < 0) {
            checkError = false;
            JOptionPane.showMessageDialog(this, "Please choose MaPT!", "Error", 0);
        }
        if (cboMaND1.getSelectedIndex() < 0) {
            checkError = false;
            JOptionPane.showMessageDialog(this, "Please choose MaPT!", "Error", 0);
        }
        ///////////////////////////////////////////////////////////////////////
        if (dchNgayTao.getDate() == null) {
            checkError = false;
            JOptionPane.showMessageDialog(this, "Please choose NgayTao!", "Error", 0);
            dchNgayTao.setBackground(Color.pink);
        } else if (dchNgayTao.getDate().getTime() > batDau.getTime()) {
            checkError = false;
            JOptionPane.showMessageDialog(this, "NgayTao must be lower than ngayBatDau!", "Error", 0);
            dchNgayTao.setBackground(Color.pink);
        } else {
            dchNgayTao.setBackground(Color.white);
        }
        ///////////////////////////////////////////////////////////////////////
        if (batDau == null) {
            checkError = false;
            JOptionPane.showMessageDialog(this, "Please choose NgayBatDau!", "Error", 0);
            dchNgayBatDau.setBackground(Color.pink);

        } else {
            dchNgayBatDau.setBackground(Color.white);
        }
        ///////////////////////////////////////////////////////////////////////
        if (ketThuc == null) {
            checkError = false;
            JOptionPane.showMessageDialog(this, "Please choose NgayHetHan!", "Error", 0);
            dchNgayHetHan.setBackground(Color.pink);
        } else if (ketThuc.getYear() - batDau.getYear() < 0) {
            checkError = false;
            dchNgayHetHan.setBackground(Color.pink);
            JOptionPane.showMessageDialog(this, "HopDong must last over a year!", "Error", 0);
        } else if (ketThuc.getYear() - batDau.getYear() < 2) {
            if (ketThuc.getMonth() >= batDau.getMonth()) {
                dchNgayHetHan.setBackground(Color.white);
            } else if (batDau.getMonth() - ketThuc.getMonth() == 1) {
                if (ketThuc.getDate() - batDau.getDate() >= 15) {
                    dchNgayHetHan.setBackground(Color.white);
                } else {
                    checkError = false;
                    dchNgayHetHan.setBackground(Color.pink);
                    JOptionPane.showMessageDialog(this, "HopDong must last over a year!", "Error", 0);
                }
            } else {
                checkError = false;
                dchNgayHetHan.setBackground(Color.pink);
                JOptionPane.showMessageDialog(this, "HopDong must last over a year!", "Error", 0);
            }
        } else {
            dchNgayHetHan.setBackground(Color.white);
        }
        ///////////////////////////////////////////////////////////////////////
        try {
            double tienCoc = Double.parseDouble(txtTienCoc.getText());
            double tienThang = Double.parseDouble(txtTienThang.getToolTipText());
            if (tienCoc < tienThang) {
                checkError = true;
                txtTienCoc.setBackground(Color.pink);
                JOptionPane.showMessageDialog(this, "TienCoc must equal or less than tienThang!", "Error", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "TienCoc is not valid! (Number is required. For instance: 100000!)", "Error", 0);
            checkError = false;
        }
        return checkError;
    }

    public boolean focus(JTextField txt) {
        txt.setBackground(Color.pink);
        txt.requestFocus();
        return false;
    }

    public boolean unfocus(JTextField txt) {
        txt.setBackground(Color.white);
        return true;
    }

    public void inHD() {
        try {
            
//                inWord inword = new inWord();
//                inword.CreateContract(TenND, txtMaHD.getText(), txtGhiChu.getText(), NgaySinh, CMND, diachi, sdt);
        } catch (Exception e) {
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tabHopDong = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dchNgayTao = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        dchNgayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTienCoc = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTraCon = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTienThang = new javax.swing.JTextField();
        dchNgayHetHan = new com.toedter.calendar.JDateChooser();
        cboMaPT = new javax.swing.JComboBox<String>();
        jButton1 = new javax.swing.JButton();
        cboMaND1 = new javax.swing.JComboBox<String>();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHD = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Hợp đồng");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("HỢP ĐỒNG");

        tabHopDong.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabHopDong.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabHopDong.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabHopDong.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Mã hợp đồng:");

        txtMaHD.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Mã người dùng:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Mã phòng trọ:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Ngày tạo:");

        dchNgayTao.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Ngày bắt đầu:");

        dchNgayBatDau.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        dchNgayBatDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dchNgayBatDauPropertyChange(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Ngày hết hạn:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Tiền cọc:");

        txtTienCoc.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienCoc.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienCocCaretUpdate(evt);
            }
        });
        txtTienCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienCocActionPerformed(evt);
            }
        });
        txtTienCoc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTienCocPropertyChange(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Trả trước còn:");

        txtTraCon.setEditable(false);
        txtTraCon.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTraCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTraConActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Các điều khoản:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        btnFirst.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnFirst.setText("|<");
        btnFirst.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnBack.setText("<<");
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnNext.setText(">>");
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnLast.setText(">|");
        btnLast.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add to basket.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnNew.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Create.png"))); // NOI18N
        btnNew.setText("Mới");
        btnNew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setText("Tổng tiền:");

        txtTongTien.setEditable(false);
        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTongTien.setToolTipText("");
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setText("Trả theo tháng:");

        txtTienThang.setEditable(false);
        txtTienThang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtTienThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienThangActionPerformed(evt);
            }
        });

        dchNgayHetHan.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        dchNgayHetHan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dchNgayHetHanPropertyChange(evt);
            }
        });

        cboMaPT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cboMaPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaPTActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton1.setText("In Hợp Đồng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cboMaND1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cboMaND1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaND1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(26, 26, 26))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(40, 40, 40)))
                                        .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel4)
                                                    .addGap(38, 38, 38))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                    .addGap(1, 1, 1)
                                                    .addComponent(jLabel5)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(dchNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                                .addComponent(cboMaPT, 0, 195, Short.MAX_VALUE)
                                                .addComponent(cboMaND1, 0, 195, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addGap(38, 38, 38)
                                                .addComponent(dchNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dchNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(73, 73, 73)
                                        .addComponent(txtTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(36, 36, 36)
                                        .addComponent(txtTraCon, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTienThang, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(jScrollPane1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(34, 34, 34))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cboMaND1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboMaPT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(dchNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtTienThang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dchNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtTraCon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dchNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel10)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62))
        );

        tabHopDong.addTab(" Cập nhật   ", new javax.swing.ImageIcon(getClass().getResource("/image/Save as.png")), jPanel1); // NOI18N

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setText("Danh sách các hợp đồng");

        tblHD.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblHD.setRowHeight(25);
        tblHD.setRowMargin(2);
        tblHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHDMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblHD);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jLabel11)
                        .addGap(0, 303, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabHopDong.addTab(" Danh sách ", new javax.swing.ImageIcon(getClass().getResource("/image/List.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 905, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(406, 406, 406)
                        .addComponent(jLabel1)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabHopDong, javax.swing.GroupLayout.PREFERRED_SIZE, 519, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        index = 0;
        showHD(index);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        try {
            ArrayList<HopDong> ds = HopDongDAO.select("");
            index = ds.size() - 1;
            showHD(index);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnLastActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        index--;
        showHD(index);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        index++;
        showHD(index);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (!validateForm(false)) {
            return;
        }
        this.addHD();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        try {
            HopDong hd = new HopDong();
            setModel(hd);
            setStatus(true);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btnNewActionPerformed

    private void dchNgayHetHanPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dchNgayHetHanPropertyChange
        // TODO add your handling code here:
        txtTongTien.setText(General.formatNumber(autoFillTongtien()));
        txtTongTien.setToolTipText(autoFillTongtien() + "");
    }//GEN-LAST:event_dchNgayHetHanPropertyChange

    private void cboMaPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaPTActionPerformed
        // TODO add your handling code here:
        autoFillTienThang();
        txtTongTien.setText(General.formatNumber(autoFillTongtien()));
        txtTongTien.setToolTipText(autoFillTongtien() + "");
    }//GEN-LAST:event_cboMaPTActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int click = JOptionPane.showConfirmDialog(this, "");
        this.removeHD();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (!validateForm(true)) {
            return;
        }
        this.updateHD();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void dchNgayBatDauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dchNgayBatDauPropertyChange
        // TODO add your handling code here:
        txtTongTien.setText(General.formatNumber(autoFillTongtien()));
        txtTongTien.setToolTipText(autoFillTongtien() + "");
    }//GEN-LAST:event_dchNgayBatDauPropertyChange

    private void txtTienCocPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTienCocPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienCocPropertyChange

    private void txtTienCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienCocActionPerformed
        // TODO add your handling code here:
        double tienThang = Double.parseDouble(txtTienCoc.getText());
        txtTraCon.setText(General.formatNumber(autoFillTongtien() - tienThang));
        txtTraCon.setToolTipText(autoFillTongtien() - tienThang + "");
        txtTongTien.setText(General.formatNumber(autoFillTongtien()));
        txtTongTien.setToolTipText(autoFillTongtien() + "");
    }//GEN-LAST:event_txtTienCocActionPerformed

    private void txtTienCocCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienCocCaretUpdate
        // TODO add your handling code here:
        try {
            double tienThang = Double.parseDouble(txtTienCoc.getText());
            txtTraCon.setText(General.formatNumber(autoFillTongtien() - tienThang));
            txtTraCon.setToolTipText(autoFillTongtien() - tienThang + "");
            txtTongTien.setText(General.formatNumber(autoFillTongtien()));
            txtTongTien.setToolTipText(autoFillTongtien() + "");
        } catch (Exception e) {
        }

    }//GEN-LAST:event_txtTienCocCaretUpdate

    private void tblHDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            index = tblHD.getSelectedRow();
            if (index >= 0) {
                showHD(index);
                tabHopDong.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tblHDMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTraConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTraConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTraConActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void txtTienThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienThangActionPerformed

    private void cboMaND1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaND1ActionPerformed
        autoND();        // TODO add your handling code here:
    }//GEN-LAST:event_cboMaND1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HopDongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HopDongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HopDongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HopDongFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HopDongFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboMaND1;
    private javax.swing.JComboBox<String> cboMaPT;
    private com.toedter.calendar.JDateChooser dchNgayBatDau;
    private com.toedter.calendar.JDateChooser dchNgayHetHan;
    private com.toedter.calendar.JDateChooser dchNgayTao;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane tabHopDong;
    private javax.swing.JTable tblHD;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtTienCoc;
    private javax.swing.JTextField txtTienThang;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txtTraCon;
    // End of variables declaration//GEN-END:variables
}

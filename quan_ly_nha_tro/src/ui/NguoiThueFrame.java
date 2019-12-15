/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.NguoiThueDAO;
import help.General;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.NguoiThue;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NguoiThueFrame extends javax.swing.JFrame {

    int index = 0;

    public NguoiThueFrame() {
        initComponents();
        setIconImage(General.AppIcon());
        setLocationRelativeTo(this);
        showOnTable();
        showNT(index);
    }

    void showOnTable() {
        DefaultTableModel model = (DefaultTableModel) tblNT.getModel();
        model.setRowCount(0);
        try {
            ArrayList<NguoiThue> ds = NguoiThueDAO.select();
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
                model.addRow(row);
            }
        } catch (Exception e) {
        }
    }

    public void showNT(int index) {
        try {
            ArrayList<NguoiThue> ds = NguoiThueDAO.select();
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
            setStatus(false);
        } catch (Exception e) {
        }
    }

    public NguoiThue getModel() {
        NguoiThue nt = new NguoiThue();
        nt.setMaND(txtMaNT.getText());
        nt.setTenND(txtTenNT.getText());
        if (rdoNam.isSelected()) {
            nt.setGioiTinh(true);
        } else {
            nt.setGioiTinh(false);
        }
        nt.setTuoi(Integer.parseInt(txtTuoi.getText()));
        nt.setDiaChi(txtDiaChi.getText());
        nt.setDienThoai(txtDT.getText());
        nt.setEmail(txtEmail.getText());
        nt.setCmnd(txtCMND.getText());
        nt.setHinh(lblHinh.getToolTipText());
        return nt;
    }

    public void setModel(NguoiThue nt) {
        txtMaNT.setText(nt.getMaND());
        txtTenNT.setText(nt.getTenND());
        if (nt.isGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtTuoi.setText(nt.getTuoi() + "");
        txtDiaChi.setText(nt.getDiaChi());
        txtDT.setText(nt.getDienThoai());
        txtEmail.setText(nt.getEmail());
        txtCMND.setText(nt.getCmnd());
        if (nt.getHinh() == null) {
            lblHinh.setText("Click to select image");
            lblHinh.setIcon(null);
        } else {
            lblHinh.setIcon(General.readLogo(nt.getHinh(), lblHinh));
            lblHinh.setToolTipText(nt.getHinh());
        }
    }

    public void addNT() {
        try {
            NguoiThue nt = getModel();
            NguoiThueDAO.AddNQL(nt);
            JOptionPane.showMessageDialog(this, "Add successfully!");
            showOnTable();
            index = tblNT.getRowCount();
            showNT(index);
        } catch (Exception e) {
        }
    }

    public void deleteNT() {
        try {
            NguoiThueDAO.deleteNQL(txtMaNT.getText());
            JOptionPane.showMessageDialog(this, "Delete successfully!");
            showOnTable();
            index--;
            showNT(index);
        } catch (Exception e) {
        }
    }

    public void updateNT() {
        try {
            NguoiThue nt = getModel();
            NguoiThueDAO.UpdateNQL(nt);
            JOptionPane.showMessageDialog(this, "Update successfully!");
            showOnTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setStatus(boolean isEnable) {
        btnAdd.setEnabled(isEnable);
        btnDelete.setEnabled(!isEnable);
        btnUpdate.setEnabled(!isEnable);
        txtMaNT.setEnabled(isEnable);
        txtMaNT.setBackground(Color.white);
        txtCMND.setBackground(Color.white);
        txtDiaChi.setBackground(Color.white);
        txtDT.setBackground(Color.white);
        txtTuoi.setBackground(Color.white);
        txtTenNT.setBackground(Color.white);
        txtEmail.setBackground(Color.white);
    }

    public boolean validateForm(boolean isUpdated) {
        boolean check = true;
        String maNT = txtMaNT.getText();
        String tenNT = txtTenNT.getText();
        String reMaNT = "NT[0-9]{3}";
        String reSDT = "0[0-9]{9}";
        String reEmail = "\\w+@\\w+(\\.\\w+){1,2}";
        String reCMND = "[0-9]{9}";
        if (isUpdated) {
        } else if (maNT.isEmpty()) {
            check = focus(txtMaNT);
        } else if (!maNT.matches(reMaNT)) {
            check = focus(txtMaNT);
            JOptionPane.showMessageDialog(this, "Wrong MaNT pattern! (For instance: NT001)", "Error", 0);
        } else {
            try {
                ArrayList<NguoiThue> ds = NguoiThueDAO.select();
                for (NguoiThue nt : ds) {
                    if (nt.equals(maNT)) {
                        check = focus(txtDT);
                        JOptionPane.showMessageDialog(this, "MaNT is already created!", "Error", 0);
                        break;
                    } else {
                        unfocus(txtMaNT);
                    }
                }
            } catch (Exception e) {
            }
        }
        //////////////////////////////////////////////////////////////////////
        if (tenNT.isEmpty()) {
            check = focus(txtTenNT);
        } else {
            unfocus(txtTenNT);
        }
        /////////////////////////////////////////////////////////////////////
        try {
            if (txtTuoi.getText().isEmpty()) {
                check = focus(txtTuoi);
            } else if (Integer.parseInt(txtTuoi.getText()) < 18) {
                check = focus(txtTuoi);
                JOptionPane.showMessageDialog(this, "Tuoi must be more than 17!", "Error", 0);
            } else {
                unfocus(txtTuoi);
            }
        } catch (Exception e) {
            check = focus(txtTuoi);
            JOptionPane.showMessageDialog(this, "Tuoi must be a number!", "Error", 0);
        }
        /////////////////////////////////////////////////////////////////////
        if (txtDiaChi.getText().isEmpty()) {
            check = focus(txtDiaChi);
        } else {
            unfocus(txtDiaChi);
        }
        /////////////////////////////////////////////////////////////////////
        if (txtDT.getText().isEmpty()) {
            check = focus(txtDT);
        } else if (!txtDT.getText().matches(reSDT)) {
            check = focus(txtDT);
            JOptionPane.showMessageDialog(this, "Wrong SDT pattern!", "Error", 0);
        } else {
            unfocus(txtDT);
        }
        /////////////////////////////////////////////////////////////////////

        if (txtEmail.getText().isEmpty()) {
            check = focus(txtEmail);
        } else if (!txtEmail.getText().matches(reEmail)) {
            check = focus(txtEmail);
            JOptionPane.showMessageDialog(this, "Wrong email pattern!", "Error", 0);
        } else {
            unfocus(txtEmail);
        }
        /////////////////////////////////////////////////////////////////////
        if (txtCMND.getText().isEmpty()) {
            check = focus(txtCMND);
        } else if (!txtCMND.getText().matches(reCMND)) {
            check = focus(txtCMND);
            JOptionPane.showMessageDialog(this, "Wrong CMND pattern!", "Error", 0);
        } else {
            unfocus(txtCMND);
        }

        return check;
    }

    public boolean focus(JTextField txt) {
        txt.setBackground(Color.pink);
        txt.requestFocus();
        return false;
    }

    public void unfocus(JTextField txt) {
        txt.setBackground(Color.white);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        lanNT = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTenNT = new javax.swing.JTextField();
        txtTuoi = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCMND = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNT = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btnxuat = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý người thuê phòng");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("QUẢN LÝ NGƯỜI THUÊ PHÒNG");

        lanNT.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        lanNT.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        lanNT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Mã người thuê:");

        txtMaNT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Tên người thuê:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Giới tính:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Tuổi:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Địa chỉ:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Điện thoại:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Email:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("CMND:");

        txtTenNT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtTuoi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtDT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtCMND.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblHinh.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinh.setText("Click to select image");
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rdoNu.setText("Nữ");

        btnFirst.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnBack.setText("<<");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnLast.setText(">|");
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
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Delete.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Edit.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Create.png"))); // NOI18N
        jButton8.setText("Mới");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel13.setText("Ảnh ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(91, 91, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jSeparator1)
                .addGap(15, 15, 15))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenNT, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtMaNT, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addGap(54, 54, 54)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDT, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel4)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(132, 132, 132)
                                    .addComponent(rdoNam)
                                    .addGap(27, 27, 27)
                                    .addComponent(rdoNu))))
                        .addComponent(jLabel2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(134, 134, 134))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaNT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenNT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 13, 13))
        );

        lanNT.addTab(" Cập  nhật ", new javax.swing.ImageIcon(getClass().getResource("/image/Save as.png")), jPanel1); // NOI18N

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblNT.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblNT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaNT", "TenNT", "GioiTinh", "Tuoi", "DiaChi", "DienThoai", "Email", "CMND", "Hinh"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNT.setRowHeight(25);
        tblNT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNTMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblNT);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel11.setText("Danh sách người thuê phòng");

        btnxuat.setText("Xuất Danh Sách");
        btnxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(231, 231, 231))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(btnxuat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel11)
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnxuat)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        lanNT.addTab(" Danh sách ", new javax.swing.ImageIcon(getClass().getResource("/image/List.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(270, 270, 270))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lanNT, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lanNT, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (General.saveLogo(file)) {
                // Hiển thị hình lên form
                lblHinh.setIcon(General.readLogo(file.getName(), lblHinh));
                lblHinh.setToolTipText(file.getName());
            }
        }
    }//GEN-LAST:event_lblHinhMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        index = 0;
        showNT(index);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        index--;
        showNT(index);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        index++;
        showNT(index);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        index = tblNT.getRowCount() - 1;
        showNT(index);
    }//GEN-LAST:event_btnLastActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        setModel(new NguoiThue());
        setStatus(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (!validateForm(false)) {
            return;
        }
        addNT();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int click = JOptionPane.showConfirmDialog(this, "Are you sure to remove this NguoiThue?");
        if (click == JOptionPane.YES_OPTION) {
            deleteNT();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (!validateForm(true)) {
            return;
        }
        updateNT();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxuatActionPerformed
        XSSFWorkbook workbook = new XSSFWorkbook();
        // create sheet khách Hàng
        XSSFSheet sheet = workbook.createSheet("Khách Hàng");
        XSSFRow row = null;
        
        Cell cell = null;
        // header sheet
        row = sheet.createRow(0);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Bảng Khách Hàng:");

        row = sheet.createRow(3);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("STT");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Mã Khách Hàng");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Tên khách hàng");

        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Giới tính");

        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Tuổi");

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Địa chỉ");

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Điện thoại");

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Mail");

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("CMND");

        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("Hình");

        // value table Người Thuê
        try {
            ArrayList<NguoiThue> listItem = NguoiThueDAO.select();
            if (listItem != null) {
                int a = listItem.size();
                for (int i = 0; i < a; i++) {
                    NguoiThue nguoiThue = listItem.get(i);

                    row = sheet.createRow(4 + i);

                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(i + 1);

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(nguoiThue.getMaND());

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(nguoiThue.getTenND());

                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(nguoiThue.isGioiTinh() ? "Nam" : "Nữ");

                     cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(String.valueOf(nguoiThue.getTuoi()));
                    
                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(nguoiThue.getDiaChi());

                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue(nguoiThue.getDienThoai());

                    cell = row.createCell(7, CellType.STRING);
                    cell.setCellValue(nguoiThue.getEmail());

                    cell = row.createCell(8, CellType.STRING);
                    cell.setCellValue(nguoiThue.getCmnd());

                    cell = row.createCell(9, CellType.STRING);
                    cell.setCellValue(nguoiThue.getHinh());
                }
            }
            File f = new File("..\\quan_ly_nha_tro\\ThongKe\\Danhsach.xls");
            try (FileOutputStream fos = new FileOutputStream(f)) {
                workbook.write(fos);
            }

            JOptionPane.showMessageDialog(this, "Danh sách đã được lưu","thông báo",1);
            JOptionPane.showMessageDialog(this, "Đang mở danh sách","thông báo",1);
      Desktop.getDesktop().open(new File("..\\quan_ly_nha_tro\\ThongKe\\Danhsach.xls"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
  
    }//GEN-LAST:event_btnxuatActionPerformed

    private void tblNTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNTMousePressed
if (evt.getClickCount() == 2) {
            index = tblNT.getSelectedRow();
            if (index >= 0) {
                showNT(index);
                lanNT.setSelectedIndex(0);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblNTMousePressed

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
            java.util.logging.Logger.getLogger(NguoiThueFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NguoiThueFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NguoiThueFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NguoiThueFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NguoiThueFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JToggleButton btnxuat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane lanNT;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNT;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDT;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNT;
    private javax.swing.JTextField txtTenNT;
    private javax.swing.JTextField txtTuoi;
    // End of variables declaration//GEN-END:variables
}

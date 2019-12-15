/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.NguoiQuanLyDAO;
import help.General;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.NguoiQuanLy;

public class NguoiQuanLyFrame extends javax.swing.JFrame {

    int index = 0;
    String title[] = {"MaNQL", "Ten","VaiTro"};
    DefaultTableModel model = new DefaultTableModel(title, 0);

    public NguoiQuanLyFrame() {
        initComponents();
        setIconImage(General.AppIcon());
        setLocationRelativeTo(this);
        showOnTable();
        showNQL(0);

    }

    public void showOnTable() {
        model.setRowCount(0);
        try {
            ArrayList<NguoiQuanLy> ds = NguoiQuanLyDAO.select();
            for (NguoiQuanLy d : ds) {
                Object row[] = {
                    d.getMaNQL(),
                    d.getTenNQL(),
                    d.isVaiTro()?"Người quản lý":"Nhân viên"
                };
                model.addRow(row);
            }
            tblNQL.setModel(model);
        } catch (Exception e) {
        }
    }

    public void showNQL(int index) {
        try {
            ArrayList<NguoiQuanLy> ds = NguoiQuanLyDAO.select();
            if (ds.get(index).getMaNQL().equals(General.MANAGER.getMaNQL())) {
                txtMatkhau.setEnabled(true);
            } else {
                txtMatkhau.setEnabled(false);
            }
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
        } catch (Exception e) {
        }
        setStatus(false);
    }

    public void setModel(NguoiQuanLy nql) {
        txtMaNQL.setText(nql.getMaNQL());
        if (nql.getMatKhau() == null) {
            txtMatkhau.setText("");
        } else {
            txtMatkhau.setText(General.decoding(nql.getMatKhau()));
        }
        txtTenNQL.setText(nql.getTenNQL());
        if (nql.isVaiTro()) {
            rdoNQL.setSelected(true);
        } else {
            rdoNV.setSelected(true);
        }
    }

    public NguoiQuanLy getModel() {
        NguoiQuanLy nql = new NguoiQuanLy();
        nql.setMaNQL(txtMaNQL.getText());
        nql.setMatKhau(txtMatkhau.getText());
        nql.setTenNQL(txtTenNQL.getText());
        nql.setVaiTro(rdoNQL.isSelected() ? true : false);
        return nql;
    }

    public void addNQL() {
        try {
            NguoiQuanLy nql = getModel();
            NguoiQuanLyDAO.AddNQL(nql);
            JOptionPane.showMessageDialog(this, "Add successfully!");
            showOnTable();
            index = tblNQL.getRowCount();
            showNQL(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNQL() {
        try {
            NguoiQuanLyDAO.deleteNQL(txtMaNQL.getText());
            JOptionPane.showMessageDialog(this, "Delete successfully!");
            showOnTable();
            index--;
            showNQL(index);
        } catch (Exception e) {
        }
    }

    public void updateNQL() {
        try {
            NguoiQuanLy nql = getModel();
            NguoiQuanLyDAO.UpdateNQL(nql);
            JOptionPane.showMessageDialog(this, "Update successfully!");
            showOnTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStatus(boolean isEnable) {
        btnAdd.setEnabled(isEnable);
        btnDelete.setEnabled(!isEnable);
        btnUpdate.setEnabled(!isEnable);
        txtMaNQL.setEnabled(isEnable);
        txtMaNQL.setBackground(Color.white);
        txtMatkhau.setBackground(Color.white);

    }

    public boolean validateForm(boolean doUpdate) {
        boolean check = true;
        String maNQL = txtMaNQL.getText();
        String matKhau = txtMatkhau.getText();
        if (doUpdate) {
        } else if (maNQL.isEmpty()) {
            check = false;
            txtMaNQL.setBackground(Color.PINK);
        } else {
            try {
                ArrayList<NguoiQuanLy> ds = NguoiQuanLyDAO.select();
                for (NguoiQuanLy nql : ds) {
                    if (nql.getMaNQL().equals(maNQL)) {
                        check = false;
                        JOptionPane.showMessageDialog(this, "MaNQL khong duoc trung!");
                        txtMaNQL.setBackground(Color.pink);
                        break;
                    } else {
                        txtMaNQL.setBackground(Color.white);
                    }
                }
            } catch (Exception e) {
            }
        }
        if (matKhau.isEmpty()) {
            check = false;
            txtMatkhau.setBackground(Color.PINK);
        } else if (matKhau.length() < 3) {
            check = false;
            JOptionPane.showMessageDialog(this, "MatKhau phai nhieu hon 3 ki tu!");
            txtMatkhau.setBackground(Color.pink);
        } else {
            txtMatkhau.setBackground(Color.white);
        }
        return check;
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
        tabNQL = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNQL = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenNQL = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMatkhau = new javax.swing.JPasswordField();
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
        jLabel5 = new javax.swing.JLabel();
        rdoNQL = new javax.swing.JRadioButton();
        rdoNV = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNQL = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý nhân viên");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("NGƯỜI QUẢN LÝ");

        tabNQL.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabNQL.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tabNQL.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Mã người quản lý:");

        txtMaNQL.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Mật khẩu:");

        txtTenNQL.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Họ và tên:");

        txtMatkhau.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

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

        btnNew.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Create.png"))); // NOI18N
        btnNew.setText("Mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Vai trò:");

        buttonGroup1.add(rdoNQL);
        rdoNQL.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rdoNQL.setText("Người quản lý");

        buttonGroup1.add(rdoNV);
        rdoNV.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        rdoNV.setText("Nhân viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(txtMaNQL)
                        .addComponent(txtMatkhau)
                        .addComponent(txtTenNQL, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(28, 28, 28)
                        .addComponent(rdoNQL)
                        .addGap(27, 27, 27)
                        .addComponent(rdoNV)))
                .addGap(145, 145, 145))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMaNQL, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMatkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenNQL, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rdoNQL)
                    .addComponent(rdoNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        tabNQL.addTab("Cập nhật", new javax.swing.ImageIcon(getClass().getResource("/image/Save as.png")), jPanel1); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblNQL.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblNQL.setModel(new javax.swing.table.DefaultTableModel(
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
        tblNQL.setRowHeight(25);
        tblNQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNQLMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblNQL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        tabNQL.addTab("Danh sách", new javax.swing.ImageIcon(getClass().getResource("/image/List.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(287, 287, 287))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabNQL)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabNQL)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        if (!validateForm(false)) {
            return;
        }
        addNQL();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        index = 0;
        showNQL(index);
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        index = tblNQL.getRowCount() - 1;
        showNQL(index);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        index--;
        showNQL(index);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        index++;
        showNQL(index);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        try {
            ArrayList<NguoiQuanLy> ds = NguoiQuanLyDAO.select();
            for (NguoiQuanLy d : ds) {
                if (d.getMaNQL().equals(General.MANAGER.getMaNQL())) {
                    JOptionPane.showMessageDialog(this, "You can't delete youself!", "Error", 0);
                    return;
                }
            }
        } catch (Exception e) {
        }
        int click = JOptionPane.showConfirmDialog(this, "Are you sure to delete?");
        if (click == JOptionPane.YES_OPTION) {
            deleteNQL();
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        setModel(new NguoiQuanLy());
        setStatus(true);
        txtMatkhau.setEnabled(true);

    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        if (!validateForm(true)) {
            return;
        }
        updateNQL();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblNQLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNQLMousePressed
    if (evt.getClickCount() == 2) {
            index = tblNQL.getSelectedRow();
            if (index >= 0) {
                showNQL(index);
                tabNQL.setSelectedIndex(0);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblNQLMousePressed

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
            java.util.logging.Logger.getLogger(NguoiQuanLyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NguoiQuanLyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NguoiQuanLyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NguoiQuanLyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NguoiQuanLyFrame().setVisible(true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rdoNQL;
    private javax.swing.JRadioButton rdoNV;
    private javax.swing.JTabbedPane tabNQL;
    private javax.swing.JTable tblNQL;
    private javax.swing.JTextField txtMaNQL;
    private javax.swing.JPasswordField txtMatkhau;
    private javax.swing.JTextField txtTenNQL;
    // End of variables declaration//GEN-END:variables
}

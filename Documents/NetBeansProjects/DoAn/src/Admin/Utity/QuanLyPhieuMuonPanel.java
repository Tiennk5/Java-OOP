/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.Utity;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Admin.Controller.CustomScrollBar;
import Singleton.MyConnection;
import DAO.DAOPhieuMuon;

import Object.BanDoc;
import Object.PhieuMuon;
import Object.Sach;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.*;
import java.awt.*;
//pdf
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;

import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;


public class QuanLyPhieuMuonPanel extends javax.swing.JPanel {

    private Connection conn;
    public QuanLyPhieuMuonPanel() {
     initComponents();
//    tbnSach.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
    tbnSach.setRowHeight(25); 
    // Custom scrollbar
    jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBar());
    jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBar());
 
    // Set mã phiếu mượn
    txtMaPhieuMuon.setText(generateNewMaPhieuMuon());
    // 🟢 BƯỚC 1: Load dữ liệu trước
    loadDataToTable();
    // 🟢 BƯỚC 2: Sau khi có dữ liệu, thêm nút "Xóa"
    addDeleteButtonToTable(tbnSach);
    // 🟢 BƯỚC 3: Gán comboBox cho cột "Thời gian mượn"
    String[] thoiGianMuon = {"3 tháng", "6 tháng", "12 tháng"};
    JComboBox<String> comboBox = new JComboBox<>(thoiGianMuon);
    // Lấy lại số cột sau khi đã thêm cột "Xóa"
    int thoiGianMuonIndex = 6;

    if (tbnSach.getColumnCount() > thoiGianMuonIndex) {
        TableColumn thoiGianMuonColumn = tbnSach.getColumnModel().getColumn(thoiGianMuonIndex);
        thoiGianMuonColumn.setCellEditor(new DefaultCellEditor(comboBox));
    } else {
        System.err.println("⚠️ Không tìm thấy cột 'Thời gian mượn'");
    }    


    }
   private void addDeleteButtonToTable(JTable table) {
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    if (table.getColumnCount() == model.getColumnCount()) {
        model.addColumn("Xóa");
    }

    // Renderer hiển thị nút màu đỏ
    table.getColumn("Xóa").setCellRenderer(new TableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                        boolean hasFocus, int row, int column) {
            JButton button = new JButton("Xóa");
            button.setForeground(Color.WHITE);
            button.setBackground(Color.RED);
            return button;
        }
    });

    // Editor xử lý click với nút màu đỏ
    table.getColumn("Xóa").setCellEditor(new DefaultCellEditor(new JCheckBox()) {
        JButton button = new JButton("Xóa");
        {
            button.setForeground(Color.WHITE);
            button.setBackground(Color.RED);
            button.addActionListener(e -> {
                int row = table.getEditingRow();
                if (row != -1) {
                    ((DefaultTableModel) table.getModel()).removeRow(row);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "Xóa";
        }
    });
}


public String generateNewMaPhieuMuon() {
    String sql = "SELECT MAX(maphieumuon) AS maxMa FROM PhieuMuon";
    String newMaPhieuMuon = "PM001"; // Mặc định nếu chưa có dữ liệu

    try (Connection conn = MyConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            String lastMa = rs.getString("maxMa");

            if (lastMa != null && !lastMa.trim().isEmpty() && lastMa.length() > 2) {
                try {
                    int number = Integer.parseInt(lastMa.substring(2)) + 1;
                    newMaPhieuMuon = String.format("PM%03d", number);
                } catch (NumberFormatException e) {
                    System.err.println("Lỗi khi chuyển đổi mã phiếu mượn: " + lastMa);
                    e.printStackTrace();
                }
            }
        }

        // Tăng dần cho đến khi tìm được mã không trùng
        while (isMaPhieuMuonExists(newMaPhieuMuon, conn)) {
            int number = Integer.parseInt(newMaPhieuMuon.substring(2)) + 1;
            newMaPhieuMuon = String.format("PM%03d", number);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return newMaPhieuMuon;
}


public void resetForm() {
   
    txtTimKiemMaSinhVien.setText("");
    txtMaSinhVien.setText("");
    txtLop.setText("");
    txtKhoa.setText("");
    txtGioiTinh.setText("");
    txtSDT.setText("");
    ((DefaultTableModel) tbnSach.getModel()).setRowCount(0);
}


// Kiểm tra mã phiếu mượn có tồn tại trong cơ sở dữ liệu hay không
private boolean isMaPhieuMuonExists(String maPhieuMuon, Connection conn) throws SQLException {
    String sqlCheck = "SELECT COUNT(*) FROM PhieuMuon WHERE maphieumuon = ?";
    try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
        psCheck.setString(1, maPhieuMuon);
        try (ResultSet rsCheck = psCheck.executeQuery()) {
            if (rsCheck.next()) {
                return rsCheck.getInt(1) > 0;
            }
        }
    }
    return false;
}
   private void searchStudentInfo() {
    String maSinhVien = txtTimKiemMaSinhVien.getText().trim();

    if (maSinhVien.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sinh viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String sql = "SELECT masinhvien, tensinhvien, lop, khoa, gioitinh, sodienthoai FROM BanDoc WHERE masinhvien = ?";

    try (Connection conn = MyConnection.getConnection();  // Sử dụng lớp tự định nghĩa
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, maSinhVien);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                txtMaSinhVien.setText(rs.getString("masinhvien"));
                txtTenSinhVien.setText(rs.getString("tensinhvien"));
                txtLop.setText(rs.getString("lop"));
                txtKhoa.setText(rs.getString("khoa"));
                txtGioiTinh.setText(rs.getString("gioitinh"));
                txtSDT.setText(rs.getString("sodienthoai"));
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi tìm sinh viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

     private void loadDataToTable() {
  
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbnSach = new javax.swing.JTable();
        txtMaPhieuMuon = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTimKiemMaSach = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTimKiemMaSinhVien = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaSinhVien = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtKhoa = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtTenSinhVien = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txtGioiTinh = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        txtLop = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnXuatPDF = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnXuatPDF1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tbnSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Thể loại", "Nhà xuất bản", "Giá tiền", "Thời gian mượn"
            }
        ));
        jScrollPane1.setViewportView(tbnSach);

        txtMaPhieuMuon.setBorder(null);
        txtMaPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPhieuMuonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Mã sách :");

        txtTimKiemMaSach.setBorder(null);
        txtTimKiemMaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemMaSachActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Mã phiếu mượn:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Mã sinh viên:");

        txtTimKiemMaSinhVien.setBorder(null);
        txtTimKiemMaSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemMaSinhVienActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Mã sinh viên:");

        txtMaSinhVien.setBorder(null);
        txtMaSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSinhVienActionPerformed(evt);
            }
        });

        jLabel7.setText("Khoa :");

        txtKhoa.setBorder(null);
        txtKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhoaActionPerformed(evt);
            }
        });

        jLabel2.setText("Họ tên :");

        txtTenSinhVien.setBorder(null);

        jLabel3.setText("Lớp :");

        txtSDT.setBorder(null);
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel5.setText("Giới tính :");

        txtGioiTinh.setBorder(null);

        jLabel6.setText("Số điện thoại :");

        txtLop.setBorder(null);
        txtLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtMaSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnXuatPDF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXuatPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel_48.png"))); // NOI18N
        btnXuatPDF.setText("Xuất Excel");
        btnXuatPDF.setBorder(null);
        btnXuatPDF.setContentAreaFilled(false);
        btnXuatPDF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXuatPDF.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnXuatPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatPDFActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add_48.png"))); // NOI18N
        btnThem.setText("Add");
        btnThem.setBorder(null);
        btnThem.setContentAreaFilled(false);
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnThem.setDefaultCapable(false);
        btnThem.setHideActionText(true);
        btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/reset_48px.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.setBorder(null);
        btnReset.setContentAreaFilled(false);
        btnReset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReset.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnXuatPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(btnXuatPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnReset))
        );

        btnXuatPDF1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXuatPDF1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pdf_48.png"))); // NOI18N
        btnXuatPDF1.setText("Xuất PDF");
        btnXuatPDF1.setBorder(null);
        btnXuatPDF1.setContentAreaFilled(false);
        btnXuatPDF1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXuatPDF1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnXuatPDF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatPDF1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiemMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(46, 46, 46)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTimKiemMaSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                    .addComponent(jSeparator2)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator1)
                                    .addComponent(txtMaPhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(273, 273, 273)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXuatPDF1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(413, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtMaPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimKiemMaSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(86, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTimKiemMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(51, 51, 51))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btnXuatPDF1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(35, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPhieuMuonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaPhieuMuonActionPerformed
    public void pdf(){
        JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
fileChooser.setSelectedFile(new File("PhieuMuon.pdf"));

int userSelection = fileChooser.showSaveDialog(null);
if (userSelection != JFileChooser.APPROVE_OPTION) {
    return;
}

File fileToSave = fileChooser.getSelectedFile();

// Nếu file đã tồn tại hỏi có ghi đè không
if (fileToSave.exists()) {
    int option = JOptionPane.showConfirmDialog(null,
            "File đã tồn tại. Bạn có muốn ghi đè?", "Xác nhận",
            JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.NO_OPTION) {
        return;
    }
}

Connection conn = null;
try {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    conn = DriverManager.getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;trustServerCertificate=true",
            "sa", "123456789");

    String sql = "SELECT pm.maphieumuon, sv.masinhvien, sv.tensinhvien, " +
                 "       value as masach, s.tensach, pm.thoigianmuonsach, pm.thoigiantrasach " +
                 "FROM PhieuMuon pm " +
                 "JOIN SinhVien sv ON pm.masinhvien = sv.masinhvien " +
                 "CROSS APPLY STRING_SPLIT(pm.masach, ',') " +
                 "JOIN Sach s ON s.masach = value " +
                 "WHERE pm.datra = 0 " +
                 "AND pm.maphieumuon = ( " +
                 "    SELECT TOP 1 maphieumuon FROM PhieuMuon WHERE datra = 0 ORDER BY thoigianmuonsach DESC " +
                 ") " +
                 "ORDER BY pm.maphieumuon";

    PreparedStatement ps = conn.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    if (!rs.isBeforeFirst()) {  // kiểm tra nếu result set rỗng
        JOptionPane.showMessageDialog(null, "Không có phiếu mượn nào chưa trả!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // Tạo document PDF
    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
    document.open();

    // Đường dẫn tới font Arial, bạn cần sửa đường dẫn này đúng với máy của bạn
    String fontPath = "C:\\Windows\\Fonts\\arial.ttf";  // ví dụ Windows
    BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    Font fontBold = new Font(baseFont, 14, Font.BOLD);
    Font fontNormal = new Font(baseFont, 12, Font.NORMAL);

    rs.next(); // di chuyển tới bản ghi đầu tiên

    String maPhieuMuon = rs.getString("maphieumuon");
    String maSinhVien = rs.getString("masinhvien");
    String tenSinhVien = rs.getString("tensinhvien");

    document.add(new Paragraph("Mã phiếu mượn:\t\t" + maPhieuMuon, fontBold));
    document.add(new Paragraph("Mã sinh viên:\t\t" + maSinhVien, fontBold));
    document.add(new Paragraph("Tên sinh viên:\t\t" + tenSinhVien, fontBold));
    document.add(new Paragraph(" ")); // dòng trống

    PdfPTable table = new PdfPTable(4);
    table.setWidthPercentage(100);
    table.setWidths(new float[]{2f, 5f, 4f, 4f});

    String[] headers = {"Mã sách", "Tên sách", "Thời gian mượn", "Thời gian trả"};
    for (String header : headers) {
        PdfPCell cell = new PdfPCell(new Paragraph(header, fontBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new Color(230, 230, 230));
        table.addCell(cell);
    }

    // Dòng đầu tiên đã gọi rs.next() rồi nên lấy dữ liệu luôn
    do {
        table.addCell(new PdfPCell(new Paragraph(rs.getString("masach"), fontNormal)));
        table.addCell(new PdfPCell(new Paragraph(rs.getString("tensach"), fontNormal)));
        table.addCell(new PdfPCell(new Paragraph(rs.getString("thoigianmuonsach"), fontNormal)));
        table.addCell(new PdfPCell(new Paragraph(rs.getString("thoigiantrasach"), fontNormal)));
    } while (rs.next());

    document.add(table);
    document.add(new Paragraph(" "));
document.add(new Paragraph(" "));
document.add(new Paragraph("Nội quy mượn và sử dụng sách thư viện", fontBold));
document.add(new Paragraph("1. Làm hỏng hoặc bẩn sách (rách, ố màu, ghi chú, vẽ bậy...) sẽ phải đền bù 25% giá trị cuốn sách.", fontNormal));
document.add(new Paragraph("2. Nếu sách vừa bẩn vừa rách, bạn đọc phải bồi thường 50% giá trị sách.", fontNormal));
document.add(new Paragraph("3. Làm mất sách, bạn đọc sẽ phải bồi thường 100% giá trị sách.", fontNormal));
document.add(new Paragraph("4. Trả sách trễ hạn sẽ bị phạt hành chính 50.000 VNĐ/lần.", fontNormal));

    document.close();

    JOptionPane.showMessageDialog(null, "Xuất file PDF thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

    if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().open(fileToSave);
    }

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Lỗi khi xuất file PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
} finally {
    try {
        if (conn != null) conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
     // Kiểm tra và lấy thông tin phiếu mượn
    PhieuMuon phieumuon = new PhieuMuon();
    phieumuon.setMaPhieuMuon(txtMaPhieuMuon.getText());
    // Kiểm tra và lấy thông tin bạn đọc (người mượn sách)
    BanDoc banDoc = new BanDoc();
    banDoc.setMaSinhVien(txtMaSinhVien.getText());
    phieumuon.setBanDoc(banDoc);
    
    // Lấy danh sách sách từ bảng tbnSach
    ArrayList<Sach> dsSach = new ArrayList<>();
    DefaultTableModel model = (DefaultTableModel) tbnSach.getModel();
    int rowCount = model.getRowCount();
    
    // Kiểm tra nếu chưa chọn sách để mượn
    if (rowCount == 0) {
        JOptionPane.showMessageDialog(this, "Thiếu thông tin phiếu mượn!");
        return;
    }
    // Kiểm tra mã sinh viên có rỗng không
    if (txtMaSinhVien.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(this, "Thiếu thông tin phiếu mượn!", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
    return;
     }
      DAOPhieuMuon dao = new DAOPhieuMuon();
    for (int i = 0; i < rowCount; i++) {
        String maSach = model.getValueAt(i, 0).toString(); // Cột 0 là mã sách
        String thoiHanMuon = model.getValueAt(i, 6).toString(); // Cột 6 là thời hạn mượn

        // Kiểm tra trạng thái sách
        if (dao.isSachChuaMuon(maSach)) {
            Sach sach = new Sach();
            sach.setMaSach(maSach);
            sach.setThoihanmuon(thoiHanMuon);
            dsSach.add(sach);
        } 
        else{
         JOptionPane.showMessageDialog(this, "Sách đã được mượn , Vui lòng nhập lại !");
         return;
        }
    }

    // Gán danh sách sách vào phiếu mượn
    phieumuon.setSach(dsSach);
    boolean isSuccess = dao.ThemPhieuMuon(phieumuon);
    // Hiển thị thông báo tùy theo kết quả thêm phiếu mượn
    if (isSuccess) {
        StringBuilder msg = new StringBuilder("Thêm phiếu mượn thành công với các sách sau:\n\n");
    for (Sach s : dsSach) {
        msg.append("Mã sách: ").append(s.getMaSach()).append("\n");
        msg.append("Thời hạn mượn: ").append(s.getThoihanmuon()).append("\n\n");
    }
    JOptionPane.showMessageDialog(this, msg.toString(), "Thêm phiếu mượn thành công!", JOptionPane.INFORMATION_MESSAGE);
        pdf();
          

  
    } else {
        JOptionPane.showMessageDialog(this, "Thêm thất bại! Vui lòng thử lại.");
    }
    resetForm() ; 
    txtMaPhieuMuon.setText(generateNewMaPhieuMuon());

    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemMaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemMaSachActionPerformed
        // TODO add your handling code here:
        String maSach = txtTimKiemMaSach.getText().trim();

        if (maSach.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Câu lệnh SQL để tìm sách theo mã
        String sql = "SELECT masach, tensach, tacgia, theloai, nhaxuatban, giatien FROM Sach WHERE masach = ?";

        try (Connection conn = MyConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maSach);

            try (ResultSet rs = ps.executeQuery()) {
                // Lấy mô hình dữ liệu của bảng
                DefaultTableModel model = (DefaultTableModel) tbnSach.getModel();

                // Kiểm tra nếu có dữ liệu từ database
                if (rs.next()) {
                    Object[] row = {
                        rs.getString("masach"),
                        rs.getString("tensach"),
                        rs.getString("tacgia"),
                        rs.getString("theloai"),
                        rs.getString("nhaxuatban"),
                        rs.getString("giatien"),
                        "3 tháng"
                    };
                    model.addRow(row); // Thêm dòng mới
                } else {
                    
                }
            }
        } catch (SQLException e) {
            
        }

        // Xóa nội dung ô nhập để nhập tiếp
        txtTimKiemMaSach.setText("");
        txtTimKiemMaSach.requestFocus(); // Đưa con trỏ chuột vào ô nhập để nhập nhanh hơn
    }//GEN-LAST:event_txtTimKiemMaSachActionPerformed

    private void txtTimKiemMaSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemMaSinhVienActionPerformed
        searchStudentInfo();
    }//GEN-LAST:event_txtTimKiemMaSinhVienActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelSach = (DefaultTableModel) tbnSach.getModel();
        modelSach.setRowCount(0);  // Xóa hết tất cả các hàng trong bảng
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtMaSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSinhVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSinhVienActionPerformed

    private void txtKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKhoaActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLopActionPerformed

    private void btnXuatPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPDFActionPerformed
      JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Chọn nơi lưu file CSV");
fileChooser.setSelectedFile(new File("PhieuMuon.csv"));

int userSelection = fileChooser.showSaveDialog(this);
if (userSelection != JFileChooser.APPROVE_OPTION) {
    return;
}

File fileToSave = fileChooser.getSelectedFile();

if (fileToSave.exists()) {
    int option = JOptionPane.showConfirmDialog(this,
        "File đã tồn tại. Bạn có muốn ghi đè?", "Xác nhận",
        JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.NO_OPTION) {
        return;
    }
}

Connection conn = null;
try {
    if (conn == null || conn.isClosed()) {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;trustServerCertificate=true",
            "sa", "123456789");
    }

    // Lấy phiếu mượn mới nhất chưa trả cùng thông tin sách
    String sql = 
        "SELECT pm.maphieumuon, sv.masinhvien, sv.tensinhvien, " +
        "       value as masach, s.tensach, pm.thoigianmuonsach, pm.thoigiantrasach " +
        "FROM PhieuMuon pm " +
        "JOIN SinhVien sv ON pm.masinhvien = sv.masinhvien " +
        "CROSS APPLY STRING_SPLIT(pm.masach, ',') " +
        "JOIN Sach s ON s.masach = value " +
        "WHERE pm.datra = 0 " +
        "AND pm.maphieumuon = ( " +
        "    SELECT TOP 1 maphieumuon FROM PhieuMuon WHERE datra = 0 ORDER BY thoigianmuonsach DESC " +
        ") " +
        "ORDER BY pm.maphieumuon";

    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
         OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileToSave), StandardCharsets.UTF_8);
         BufferedWriter writer = new BufferedWriter(osw)) {

        writer.write('\uFEFF'); // BOM UTF-8 cho Excel

        String maPhieuMuon = null;
        String maSinhVien = null;
        String tenSinhVien = null;

        // In dòng đầu: Mã phiếu mượn
        if (rs.next()) {
            maPhieuMuon = rs.getString("maphieumuon");
            maSinhVien = rs.getString("masinhvien");
            tenSinhVien = rs.getString("tensinhvien");

            writer.write("Mã phiếu mượn:\t\t" + maPhieuMuon);
            writer.newLine();
            writer.write("Mã sinh viên:\t\t" + maSinhVien);
            writer.newLine();
            writer.write("Tên sinh viên:\t\t" + tenSinhVien);
            writer.newLine();

            // In header bảng sách
            writer.write("Mã sách\tTên sách\tThời gian mượn\tThời gian trả");
            writer.newLine();

            // In dòng đầu tiên của sách
            writer.write(rs.getString("masach") + "\t" 
                + rs.getString("tensach") + "\t"
                + rs.getString("thoigianmuonsach") + "\t"
                + rs.getString("thoigiantrasach"));
            writer.newLine();

            // In tiếp các dòng còn lại
            while (rs.next()) {
                writer.write(rs.getString("masach") + "\t" 
                    + rs.getString("tensach") + "\t"
                    + rs.getString("thoigianmuonsach") + "\t"
                    + rs.getString("thoigiantrasach"));
                writer.newLine();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không có phiếu mượn nào chưa trả!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        writer.flush();
    }

    JOptionPane.showMessageDialog(this, "Xuất file thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

    if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().open(fileToSave);
    }

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Lỗi khi xuất file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
}


    }//GEN-LAST:event_btnXuatPDFActionPerformed

    private void btnXuatPDF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPDF1ActionPerformed
  JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
fileChooser.setSelectedFile(new File("PhieuMuon.pdf"));

int userSelection = fileChooser.showSaveDialog(null);
if (userSelection != JFileChooser.APPROVE_OPTION) {
    return;
}

File fileToSave = fileChooser.getSelectedFile();

// Nếu file đã tồn tại hỏi có ghi đè không
if (fileToSave.exists()) {
    int option = JOptionPane.showConfirmDialog(null,
            "File đã tồn tại. Bạn có muốn ghi đè?", "Xác nhận",
            JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.NO_OPTION) {
        return;
    }
}

Connection conn = null;
try {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    conn = DriverManager.getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;trustServerCertificate=true",
            "sa", "123456789");

    String sql = "SELECT pm.maphieumuon, sv.masinhvien, sv.tensinhvien, " +
                 "       value as masach, s.tensach, pm.thoigianmuonsach, pm.thoigiantrasach " +
                 "FROM PhieuMuon pm " +
                 "JOIN SinhVien sv ON pm.masinhvien = sv.masinhvien " +
                 "CROSS APPLY STRING_SPLIT(pm.masach, ',') " +
                 "JOIN Sach s ON s.masach = value " +
                 "WHERE pm.datra = 0 " +
                 "AND pm.maphieumuon = ( " +
                 "    SELECT TOP 1 maphieumuon FROM PhieuMuon WHERE datra = 0 ORDER BY thoigianmuonsach DESC " +
                 ") " +
                 "ORDER BY pm.maphieumuon";

    PreparedStatement ps = conn.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    if (!rs.isBeforeFirst()) {  // kiểm tra nếu result set rỗng
        JOptionPane.showMessageDialog(null, "Không có phiếu mượn nào chưa trả!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // Tạo document PDF
    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
    document.open();

    // Đường dẫn tới font Arial, bạn cần sửa đường dẫn này đúng với máy của bạn
    String fontPath = "C:\\Windows\\Fonts\\arial.ttf";  // ví dụ Windows
    BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    Font fontBold = new Font(baseFont, 14, Font.BOLD);
    Font fontNormal = new Font(baseFont, 12, Font.NORMAL);

    rs.next(); // di chuyển tới bản ghi đầu tiên

    String maPhieuMuon = rs.getString("maphieumuon");
    String maSinhVien = rs.getString("masinhvien");
    String tenSinhVien = rs.getString("tensinhvien");

    document.add(new Paragraph("Mã phiếu mượn:\t\t" + maPhieuMuon, fontBold));
    document.add(new Paragraph("Mã sinh viên:\t\t" + maSinhVien, fontBold));
    document.add(new Paragraph("Tên sinh viên:\t\t" + tenSinhVien, fontBold));
    document.add(new Paragraph(" ")); // dòng trống

    PdfPTable table = new PdfPTable(4);
    table.setWidthPercentage(100);
    table.setWidths(new float[]{2f, 5f, 4f, 4f});

    String[] headers = {"Mã sách", "Tên sách", "Thời gian mượn", "Thời gian trả"};
    for (String header : headers) {
        PdfPCell cell = new PdfPCell(new Paragraph(header, fontBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new Color(230, 230, 230));
        table.addCell(cell);
    }

    // Dòng đầu tiên đã gọi rs.next() rồi nên lấy dữ liệu luôn
    do {
        table.addCell(new PdfPCell(new Paragraph(rs.getString("masach"), fontNormal)));
        table.addCell(new PdfPCell(new Paragraph(rs.getString("tensach"), fontNormal)));
        table.addCell(new PdfPCell(new Paragraph(rs.getString("thoigianmuonsach"), fontNormal)));
        table.addCell(new PdfPCell(new Paragraph(rs.getString("thoigiantrasach"), fontNormal)));
    } while (rs.next());

    document.add(table);
    document.add(new Paragraph(" "));
document.add(new Paragraph(" "));
document.add(new Paragraph("Nội quy mượn và sử dụng sách thư viện", fontBold));
document.add(new Paragraph("1. Làm hỏng hoặc bẩn sách (rách, ố màu, ghi chú, vẽ bậy...) sẽ phải đền bù 25% giá trị cuốn sách.", fontNormal));
document.add(new Paragraph("2. Nếu sách vừa bẩn vừa rách, bạn đọc phải bồi thường 50% giá trị sách.", fontNormal));
document.add(new Paragraph("3. Làm mất sách, bạn đọc sẽ phải bồi thường 100% giá trị sách.", fontNormal));
document.add(new Paragraph("4. Trả sách trễ hạn sẽ bị phạt hành chính 50.000 VNĐ/lần.", fontNormal));

    document.close();

    JOptionPane.showMessageDialog(null, "Xuất file PDF thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

    if (Desktop.isDesktopSupported()) {
        Desktop.getDesktop().open(fileToSave);
    }

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Lỗi khi xuất file PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
} finally {
    try {
        if (conn != null) conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    }//GEN-LAST:event_btnXuatPDF1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXuatPDF;
    private javax.swing.JButton btnXuatPDF1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable tbnSach;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtKhoa;
    private javax.swing.JTextField txtLop;
    private javax.swing.JTextField txtMaPhieuMuon;
    private javax.swing.JTextField txtMaSinhVien;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenSinhVien;
    private javax.swing.JTextField txtTimKiemMaSach;
    private javax.swing.JTextField txtTimKiemMaSinhVien;
    // End of variables declaration//GEN-END:variables
}

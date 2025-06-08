/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.Utity;
import Admin.Controller.CustomScrollBar;
import Singleton.MyConnection;
import DAO.DAOPhieuMuon;
import DAO.DAOPhieuTra;
import Object.BanDoc;
import Object.PhieuMuon;
import Object.PhieuTra;
import Object.Sach;
//import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;  // Đảm bảo rằng bạn import đúng thư viện
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ADMIN
 */
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
import java.io.File;
import java.io.FileOutputStream;

public class QuanLyPhieuTraPanel extends javax.swing.JPanel {

    /**
     * Creates new form QuanLyPhieuTra
     */
    
    public QuanLyPhieuTraPanel() {
        initComponents();
          
        tbnSach.setRowHeight(25); 
        
        jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBar());
        jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBar());
        
    }

 private void searchStudentInfo() {
    String maPhieuMuon = txtMaPhieuMuon.getText().trim();
    if (maPhieuMuon.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phiếu mượn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String sql = """
        SELECT pm.maphieumuon, pm.masinhvien, pm.masach
        FROM PhieuMuon pm
        JOIN Sach s ON pm.masach = s.masach
        WHERE pm.maphieumuon = ? AND s.trangthai = N'đã mượn'
    """;

    try (Connection conn = MyConnection.getConnection();  // ✅ dùng lại kết nối
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, maPhieuMuon);
        try (ResultSet rs = ps.executeQuery()) {

            DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Mã sách", "Trả sách", "Hư hỏng"}, 0
            ) {
                @Override
                public Class<?> getColumnClass(int column) {
                    return (column == 1 || column == 2) ? Boolean.class : String.class;
                }
            };

            tbnSach.setModel(model);
            model.setRowCount(0);

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;
                txtMaPhieuMuon.setText(rs.getString("maphieumuon"));
                txtMaSinhVien.setText(rs.getString("masinhvien"));
                Object[] row = {
                    rs.getString("masach"),
                    false,
                    false
                };
                model.addRow(row);
            }

            if (!hasData) {
                JOptionPane.showMessageDialog(this, "Không có sách 'đã mượn' trong phiếu này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi tìm phiếu mượn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMaSinhVien = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbnSach = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtMaPhieuMuon = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        btnXuatPDF1 = new javax.swing.JButton();
        btnTraSach = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Mã sinh viên:");

        txtMaSinhVien.setBorder(null);
        txtMaSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSinhVienActionPerformed(evt);
            }
        });

        tbnSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Trả sách", "Hư hỏng"
            }
        ));
        jScrollPane1.setViewportView(tbnSach);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Mã phiếu mượn :");

        txtMaPhieuMuon.setBorder(null);
        txtMaPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPhieuMuonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        btnTraSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add_48.png"))); // NOI18N
        btnTraSach.setText("Trả sách");
        btnTraSach.setBorder(null);
        btnTraSach.setContentAreaFilled(false);
        btnTraSach.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTraSach.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTraSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnXuatPDF1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTraSach, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnXuatPDF1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTraSach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addComponent(txtMaPhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(jSeparator2)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1094, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(408, 408, 408)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txtMaPhieuMuon)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(txtMaSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(261, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSinhVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSinhVienActionPerformed

public boolean danhDauDaTra(String maPhieuMuon, String maSach) {
    String sql = "UPDATE PhieuMuon SET datra = 1 WHERE maphieumuon = ? AND masach = ?";
    
    try (Connection conn = MyConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
         
        ps.setString(1, maPhieuMuon);
        ps.setString(2, maSach);
        
        int rows = ps.executeUpdate();
        return rows > 0;
        
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

public void pdf(){
        JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
fileChooser.setSelectedFile(new File("PhieuTra.pdf"));

int userSelection = fileChooser.showSaveDialog(null);
if (userSelection != JFileChooser.APPROVE_OPTION) {
    return;
}

File fileToSave = fileChooser.getSelectedFile();

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
    // Sửa phần tạo kết nối bằng MyConnection
    conn = MyConnection.getConnection();

    String sql = "SELECT pt.maphieumuon, sv.masinhvien, sv.tensinhvien, " +
                 "       value as masach, s.tensach, pt.tinhtrangsach, pt.thoihan " +
                 "FROM PhieuTra pt " +
                 "JOIN SinhVien sv ON pt.masinhvien = sv.masinhvien " +
                 "CROSS APPLY STRING_SPLIT(pt.masach, ',') " +
                 "JOIN Sach s ON s.masach = value " +
                 "WHERE pt.thanhtoan = 0 " +
                 "AND pt.maphieumuon = ( " +
                 "    SELECT TOP 1 maphieumuon FROM PhieuTra WHERE thanhtoan = 0 ORDER BY thoigian DESC " +
                 ") " +
                 "ORDER BY pt.maphieumuon";

    PreparedStatement ps = conn.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    if (!rs.isBeforeFirst()) {
        JOptionPane.showMessageDialog(null, "Không có phiếu trả nào chưa thanh toán!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
    document.open();

    String fontPath = "C:\\Windows\\Fonts\\arial.ttf";
    BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    Font fontBold = new Font(baseFont, 14, Font.BOLD);
    Font fontNormal = new Font(baseFont, 12, Font.NORMAL);

    rs.next(); // Lấy bản ghi đầu tiên để in thông tin chung

    String maPhieuMuon = rs.getString("maphieumuon");
    String maSinhVien = rs.getString("masinhvien");
    String tenSinhVien = rs.getString("tensinhvien");

    document.add(new Paragraph("Mã phiếu mượn:\t" + maPhieuMuon, fontBold));
    document.add(new Paragraph("Mã sinh viên:\t" + maSinhVien, fontBold));
    document.add(new Paragraph("Tên sinh viên:\t" + tenSinhVien, fontBold));
    document.add(new Paragraph(" ")); // dòng trống

    PdfPTable table = new PdfPTable(4);
    table.setWidthPercentage(100);
    table.setWidths(new float[]{2f, 5f, 4f, 3f});

    String[] headers = {"Mã sách", "Tên sách", "Tình trạng sách", "Thời hạn"};
    for (String header : headers) {
        PdfPCell cell = new PdfPCell(new Paragraph(header, fontBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new Color(230, 230, 230));
        table.addCell(cell);
    }

    // Dữ liệu bản ghi đầu tiên trở đi
    do {
        String tinhTrangSach = rs.getString("tinhtrangsach");
        String tinhTrangText = "Tốt";
        if ("1".equals(tinhTrangSach)) {
            tinhTrangText = "Hư hỏng";
        }

        table.addCell(new PdfPCell(new Paragraph(rs.getString("masach"), fontNormal)));
        table.addCell(new PdfPCell(new Paragraph(rs.getString("tensach"), fontNormal)));
        table.addCell(new PdfPCell(new Paragraph(tinhTrangText, fontNormal)));
        table.addCell(new PdfPCell(new Paragraph(rs.getString("thoihan"), fontNormal)));
    } while (rs.next());

    document.add(table);
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


    private void btnTraSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraSachActionPerformed
       PhieuTra phieuTra = new PhieuTra();

    // Tạo phiếu mượn và gán cho phiếu trả
    PhieuMuon phieuMuon = new PhieuMuon();
    phieuMuon.setMaPhieuMuon(txtMaPhieuMuon.getText());

    // Tạo bạn đọc và gán mã sinh viên
    BanDoc banDoc = new BanDoc();
    banDoc.setMaSinhVien(txtMaSinhVien.getText());
    phieuMuon.setBanDoc(banDoc);

    phieuTra.setPhieuMuon(phieuMuon);

    // Lấy danh sách sách được trả
    ArrayList<Sach> dsSach = new ArrayList<>();
    DefaultTableModel model = (DefaultTableModel) tbnSach.getModel(); 
    int rowCount = model.getRowCount();

    if (rowCount == 0) {
        JOptionPane.showMessageDialog(this, "Chưa chọn sách để trả!");
        return;
    }

    for (int i = 0; i < rowCount; i++) {
        Boolean isTra = (Boolean) model.getValueAt(i, 1); // Checkbox trả sách
        Boolean isTinhTrangSach = (Boolean) model.getValueAt(i, 2); // Checkbox tình trạng sách

        if (isTra != null && isTra) {
            String maSach = model.getValueAt(i, 0).toString();

            Sach sach = new Sach();
            sach.setMaSach(maSach);
            sach.setTinhTrangSach(isTinhTrangSach != null && isTinhTrangSach); // Nếu null thì là false
            dsSach.add(sach);
            txtMaPhieuMuon.setText(" ");
        }
    }

    if (dsSach.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bạn chưa chọn sách nào để trả!");
        return;
    }

    phieuTra.setSach(dsSach); // Gán danh sách sách vào phiếu trả

    DAOPhieuTra dao = new DAOPhieuTra();
    DAOPhieuMuon daoPM = new DAOPhieuMuon(); // Thêm đối tượng DAOPhieuMuon để cập nhật trạng thái

    if (dao.ThemPhieuTra(phieuTra)) {
        JOptionPane.showMessageDialog(this, "Trả sách thành công!");
    

        // Cập nhật trạng thái đã trả thay vì xóa phiếu mượn
        for (Sach sach : dsSach) {
            danhDauDaTra(txtMaPhieuMuon.getText(), sach.getMaSach());
        }
        pdf();
    } else {
        JOptionPane.showMessageDialog(this, "Trả sách thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnTraSachActionPerformed

    private void txtMaPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPhieuMuonActionPerformed
        // TODO add your handling code here:
        searchStudentInfo();

    }//GEN-LAST:event_txtMaPhieuMuonActionPerformed

    private void btnXuatPDF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPDF1ActionPerformed
    JFileChooser fileChooser = new JFileChooser();
fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
fileChooser.setSelectedFile(new File("PhieuTra.pdf"));

int userSelection = fileChooser.showSaveDialog(null);
if (userSelection != JFileChooser.APPROVE_OPTION) {
    return;
}

File fileToSave = fileChooser.getSelectedFile();

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
    // Sửa phần tạo kết nối bằng MyConnection
    conn = MyConnection.getConnection();

    String sql = "SELECT pt.maphieumuon, sv.masinhvien, sv.tensinhvien, " +
                 "       value as masach, s.tensach, pt.tinhtrangsach, pt.thoihan " +
                 "FROM PhieuTra pt " +
                 "JOIN SinhVien sv ON pt.masinhvien = sv.masinhvien " +
                 "CROSS APPLY STRING_SPLIT(pt.masach, ',') " +
                 "JOIN Sach s ON s.masach = value " +
                 "WHERE pt.thanhtoan = 0 " +
                 "AND pt.maphieumuon = ( " +
                 "    SELECT TOP 1 maphieumuon FROM PhieuTra WHERE thanhtoan = 0 ORDER BY thoigian DESC " +
                 ") " +
                 "ORDER BY pt.maphieumuon";

    PreparedStatement ps = conn.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    if (!rs.isBeforeFirst()) {
        JOptionPane.showMessageDialog(null, "Không có phiếu trả nào chưa thanh toán!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
    document.open();

    String fontPath = "C:\\Windows\\Fonts\\arial.ttf";
    BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    Font fontBold = new Font(baseFont, 14, Font.BOLD);
    Font fontNormal = new Font(baseFont, 12, Font.NORMAL);

    rs.next(); // Lấy bản ghi đầu tiên để in thông tin chung

    String maPhieuMuon = rs.getString("maphieumuon");
    String maSinhVien = rs.getString("masinhvien");
    String tenSinhVien = rs.getString("tensinhvien");

    document.add(new Paragraph("Mã phiếu mượn:\t" + maPhieuMuon, fontBold));
    document.add(new Paragraph("Mã sinh viên:\t" + maSinhVien, fontBold));
    document.add(new Paragraph("Tên sinh viên:\t" + tenSinhVien, fontBold));
    document.add(new Paragraph(" ")); // dòng trống

    PdfPTable table = new PdfPTable(4);
    table.setWidthPercentage(100);
    table.setWidths(new float[]{2f, 5f, 4f, 3f});

    String[] headers = {"Mã sách", "Tên sách", "Tình trạng sách", "Thời hạn"};
    for (String header : headers) {
        PdfPCell cell = new PdfPCell(new Paragraph(header, fontBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new Color(230, 230, 230));
        table.addCell(cell);
    }

    // Dữ liệu bản ghi đầu tiên trở đi
    do {
        String tinhTrangSach = rs.getString("tinhtrangsach");
        String tinhTrangText = "Tốt";
        if ("1".equals(tinhTrangSach)) {
            tinhTrangText = "Hư hỏng";
        }

        table.addCell(new PdfPCell(new Paragraph(rs.getString("masach"), fontNormal)));
        table.addCell(new PdfPCell(new Paragraph(rs.getString("tensach"), fontNormal)));
        table.addCell(new PdfPCell(new Paragraph(tinhTrangText, fontNormal)));
        table.addCell(new PdfPCell(new Paragraph(rs.getString("thoihan"), fontNormal)));
    } while (rs.next());

    document.add(table);
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
    private javax.swing.JButton btnTraSach;
    private javax.swing.JButton btnXuatPDF1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tbnSach;
    private javax.swing.JTextField txtMaPhieuMuon;
    private javax.swing.JTextField txtMaSinhVien;
    // End of variables declaration//GEN-END:variables
}

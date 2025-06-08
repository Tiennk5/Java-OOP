/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.Utity;

import Admin.Controller.CustomScrollBar;
import static Admin.Utity.QuanLySinhVienPanel.getConnection;
import Singleton.MyConnection;

//import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.sql.Timestamp;


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
import javax.swing.table.TableRowSorter;

public class QuanLyPhieuPhat extends javax.swing.JPanel {
    public QuanLyPhieuPhat() {
         initComponents();

        tblHuHong.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "Mã phiếu mượn", "Tên sinh viên", "Mã sinh viên", "Mã sách",
                "Tình trạng sách", "Đúng hạn", "Giá tiền", "Phần trăm thiệt hại",
                "Tiền phạt", "Thanh toán"
            }
        ) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 7) return Integer.class;
                if (columnIndex == 8) return Double.class;
                if (columnIndex == 9) return Boolean.class;
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return (column == 7 || column == 9);
            }
        });

        // ComboBox từ 1-100 cho phần trăm thiệt hại
        JComboBox<Integer> percentageBox = new JComboBox<>();
        for (int i : new int[]{25, 50, 100}) {
    percentageBox.addItem(i);
}

        TableColumn column = tblHuHong.getColumnModel().getColumn(7);
        column.setCellEditor(new DefaultCellEditor(percentageBox));

        loadDamagedBooks();

        // Cập nhật tiền phạt khi phần trăm thiệt hại thay đổi
        tblHuHong.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int columnChanged = e.getColumn();

            if (columnChanged == 7) {
                DefaultTableModel model = (DefaultTableModel) tblHuHong.getModel();
                try {
                    Object val = model.getValueAt(row, 7);
                    if (val == null) return;

                    int phanTram = (int) val;
                    double giaTien = (double) model.getValueAt(row, 6);
                    double tienPhat = giaTien * phanTram / 100.0;
                    model.setValueAt(tienPhat, row, 8);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật tiền phạt: " + ex.getMessage());
                }
            }
        });
     }
    
public void loadDamagedBooks() {
    String sql = """
        SELECT pt.maphieumuon,
               sv.masinhvien,
               sv.tensinhvien,
               s.masach,
               s.giatien,
               pt.thoihan,
               pt.tinhtrangsach
        FROM PhieuTra pt
        JOIN SinhVien sv ON pt.masinhvien = sv.masinhvien
        JOIN Sach s ON pt.masach = s.masach
        WHERE (pt.tinhtrangsach = 1 OR pt.thoihan = N'quá hạn')
        AND pt.thanhtoan = 0
    """;

    try (Connection conn = MyConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        DefaultTableModel model = (DefaultTableModel) tblHuHong.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        while (rs.next()) {
            double giaTien = rs.getDouble("giatien");
            boolean isHuHong = rs.getBoolean("tinhtrangsach");
            String thoihan = rs.getString("thoihan");
            boolean isQuaHan = "quá hạn".equalsIgnoreCase(thoihan);

            double tienPhat = 0.0;

            if (isHuHong && !isQuaHan) {
                tienPhat = giaTien;
            } else if (!isHuHong && isQuaHan) {
                tienPhat = 50000;
            } else if (isHuHong && isQuaHan) {
                tienPhat = giaTien + 50000;
            }

            Object[] row = {
                rs.getString("maphieumuon"),
                rs.getString("tensinhvien"),
                rs.getString("masinhvien"),
                rs.getString("masach"),
                isHuHong ? "Hư hỏng" : "Tốt",
                isQuaHan ? "quá hạn" : "Đúng hạn",
                isHuHong ? giaTien : 0.0,
                isHuHong ? 100 : null, // phần trăm thiệt hại mặc định
                tienPhat,
                Boolean.FALSE // trạng thái thanh toán
            };

            model.addRow(row);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu sách hư hỏng hoặc quá hạn: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}





    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHuHong = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtTimKiem = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnXuatPDF1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblHuHong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu mượn", "Tên sinh viên", "Mã sinh viên", "Mã sách", "Tình trạng sách", "Tiền phạt", "Thanh toán"
            }
        ));
        jScrollPane2.setViewportView(tblHuHong);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/ticket_20.png"))); // NOI18N
        jLabel3.setText("Danh sách phiếu phạt");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 906, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã PM", " ", " " }));

        txtTimKiem.setToolTipText("");
        txtTimKiem.setSelectionColor(new java.awt.Color(187, 187, 187));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add_48px.png"))); // NOI18N
        btnAdd.setText("THANH TOÁN");
        btnAdd.setBorder(null);
        btnAdd.setContentAreaFilled(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel_48px.png"))); // NOI18N
        jButton7.setText("XUẤT EXCEL");
        jButton7.setBorder(null);
        jButton7.setContentAreaFilled(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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
                .addContainerGap()
                .addComponent(btnAdd)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXuatPDF1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 344, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnXuatPDF1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
public void pdf(){
       JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
    fileChooser.setSelectedFile(new File("PhieuPhat.pdf"));

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
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;trustServerCertificate=true",
                "sa", "123456789");

       String sql = "SELECT pp.maphieumuon, sv.masinhvien, sv.tensinhvien, " +
             "       value as masach, s.tensach, s.giatien, pp.phantramthiethai, pp.tienphat, pp.thoigian " +
             "FROM PhieuPhat pp " +
             "JOIN SinhVien sv ON pp.masinhvien = sv.masinhvien " +
             "CROSS APPLY STRING_SPLIT(pp.masach, ',') " +
             "JOIN Sach s ON s.masach = value " +
             "WHERE pp.maphieumuon = ( " +
             "    SELECT TOP 1 maphieumuon FROM PhieuPhat ORDER BY thoigian DESC " +
             ") " +
             "ORDER BY pp.maphieumuon";


        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu phiếu phạt!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
        document.open();

        String fontPath = "C:\\Windows\\Fonts\\arial.ttf";
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fontBold = new Font(baseFont, 14, Font.BOLD);
        Font fontNormal = new Font(baseFont, 12, Font.NORMAL);

        String maPhieuMuon = "";
        String maSinhVien = "";
        String tenSinhVien = "";

        boolean isFirstRow = true;
        double tongTienPhat = 0;

        PdfPTable table = null;

        String[] headers = {"Mã sách", "Tên sách", "Giá tiền", "Phần trăm thiệt hại", "Tiền phạt", "Thời gian"};

        while (rs.next()) {
            if (isFirstRow) {
                maPhieuMuon = rs.getString("maphieumuon");
                maSinhVien = rs.getString("masinhvien");
                tenSinhVien = rs.getString("tensinhvien");

                document.add(new Paragraph("Mã phiếu mượn: " + maPhieuMuon, fontBold));
                document.add(new Paragraph("Mã sinh viên: " + maSinhVien, fontBold));
                document.add(new Paragraph("Tên sinh viên: " + tenSinhVien, fontBold));
                document.add(new Paragraph(" ")); // dòng trống

                table = new PdfPTable(6);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{2f, 5f, 3f, 3f, 3f, 4f});

                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontBold));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(new Color(230, 230, 230));
                    table.addCell(cell);
                }

                isFirstRow = false;
            }

            table.addCell(new PdfPCell(new Paragraph(rs.getString("masach"), fontNormal)));
            table.addCell(new PdfPCell(new Paragraph(rs.getString("tensach"), fontNormal)));
            table.addCell(new PdfPCell(new Paragraph(String.format("%.0f", rs.getDouble("giatien")), fontNormal)));
            table.addCell(new PdfPCell(new Paragraph(rs.getInt("phantramthiethai") + "%", fontNormal)));

            double tienPhat = rs.getDouble("tienphat");
            table.addCell(new PdfPCell(new Paragraph(String.format("%.0f", tienPhat), fontNormal)));
            table.addCell(new PdfPCell(new Paragraph(rs.getString("thoigian"), fontNormal)));

            tongTienPhat += tienPhat;
        }

        // Thêm dòng tổng tiền phạt
        PdfPCell cellTong = new PdfPCell(new Paragraph("Tổng tiền phạt", fontBold));
        cellTong.setColspan(4);
        cellTong.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellTong.setBackgroundColor(new Color(230, 230, 230));
        table.addCell(cellTong);

        PdfPCell cellTongTien = new PdfPCell(new Paragraph(String.format("%.0f", tongTienPhat), fontBold));
        cellTongTien.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTongTien.setBackgroundColor(new Color(230, 230, 230));
        table.addCell(cellTongTien);

        PdfPCell cellEmpty = new PdfPCell(new Paragraph(""));
        table.addCell(cellEmpty);

        document.add(table);
        document.close();

        JOptionPane.showMessageDialog(null, "Xuất file PDF Phiếu Phạt thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

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
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
    DefaultTableModel model = (DefaultTableModel) tblHuHong.getModel();
try (Connection conn = MyConnection.getConnection()) {
    int rowCount = model.getRowCount();
    int successCount = 0;

    for (int i = 0; i < rowCount; i++) {
        // Kiểm tra nếu giá trị trong cột 9 là null trước khi thực hiện
        Boolean isThanhToan = (Boolean) model.getValueAt(i, 9);
        if (isThanhToan != null && isThanhToan) {
            String maphieumuon = model.getValueAt(i, 0) != null ? model.getValueAt(i, 0).toString() : "";
            String masinhvien = model.getValueAt(i, 2) != null ? model.getValueAt(i, 2).toString() : "";
            String masach = model.getValueAt(i, 3) != null ? model.getValueAt(i, 3).toString() : "";
            String phantramthiethai = model.getValueAt(i, 7) != null ? model.getValueAt(i, 7).toString() : "";
            String tienphat1 = model.getValueAt(i, 8) != null ? model.getValueAt(i, 8).toString() : "";

            // Kiểm tra giá trị trong cột 8 và chuyển thành double nếu không null, nếu null gán giá trị 0
            double tienphat = 0;
            if (model.getValueAt(i, 8) != null) {
                try {
                    tienphat = Double.parseDouble(model.getValueAt(i, 8).toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi chuyển đổi giá trị tiền phạt tại dòng " + (i + 1) + ": " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Dừng xử lý nếu có lỗi
                }
            }

            // Insert vào bảng PhieuPhat
            String insertSql = "INSERT INTO PhieuPhat (maphieumuon, masinhvien, masach, phantramthiethai, tienphat, thoigian) " +
                               "VALUES (?, ?, ?, ?, ?, GETDATE())";
            try (PreparedStatement psInsert = conn.prepareStatement(insertSql)) {
                psInsert.setString(1, maphieumuon);
                psInsert.setString(2, masinhvien);
                psInsert.setString(3, masach);
                psInsert.setString(4, phantramthiethai);
                psInsert.setString(5, tienphat1);

                psInsert.executeUpdate();
            }

            // Cập nhật bảng PhieuTra sau khi thanh toán
           
// String updateSql = "UPDATE PhieuTra SET thanhtoan = 1 WHERE maphieumuon = ? AND masinhvien = ? AND masach = ?";
String updateSql = "UPDATE PhieuTra SET thanhtoan = 1 WHERE maphieumuon = ? AND masinhvien = ? AND masach = ?";
            try (PreparedStatement psUpdate = conn.prepareStatement(updateSql)) {
                psUpdate.setString(1, maphieumuon);
                psUpdate.setString(2, masinhvien);
                psUpdate.setString(3, masach);
                psUpdate.executeUpdate();
                successCount++; // Đếm số lượng phiếu thanh toán thành công
            }
        }
    }

    JOptionPane.showMessageDialog(this, "Đã thanh toán " + successCount + " phiếu thành công!");
    loadDamagedBooks(); // Load lại dữ liệu
    pdf();

} catch (SQLException | NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Lỗi khi xử lý thanh toán: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
}

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnXuatPDF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatPDF1ActionPerformed
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file PDF");
    fileChooser.setSelectedFile(new File("PhieuPhat.pdf"));

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
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;trustServerCertificate=true",
                "sa", "123456789");

       String sql = "SELECT pp.maphieumuon, sv.masinhvien, sv.tensinhvien, " +
             "       value as masach, s.tensach, s.giatien, pp.phantramthiethai, pp.tienphat, pp.thoigian " +
             "FROM PhieuPhat pp " +
             "JOIN SinhVien sv ON pp.masinhvien = sv.masinhvien " +
             "CROSS APPLY STRING_SPLIT(pp.masach, ',') " +
             "JOIN Sach s ON s.masach = value " +
             "WHERE pp.maphieumuon = ( " +
             "    SELECT TOP 1 maphieumuon FROM PhieuPhat ORDER BY thoigian DESC " +
             ") " +
             "ORDER BY pp.maphieumuon";


        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (!rs.isBeforeFirst()) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu phiếu phạt!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
        document.open();

        String fontPath = "C:\\Windows\\Fonts\\arial.ttf";
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fontBold = new Font(baseFont, 14, Font.BOLD);
        Font fontNormal = new Font(baseFont, 12, Font.NORMAL);

        String maPhieuMuon = "";
        String maSinhVien = "";
        String tenSinhVien = "";

        boolean isFirstRow = true;
        double tongTienPhat = 0;

        PdfPTable table = null;

        String[] headers = {"Mã sách", "Tên sách", "Giá tiền", "Phần trăm thiệt hại", "Tiền phạt", "Thời gian"};

        while (rs.next()) {
            if (isFirstRow) {
                maPhieuMuon = rs.getString("maphieumuon");
                maSinhVien = rs.getString("masinhvien");
                tenSinhVien = rs.getString("tensinhvien");

                document.add(new Paragraph("Mã phiếu mượn: " + maPhieuMuon, fontBold));
                document.add(new Paragraph("Mã sinh viên: " + maSinhVien, fontBold));
                document.add(new Paragraph("Tên sinh viên: " + tenSinhVien, fontBold));
                document.add(new Paragraph(" ")); // dòng trống

                table = new PdfPTable(6);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{2f, 5f, 3f, 3f, 3f, 4f});

                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Paragraph(header, fontBold));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(new Color(230, 230, 230));
                    table.addCell(cell);
                }

                isFirstRow = false;
            }

            table.addCell(new PdfPCell(new Paragraph(rs.getString("masach"), fontNormal)));
            table.addCell(new PdfPCell(new Paragraph(rs.getString("tensach"), fontNormal)));
            table.addCell(new PdfPCell(new Paragraph(String.format("%.0f", rs.getDouble("giatien")), fontNormal)));
            table.addCell(new PdfPCell(new Paragraph(rs.getInt("phantramthiethai") + "%", fontNormal)));

            double tienPhat = rs.getDouble("tienphat");
            table.addCell(new PdfPCell(new Paragraph(String.format("%.0f", tienPhat), fontNormal)));
            table.addCell(new PdfPCell(new Paragraph(rs.getString("thoigian"), fontNormal)));

            tongTienPhat += tienPhat;
        }

        // Thêm dòng tổng tiền phạt
        PdfPCell cellTong = new PdfPCell(new Paragraph("Tổng tiền phạt", fontBold));
        cellTong.setColspan(4);
        cellTong.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cellTong.setBackgroundColor(new Color(230, 230, 230));
        table.addCell(cellTong);

        PdfPCell cellTongTien = new PdfPCell(new Paragraph(String.format("%.0f", tongTienPhat), fontBold));
        cellTongTien.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellTongTien.setBackgroundColor(new Color(230, 230, 230));
        table.addCell(cellTongTien);

        PdfPCell cellEmpty = new PdfPCell(new Paragraph(""));
        table.addCell(cellEmpty);

        document.add(table);
        document.close();

        JOptionPane.showMessageDialog(null, "Xuất file PDF Phiếu Phạt thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

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
private void timKiemPhieuPhat() {
    String keyword = txtTimKiem.getText().trim();
    String selectedOption = jComboBox1.getSelectedItem() != null ? jComboBox1.getSelectedItem().toString().trim() : "";

    if (keyword.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) tblHuHong.getModel();
    model.setRowCount(0); // Xóa dữ liệu cũ

    String sql = "SELECT pt.maphieumuon, sv.tensinhvien, sv.masinhvien, s.masach, "
               + "pt.tinhtrangsach, pt.thoihan, s.giatien, pp.phantramthiethai, pp.tienphat "
               + "FROM PhieuPhat pp "
               + "JOIN PhieuTra pt ON pp.maphieumuon = pt.maphieumuon AND pp.masach = pt.masach "
               + "JOIN SinhVien sv ON pt.masinhvien = sv.masinhvien "
               + "JOIN Sach s ON pt.masach = s.masach "
               + "WHERE ";

    if (selectedOption.equals("Mã PM")) {
        sql += "pp.maphieumuon LIKE ?";
    } else if (selectedOption.equals("Mã SV")) {
        sql += "sv.masinhvien LIKE ?";
    } else if (selectedOption.equals("Tất cả")) {
        sql += "(sv.masinhvien LIKE ? OR pm.maphieumuon LIKE ? OR s.masach LIKE ?)";
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn tiêu chí tìm kiếm hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        if (selectedOption.equals("Tất cả")) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
        } else {
            ps.setString(1, "%" + keyword + "%");
        }

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("maphieumuon"),
                    rs.getString("tensinhvien"),
                    rs.getString("masinhvien"),
                    rs.getString("masach"),
                    rs.getInt("tinhtrangsach") == 1 ? "Hư hỏng" : "Bình thường",
                    rs.getString("thoihan"),
                    rs.getDouble("giatien"),
                    rs.getInt("phantramthiethai"),
                    rs.getDouble("tienphat")
                });
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi tìm kiếm: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
       String text = txtTimKiem.getText().trim();

    // Lấy DefaultTableModel từ JTable "model"
    DefaultTableModel tableModel = (DefaultTableModel) tblHuHong.getModel();

    // Tạo bộ lọc cho JTable
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
    tblHuHong.setRowSorter(sorter);

    if (text.length() == 0) {
        sorter.setRowFilter(null); // Hiển thị tất cả
    } else {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0)); // Lọc theo cột 0 (Mã phiếu mượn)
    }
    }//GEN-LAST:event_txtTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnXuatPDF1;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblHuHong;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}

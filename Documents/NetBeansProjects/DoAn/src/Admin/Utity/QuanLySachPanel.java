/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.Utity;

import Admin.Controller.CustomScrollBar;
import Singleton.MyConnection;
import DAO.DAOChangeSach;
import DAO.DAOSach;
import Object.Sach;
import java.awt.Cursor;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class QuanLySachPanel extends javax.swing.JPanel {

    private List<Sach> dsSach;
    private DefaultTableModel model;
    private Connection conn;
    public QuanLySachPanel() {
        initComponents();
        tblModel.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblModel.setRowHeight(25); 
        dsSach = new ArrayList<>();
        model = (DefaultTableModel) tblModel.getModel();
        jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBar());
        jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBar());
       loadBooksFromDatabase();

        jpnSachMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(
          "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;trustServerCertificate=true",
          "sa", "123456789");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

      public static Connection getConnection() {
        try {
            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setUser("sa");
            ds.setPassword("123456789");
            ds.setServerName("DESKTOP-S0888IV");
            ds.setPortNumber(1433);
            ds.setDatabaseName("Test");
            ds.setEncrypt(false);
            return ds.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
      
    public void loadBooksFromDatabase() {
   Connection conn = MyConnection.getConnection();
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Không thể kết nối đến Database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    String sql = "SELECT * from Sach ORDER BY thoigianthemsach ASC"; 

    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        dsSach.clear();
        model.setRowCount(0);

        while (rs.next()) {
            String maSach = rs.getString("masach");
            String tenSach = rs.getString("tensach");
            String tacGia = rs.getString("tacgia");
            String theLoai = rs.getString("theloai");
            String nhaXuatBan = rs.getString("nhaxuatban");
            String giaSach= rs.getString("giatien");
            String trangthai =rs.getString("trangthai");
            Sach sach = new Sach(maSach, tenSach, tacGia, theLoai, nhaXuatBan, giaSach, trangthai);
            dsSach.add(sach);
            model.addRow(new Object[]{maSach, tenSach, tacGia, theLoai, nhaXuatBan, giaSach, trangthai});
        }

    } catch (SQLException e) {
       // JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
         e.printStackTrace();
    }
}
   public void addBook(Sach sach) {
   Connection conn = MyConnection.getConnection();
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Không thể kết nối đến Database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 📌 Thêm cột InsertedAt để đảm bảo hiển thị đúng thứ tự nhập vào
    String sql = "INSERT INTO Sach (masach, tensach, tacgia, theloai, nhaxuatban, thoigianthemsach, giatien, trangthai) VALUES (?, ?, ?, ?, ?, GETDATE(), ?, ?)";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, sach.getMaSach());
        ps.setString(2, sach.getTenSach());
        ps.setString(3, sach.getTacGia());
        ps.setString(4, sach.getTheLoai());
        ps.setString(5, sach.getNhaXuatBan());
        ps.setString(6, sach.getGiaSach());
        ps.setString(7, sach.getTrangThai());
        int rowsInserted = ps.executeUpdate();
        if (rowsInserted > 0) {
            dsSach.add(sach);
            model.addRow(new Object[]{sach.getMaSach(), sach.getTenSach(), sach.getTacGia(), sach.getTheLoai(), sach.getNhaXuatBan(), sach.getGiaSach()});
            model.fireTableDataChanged();
           String msg = "Thêm sách thành công!\n\n"
           + "Mã sách: " + sach.getMaSach() + "\n"
           + "Tên sách: " + sach.getTenSach() + "\n"
           + "Tác giả: " + sach.getTacGia() + "\n"
           + "Thể loại: " + sach.getTheLoai() + "\n"
           + "Nhà xuất bản: " + sach.getNhaXuatBan() + "\n"
           + "Giá sách: " + sach.getGiaSach();

JOptionPane.showMessageDialog(this, msg, "Thêm sách thành công!", JOptionPane.INFORMATION_MESSAGE);

        }
    } catch (SQLException e) {
       // JOptionPane.showMessageDialog(this, "Lỗi khi thêm sách: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
public void updateBook(Sach sach) {
    Connection conn = getConnection();
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Không thể kết nối đến Database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String sql = "UPDATE Sach SET tensach = ?, tacgia = ?, theloai = ?, nhaxuatban = ?, giatien = ?, trangthai = ? WHERE masach = ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, sach.getTenSach());
        ps.setString(2, sach.getTacGia());
        ps.setString(3, sach.getTheLoai());
        ps.setString(4, sach.getNhaXuatBan());
        ps.setString(5, sach.getGiaSach());
        ps.setString(6, sach.getTrangThai());
        ps.setString(7, sach.getMaSach());

        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            // Cập nhật lại dữ liệu trên bảng nếu cần
            model.fireTableDataChanged();

            String msg = "Cập nhật sách thành công!\n\n"
                       + "Mã sách: " + sach.getMaSach() + "\n"
                       + "Tên sách: " + sach.getTenSach() + "\n"
                       + "Tác giả: " + sach.getTacGia() + "\n"
                       + "Thể loại: " + sach.getTheLoai() + "\n"
                       + "Nhà xuất bản: " + sach.getNhaXuatBan() + "\n"
                       + "Giá sách: " + sach.getGiaSach();

            JOptionPane.showMessageDialog(this, msg, "Cập nhật sách", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sách để cập nhật!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật sách: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}


    // 🔥 Xóa sách khỏi database
  private void deleteBookFromDatabase(String maSach) {
    Connection conn = MyConnection.getConnection();
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Không thể kết nối đến Database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Kiểm tra sách có từng bị trả về với tình trạng hư hỏng không
        String checkHuHong = "SELECT COUNT(*) FROM PhieuTra WHERE masach = ? AND tinhtrangsach = '1' AND thanhtoan = '0'";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkHuHong)) {
            checkStmt.setString(1, maSach);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "Không thể xóa! Sách đã từng bị trả về với tình trạng 'Hư hỏng'.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Xóa nếu sách không ở trạng thái "đã mượn"
        String sql = "DELETE FROM Sach WHERE masach = ? AND trangthai != N'đã mượn'";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSach);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Xóa sách thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Không thể xóa! Sách đang được mượn hoặc không tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi xóa sách: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jpnSachMenu = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtTimKiem = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        brnXuatExcelSach = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();
        btnGhi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblModel = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jpnSachMenu.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã sách", "Tên sách", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        txtTimKiem.setToolTipText("");
        txtTimKiem.setSelectionColor(new java.awt.Color(187, 187, 187));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add_48px.png"))); // NOI18N
        btnAdd.setText("THÊM");
        btnAdd.setBorder(null);
        btnAdd.setContentAreaFilled(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash_48px.png"))); // NOI18N
        btnDelete.setText("XÓA");
        btnDelete.setBorder(null);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        brnXuatExcelSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel_48px.png"))); // NOI18N
        brnXuatExcelSach.setText("XUẤT EXCEL");
        brnXuatExcelSach.setBorder(null);
        brnXuatExcelSach.setContentAreaFilled(false);
        brnXuatExcelSach.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        brnXuatExcelSach.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        brnXuatExcelSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnXuatExcelSachActionPerformed(evt);
            }
        });

        btnChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pencil_48px.png"))); // NOI18N
        btnChange.setText("SỬA");
        btnChange.setBorder(null);
        btnChange.setContentAreaFilled(false);
        btnChange.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChange.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        btnGhi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/GHI_EXCEL.png"))); // NOI18N
        btnGhi.setText("GHI EXCEL");
        btnGhi.setBorder(null);
        btnGhi.setContentAreaFilled(false);
        btnGhi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGhi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGhi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGhiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnSachMenuLayout = new javax.swing.GroupLayout(jpnSachMenu);
        jpnSachMenu.setLayout(jpnSachMenuLayout);
        jpnSachMenuLayout.setHorizontalGroup(
            jpnSachMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSachMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(brnXuatExcelSach, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGhi, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );
        jpnSachMenuLayout.setVerticalGroup(
            jpnSachMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSachMenuLayout.createSequentialGroup()
                .addGroup(jpnSachMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnSachMenuLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jpnSachMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnSachMenuLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jpnSachMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnChange)
                            .addComponent(btnAdd)
                            .addComponent(btnDelete)
                            .addComponent(brnXuatExcelSach)
                            .addComponent(btnGhi))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnSachMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpnSachMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblModel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Tác giả", "Thể loại", "Nhà xuất bản", "Giá sách", "Trạng thái"
            }
        ));
        jScrollPane1.setViewportView(tblModel);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        jButton1.setText("Làm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(987, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(615, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    int selectedRow = tblModel.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một sách để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String maSach = model.getValueAt(selectedRow, 0).toString();
    String tenSach = model.getValueAt(selectedRow, 1).toString();

    int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa sách \"" + tenSach + "\"?", 
            "Xác nhận xóa", JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
    
    if (confirm == JOptionPane.YES_OPTION) {
        deleteBookFromDatabase(maSach);
        
        // Cập nhật lại bảng sau khi xóa thành công
         loadBooksFromDatabase();
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        //TODO add your handling code here:
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
    
     //Nếu không tìm thấy JFrame cha, báo lỗi
    if (parentFrame == null) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy cửa sổ chính!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

     DAOSach addDialog = new DAOSach(parentFrame, this);
     addDialog.setVisible(true);
      loadBooksFromDatabase();
    }                                      

    private void jbtnDeleteBookActionPerformed(java.awt.event.ActionEvent evt) {                                               
         int selectedRow = tblModel.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một sách để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String maSach = model.getValueAt(selectedRow, 0).toString();
    String tenSach = model.getValueAt(selectedRow, 1).toString();

    int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa sách \"" + tenSach + "\"?", 
            "Xác nhận xóa", JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
    
    if (confirm == JOptionPane.YES_OPTION) {
        deleteBookFromDatabase(maSach);
        
        // Cập nhật lại bảng sau khi xóa thành công
        loadBooksFromDatabase();
    }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
       //TODO add your handling code here:
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
    
     //Nếu không tìm thấy JFrame cha, báo lỗi
    if (parentFrame == null) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy cửa sổ chính!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

     DAOChangeSach addDialog = new DAOChangeSach(parentFrame, this);
     addDialog.setVisible(true);
      loadBooksFromDatabase();
    }//GEN-LAST:event_btnChangeActionPerformed

    private void brnXuatExcelSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnXuatExcelSachActionPerformed
       JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file CSV");
    fileChooser.setSelectedFile(new File("DanhSachSach.csv"));

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection != JFileChooser.APPROVE_OPTION) {
        return;
    }

    File fileToSave = fileChooser.getSelectedFile();

    if (fileToSave.exists()) {
        int option = JOptionPane.showConfirmDialog(this, "File đã tồn tại. Bạn có muốn ghi đè?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.NO_OPTION) {
            return;
        }
    }

    // Lấy dữ liệu từ bảng sách
    DefaultTableModel model = (DefaultTableModel) tblModel.getModel(); // Đổi tên nếu cần
    int rowCount = model.getRowCount();
    int columnCount = model.getColumnCount();

    try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileToSave), StandardCharsets.UTF_8);
         BufferedWriter writer = new BufferedWriter(osw)) {

        // Ghi BOM UTF-8 để không lỗi font
        writer.write('\uFEFF');

        // Ghi tiêu đề cột
        for (int col = 0; col < columnCount; col++) {
            writer.write(model.getColumnName(col));
            if (col < columnCount - 1) writer.write(",");
        }
        writer.newLine();

        // Ghi dữ liệu từng dòng
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                Object value = model.getValueAt(row, col);
                writer.write(value != null ? value.toString() : "");
                if (col < columnCount - 1) writer.write(",");
            }
            writer.newLine();
        }

        writer.flush();
        JOptionPane.showMessageDialog(this, "Xuất file CSV thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        // Mở file CSV sau khi lưu
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(fileToSave);
        }

    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi xuất file CSV: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_brnXuatExcelSachActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
       String keyword = txtTimKiem.getText().trim();
String selectedOption = jComboBox1.getSelectedItem() != null ? jComboBox1.getSelectedItem().toString().trim() : "";

if (keyword.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
    return;
}

String sql = "SELECT * FROM Sach WHERE ";

// Kiểm tra các tiêu chí tìm kiếm
if (selectedOption.equals("Mã sách")) {
    sql += "masach LIKE ?";
} else if (selectedOption.equals("Tên sách")) {
    sql += "tensach LIKE ?";
} else if (selectedOption.equals("Tất cả")) {
    sql += "(masach LIKE ? OR tensach LIKE ? OR tacgia LIKE ? OR theloai LIKE ? OR nhaxuatban LIKE ? OR giatien LIKE ?)";
} else {
    JOptionPane.showMessageDialog(this, "Vui lòng chọn tiêu chí tìm kiếm hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    return;
}

try (Connection conn = getConnection(); 
     PreparedStatement ps = conn.prepareStatement(sql)) {

    // Nếu chọn "Tất cả", tìm kiếm trên tất cả các trường
    if (selectedOption.equals("Tất cả")) {
        for (int i = 1; i <= 6; i++) {
            ps.setString(i, "%" + keyword + "%"); // Đặt giá trị cho tất cả các cột, vì chúng là kiểu varchar
        }
    } else {
        ps.setString(1, "%" + keyword + "%"); // Đặt giá trị cho cột được chọn, vì tất cả đều là varchar
    }

    try (ResultSet rs = ps.executeQuery()) {
        model.setRowCount(0);  // Xóa dữ liệu cũ trong bảng

        while (rs.next()) {
            String maSach = rs.getString("masach");
            String tenSach = rs.getString("tensach");
            String tacGia = rs.getString("tacgia");
            String theLoai = rs.getString("theloai");
            String nhaXuatBan = rs.getString("nhaxuatban");
            String giaTien = rs.getString("giatien");  // Đọc là String vì giatien là varchar
            String trangthai = rs.getString("trangthai");
            model.addRow(new Object[]{maSach, tenSach, tacGia, theLoai, nhaXuatBan, giaTien, trangthai});
        }
    }

} catch (SQLException e) {
    JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
}

    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          loadBooksFromDatabase();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnGhiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGhiActionPerformed
      JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn file CSV để nhập vào database");
    int result = fileChooser.showOpenDialog(this);

    if (result != JFileChooser.APPROVE_OPTION) {
        return; // Người dùng hủy chọn
    }

    File selectedFile = fileChooser.getSelectedFile();

    try (
        BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream(selectedFile), StandardCharsets.UTF_8)
        );
        Connection conn = DriverManager.getConnection(
            "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;trustServerCertificate=true",
          "sa", "123456789"
        )
    ) {
        String line;
        boolean isFirstLine = true;

        String sql = "INSERT INTO Sach (masach, tensach, tacgia, theloai, nhaxuatban, thoigianthemsach, giatien, trangthai)"
                + " VALUES (?, ?, ?, ?, ?, getdate(), ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue; // Bỏ dòng tiêu đề
            }

            // Tách dữ liệu theo dấu phân cách
            String[] values = line.split(";", -1); // đổi thành "," nếu file dùng dấu phẩy

            if (values.length >= 6) {
                  pstmt.setString(1, values[0].trim()); // masach
                  pstmt.setString(2, values[1].trim()); // tensach
                  pstmt.setString(3, values[2].trim()); // tacgia
                  pstmt.setString(4, values[3].trim()); // theloai
                  pstmt.setString(5, values[4].trim()); // nhaxuatban
                  pstmt.setString(6, values[5].trim()); // giatien
                  pstmt.setString(7, "chưa mượn"); // trangthai


                  pstmt.executeUpdate();
            }
        }

        JOptionPane.showMessageDialog(this, "Import dữ liệu thành công!");
        loadBooksFromDatabase();

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi import CSV: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);

    }
    }//GEN-LAST:event_btnGhiActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brnXuatExcelSach;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnGhi;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpnSachMenu;
    private javax.swing.JTable tblModel;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}

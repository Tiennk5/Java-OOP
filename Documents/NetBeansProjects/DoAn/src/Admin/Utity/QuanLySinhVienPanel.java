/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.Utity;
import Admin.Controller.CustomScrollBar;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import Admin.Controller.CustomScrollBar;
import DAO.DAOBanDoc;
//import static GiaoDien.TableBanDoc.getConnection;
import Object.BanDoc;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


import Admin.Controller.CustomScrollBar;
import Object.BanDoc;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.DriverManager;
import javax.swing.JFileChooser;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ADMIN
 */
public class QuanLySinhVienPanel extends javax.swing.JPanel {

    private List<BanDoc> dsBanDoc;
    private DefaultTableModel model;
    private Connection conn;
    public QuanLySinhVienPanel() {
        initComponents();
        
        
        
        tblModel.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        //tlbManageReader.getTableHeader().setForeground(new Color(255,255,255));
   
        tblModel.setRowHeight(25); 
        
        jScrollPane2.getVerticalScrollBar().setUI(new CustomScrollBar());
        jScrollPane2.getHorizontalScrollBar().setUI(new CustomScrollBar());
        //setUndecorated(true); // ✅ Đặt JFrame thành không viền trước khi thay đổi màu nền
        initComponents(); // Sau đó mới khởi tạo giao diện
        tblModel.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        //tlbManageReader.getTableHeader().setForeground(new Color(255,255,255));
   
        tblModel.setRowHeight(25); 
        
        jpnSinhVienMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        DefaultTableModel model = (DefaultTableModel) tblModel.getModel();

        
        dsBanDoc = new ArrayList<>();
        model = (DefaultTableModel) tblModel.getModel();
        setupTable(); // ✅ Đảm bảo model được khởi tạo trước khi dùng
        loadStudentsFromDatabase(); // Gọi sau khi setupTable()
    
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(
          "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;trustServerCertificate=true",
          "sa", "123456789");
    } catch (Exception e) {
        e.printStackTrace();
    }
       
    }

    private void setupTable() {
    model = new DefaultTableModel(
        new Object[][]{},
        new String[]{"Mã sinh viên", "Tên sinh viên", "Lớp", "Khoa", "Số điện thoại", "Giới tính"}
    );

    // Gán model vào JTable
    
    tblModel.setModel(model);
    jScrollPane2.getVerticalScrollBar().setUI(new CustomScrollBar());
       jScrollPane2.getHorizontalScrollBar().setUI(new CustomScrollBar());
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

    public void loadStudentsFromDatabase() {
    Connection conn = getConnection();
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Không thể kết nối đến Database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String sql = "SELECT * FROM BanDoc ORDER BY thoigiandangky ASC";  

    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        dsBanDoc.clear();
        model.setRowCount(0);

        while (rs.next()) {
            String maSV = rs.getString("masinhvien");
            String tenSV = rs.getString("tensinhvien");
            String lop = rs.getString("lop");
            String khoa = rs.getString("khoa");
            String soDT = rs.getString("sodienthoai");
            String gioiTinh = rs.getString("gioitinh");

            BanDoc bandoc = new BanDoc(maSV, tenSV, lop, khoa, soDT, gioiTinh);
            dsBanDoc.add(bandoc);
            model.addRow(new Object[]{maSV, tenSV, lop, khoa, soDT, gioiTinh});
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    public void addStudent(BanDoc bandoc) {
   Connection conn = getConnection();
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Không thể kết nối đến Database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
         
        String checkSQL = "SELECT tensinhvien, lop, khoa, sodienthoai, gioitinh FROM SinhVien WHERE masinhvien = ?";
        try (PreparedStatement checkPS = conn.prepareStatement(checkSQL)) {
            checkPS.setString(1, bandoc.getMaSinhVien());
            ResultSet rs = checkPS.executeQuery();

            if (!rs.next()) {  
                JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên trong hệ thống. Không thể thêm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            String dbTenSinhVien = rs.getString("tensinhvien");
            String dbLop = rs.getString("lop");
            String dbKhoa = rs.getString("khoa");
            String dbSoDienThoai = rs.getString("sodienthoai");
            String dbGioiTinh = rs.getString("gioitinh");
            rs.close();

            
            if (!bandoc.getTenSinhVien().equalsIgnoreCase(dbTenSinhVien) ||
                !bandoc.getLop().equalsIgnoreCase(dbLop) ||
                !bandoc.getKhoa().equalsIgnoreCase(dbKhoa) ||
                !bandoc.getSDT().equalsIgnoreCase(dbSoDienThoai) ||
                !bandoc.getGioiTinh().equalsIgnoreCase(dbGioiTinh)) {
                
                JOptionPane.showMessageDialog(this, "Thông tin nhập không khớp với dữ liệu trong hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

             
            String checkBanDocSQL = "SELECT 1 FROM BanDoc WHERE masinhvien = ?";
            try (PreparedStatement checkBanDocPS = conn.prepareStatement(checkBanDocSQL)) {
                checkBanDocPS.setString(1, bandoc.getMaSinhVien());
                ResultSet rsBanDoc = checkBanDocPS.executeQuery();
                
                if (rsBanDoc.next()) { 
                    JOptionPane.showMessageDialog(this, "Sinh viên này đã là bạn đọc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                rsBanDoc.close();
            }

           
            String insertSQL = "INSERT INTO BanDoc (masinhvien, tensinhvien, lop, khoa, sodienthoai, gioitinh, thoigiandangky) VALUES (?, ?, ?, ?, ?, ?, GETDATE())";
            try (PreparedStatement insertPS = conn.prepareStatement(insertSQL)) {
                insertPS.setString(1, bandoc.getMaSinhVien());
                insertPS.setString(2, dbTenSinhVien);
                insertPS.setString(3, dbLop);
                insertPS.setString(4, dbKhoa);
                insertPS.setString(5, dbSoDienThoai);
                insertPS.setString(6, dbGioiTinh);

                int rowsInserted = insertPS.executeUpdate();
                if (rowsInserted > 0) {
                    dsBanDoc.add(bandoc);
                    model.addRow(new Object[]{bandoc.getMaSinhVien(), dbTenSinhVien, dbLop, dbKhoa, dbSoDienThoai, dbGioiTinh});
                    model.fireTableDataChanged();
                    JOptionPane.showMessageDialog(this, "Thêm bạn đọc thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi thêm bạn đọc: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    
private void deleteStudentFromDatabase(String maSinhVien) {
    Connection conn = getConnection();
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Không thể kết nối đến Database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Kiểm tra nếu sinh viên từng có phiếu trả với tình trạng hư hỏng chưa thanh toán
        String checkHuHong = "SELECT COUNT(*) FROM PhieuTra WHERE masinhvien = ? AND tinhtrangsach = '1' AND thanhtoan = '0'";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkHuHong)) {
            checkStmt.setString(1, maSinhVien);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "Không thể xóa! Sinh viên có phiếu trả 'hư hỏng' chưa thanh toán.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Kiểm tra nếu sinh viên có phiếu mượn
        String checkPhieuMuon = "SELECT COUNT(*) FROM PhieuMuon WHERE masinhvien = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkPhieuMuon)) {
            checkStmt.setString(1, maSinhVien);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "Không thể xóa! Sinh viên đang có hoặc đã từng mượn sách.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Xóa bạn đọc
        String sql = "DELETE FROM BanDoc WHERE masinhvien = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSinhVien);

            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Xóa bạn đọc thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy bạn đọc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi xóa bạn đọc: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jpnSinhVienMenu = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnXuatFileExcel = new javax.swing.JButton();
        btnGhiExcel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblModel = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jpnSinhVienMenu.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Mã sinh viên ", "Tên sinh viên", " ", " " }));
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

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add_48px.png"))); // NOI18N
        btnAdd.setText("THÊM");
        btnAdd.setBorder(null);
        buttonGroup1.add(btnAdd);
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
        buttonGroup1.add(btnDelete);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnXuatFileExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel_48px.png"))); // NOI18N
        btnXuatFileExcel.setText("XUẤT EXCEL");
        btnXuatFileExcel.setBorder(null);
        buttonGroup1.add(btnXuatFileExcel);
        btnXuatFileExcel.setContentAreaFilled(false);
        btnXuatFileExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXuatFileExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnXuatFileExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileExcelActionPerformed(evt);
            }
        });

        btnGhiExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/GHI_EXCEL.png"))); // NOI18N
        btnGhiExcel.setText("GHI EXCEL");
        btnGhiExcel.setBorder(null);
        buttonGroup1.add(btnGhiExcel);
        btnGhiExcel.setContentAreaFilled(false);
        btnGhiExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGhiExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGhiExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGhiExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnSinhVienMenuLayout = new javax.swing.GroupLayout(jpnSinhVienMenu);
        jpnSinhVienMenu.setLayout(jpnSinhVienMenuLayout);
        jpnSinhVienMenuLayout.setHorizontalGroup(
            jpnSinhVienMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSinhVienMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXuatFileExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGhiExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpnSinhVienMenuLayout.setVerticalGroup(
            jpnSinhVienMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSinhVienMenuLayout.createSequentialGroup()
                .addGroup(jpnSinhVienMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnSinhVienMenuLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jpnSinhVienMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnSinhVienMenuLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jpnSinhVienMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDelete)
                            .addComponent(btnAdd)
                            .addComponent(btnXuatFileExcel)
                            .addComponent(btnGhiExcel))))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnSinhVienMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnSinhVienMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tblModel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblModel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblModel.setShowGrid(false);
        jScrollPane2.setViewportView(tblModel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
    
        // Nếu không tìm thấy JFrame cha, báo lỗi
        if (parentFrame == null) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy cửa sổ chính!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
         }

      // ✅ Truyền `this` thay vì `true`
      DAOBanDoc addDialog = new DAOBanDoc(parentFrame, this);
      addDialog.setVisible(true);
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
         int selectedRow = tblModel.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một bạn đọc để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String maSinhVien = tblModel.getValueAt(selectedRow, 0).toString();

    int confirm = JOptionPane.showConfirmDialog(this,
            "Bạn có chắc chắn muốn xóa bạn đọc có mã \"" + maSinhVien + "\"?",
            "Xác nhận xóa", JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

    if (confirm == JOptionPane.YES_OPTION) {
        deleteStudentFromDatabase(maSinhVien);

        // Cập nhật lại bảng sau khi xóa
        loadStudentsFromDatabase();
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

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

    String sql = "SELECT * FROM SinhVien WHERE ";

    // Kiểm tra các tiêu chí tìm kiếm
    if (selectedOption.equals("Mã sinh viên")) {
        sql += "masinhvien LIKE ?";
    } else if (selectedOption.equals("Tên sinh viên")) {
        sql += "tensinhvien LIKE ?";
    } else if (selectedOption.equals("Tất cả")) {
        sql += "(masinhvien LIKE ? OR tensinhvien LIKE ? OR lop LIKE ? OR khoa LIKE ? OR sodienthoai LIKE ? OR gioitinh LIKE ?)";
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn tiêu chí tìm kiếm hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (Connection conn = getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {

        // Nếu chọn "Tất cả", tìm kiếm trên tất cả các trường
        if (selectedOption.equals("Tất cả")) {
            for (int i = 1; i <= 6; i++) {
                ps.setString(i, "%" + keyword + "%");
            }
        } else {
            ps.setString(1, "%" + keyword + "%");
        }

        try (ResultSet rs = ps.executeQuery()) {
            model.setRowCount(0); 

            while (rs.next()) {
                String maSV = rs.getString("masinhvien");
                String tenSV = rs.getString("tensinhvien");
                String lop = rs.getString("lop");
                String khoa = rs.getString("khoa");
                String sdt = rs.getString("sodienthoai");
                String gioiTinh = rs.getString("gioitinh");

                model.addRow(new Object[]{maSV, tenSV, lop, khoa, sdt, gioiTinh});
            }
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        loadStudentsFromDatabase(); // Gọi sau khi setupTable()
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXuatFileExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileExcelActionPerformed
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn nơi lưu file CSV");
    fileChooser.setSelectedFile(new File("SinhVien.csv"));

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

    DefaultTableModel model = (DefaultTableModel) tblModel.getModel(); // Đổi tên tblModel nếu cần
    int rowCount = model.getRowCount();
    int columnCount = model.getColumnCount();

    try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileToSave), StandardCharsets.UTF_8);
         BufferedWriter writer = new BufferedWriter(osw)) {

        // Ghi BOM UTF-8 để Excel hiểu đúng encoding
        writer.write('\uFEFF');

        // Ghi tiêu đề cột
for (int col = 0; col < columnCount; col++) {
    writer.write(model.getColumnName(col));
    if (col < columnCount - 1) writer.write(","); // ← sửa từ ',' thành ';'
}
writer.newLine();

// Ghi dữ liệu từng dòng
for (int row = 0; row < rowCount; row++) {
    for (int col = 0; col < columnCount; col++) {
        Object value = model.getValueAt(row, col);
        writer.write(value != null ? value.toString() : "");
        if (col < columnCount - 1) writer.write(","); // ← sửa từ ',' thành ';'
    }
    writer.newLine();
}


        writer.flush();
        JOptionPane.showMessageDialog(this, "Xuất file CSV thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        // Tự mở file nếu muốn
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(fileToSave);
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi xuất file CSV: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnXuatFileExcelActionPerformed
    }
    private void btnGhiExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGhiExcelActionPerformed
        // TODO add your handling code here:
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

        String sql = "INSERT INTO SinhVien (masinhvien, tensinhvien, lop, khoa, sodienthoai, gioitinh) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue; // Bỏ dòng tiêu đề
            }

            // Tách dữ liệu theo dấu phân cách
            String[] values = line.split(";", -1); // đổi thành "," nếu file dùng dấu phẩy

            if (values.length >= 4) {
                  pstmt.setString(1, values[0].trim()); // masinhvien
                  pstmt.setString(2, values[1].trim()); // tensinhvien
                  pstmt.setString(3, values[2].trim()); // lop
                  pstmt.setString(4, values[3].trim()); // khoa
                  pstmt.setString(5, values[4].trim()); // sodienthoai
                  pstmt.setString(6, values[5].trim()); // gioitinh



                pstmt.executeUpdate();
            }
        }

        JOptionPane.showMessageDialog(this, "Import dữ liệu thành công!");

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi import CSV: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_btnGhiExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnGhiExcel;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnXuatFileExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpnSinhVienMenu;
    private javax.swing.JTable tblModel;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}

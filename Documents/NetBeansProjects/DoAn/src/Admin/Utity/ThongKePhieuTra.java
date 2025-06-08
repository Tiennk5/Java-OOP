/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.Utity;

import Admin.Controller.CustomScrollBar;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ADMIN
 */
public class ThongKePhieuTra extends javax.swing.JPanel {
private static final int TINHTRANG_COLUMN_INDEX = 6;

    private Connection conn;
    public ThongKePhieuTra() {
        initComponents();
//         setIconImage(new ImageIcon(getClass().getResource("/image/library-24px.png")).getImage());
           model.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        //tlbManageReader.getTableHeader().setForeground(new Color(255,255,255));
   
        model.setRowHeight(25); 
        
        jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBar());
        jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBar());
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
              "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;"
              + "trustServerCertificate=true", "sa", "123456789");
        } catch (Exception e) {
            e.printStackTrace();
        }
         
           Load(); // <-- Gọi sau khi kết nối
           addActionButtonsToTable(model);
    }
    @SuppressWarnings("unchecked")
    
public void deleteReturnSlipAndUpdateBookStatus(String maPhieuMuon, String maSach) {
    try {
        // 1. Xóa phiếu trả trong bảng PhieuTra
        String sqlDelete = "DELETE FROM PhieuTra WHERE maphieumuon = ? AND masach = ?" ;
        PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
        psDelete.setString(1, maPhieuMuon);
        psDelete.setString(2, maSach);
        psDelete.executeUpdate();
        psDelete.close();

        // 2. Cập nhật trạng thái sách thành "đã mượn"
        String sqlUpdateSach = "UPDATE Sach SET trangthai = N'đã mượn' WHERE masach = ?";
        PreparedStatement psUpdateSach = conn.prepareStatement(sqlUpdateSach);
        psUpdateSach.setString(1, maSach);
        psUpdateSach.executeUpdate();
        psUpdateSach.close();

        // 3. Cập nhật lại datra = 0 trong bảng PhieuMuon
        String sqlUpdateDatra = "UPDATE PhieuMuon SET datra = 0 WHERE maphieumuon = ? AND masach = ?";
        PreparedStatement psUpdateDatra = conn.prepareStatement(sqlUpdateDatra);
        psUpdateDatra.setString(1, maPhieuMuon);
        psUpdateDatra.setString(2, maSach);
        psUpdateDatra.executeUpdate();
        psUpdateDatra.close();

        JOptionPane.showMessageDialog(this, "Khôi phục phiếu mượn thành công và cập nhật trạng thái sách!");
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi khôi phục phiếu mượn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

public boolean capNhatTinhTrangTrongDB(Connection conn, String maPhieuMuon, String maSach, String tinhTrangMoi) {
    try {
        String sql = "UPDATE PhieuTra SET tinhtrangsach = ? WHERE maphieumuon = ? AND masach = ?";
PreparedStatement ps = conn.prepareStatement(sql);

int tinhTrangBool = tinhTrangMoi.equals("Hư hỏng") ? 1 : 0;

ps.setBoolean(1, tinhTrangBool == 1);
ps.setString(2, maPhieuMuon);
ps.setString(3, maSach);



        int rowsUpdated = ps.executeUpdate();
        ps.close();

        return rowsUpdated > 0;
        
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật tình trạng: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
public void capNhatHienThiTrenBang(DefaultTableModel model, int rowIndex, int tinhTrangColumnIndex, String tinhTrangMoi) {
    model.setValueAt(tinhTrangMoi, rowIndex, tinhTrangColumnIndex);
}


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        model = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        model.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu mượn", "Mã sinh viên", "Mã sách", "Tên sinh viên", "Lớp", "Tên sách", "Tình trạng sách", "Thời hạn"
            }
        ));
        jScrollPane1.setViewportView(model);

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        String text = txtTimKiem.getText().trim();

    // Lấy DefaultTableModel từ JTable "model"
    DefaultTableModel tableModel = (DefaultTableModel) model.getModel();

    // Tạo bộ lọc cho JTable
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
    model.setRowSorter(sorter);

    if (text.length() == 0) {
        sorter.setRowFilter(null); // Hiển thị tất cả
    } else {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0)); // Lọc theo cột 0 (Mã phiếu mượn)
    }
    }//GEN-LAST:event_txtTimKiemActionPerformed
private void addActionButtonsToTable(JTable table) {
    table.setRowHeight(30);
    TableColumn actionColumn = table.getColumn("Hành động");
    actionColumn.setPreferredWidth(150);

    // Renderer chỉ hiển thị nút
    actionColumn.setCellRenderer((tbl, value, isSelected, hasFocus, row, column) -> {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");

        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);

        if (isSelected) {
            panel.setBackground(tbl.getSelectionBackground());
        } else {
            panel.setBackground(tbl.getBackground());
        }

        panel.add(btnSua);
        panel.add(btnXoa);
        return panel;
    });

    // Editor xử lý sự kiện
    final int[] currentRow = {-1};

    actionColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");

        {
            panel.add(btnSua);
            panel.add(btnXoa);
btnSua.addActionListener(e -> {
    fireEditingStopped();

    int row = table.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(null, "Không xác định được dòng đã chọn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int modelRow = table.convertRowIndexToModel(row);
    DefaultTableModel model = (DefaultTableModel) table.getModel();

    String maPhieuMuon = model.getValueAt(modelRow, 0).toString();
    String maSach = model.getValueAt(modelRow, 2).toString();

    // Lấy giá trị tình trạng từ model bảng (đó chính là giá trị combo box đã chọn)
    String tinhTrangMoi = model.getValueAt(modelRow, TINHTRANG_COLUMN_INDEX).toString();

    boolean success = capNhatTinhTrangTrongDB(conn, maPhieuMuon, maSach, tinhTrangMoi);
    if (success) {
        JOptionPane.showMessageDialog(null, "Cập nhật tình trạng thành công!");
        // Model đã có giá trị mới rồi nên không cần set lại
        // Nhưng nếu bạn cần, có thể gọi:
        // capNhatHienThiTrenBang(model, modelRow, TINHTRANG_COLUMN_INDEX, tinhTrangMoi);
    } else {
        JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu mượn hoặc sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
});

            btnXoa.addActionListener(e -> {
                fireEditingStopped();
                int row = currentRow[0];
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trong bảng trước khi thao tác.");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(row);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                String maPhieuMuon = model.getValueAt(modelRow, 0).toString();
                String maSach = model.getValueAt(modelRow, 2).toString();

                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phiếu trả này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteReturnSlipAndUpdateBookStatus(maPhieuMuon, maSach);
                    model.removeRow(modelRow);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable tbl, Object value, boolean isSelected, int row, int column) {
            currentRow[0] = row;  // Lưu dòng được click
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    });
}
public void Load() {
   String sql = "SELECT pt.maphieumuon, " +
                "       pt.masinhvien, " +
                "       pt.masach, " +
                "       sv.tensinhvien, " +
                "       sv.lop, " +
                "       s.tensach, " +
                "       pt.tinhtrangsach, " +
                "       pt.thoihan " +
                "FROM PhieuTra pt " +
                "JOIN SinhVien sv ON pt.masinhvien = sv.masinhvien " +
                "JOIN Sach s ON pt.masach = s.masach " +
                "WHERE pt.thanhtoan = 0";

    DefaultTableModel tableModel = new DefaultTableModel();
    tableModel.setColumnIdentifiers(new String[]{
        "Mã phiếu mượn", "Mã sinh viên", "Mã sách",
        "Tên sinh viên", "Lớp", "Tên sách",
        "Tình trạng sách", "Thời hạn", "Hành động"
    });

    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String maPhieuMuon = rs.getString("maphieumuon");
            String maSinhVien = rs.getString("masinhvien");
            String maSach = rs.getString("masach");
            String tenSinhVien = rs.getString("tensinhvien");
            String lop = rs.getString("lop");
            String tenSach = rs.getString("tensach");
            int tinhTrangSach = rs.getInt("tinhtrangsach");
            String thoiHan = rs.getString("thoihan");

            String hienThiTinhTrang = (tinhTrangSach == 0) ? "Tốt" : "Hư hỏng";

            tableModel.addRow(new Object[]{
                maPhieuMuon, maSinhVien, maSach,
                tenSinhVien, lop, tenSach,
                hienThiTinhTrang, thoiHan, "" // Cột Hành động để trống
            });
        }

        this.model.setModel(tableModel);

        // Gán comboBox cho cột "Tình trạng sách" (cột thứ 7, index 6)
        String[] options = {"Tốt", "Hư hỏng"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        model.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(comboBox));

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKePhieuTra().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable model;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.Utity;
import Admin.Controller.CustomScrollBar;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ADMIN
 */
public class ThongKePhieuPhat extends javax.swing.JPanel {
    private Connection conn;
    private DefaultTableModel tableModel; // ✅ THÊM DÒNG NÀY
 
    public ThongKePhieuPhat() {
       
        initComponents(); // tạo giao diện, frame, layout (nếu có)

    // 1. Tạo model cho JTable trước
    tableModel = new DefaultTableModel(new String[]{
            "Mã phiếu mượn", "Mã sinh viên", "Tên sinh viên",
            "Mã sách", "Tên sách", "Giá sách",
            "Phần trăm thiệt hại", "Tiền phạt", "Thời gian", "Hành động"
    }, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Chỉ cho phép sửa cột phần trăm thiệt hại (6) và hành động (9)
            return column == 6 || column == 9;
        }
    };



    jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBar());
    jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBar());
     model.setModel(tableModel);
    // 4. Kết nối CSDL
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;trustServerCertificate=true",
                "sa", "123456789"
        );
    } catch (Exception e) {
        e.printStackTrace();
    }

    // 5. Load dữ liệu từ DB vào bảng
    Load();

    // 6. Thêm TableModelListener để tự tính tiền phạt khi phần trăm thiệt hại thay đổi
    tableModel.addTableModelListener(e -> {
        int row = e.getFirstRow();
        int col = e.getColumn();
        if (col == 6) {
            try {
                Object val = tableModel.getValueAt(row, 6);
                if (val == null) return;
                int phanTram = Integer.parseInt(val.toString());
                double giaTien = Double.parseDouble(tableModel.getValueAt(row, 5).toString());
                double tienPhat = giaTien * phanTram / 100.0;
                tableModel.setValueAt(tienPhat, row, 7);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi tính tiền phạt: " + ex.getMessage());
            }
        }
    });

    // 7. Tạo ComboBox chọn phần trăm cho cột phần trăm thiệt hại
    JComboBox<Integer> percentageBox = new JComboBox<>();
    for (int i : new int[]{25, 50, 100}) {
        percentageBox.addItem(i);
    }
    model.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(percentageBox));

    // 8. Thêm nút hành động vào cột cuối
    addActionButtonsToTable();

    }
  
    @SuppressWarnings("unchecked")
    public void deleteReturnSlipAndUpdateBookStatus(String maPhieuMuon,String maSinhVien, String maSach) {
    try {
        // 1. Xóa phiếu trả trong bảng PhieuTra theo maPhieuMuon, maSach (nếu có)
        String sqlDelete = "DELETE FROM PhieuPhat WHERE maphieumuon = ? AND masach = ? AND masinhvien = ?";
        PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
        psDelete.setString(1, maPhieuMuon);
        psDelete.setString(2, maSach);
         psDelete.setString(3, maSinhVien);
        psDelete.executeUpdate();
        
        // 2. Cập nhật trạng thái sách thành "chưa mượn" (giả sử cột trangthaiSach = 'chua muon')
      String sqlUpdate = "UPDATE PhieuTra SET thanhtoan = 0 WHERE masach = ? AND (tinhtrangsach = 1 OR thoihan = N'quá hạn')";
        PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
        psUpdate.setString(1, maSach);
        psUpdate.executeUpdate();
        psDelete.close();
        psUpdate.close();

        JOptionPane.showMessageDialog(this, "Xóa thành công và cập nhật trạng thái sách!");
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi xóa phiếu trả hoặc cập nhật sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
   
public void capNhatHienThiTrenBang(DefaultTableModel model, int rowIndex, int tinhTrangColumnIndex, String tinhTrangMoi) {
    model.setValueAt(tinhTrangMoi, rowIndex, tinhTrangColumnIndex);
}
private void suaPhieuPhat() {
    int row = model.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu phạt để sửa!");
        return;
    }

    int modelRow = model.convertRowIndexToModel(row);

    try {
        String maPhieuMuon = tableModel.getValueAt(modelRow, 0).toString();
        String maSach = tableModel.getValueAt(modelRow, 3).toString();  // Lấy mã sách ở cột 3
        int phanTramThietHai = Integer.parseInt(tableModel.getValueAt(modelRow, 6).toString());
        double giaSach = Double.parseDouble(tableModel.getValueAt(modelRow, 5).toString());
        double tienPhat = giaSach * phanTramThietHai / 100.0;

        String sql = "UPDATE PhieuPhat SET PhanTramThietHai = ?, TienPhat = ? WHERE MaPhieuMuon = ? AND MaSach = ?";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, phanTramThietHai);
        pst.setDouble(2, tienPhat);
        pst.setString(3, maPhieuMuon);
        pst.setString(4, maSach);

        int result = pst.executeUpdate();

        if (result > 0) {
            tableModel.setValueAt(tienPhat, modelRow, 7);
            JOptionPane.showMessageDialog(this, "Cập nhật phiếu phạt thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại! Không tìm thấy phiếu phạt.");
        }

        pst.close();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Lỗi khi sửa phiếu phạt: " + ex.getMessage());
    }
}


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        model = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        model.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu mượn", "Mã sinh viên", "Mã sách", "Thời hạn mượn", "Thời gian mượn sách", "Thời gian trả sách"
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 816, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(492, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(158, Short.MAX_VALUE)))
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
private void addActionButtonsToTable() {
    model.setRowHeight(30);
    TableColumn actionColumn = model.getColumn("Hành động");
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
    actionColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        JButton btnSua = new JButton("Sửa");
        JButton btnXoa = new JButton("Xóa");
        JTable table; // Biến lưu reference table hiện tại

        {
            panel.add(btnSua);
            panel.add(btnXoa);

            // Xử lý khi click "Sửa"
            btnSua.addActionListener(e -> {
                suaPhieuPhat();
            });

            // Xử lý khi click "Xóa"
            btnXoa.addActionListener(e -> {
               
                int row = table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trong bảng trước khi thao tác.");
                    return;
                }

                int modelRow = table.convertRowIndexToModel(row);
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                String maPhieuMuon = model.getValueAt(modelRow, 0).toString();
                String maSinhVien = model.getValueAt(modelRow, 1).toString();
                String maSach = model.getValueAt(modelRow, 3).toString();

                int confirm = JOptionPane.showConfirmDialog(null, 
                    "Bạn có chắc chắn muốn xóa phiếu phạt này?", 
                    "Xác nhận", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    deleteReturnSlipAndUpdateBookStatus(maPhieuMuon, maSinhVien, maSach);
                    model.removeRow(modelRow);
                }
            
               
               });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    });
}

   
    private void Load() {
        String sql = "SELECT pp.maphieumuon, sv.masinhvien, sv.tensinhvien, s.masach, s.tensach, " +
                     " s.giatien, pp.phantramthiethai, pp.tienphat, pp.thoigian " +
                     "FROM PhieuPhat pp " +
                     "JOIN SinhVien sv ON pp.masinhvien = sv.masinhvien " +
                     "JOIN Sach s ON pp.masach = s.masach";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            tableModel.setRowCount(0);

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("maphieumuon"));
                row.add(rs.getString("masinhvien"));
                row.add(rs.getString("tensinhvien"));
                row.add(rs.getString("masach"));
                row.add(rs.getString("tensach"));
                row.add(rs.getString("giatien"));
                row.add(rs.getString("phantramthiethai"));
                row.add(rs.getString("tienphat"));
                row.add(rs.getTimestamp("thoigian"));

                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

      

    public static void main(String args[]) {
  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKePhieuPhat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable model;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}

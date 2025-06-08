/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.Utity;


import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class ThongKePhieuMuon extends javax.swing.JPanel {

    private Connection conn;
    private DefaultTableModel tableModel;
    public ThongKePhieuMuon() {
    initComponents();
//    setImageIcon(new ImageIcon(getClass().getResource("/image/library-24px.png")).getImage());

    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(
          "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;trustServerCertificate=true",
          "sa", "123456789");
    } catch (Exception e) {
        e.printStackTrace();
    }

    // 1. Tạo model có đủ cột (bao gồm "Hành động")
    tableModel = new DefaultTableModel();
    tableModel.setColumnIdentifiers(new String[]{
    "Mã phiếu mượn", "Mã sinh viên", "Tên sinh viên",
    "Mã sách", "Tên sách", "Thời hạn mượn",
    "Thời gian mượn sách", "Thời gian trả sách", "Hành động"
});

    
    // 2. Gán model cho table trước
    tablePhieuMuon.setModel(tableModel);

    // 3. Load dữ liệu vào model (thêm các dòng)
    Load();

    // 4. Sau khi có model và cột "Hành động" rồi mới thêm nút Xóa
     addDeleteButtonToTable(tablePhieuMuon);


    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablePhieuMuon = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        tablePhieuMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu mượn", "Mã sinh viên", "Mã sách", "Tên sách", "Thời hạn mượn", "Thời gian mượn  sách", "Thời gian trả sách", "Hành động"
            }
        ));
        jScrollPane1.setViewportView(tablePhieuMuon);

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
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 828, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(493, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(69, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        String text = txtTimKiem.getText().trim();
    DefaultTableModel model = (DefaultTableModel) tablePhieuMuon.getModel();

    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    tablePhieuMuon.setRowSorter(sorter);

    if (text.length() == 0) {
        sorter.setRowFilter(null); // hiển thị tất cả khi tìm trống
    } else {
        // Giả sử cột "Mã phiếu mượn" là cột 0 (bạn sửa lại số cột cho đúng)
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
    }
    }//GEN-LAST:event_txtTimKiemActionPerformed
public void deleteReturnSlipAndUpdateBookStatus(String maPhieuMuon, String maSach) {
    try {
        String sqlDelete = "DELETE FROM PhieuMuon WHERE maphieumuon = ?";
        PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
        psDelete.setString(1, maPhieuMuon);
        psDelete.executeUpdate();

        
String sqlUpdate = "UPDATE Sach SET trangthai = N'chưa mượn' WHERE masach = ?";
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
private void addDeleteButtonToTable(JTable table) {
    // Không thêm cột mới nữa, vì cột "Hành động" đã có
    TableColumn actionColumn = null;
    try {
        actionColumn = table.getColumn("Hành động"); // hoặc "Xóa" nếu bạn muốn đổi tên
    } catch (IllegalArgumentException e) {
        // Nếu không tìm thấy, tạo cột mới (nếu cần)
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addColumn("Hành động");
        actionColumn = table.getColumn("Hành động");
    }

    actionColumn.setCellRenderer(new TableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            JButton button = new JButton("Xóa");
            button.setForeground(Color.WHITE);
            button.setBackground(Color.RED);
            return button;
        }
    });

    actionColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()) {
        JButton button = new JButton("Xóa");
        {
            button.setForeground(Color.WHITE);
            button.setBackground(Color.RED);
            button.addActionListener(e -> {
                int row = table.getEditingRow();
                if (row != -1) {
                    String maPhieuMuon = (String) table.getModel().getValueAt(row, 0); // Mã phiếu mượn
                    String maSach = (String) table.getModel().getValueAt(row, 3); // Mã sách
                    deleteReturnSlipAndUpdateBookStatus(maPhieuMuon, maSach);
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


public void Load() {
    String sql = "SELECT pm.maphieumuon, " +
             "       pm.masinhvien, " +
             "       sv.tensinhvien, " +
             "       pm.masach, " +
             "       s.tensach, " +
             "       pm.thoihanmuonsach, " +
             "       pm.thoigianmuonsach, " +
             "       pm.thoigiantrasach " +
             "FROM PhieuMuon pm " +
             "JOIN SinhVien sv ON pm.masinhvien = sv.masinhvien " +
             "JOIN Sach s ON pm.masach = s.masach " +
             "WHERE pm.datra = 0";


    // Xóa dữ liệu cũ trong model
    tableModel.setRowCount(0);

    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String maPhieuMuon = rs.getString("maphieumuon");
            String maSinhVien = rs.getString("masinhvien");
            String tenSinhVien = rs.getString("tensinhvien");
            String maSach = rs.getString("masach");
            String tenSach = rs.getString("tensach");
            String thoiHanMuon = rs.getString("thoihanmuonsach");
            Timestamp tgMuon = rs.getTimestamp("thoigianmuonsach");
            Timestamp tgTra = rs.getTimestamp("thoigiantrasach");

           tableModel.addRow(new Object[]{
    maPhieuMuon, maSinhVien, tenSinhVien,
    maSach, tenSach, thoiHanMuon, tgMuon, tgTra
    // không cần thêm cột "Hành động" ở đây
});

        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    tablePhieuMuon.setModel(tableModel);
    addDeleteButtonToTable(tablePhieuMuon); // Thêm lại renderer và editor cho cột Xóa
}

   
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
            java.util.logging.Logger.getLogger(ThongKePhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKePhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKePhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKePhieuMuon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKePhieuMuon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablePhieuMuon;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}

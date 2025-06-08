
package DAO;

import Admin.AminView;
import Admin.Utity.QuanLySinhVienPanel;
import Object.BanDoc;
import Object.Sach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author HP
 */
public class DAOBanDoc extends javax.swing.JDialog {
   private QuanLySinhVienPanel panel;
   public DAOBanDoc(JFrame parent, QuanLySinhVienPanel panel) { 
       
        super(parent, true); // ✅ Đặt modal là true
        this.panel = panel;  // ✅ Lưu tham chiếu panel để cập nhật bảng
        initComponents();
        this.setLocationRelativeTo(null);
  
        
    }

    private boolean isStudentExistsInDatabase(String maSinhVien) {
    Connection conn = QuanLySinhVienPanel.getConnection();
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Lỗi kết nối Database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    String sql = "SELECT 1 FROM SinhVien WHERE masinhvien = ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, maSinhVien);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); // Trả về true nếu tìm thấy sinh viên
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi kiểm tra sinh viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnComplete = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtMaSinhVien = new javax.swing.JTextField();
        txtTenSinhVien = new javax.swing.JTextField();
        txtLop = new javax.swing.JTextField();
        txtKhoa = new javax.swing.JTextField();
        txtSoDienThoai = new javax.swing.JTextField();
        txtGioiTinh = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator1.setForeground(new java.awt.Color(57, 113, 177));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(57, 113, 177));
        jLabel2.setText("Nhập thông tin bạn đọc");

        jLabel3.setForeground(new java.awt.Color(57, 113, 177));
        jLabel3.setText("Tên sinh viên");

        jSeparator3.setForeground(new java.awt.Color(57, 113, 177));

        btnComplete.setBackground(new java.awt.Color(126, 87, 194));
        btnComplete.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnComplete.setForeground(new java.awt.Color(255, 255, 255));
        btnComplete.setText("Complete");
        btnComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(57, 113, 177));
        jLabel5.setText("Lớp");

        jSeparator4.setForeground(new java.awt.Color(57, 113, 177));

        jLabel6.setForeground(new java.awt.Color(57, 113, 177));
        jLabel6.setText("Giới tính");

        jSeparator5.setBackground(new java.awt.Color(57, 113, 177));
        jSeparator5.setForeground(new java.awt.Color(57, 113, 177));

        jLabel7.setForeground(new java.awt.Color(57, 113, 177));
        jLabel7.setText("Khoa");

        jSeparator6.setForeground(new java.awt.Color(57, 113, 177));

        jLabel8.setForeground(new java.awt.Color(57, 113, 177));
        jLabel8.setText("Số điện thoại");

        jSeparator7.setForeground(new java.awt.Color(57, 113, 177));

        jLabel4.setForeground(new java.awt.Color(57, 113, 177));
        jLabel4.setText("Mã sinh viên");

        txtMaSinhVien.setForeground(new java.awt.Color(57, 113, 177));
        txtMaSinhVien.setBorder(null);
        txtMaSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSinhVienActionPerformed(evt);
            }
        });

        txtTenSinhVien.setForeground(new java.awt.Color(57, 113, 177));
        txtTenSinhVien.setBorder(null);

        txtLop.setForeground(new java.awt.Color(57, 113, 177));
        txtLop.setBorder(null);

        txtKhoa.setForeground(new java.awt.Color(57, 113, 177));
        txtKhoa.setBorder(null);

        txtSoDienThoai.setForeground(new java.awt.Color(57, 113, 177));
        txtSoDienThoai.setBorder(null);

        txtGioiTinh.setForeground(new java.awt.Color(57, 113, 177));
        txtGioiTinh.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(btnComplete, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator3)
                        .addComponent(txtMaSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator1)
                        .addComponent(txtTenSinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator4)
                        .addComponent(txtLop, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator6)
                            .addComponent(txtKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator5)
                        .addComponent(txtGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnComplete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSinhVienActionPerformed
       String dbMaSinhVien = txtMaSinhVien.getText();
       // Nếu mã sinh viên không rỗng và sinh viên tồn tại trong cơ sở dữ liệu
    if (!dbMaSinhVien.isEmpty() && isStudentExistsInDatabase(dbMaSinhVien)) {
        String selectSQL = "SELECT tensinhvien, lop, khoa, sodienthoai, gioitinh FROM SinhVien WHERE masinhvien = ?";
        try (Connection conn = QuanLySinhVienPanel.getConnection();
             PreparedStatement ps = conn.prepareStatement(selectSQL)) {
            ps.setString(1, dbMaSinhVien); // Đặt giá trị mã sinh viên vào câu lệnh SQL
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Điền thông tin vào các TextField
                    txtTenSinhVien.setText(rs.getString("tensinhvien"));
                    txtLop.setText(rs.getString("lop"));
                    txtKhoa.setText(rs.getString("khoa"));
                    txtSoDienThoai.setText(rs.getString("sodienthoai"));
                    txtGioiTinh.setText(rs.getString("gioitinh"));
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin sinh viên!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Mã sinh viên không tồn tại hoặc trống!", "Thông báo", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_txtMaSinhVienActionPerformed

    private void btnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteActionPerformed

        // Lấy dữ liệu từ các ô nhập liệu
        String maSinhVien = txtMaSinhVien.getText().trim();
        String tenSinhVien = txtTenSinhVien.getText().trim();
        String lop = txtLop.getText().trim();
        String khoa = txtKhoa.getText().trim();
        String soDienThoai = txtSoDienThoai.getText().trim();
        String gioiTinh = txtGioiTinh.getText().trim();

        // Kiểm tra nếu các trường bắt buộc bị trống
        if (maSinhVien.isEmpty() || tenSinhVien.isEmpty() || lop.isEmpty() || khoa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tạo đối tượng BanDoc
        BanDoc student = new BanDoc(maSinhVien, tenSinhVien, lop, khoa, soDienThoai, gioiTinh);

        // Kiểm tra xem sinh viên có tồn tại trong bảng SinhVien không
        if (isStudentExistsInDatabase(maSinhVien)) {
            // ✅ Gọi phương thức addStudent từ QuanLyDocGiaPanel
            panel.addStudent(student);

            this.dispose(); // Đóng hộp thoại sau khi thêm thành công
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sinh viên trong hệ thống. Không thể thêm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnCompleteActionPerformed

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
            java.util.logging.Logger.getLogger(DAOBanDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DAOBanDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DAOBanDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DAOBanDoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
        JFrame parentFrame = new JFrame(); // Tạo một JFrame giả
QuanLySinhVienPanel panel = new QuanLySinhVienPanel(); // Tạo một Panel

DAOBanDoc dialog = new DAOBanDoc(parentFrame, panel); // ✅ Truyền đúng kiểu
dialog.setVisible(true);

                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComplete;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtKhoa;
    private javax.swing.JTextField txtLop;
    private javax.swing.JTextField txtMaSinhVien;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenSinhVien;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.Utity;

import Admin.Controller.CustomScrollBar;
import static Admin.Utity.QuanLySachPanel.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // java.sql.Date, dùng để set tham số ngày trong PreparedStatement

import java.text.SimpleDateFormat;
import java.text.ParseException;

import javax.swing.JTextField;  // nếu bạn dùng JTextField txtTimKiem, txtDoanhThu
import java.sql.DriverManager; // nếu bạn tự tạo connection

/**
 *
 * @author ADMIN
 */
public class TongDoanhThuPanel extends javax.swing.JPanel {

     
    private Connection conn;
    private DefaultTableModel tableModel; 
   // private javax.swing.JTable table;  // đổi tên từ model thành table
    public TongDoanhThuPanel() {
      initComponents(); // bắt buộc gọi để khởi tạo các component

        // Tạo model với tiêu đề cột
        tableModel = new DefaultTableModel(new String[]{
            "Mã phiếu mượn", "Mã sinh viên", "Tên sinh viên",
            "Mã sách", "Tên sách", "Giá sách",
            "Phần trăm thiệt hại", "Tiền phạt", "Thời gian"
        }, 0);

        
table.setModel(tableModel); // lúc này table đã không null
        // Kết nối CSDL
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
              "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;"
              + "trustServerCertificate=true", "sa", "123456789");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Load();
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDoanhThu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        jLabel1.setText("Tổng doanh thu trong ngày: ");

        jLabel2.setText("Giá tiền:");

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(408, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(544, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(64, 64, 64)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(65, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents
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
    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
    String ngayStr = txtTimKiem.getText().trim();

SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");  // CHÚ Ý: 'd' và 'M' là 1 chữ số cũng được
sdf.setLenient(false);  // không cho phép tự động sửa ngày sai

        try {
    
java.util.Date utilDate = sdf.parse(ngayStr);  // parse từ chuỗi String sang java.util.Date
Date sqlDate = new Date(utilDate.getTime());  // convert sang java.sql.Date

String sql = "SELECT SUM(CAST(tienphat AS FLOAT)) AS TongDoanhThu FROM PhieuPhat WHERE CONVERT(date, thoigian) = ?";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setDate(1, sqlDate);  // setDate chứ không phải setString

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            double tong = rs.getDouble("TongDoanhThu");
            txtDoanhThu.setText(String.valueOf(tong));  // Hiện doanh thu lên txtDoanhThu
        }
        rs.close();
        ps.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_txtTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtDoanhThu;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}

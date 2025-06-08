/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.Utity;

import Object.Sach;
import Admin.Controller.CustomScrollBar;
import static Admin.Utity.QuanLySachPanel.getConnection;
import Object.Sach;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ThongKeSach extends javax.swing.JPanel {
  private Connection conn;
  private List<Sach> dsSach;
  private DefaultTableModel model;
public ThongKeSach() {
        initComponents();
        conn = (Connection) getConnection();
      initComponents();
        tblModel.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblModel.setRowHeight(25); 
        dsSach = new ArrayList<>();
        model = (DefaultTableModel) tblModel.getModel();
        jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBar());
        jScrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBar());
        conn = getConnection();
       loadSoLuongMuon();
       loadSoLuongConLai();
       loadSoLuong();
       loadBooks();
       
    }
    
    @SuppressWarnings("unchecked")
   public void loadBooks() {
    Connection conn = getConnection();
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Không thể kết nối đến Database!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

     //Sửa SQL để chỉ lấy sách có trạng thái "chưa mượn"
    String sql = "SELECT * FROM Sach  ORDER BY thoigianthemsach ASC";

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
            String giaSach = rs.getString("giatien");
            
            Sach sach = new Sach(maSach, tenSach, tacGia, theLoai, nhaXuatBan, giaSach);
            dsSach.add(sach);
            model.addRow(new Object[]{tenSach, tacGia, theLoai, nhaXuatBan, giaSach});
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnThongKeSachMenu = new javax.swing.JPanel();
        jpnSachDaMuon1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTongSach = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jpnSachDaMuon3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtSoLuongConLai = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jpnSachDaMuon4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtSoLuongMuon = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jpnThongKeSachView = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblModel = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jpnThongKeSachMenu.setBackground(new java.awt.Color(255, 255, 255));

        jpnSachDaMuon1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Tổng số sách hiện có");

        txtTongSach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTongSach.setBorder(null);
        txtTongSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnSachDaMuon1Layout = new javax.swing.GroupLayout(jpnSachDaMuon1);
        jpnSachDaMuon1.setLayout(jpnSachDaMuon1Layout);
        jpnSachDaMuon1Layout.setHorizontalGroup(
            jpnSachDaMuon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSachDaMuon1Layout.createSequentialGroup()
                .addGroup(jpnSachDaMuon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnSachDaMuon1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpnSachDaMuon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongSach, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 106, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jpnSachDaMuon1Layout.setVerticalGroup(
            jpnSachDaMuon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnSachDaMuon1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addComponent(txtTongSach, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnSachDaMuon3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Sách sẵn sàng cho mượn");

        txtSoLuongConLai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSoLuongConLai.setBorder(null);

        javax.swing.GroupLayout jpnSachDaMuon3Layout = new javax.swing.GroupLayout(jpnSachDaMuon3);
        jpnSachDaMuon3.setLayout(jpnSachDaMuon3Layout);
        jpnSachDaMuon3Layout.setHorizontalGroup(
            jpnSachDaMuon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSachDaMuon3Layout.createSequentialGroup()
                .addGroup(jpnSachDaMuon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnSachDaMuon3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpnSachDaMuon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuongConLai, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 106, Short.MAX_VALUE))
                    .addComponent(jSeparator2))
                .addContainerGap())
        );
        jpnSachDaMuon3Layout.setVerticalGroup(
            jpnSachDaMuon3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnSachDaMuon3Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addComponent(txtSoLuongConLai, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnSachDaMuon4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Sách đang mượn");

        txtSoLuongMuon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSoLuongMuon.setBorder(null);
        txtSoLuongMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongMuonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnSachDaMuon4Layout = new javax.swing.GroupLayout(jpnSachDaMuon4);
        jpnSachDaMuon4.setLayout(jpnSachDaMuon4Layout);
        jpnSachDaMuon4Layout.setHorizontalGroup(
            jpnSachDaMuon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnSachDaMuon4Layout.createSequentialGroup()
                .addGroup(jpnSachDaMuon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnSachDaMuon4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpnSachDaMuon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnSachDaMuon4Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jpnSachDaMuon4Layout.createSequentialGroup()
                                .addComponent(txtSoLuongMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator3))
                .addContainerGap())
        );
        jpnSachDaMuon4Layout.setVerticalGroup(
            jpnSachDaMuon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnSachDaMuon4Layout.createSequentialGroup()
                .addGroup(jpnSachDaMuon4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnSachDaMuon4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnSachDaMuon4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(12, 12, 12)
                        .addComponent(txtSoLuongMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jpnThongKeSachMenuLayout = new javax.swing.GroupLayout(jpnThongKeSachMenu);
        jpnThongKeSachMenu.setLayout(jpnThongKeSachMenuLayout);
        jpnThongKeSachMenuLayout.setHorizontalGroup(
            jpnThongKeSachMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThongKeSachMenuLayout.createSequentialGroup()
                .addComponent(jpnSachDaMuon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnSachDaMuon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnSachDaMuon4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );
        jpnThongKeSachMenuLayout.setVerticalGroup(
            jpnThongKeSachMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThongKeSachMenuLayout.createSequentialGroup()
                .addGroup(jpnThongKeSachMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnSachDaMuon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpnSachDaMuon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpnSachDaMuon4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jpnThongKeSachView.setBackground(new java.awt.Color(255, 255, 255));

        tblModel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sách", "Tác giả", "Thể loại", "Nhà xuất bản", "Giá sách"
            }
        ));
        jScrollPane1.setViewportView(tblModel);

        javax.swing.GroupLayout jpnThongKeSachViewLayout = new javax.swing.GroupLayout(jpnThongKeSachView);
        jpnThongKeSachView.setLayout(jpnThongKeSachViewLayout);
        jpnThongKeSachViewLayout.setHorizontalGroup(
            jpnThongKeSachViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
            .addGroup(jpnThongKeSachViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE))
        );
        jpnThongKeSachViewLayout.setVerticalGroup(
            jpnThongKeSachViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
            .addGroup(jpnThongKeSachViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnThongKeSachViewLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(128, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnThongKeSachMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnThongKeSachView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnThongKeSachMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnThongKeSachView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
private void loadSoLuongMuon() {
    String sql = "SELECT COUNT(*) AS soLuong FROM Sach WHERE trangthai = N'đã mượn'";
    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        if (rs.next()) {
            int soLuong = rs.getInt("soLuong");
            txtSoLuongMuon.setText(String.valueOf(soLuong));
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải số lượng sách còn lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
private void loadSoLuongConLai() {
    String sql = "SELECT COUNT(*) AS soLuong FROM Sach WHERE trangthai = N'chưa mượn'";
    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        if (rs.next()) {
            int soLuong = rs.getInt("soLuong");
            txtSoLuongConLai.setText(String.valueOf(soLuong));
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải số lượng sách còn lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
private void loadSoLuong() {
    String sql = "SELECT COUNT(*) AS soLuong FROM Sach ";
    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        if (rs.next()) {
            int soLuong = rs.getInt("soLuong");
            txtTongSach.setText(String.valueOf(soLuong));
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi tải số lượng sách còn lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

    private void txtSoLuongMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongMuonActionPerformed

   

    }//GEN-LAST:event_txtSoLuongMuonActionPerformed

    private void txtTongSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongSachActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel jpnSachDaMuon1;
    private javax.swing.JPanel jpnSachDaMuon3;
    private javax.swing.JPanel jpnSachDaMuon4;
    private javax.swing.JPanel jpnThongKeSachMenu;
    private javax.swing.JPanel jpnThongKeSachView;
    private javax.swing.JTable tblModel;
    private javax.swing.JTextField txtSoLuongConLai;
    private javax.swing.JTextField txtSoLuongMuon;
    private javax.swing.JTextField txtTongSach;
    // End of variables declaration//GEN-END:variables
}

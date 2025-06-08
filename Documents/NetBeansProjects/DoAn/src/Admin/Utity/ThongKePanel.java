/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.Utity;

import Admin.Controller.ThongKeController;
import Admin.QLTVBean.DanhMucBean;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongKePanel extends javax.swing.JPanel {

    public ThongKePanel() {
        initComponents();
        
        ThongKeController controller = new ThongKeController(jpnThongKeView);
        controller.setView(jpnThongKePhieu, jlbThongKePhieu);
        
        List<DanhMucBean> ListItem = new ArrayList<>();
        ListItem.add(new DanhMucBean("ThongKePhieu", jpnThongKePhieu,jlbThongKePhieu));
        ListItem.add(new DanhMucBean("ThongKeSach", jpnThongKeSach,jlbThongKeSach));
        ListItem.add(new DanhMucBean("TongDoanhThu", jpnTongDoanhThu,jlbTongDoanhThu));
        
        controller.setEvent(ListItem);
        jpnThongKeMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnThongKeAdmin = new javax.swing.JPanel();
        jpnThongKeMenu = new javax.swing.JPanel();
        jpnThongKePhieu = new javax.swing.JPanel();
        jlbThongKePhieu = new javax.swing.JLabel();
        jpnThongKeSach = new javax.swing.JPanel();
        jlbThongKeSach = new javax.swing.JLabel();
        jpnTongDoanhThu = new javax.swing.JPanel();
        jlbTongDoanhThu = new javax.swing.JLabel();
        jpnThongKeView = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jpnThongKeAdmin.setBackground(new java.awt.Color(255, 255, 255));

        jpnThongKeMenu.setBackground(new java.awt.Color(255, 255, 255));

        jpnThongKePhieu.setBackground(new java.awt.Color(204, 204, 204));

        jlbThongKePhieu.setBackground(new java.awt.Color(57, 113, 177));
        jlbThongKePhieu.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jlbThongKePhieu.setForeground(new java.awt.Color(255, 255, 255));
        jlbThongKePhieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbThongKePhieu.setText("Quản lý phiếu ");
        jlbThongKePhieu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jpnThongKePhieuLayout = new javax.swing.GroupLayout(jpnThongKePhieu);
        jpnThongKePhieu.setLayout(jpnThongKePhieuLayout);
        jpnThongKePhieuLayout.setHorizontalGroup(
            jpnThongKePhieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThongKePhieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbThongKePhieu, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnThongKePhieuLayout.setVerticalGroup(
            jpnThongKePhieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThongKePhieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbThongKePhieu, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnThongKeSach.setBackground(new java.awt.Color(204, 204, 204));

        jlbThongKeSach.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jlbThongKeSach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbThongKeSach.setText("Sách");

        javax.swing.GroupLayout jpnThongKeSachLayout = new javax.swing.GroupLayout(jpnThongKeSach);
        jpnThongKeSach.setLayout(jpnThongKeSachLayout);
        jpnThongKeSachLayout.setHorizontalGroup(
            jpnThongKeSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThongKeSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbThongKeSach, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnThongKeSachLayout.setVerticalGroup(
            jpnThongKeSachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThongKeSachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbThongKeSach, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnTongDoanhThu.setBackground(new java.awt.Color(204, 204, 204));

        jlbTongDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jlbTongDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTongDoanhThu.setText("Tổng Doanh Thu");

        javax.swing.GroupLayout jpnTongDoanhThuLayout = new javax.swing.GroupLayout(jpnTongDoanhThu);
        jpnTongDoanhThu.setLayout(jpnTongDoanhThuLayout);
        jpnTongDoanhThuLayout.setHorizontalGroup(
            jpnTongDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTongDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnTongDoanhThuLayout.setVerticalGroup(
            jpnTongDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnTongDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnThongKeMenuLayout = new javax.swing.GroupLayout(jpnThongKeMenu);
        jpnThongKeMenu.setLayout(jpnThongKeMenuLayout);
        jpnThongKeMenuLayout.setHorizontalGroup(
            jpnThongKeMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThongKeMenuLayout.createSequentialGroup()
                .addComponent(jpnThongKePhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnThongKeSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnTongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnThongKeMenuLayout.setVerticalGroup(
            jpnThongKeMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThongKeMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnThongKeMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpnThongKeSach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpnThongKePhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnThongKeView.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpnThongKeViewLayout = new javax.swing.GroupLayout(jpnThongKeView);
        jpnThongKeView.setLayout(jpnThongKeViewLayout);
        jpnThongKeViewLayout.setHorizontalGroup(
            jpnThongKeViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnThongKeViewLayout.setVerticalGroup(
            jpnThongKeViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnThongKeAdminLayout = new javax.swing.GroupLayout(jpnThongKeAdmin);
        jpnThongKeAdmin.setLayout(jpnThongKeAdminLayout);
        jpnThongKeAdminLayout.setHorizontalGroup(
            jpnThongKeAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnThongKeMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnThongKeView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnThongKeAdminLayout.setVerticalGroup(
            jpnThongKeAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThongKeAdminLayout.createSequentialGroup()
                .addComponent(jpnThongKeMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnThongKeView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnThongKeAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnThongKeAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlbThongKePhieu;
    private javax.swing.JLabel jlbThongKeSach;
    private javax.swing.JLabel jlbTongDoanhThu;
    private javax.swing.JPanel jpnThongKeAdmin;
    private javax.swing.JPanel jpnThongKeMenu;
    private javax.swing.JPanel jpnThongKePhieu;
    private javax.swing.JPanel jpnThongKeSach;
    private javax.swing.JPanel jpnThongKeView;
    private javax.swing.JPanel jpnTongDoanhThu;
    // End of variables declaration//GEN-END:variables
}

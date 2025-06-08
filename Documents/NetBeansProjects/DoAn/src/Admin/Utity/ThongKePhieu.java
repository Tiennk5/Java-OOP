/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Admin.Utity;

import Admin.Controller.ThongKeController;
import Admin.Controller.ThongKePhieuController;
import Admin.QLTVBean.DanhMucBean;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ThongKePhieu extends javax.swing.JPanel {

    /**
     * Creates new form ThongKePhieu
     */
    public ThongKePhieu() {
        initComponents();
        ThongKePhieuController controller = new ThongKePhieuController(jpnThongKePhieuView);
        controller.setView(jpnPhieuMuon, jlbPhieuMuon);
        
        List<DanhMucBean> ListItem = new ArrayList<>();
        ListItem.add(new DanhMucBean("PhieuMuon", jpnPhieuMuon,jlbPhieuMuon));
        ListItem.add(new DanhMucBean("PhieuTra", jpnPhieuTra,jlbPhieuTra));
        ListItem.add(new DanhMucBean("PhieuPhat", jpnPhieuPhat,jlbPhieuPhat));
        
        controller.setEvent(ListItem);
        jpnThongKePhieuMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnThongKePhieuMenu = new javax.swing.JPanel();
        jpnPhieuTra = new javax.swing.JPanel();
        jlbPhieuTra = new javax.swing.JLabel();
        jpnPhieuMuon = new javax.swing.JPanel();
        jlbPhieuMuon = new javax.swing.JLabel();
        jpnPhieuPhat = new javax.swing.JPanel();
        jlbPhieuPhat = new javax.swing.JLabel();
        jpnThongKePhieuView = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jpnThongKePhieuMenu.setBackground(new java.awt.Color(255, 255, 255));

        jlbPhieuTra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPhieuTra.setText("Phiếu trả");

        javax.swing.GroupLayout jpnPhieuTraLayout = new javax.swing.GroupLayout(jpnPhieuTra);
        jpnPhieuTra.setLayout(jpnPhieuTraLayout);
        jpnPhieuTraLayout.setHorizontalGroup(
            jpnPhieuTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPhieuTraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbPhieuTra, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnPhieuTraLayout.setVerticalGroup(
            jpnPhieuTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPhieuTraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbPhieuTra, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jlbPhieuMuon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPhieuMuon.setText("Phiếu mượn");

        javax.swing.GroupLayout jpnPhieuMuonLayout = new javax.swing.GroupLayout(jpnPhieuMuon);
        jpnPhieuMuon.setLayout(jpnPhieuMuonLayout);
        jpnPhieuMuonLayout.setHorizontalGroup(
            jpnPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPhieuMuonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbPhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnPhieuMuonLayout.setVerticalGroup(
            jpnPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPhieuMuonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbPhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        jlbPhieuPhat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbPhieuPhat.setText("Phiếu phạt");

        javax.swing.GroupLayout jpnPhieuPhatLayout = new javax.swing.GroupLayout(jpnPhieuPhat);
        jpnPhieuPhat.setLayout(jpnPhieuPhatLayout);
        jpnPhieuPhatLayout.setHorizontalGroup(
            jpnPhieuPhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPhieuPhatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbPhieuPhat, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnPhieuPhatLayout.setVerticalGroup(
            jpnPhieuPhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPhieuPhatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbPhieuPhat, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnThongKePhieuMenuLayout = new javax.swing.GroupLayout(jpnThongKePhieuMenu);
        jpnThongKePhieuMenu.setLayout(jpnThongKePhieuMenuLayout);
        jpnThongKePhieuMenuLayout.setHorizontalGroup(
            jpnThongKePhieuMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThongKePhieuMenuLayout.createSequentialGroup()
                .addComponent(jpnPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnPhieuPhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(545, Short.MAX_VALUE))
        );
        jpnThongKePhieuMenuLayout.setVerticalGroup(
            jpnThongKePhieuMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jpnPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jpnPhieuPhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jpnThongKePhieuView.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpnThongKePhieuViewLayout = new javax.swing.GroupLayout(jpnThongKePhieuView);
        jpnThongKePhieuView.setLayout(jpnThongKePhieuViewLayout);
        jpnThongKePhieuViewLayout.setHorizontalGroup(
            jpnThongKePhieuViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnThongKePhieuViewLayout.setVerticalGroup(
            jpnThongKePhieuViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnThongKePhieuMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnThongKePhieuView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnThongKePhieuMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnThongKePhieuView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlbPhieuMuon;
    private javax.swing.JLabel jlbPhieuPhat;
    private javax.swing.JLabel jlbPhieuTra;
    private javax.swing.JPanel jpnPhieuMuon;
    private javax.swing.JPanel jpnPhieuPhat;
    private javax.swing.JPanel jpnPhieuTra;
    private javax.swing.JPanel jpnThongKePhieuMenu;
    private javax.swing.JPanel jpnThongKePhieuView;
    // End of variables declaration//GEN-END:variables
}

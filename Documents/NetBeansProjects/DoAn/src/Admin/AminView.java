
package Admin;

import Admin.Controller.ChuyenManHinhController;
import Admin.QLTVBean.DanhMucBean;
import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
public class AminView extends javax.swing.JFrame {
    public AminView() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/image/library-24px.png")).getImage());
        this.setLocationRelativeTo(null);

        ChuyenManHinhController controller = new ChuyenManHinhController(jpnView);
        controller.setView(jpnQuanLySinhVien, jlbQuanLySinhVien);
        List<DanhMucBean> ListItem = new ArrayList<>();
        ListItem.add(new DanhMucBean("QuanLySinhVien", jpnQuanLySinhVien, jlbQuanLySinhVien));
        ListItem.add(new DanhMucBean("QuanLySach", jpnQuanLySach, jlbQuanLySach));
        ListItem.add(new DanhMucBean("QuanLyPhieuMuon", jpnQuanLyPhieuMuon, jlbQuanLyPhieuMuon));
        ListItem.add(new DanhMucBean("QuanLyPhieuTra", jpnQuanLyPhieuTra, jlbQuanLyPhieuTra));
        ListItem.add(new DanhMucBean("QuanLyPhieuPhat", jpnQuanLyPhieuPhat, jlbQuanLyPhieuPhat));
        ListItem.add(new DanhMucBean("ThongKe", jpnThongKe, jlbThongKe));
        controller.setEvent(ListItem);
        jpnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Di chuyển phương thức setUserName ra ngoài constructor
    public void setUserName(String username) {
        txtNameLogin.setText(username);  // Điền tên đăng nhập vào ô txtNameLogin
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jpnAdmin = new javax.swing.JPanel();
        jpnMenu = new javax.swing.JPanel();
        jpnQuanLyPhieuMuon = new javax.swing.JPanel();
        jlbQuanLyPhieuMuon = new javax.swing.JLabel();
        jpnQuanLySinhVien = new javax.swing.JPanel();
        jlbQuanLySinhVien = new javax.swing.JLabel();
        jpnQuanLyPhieuTra = new javax.swing.JPanel();
        jlbQuanLyPhieuTra = new javax.swing.JLabel();
        jpnQuanLyPhieuPhat = new javax.swing.JPanel();
        jlbQuanLyPhieuPhat = new javax.swing.JLabel();
        jpnQuanLySach = new javax.swing.JPanel();
        jlbQuanLySach = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jlbFaceID = new javax.swing.JLabel();
        txtNameLogin = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jpnThongKe = new javax.swing.JPanel();
        jlbThongKe = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm quản lý thư viện");
        setBackground(new java.awt.Color(255, 255, 255));

        jpnAdmin.setBackground(new java.awt.Color(255, 255, 255));
        jpnAdmin.setForeground(new java.awt.Color(255, 255, 255));

        jpnMenu.setBackground(new java.awt.Color(255, 255, 255));

        jpnQuanLyPhieuMuon.setBackground(new java.awt.Color(255, 255, 255));

        jlbQuanLyPhieuMuon.setBackground(new java.awt.Color(80, 152, 94));
        jlbQuanLyPhieuMuon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbQuanLyPhieuMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/10131908_ticket_check_line_icon.png"))); // NOI18N
        jlbQuanLyPhieuMuon.setText("PHIẾU MƯỢN");

        javax.swing.GroupLayout jpnQuanLyPhieuMuonLayout = new javax.swing.GroupLayout(jpnQuanLyPhieuMuon);
        jpnQuanLyPhieuMuon.setLayout(jpnQuanLyPhieuMuonLayout);
        jpnQuanLyPhieuMuonLayout.setHorizontalGroup(
            jpnQuanLyPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyPhieuMuonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbQuanLyPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnQuanLyPhieuMuonLayout.setVerticalGroup(
            jpnQuanLyPhieuMuonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbQuanLyPhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jpnQuanLySinhVien.setBackground(new java.awt.Color(255, 255, 255));

        jlbQuanLySinhVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbQuanLySinhVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/3099383_student_man_icon.png"))); // NOI18N
        jlbQuanLySinhVien.setText("QUẢN LÝ SINH VIÊN");

        javax.swing.GroupLayout jpnQuanLySinhVienLayout = new javax.swing.GroupLayout(jpnQuanLySinhVien);
        jpnQuanLySinhVien.setLayout(jpnQuanLySinhVienLayout);
        jpnQuanLySinhVienLayout.setHorizontalGroup(
            jpnQuanLySinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLySinhVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbQuanLySinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnQuanLySinhVienLayout.setVerticalGroup(
            jpnQuanLySinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbQuanLySinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jpnQuanLyPhieuTra.setBackground(new java.awt.Color(255, 255, 255));

        jlbQuanLyPhieuTra.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbQuanLyPhieuTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/6586148_accept_check_good_mark_ok_icon.png"))); // NOI18N
        jlbQuanLyPhieuTra.setText("PHIẾU TRẢ");

        javax.swing.GroupLayout jpnQuanLyPhieuTraLayout = new javax.swing.GroupLayout(jpnQuanLyPhieuTra);
        jpnQuanLyPhieuTra.setLayout(jpnQuanLyPhieuTraLayout);
        jpnQuanLyPhieuTraLayout.setHorizontalGroup(
            jpnQuanLyPhieuTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyPhieuTraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbQuanLyPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnQuanLyPhieuTraLayout.setVerticalGroup(
            jpnQuanLyPhieuTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbQuanLyPhieuTra, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jpnQuanLyPhieuPhat.setBackground(new java.awt.Color(255, 255, 255));

        jlbQuanLyPhieuPhat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbQuanLyPhieuPhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/huybo_20.png"))); // NOI18N
        jlbQuanLyPhieuPhat.setText("PHIẾU PHẠT");

        javax.swing.GroupLayout jpnQuanLyPhieuPhatLayout = new javax.swing.GroupLayout(jpnQuanLyPhieuPhat);
        jpnQuanLyPhieuPhat.setLayout(jpnQuanLyPhieuPhatLayout);
        jpnQuanLyPhieuPhatLayout.setHorizontalGroup(
            jpnQuanLyPhieuPhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLyPhieuPhatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbQuanLyPhieuPhat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnQuanLyPhieuPhatLayout.setVerticalGroup(
            jpnQuanLyPhieuPhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbQuanLyPhieuPhat, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jpnQuanLySach.setBackground(new java.awt.Color(255, 255, 255));

        jlbQuanLySach.setBackground(new java.awt.Color(80, 152, 94));
        jlbQuanLySach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbQuanLySach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/book.png"))); // NOI18N
        jlbQuanLySach.setText("QUẢN LÝ SÁCH");

        javax.swing.GroupLayout jpnQuanLySachLayout = new javax.swing.GroupLayout(jpnQuanLySach);
        jpnQuanLySach.setLayout(jpnQuanLySachLayout);
        jpnQuanLySachLayout.setHorizontalGroup(
            jpnQuanLySachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnQuanLySachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbQuanLySach)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnQuanLySachLayout.setVerticalGroup(
            jpnQuanLySachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbQuanLySach, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        jButton1.setBackground(new java.awt.Color(80, 152, 94));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/turn_off-24px.png"))); // NOI18N
        jButton1.setText("ĐĂNG XUẤT");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Hi!");

        jlbFaceID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/profile_64px.png"))); // NOI18N

        txtNameLogin.setBorder(null);

        jButton2.setText("Thông tin tài khoản");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbFaceID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNameLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2))
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNameLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addComponent(jButton2))
                    .addComponent(jlbFaceID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jpnThongKe.setBackground(new java.awt.Color(255, 255, 255));

        jlbThongKe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlbThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/5027854_analytics_business_chart_graph_statistics_icon.png"))); // NOI18N
        jlbThongKe.setText("THỐNG KÊ THƯ VIỆN");

        javax.swing.GroupLayout jpnThongKeLayout = new javax.swing.GroupLayout(jpnThongKe);
        jpnThongKe.setLayout(jpnThongKeLayout);
        jpnThongKeLayout.setHorizontalGroup(
            jpnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbThongKe)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnThongKeLayout.setVerticalGroup(
            jpnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnQuanLySach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnQuanLyPhieuMuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnQuanLyPhieuTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnQuanLyPhieuPhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnQuanLySinhVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addComponent(jpnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnMenuLayout.setVerticalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpnQuanLySinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnQuanLySach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnQuanLyPhieuMuon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnQuanLyPhieuTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnQuanLyPhieuPhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        jpnView.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1072, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnAdminLayout = new javax.swing.GroupLayout(jpnAdmin);
        jpnAdmin.setLayout(jpnAdminLayout);
        jpnAdminLayout.setHorizontalGroup(
            jpnAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnAdminLayout.createSequentialGroup()
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnAdminLayout.setVerticalGroup(
            jpnAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnAdminLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jpnView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Bạn có muốn đăng xuất không?",
                "Xác nhận đăng xuất",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            Login h = new Login();

            this.dispose(); // hoặc chuyển sang màn hình đăng nhập nếu cần
            h.setVisible(true);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AminView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlbFaceID;
    private javax.swing.JLabel jlbQuanLyPhieuMuon;
    private javax.swing.JLabel jlbQuanLyPhieuPhat;
    private javax.swing.JLabel jlbQuanLyPhieuTra;
    private javax.swing.JLabel jlbQuanLySach;
    private javax.swing.JLabel jlbQuanLySinhVien;
    private javax.swing.JLabel jlbThongKe;
    private javax.swing.JPanel jpnAdmin;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnQuanLyPhieuMuon;
    private javax.swing.JPanel jpnQuanLyPhieuPhat;
    private javax.swing.JPanel jpnQuanLyPhieuTra;
    private javax.swing.JPanel jpnQuanLySach;
    private javax.swing.JPanel jpnQuanLySinhVien;
    private javax.swing.JPanel jpnThongKe;
    private javax.swing.JPanel jpnView;
    private javax.swing.JTextField txtNameLogin;
    // End of variables declaration//GEN-END:variables
}

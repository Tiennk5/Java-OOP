package Admin.Controller;

import Admin.QLTVBean.DanhMucBean;
import Admin.Utity.ThongKePhieuMuon;
import Admin.Utity.ThongKePhieuPhat;
import Admin.Utity.ThongKePhieuTra;
import Admin.Utity.TongDoanhThuPanel;
import DAO.DAOPhieuMuon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThongKePhieuController {
    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> listItem = null;

    public ThongKePhieuController(JPanel jpnRoot) {
        this.root = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "PhieuMuon";
        jlbItem.setForeground(new Color(0,0,0));
        jpnItem.setBackground(new Color(187, 221, 249));

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new ThongKePhieuMuon());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
            item.getJpn().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
            // Không gắn MouseListener cho JPanel để tránh xung đột
        }
    }

    class LabelEvent implements MouseListener {
        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            kindSelected = kind; // Cập nhật kind được chọn
            switch (kind) {
                case "PhieuMuon":
                    node = new ThongKePhieuMuon();
                    break;
                case "PhieuTra":
                    node = new ThongKePhieuTra();
                    break;
                case "PhieuPhat":
                    node = new ThongKePhieuPhat();
                    break;
                default:
                    throw new AssertionError();
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind); // Cập nhật màu sau khi click
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Không cần xử lý ở đây
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Không cần xử lý ở đây
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jlbItem.setForeground(new Color(0,0,0));
                jpnItem.setBackground(new Color(245, 245, 245));
            }
            // Label đã chọn giữ nguyên màu đỏ, không thay đổi
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jlbItem.setForeground(new Color(0,0,0));
                jpnItem.setBackground(new Color(204, 204, 204));// Màu trắng khi không hover
            }
            // Label đã chọn giữ nguyên màu đỏ, không thay đổi
        }
    }

    private void setChangeBackground(String kind) {
        for (DanhMucBean item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJlb().setForeground(new Color(0,0,0)); // Màu đỏ cho label được chọn
                item.getJpn().setBackground(new Color(187, 221, 249));
            } else {
                item.getJlb().setForeground(new Color(0,0,0));// Màu trắng cho các label khác
                item.getJpn().setBackground(new Color(204,204,204));
            }
        }
    }
}
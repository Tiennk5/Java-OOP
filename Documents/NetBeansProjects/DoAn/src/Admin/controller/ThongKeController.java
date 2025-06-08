package Admin.Controller;

import Admin.QLTVBean.DanhMucBean;
import Admin.Utity.ThongKePhieu;
import Admin.Utity.ThongKeSach;
import Admin.Utity.TongDoanhThuPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThongKeController {
    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> listItem = null;

    public ThongKeController(JPanel jpnRoot) {
        this.root = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "ThongKePhieu";
        jlbItem.setForeground(new Color(0,0,0));
        jpnItem.setBackground(new Color(187, 221, 249));

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new ThongKePhieu());
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
                case "ThongKePhieu":
                    node = new ThongKePhieu();
                    break;
                case "ThongKeSach":
                    node = new ThongKeSach();
                    break;
                case "TongDoanhThu":
                    node = new TongDoanhThuPanel();
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
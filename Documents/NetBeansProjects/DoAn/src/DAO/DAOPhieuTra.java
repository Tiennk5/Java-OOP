package DAO;

import Object.PhieuMuon;
import Object.PhieuTra;
import Object.Sach;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;

public class DAOPhieuTra {
    private Connection conn;

    // Constructor để khởi tạo kết nối đến cơ sở dữ liệu
    public DAOPhieuTra(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;"
                + "trustServerCertificate=true", "sa", "123456789");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     public boolean CapNhatSachThanhChuaMuon(String maSach) {
    String sql1 = "UPDATE Sach SET trangthai = N'chưa mượn' WHERE masach = ?";
    String sql2 = "UPDATE PhieuMuon SET trangthai = N'đã trả' WHERE masach = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql1)) {
        ps.setString(1, maSach);
        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0; // Trả về true nếu có bản ghi được cập nhật
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false; // Trả về false nếu có lỗi xảy ra
    }

    // Phương thức để thêm phiếu trả sách
    public boolean ThemPhieuTra(PhieuTra phieuTra) {
        String sqlInsert = "INSERT INTO PhieuTra(maphieumuon, masinhvien, masach, tinhtrangsach, thoihan, thoigian, thanhtoan) "
                         + "VALUES(?, ?, ?, ?, ?, GETDATE(), ?)";
        String sqlSelect = "SELECT thoigiantrasach FROM PhieuMuon WHERE maphieumuon = ? AND masinhvien = ? AND masach = ?";
        String sqlUpdate = "UPDATE Sach SET trangthai = N'chưa mượn' WHERE masach = ?";

        try (
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
            PreparedStatement psSelect = conn.prepareStatement(sqlSelect)
        ) {
            // Lặp qua danh sách sách trong phiếu trả
            for (Sach sach : phieuTra.getSach()) {
                // Lấy thông tin thời gian trả sách từ cơ sở dữ liệu
                psSelect.setString(1, phieuTra.getPhieuMuon().getMaPhieuMuon());
                psSelect.setString(2, phieuTra.getPhieuMuon().getBanDoc().getMaSinhVien());
                psSelect.setString(3, sach.getMaSach());

                ResultSet rs = psSelect.executeQuery();  // Execute query

                // Kiểm tra nếu có dữ liệu trả về
                if (rs.next()) {
                    // Lấy thời gian trả sách từ cơ sở dữ liệu
                    Timestamp thoigianTraSach = rs.getTimestamp("thoigiantrasach");

                    if (thoigianTraSach != null) {
                        // Lấy thời gian hiện tại
                        LocalDate ngayHienTai = LocalDate.now();

                        // So sánh thời gian hiện tại với thời gian trả sách
                        String hanTra = ngayHienTai.isAfter(thoigianTraSach.toLocalDateTime().toLocalDate()) ? "quá hạn" : "đúng hạn";

                        // Insert phiếu trả vào cơ sở dữ liệu
                        psInsert.setString(1, phieuTra.getPhieuMuon().getMaPhieuMuon());
                        psInsert.setString(2, phieuTra.getPhieuMuon().getBanDoc().getMaSinhVien());
                        psInsert.setString(3, sach.getMaSach());
                        psInsert.setBoolean(4, sach.isTinhTrangSach());
                        psInsert.setString(5, hanTra);
                        psInsert.setBoolean(6, false);
                        psInsert.executeUpdate();

                        // Cập nhật trạng thái sách về "chưa mượn"
                        psUpdate.setString(1, sach.getMaSach());
                        psUpdate.executeUpdate();
                    } else {
                        System.out.println("Không tìm thấy phiếu mượn ! ");
                        return false;  // Nếu không có giá trị thời gian trả sách, trả về false
                    }
                } else {
                    System.out.println("Không tìm thấy thông tin phiếu mượn cho sách: " + sach.getMaSach());
                    return false;  // Không tìm thấy thông tin, có thể xử lý tùy theo yêu cầu
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

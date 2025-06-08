package DAO;

import Object.PhieuMuon;
import Object.Sach;
import Object.BanDoc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class DAOPhieuMuon {
    private Connection conn;

    public DAOPhieuMuon() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
              "jdbc:sqlserver://localhost:1433;databaseName=Test;encrypt=true;"
              + "trustServerCertificate=true", "sa", "123456789");
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
public boolean isSachChuaMuon(String maSach) {
    String sql = "SELECT trangthai FROM Sach WHERE masach = ?";
    try (
        PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, maSach);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String trangThai = rs.getString("trangthai");
            return trangThai != null && trangThai.equalsIgnoreCase("chưa mượn");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
   public boolean ThemPhieuMuon(PhieuMuon phieuMuon) {
    String sqlInsert = "INSERT INTO PhieuMuon(maphieumuon, masinhvien, masach, thoihanmuonsach,  thoigianmuonsach, thoigiantrasach, datra) "
                   + "VALUES(?, ?, ?, ?, GETDATE(),?,?)";
    String sqlUpdate = "UPDATE Sach SET trangthai = N'đã mượn' WHERE masach = ?";  // Cập nhật trạng thái sách thành "đã mượn"

    for (Sach sach : phieuMuon.getSach()) {
        try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
             PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
            // Thêm phiếu mượn
            psInsert.setString(1, phieuMuon.getMaPhieuMuon()); // Mã phiếu mượn
            psInsert.setString(2, phieuMuon.getBanDoc().getMaSinhVien()); // Mã sinh viên
            psInsert.setString(3, sach.getMaSach()); // Mã sách
            psInsert.setString(4, sach.getThoihanmuon()); // Thời gian mượn sách
          
            String input = sach.getThoihanmuon();
            // Loại bỏ "tháng" và trim khoảng trắng
            String numberString = input.trim().replace("tháng", "").trim();
            int number = Integer.parseInt(numberString); // Chuyển đổi chuỗi thành số nguyên
            // Lấy thời gian hiện tại
            LocalDate ngayMuon = LocalDate.now(); // Lấy ngày hiện tại
            // Thêm số tháng vào ngày mượn
            LocalDate thoigiantrasach = ngayMuon.plusMonths(number);  // Thêm số tháng vào ngày mượn
            java.sql.Date sqlDate = java.sql.Date.valueOf(thoigiantrasach); // Chuyển đổi LocalDate thành java.sql.Date
            // Cập nhật ngày trả sách vào PreparedStatement
            psInsert.setDate(5, sqlDate); // Cập nhật ngày trả sách tại cột thứ 7 (cột thoigiantrasach)
            psInsert.setBoolean(6, false); // datra = false khi mới thêm
            int rowsAffected = psInsert.executeUpdate(); // Trả về số lượng bản ghi đã được chèn
            System.out.println("Số bản ghi đã được thêm: " + rowsAffected);
            // Cập nhật trạng thái sách thành "đã mượn"
            psUpdate.setString(1, sach.getMaSach()); // Mã sách
            int rowsUpdated = psUpdate.executeUpdate(); // Cập nhật trạng thái sách
            System.out.println("Số bản ghi đã được cập nhật trạng thái: " + rowsUpdated);
            } catch (Exception e) {
            e.printStackTrace();
            return false;
            }
        }
        return true;
}

public boolean KiemTraSach(Sach sach){
   String sql = "SELECT trangthai FROM Sach WHERE masach = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, sach.getMaSach());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String trangThai = rs.getString("trangthai");
            return "chưa mượn".equalsIgnoreCase(trangThai); // Trả về true nếu là "chưa mượn"
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false; // Mặc định là false nếu không có kết quả hoặc có lỗi
}
    public static void main(String[] args) {
    }
}


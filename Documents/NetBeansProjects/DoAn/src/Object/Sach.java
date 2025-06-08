/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.time.LocalDateTime;
import java.util.logging.Logger;
public class Sach {
    private String maSach; 
    private String tenSach; 
    private String tacGia; 
    private String theLoai; 
    private String nhaXuatBan;
    private String giaSach; 
    private String trangThai;
    private boolean tinhTrangSach;
    public boolean isTinhTrangSach() {
        return tinhTrangSach;
    }

    public void setTinhTrangSach(boolean tinhTrangSach) {
        this.tinhTrangSach = tinhTrangSach;
    }
 

    public Sach(String maSach, String tenSach, String tacGia, String theLoai, String nhaXuatBan, String giaSach) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.nhaXuatBan = nhaXuatBan;
        this.giaSach = giaSach;
    }

    public Sach(String maSach, String tenSach, String tacGia, String theLoai, String nhaXuatBan, String giaSach, String trangThai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.nhaXuatBan = nhaXuatBan;
        this.giaSach = giaSach;
        this.trangThai = trangThai;
    }
    private String thoihanmuon; 

    public Sach() {
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(String giaSach) {
        this.giaSach = giaSach;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getThoihanmuon() {
        return thoihanmuon;
    }

    public void setThoihanmuon(String thoihanmuon) {
        this.thoihanmuon = thoihanmuon;
    }
  
    public Sach(String maSach, String tenSach, String tacGia, String theLoai, String nhaXuatBan, String giaSach, String trangThai, String thoihanmuon) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.theLoai = theLoai;
        this.nhaXuatBan = nhaXuatBan;
        this.giaSach = giaSach;
        this.trangThai = trangThai;
        this.thoihanmuon = thoihanmuon;
    }

   
}

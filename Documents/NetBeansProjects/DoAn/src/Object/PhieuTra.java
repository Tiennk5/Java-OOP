/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.util.ArrayList;

public class PhieuTra {
    private String maPhieuTra;
    private PhieuMuon phieuMuon ; 
    private ArrayList<Sach> sach;
    private String thoiHan; // đúng hạn hoặc quá hạn

    public PhieuTra() {
    }

    public PhieuTra(String maPhieuTra, PhieuMuon phieuMuon, ArrayList<Sach> sach, String thoiHan) {
        this.maPhieuTra = maPhieuTra;
        this.phieuMuon = phieuMuon;
        this.sach = sach;
        this.thoiHan = thoiHan;
    }

    public String getMaPhieuTra() {
        return maPhieuTra;
    }

    public void setMaPhieuTra(String maPhieuTra) {
        this.maPhieuTra = maPhieuTra;
    }

    public PhieuMuon getPhieuMuon() {
        return phieuMuon;
    }

    public void setPhieuMuon(PhieuMuon phieuMuon) {
        this.phieuMuon = phieuMuon;
    }

    public ArrayList<Sach> getSach() {
        return sach;
    }

    public void setSach(ArrayList<Sach> sach) {
        this.sach = sach;
    }

    public String getThoiHan() {
        return thoiHan;
    }

    public void setThoiHan(String thoiHan) {
        this.thoiHan = thoiHan;
    }

   
    }

  
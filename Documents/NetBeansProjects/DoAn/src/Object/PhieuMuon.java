/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class PhieuMuon {

    public PhieuMuon() {
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public BanDoc getBanDoc() {
        return banDoc;
    }

    public void setBanDoc(BanDoc banDoc) {
        this.banDoc = banDoc;
    }

    public ArrayList<Sach> getSach() {
        return sach;
    }

    public void setSach(ArrayList<Sach> sach) {
        this.sach = sach;
    }

  
 
    public String getThoigiantra() {
        return thoigiantra;
    }

    public void setThoigiantra(String thoigiantra) {
        this.thoigiantra = thoigiantra;
    }

    public LocalDateTime getThoigian() {
        return thoigian;
    }

    public void setThoigian(LocalDateTime thoigian) {
        this.thoigian = thoigian;
    }

    public PhieuMuon(String maPhieuMuon, BanDoc banDoc, ArrayList<Sach> sach, String thoigiantra, LocalDateTime thoigian) {
        this.maPhieuMuon = maPhieuMuon;
        this.banDoc = banDoc;
        this.sach = sach;
        this.thoigiantra = thoigiantra;
        this.thoigian = thoigian;
    }

   
    private String maPhieuMuon;
    private BanDoc banDoc; 
    private ArrayList<Sach> sach;   
    private  String thoigiantra ;
    private LocalDateTime thoigian;

   
}

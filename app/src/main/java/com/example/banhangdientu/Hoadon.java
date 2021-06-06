package com.example.banhangdientu;

public class Hoadon {
     private String id, ngaytaoHD, ngaygiaoHT, hoten, sdt, diachi, trangthaiGH, id_User;
    private int tongtien;

    public Hoadon(String id, String ngaytaoHD, String ngaygiaoHT, String hoten, String sdt, String diachi, String trangthaiGH, String id_User, int tongtien) {
        this.id = id;
        this.ngaytaoHD = ngaytaoHD;
        this.ngaygiaoHT = ngaygiaoHT;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.trangthaiGH = trangthaiGH;
        this.id_User = id_User;
        this.tongtien = tongtien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNgaytaoHD() {
        return ngaytaoHD;
    }

    public void setNgaytaoHD(String ngaytaoHD) {
        this.ngaytaoHD = ngaytaoHD;
    }

    public String getNgaygiaoHT() {
        return ngaygiaoHT;
    }

    public void setNgaygiaoHT(String ngaygiaoHT) {
        this.ngaygiaoHT = ngaygiaoHT;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTrangthaiGH() {
        return trangthaiGH;
    }

    public void setTrangthaiGH(String trangthaiGH) {
        this.trangthaiGH = trangthaiGH;
    }

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
}

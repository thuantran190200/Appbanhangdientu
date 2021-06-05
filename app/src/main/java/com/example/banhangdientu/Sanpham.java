package com.example.banhangdientu;

public class Sanpham {
    public String id, anhsp, tensp,  thuonghieusp, loaisp, mota, madein, diachi, sdt;
    public int giasp, soluongsp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int soluong = 0;

    public Sanpham(String id, String anhsp, String tensp, int giasp, int soluongsp, String thuonghieusp, String loaisp, String mota, String madein, String diachi, String sdt) {
        this.id = id;
        this.anhsp = anhsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluongsp = soluongsp;
        this.thuonghieusp = thuonghieusp;
        this.loaisp = loaisp;
        this.mota = mota;
        this.madein = madein;
        this.diachi = diachi;
        this.sdt = sdt;
    }
    public Sanpham(){}

    public String getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(String anhsp) {
        this.anhsp = anhsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }

    public String getThuonghieusp() {
        return thuonghieusp;
    }

    public void setThuonghieusp(String thuonghieusp) {
        this.thuonghieusp = thuonghieusp;
    }

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getMadein() {
        return madein;
    }

    public void setMadein(String madein) {
        this.madein = madein;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}

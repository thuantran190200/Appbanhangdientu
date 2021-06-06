package com.example.banhangdientu;

public class Chitiethoadon {
    private String id, idsp, idhd;
    private int soluong;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getIdhd() {
        return idhd;
    }

    public void setIdhd(String idhd) {
        this.idhd = idhd;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Chitiethoadon(String id, String idsp, String idhd, int soluong) {
        this.id = id;
        this.idsp = idsp;
        this.idhd = idhd;
        this.soluong = soluong;
    }
}

package com.example.banhangdientu;

public class Danhmuc {
    String tendm, hinhdm;

    public String getTendm() {
        return tendm;
    }

    public void setTendm(String tendm) {
        this.tendm = tendm;
    }

    public String getHinhdm() {
        return hinhdm;
    }

    public void setHinhdm(String hinhdm) {
        this.hinhdm = hinhdm;
    }

    public Danhmuc(String tendm, String hinhdm) {
        this.tendm = tendm;
        this.hinhdm = hinhdm;
    }
}

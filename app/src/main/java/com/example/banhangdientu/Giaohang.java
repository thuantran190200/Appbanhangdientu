package com.example.banhangdientu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Giaohang extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_giaohang, container, false);
        if (MainActivity.ktdangnhap == false){
            Fragment_Giaohang giaohang = new Fragment_Giaohang();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.Giaohang, giaohang);
            fragmentTransaction.commit();
        }
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        if(MainActivity.ktdangnhap == true && MainActivity.loaitk.equals("admin")){
            Fragment_Giaohang_Nhanvien giaohang_nhanvien = new Fragment_Giaohang_Nhanvien();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.Giaohang,giaohang_nhanvien);
            fragmentTransaction.commit();
        }else if(MainActivity.ktdangnhap == true && MainActivity.loaitk.equals("khachhang")) {
            Fragment_Giaohang_Khachhang giaohang_khachhang = new Fragment_Giaohang_Khachhang();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.Giaohang, giaohang_khachhang);
            fragmentTransaction.commit();
        }
    }

}
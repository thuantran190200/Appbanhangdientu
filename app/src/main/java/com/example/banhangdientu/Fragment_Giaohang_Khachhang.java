package com.example.banhangdientu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;


public class Fragment_Giaohang_Khachhang extends Fragment {

    DonmuaViewadapter donmuaViewadapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__giaohang__khachhang, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpaper);
        //Intent intent = getIntent();
        //String idHD = intent.getStringExtra("idHD");

        donmuaViewadapter = new DonmuaViewadapter(getActivity().getSupportFragmentManager());
        donmuaViewadapter.addFragment(new Fragment_Giaohang_khachhang_Choxacnhan(),"Chờ Xác Nhận");
        donmuaViewadapter.addFragment(new Fragment_Giaohang_khachhang_danggiao(),"Đang giao");
        donmuaViewadapter.addFragment(new Fragment_Giaohang_Khachhang_dagiao(),"Đã giao");
        donmuaViewadapter.addFragment(new Fragment_Giaohang_Khachhang_HuyDH(),"Đã hủy");
        viewPager.setAdapter(donmuaViewadapter);
        tabLayout.setupWithViewPager(viewPager);



        return view;
    }
}
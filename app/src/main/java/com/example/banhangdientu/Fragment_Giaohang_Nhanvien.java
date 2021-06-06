package com.example.banhangdientu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class Fragment_Giaohang_Nhanvien extends Fragment {

    Donmua_admin_viewadapter donmua_admin_viewadapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__giaohang__nhanvien, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_nv);
        viewPager = (ViewPager) view.findViewById(R.id.viewpaper_nv);
        donmua_admin_viewadapter = new Donmua_admin_viewadapter(getActivity().getSupportFragmentManager());
        donmua_admin_viewadapter.addFragment(new Fragment_Giaohang_Nhanvien_choxacnhan(), "Chờ xác nhận");
        donmua_admin_viewadapter.addFragment(new Fragment_Giaohang_Nhanvien_danggiao(), "Đang giao");
        donmua_admin_viewadapter.addFragment(new Fragment_Giaohang_Nhanvien_dagiao(), "Đã giao");
        donmua_admin_viewadapter.addFragment(new Fragment_Giaohang_Nhanvien_HuyDH(), "Hủy ĐH");
        viewPager.setAdapter(donmua_admin_viewadapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
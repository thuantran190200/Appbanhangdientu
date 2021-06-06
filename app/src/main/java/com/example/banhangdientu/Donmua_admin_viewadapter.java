package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Donmua_admin_viewadapter extends FragmentPagerAdapter {
    ArrayList<Fragment> mfragmentList1;
    ArrayList<String> mfragmenttittleList1;
    private String[] tabtitles = new String[]{"Chờ xác nhận","Đang giao","Đã giao","Hủy ĐH"};
    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
    public Donmua_admin_viewadapter(@NonNull FragmentManager fm) {
        super(fm);
        mfragmentList1 = new ArrayList<>();
        mfragmenttittleList1 = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment_Giaohang_Nhanvien_choxacnhan();
            case 1:
                return new Fragment_Giaohang_Nhanvien_danggiao();
            case 2:
                return new Fragment_Giaohang_Nhanvien_dagiao();
            case 3:
                return new Fragment_Giaohang_Nhanvien_HuyDH();
            default:
                throw new RuntimeException("Invalid tab position");
        }
    }
    @Override
    public int getCount() {
        return 4;
    }
    public void addFragment(Fragment fragment,String title){
        mfragmentList1.add(fragment);
        mfragmenttittleList1.add(title);
    }
}

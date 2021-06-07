package com.example.banhangdientu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;


public class DonmuaViewadapter extends FragmentPagerAdapter {
    ArrayList<Fragment> mfragmentList;
    ArrayList<String> mfragmenttittleList;
    private String[] tabtitles = new String[]{"Chờ giao hàng","Đã giao","Hủy ĐH"};
    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
    public DonmuaViewadapter(@NonNull FragmentManager fm) {
        super(fm);
        mfragmentList = new ArrayList<>();
        mfragmenttittleList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment_Giaohang_khachhang_Choxacnhan();
            case 1:
                return new Fragment_Giaohang_Khachhang_dagiao();
            case 2:
                return new Fragment_Giaohang_Khachhang_HuyDH();
            default:
                throw new RuntimeException("Invalid tab position");
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
    public void addFragment(Fragment fragment,String title){
        mfragmentList.add(fragment);
        mfragmenttittleList.add(title);
    }
}

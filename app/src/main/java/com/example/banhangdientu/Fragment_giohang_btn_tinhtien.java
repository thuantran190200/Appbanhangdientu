package com.example.banhangdientu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class Fragment_giohang_btn_tinhtien extends Fragment {
    ListView listview_dsgiohang;
    TextView tonggiatien;
    Button btn_xacnhan;
    private int tongtien = 0;
    public static int tt;
    GiohangAdapter giohangAdapter;
    public Fragment_giohang_btn_tinhtien(){}
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_giohang_btn_tinhtien, container, false);
        btn_xacnhan = (Button) view.findViewById(R.id.btn_muahang);
        listview_dsgiohang = (ListView) view.findViewById(R.id.list_spgiohang);
        tonggiatien = (TextView) view.findViewById(R.id.txt_tongtien);
        giohangAdapter = new GiohangAdapter(getActivity(), Giaodienchinh.listspGiohang, tonggiatien);
        listview_dsgiohang.setAdapter(giohangAdapter);
        giohangAdapter.notifyDataSetChanged();
        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.ktdangnhap&& Giaodienchinh.listspGiohang.size() == 0) {
                    Toast.makeText(getActivity(), "Giỏ hàng đang trống", Toast.LENGTH_SHORT).show();
                } else if(MainActivity.ktdangnhap && Giaodienchinh.listspGiohang.size() > 0){
                    Fragment_Thongtingiaohang fragment = new Fragment_Thongtingiaohang();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_giohangtrong, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }else {
                    Intent intent = new Intent(getActivity(), Dangnhap.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onResume() {
        super.onResume();
        giohangAdapter.notifyDataSetChanged();
        Giaodienchinh.listspGiohang.forEach(Sanpham -> {
            tongtien += (Sanpham.getSoluong()*Sanpham.getGiasp());
            tonggiatien.setText("Giá: " + tongtien +" VNĐ");
            tt = tongtien;
        });
        tongtien = 0;
    }
}
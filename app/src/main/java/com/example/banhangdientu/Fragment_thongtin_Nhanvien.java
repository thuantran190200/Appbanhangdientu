package com.example.banhangdientu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Fragment_thongtin_Nhanvien extends Fragment {

    Button btn_ttcanhan, btn_doi_mk, btn_ql_dh, btn_cn_sp, btn_them_sp, btn_dang_xuat, btn_ttvc;
    TextView hoten, sdt;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thongtin__nhanvien, container, false);

        //---------------------------
        hoten = (TextView) view.findViewById(R.id.show_hoten);
        sdt = (TextView) view.findViewById(R.id.show_sdt);
        btn_ttcanhan = (Button) view.findViewById(R.id.btn_ttcn);
        btn_doi_mk = (Button) view.findViewById(R.id.btn_doimk);
       // btn_ql_dh = (Button) view.findViewById(R.id.btn_nv_qldh);
        btn_cn_sp = (Button) view.findViewById(R.id.btn_nv_capnhatsp);
        btn_them_sp = (Button) view.findViewById(R.id.btn_nv_themsanpham);
        btn_dang_xuat = (Button) view.findViewById(R.id.btn_dangxuat);
        hoten.setText(MainActivity.hoten);
        sdt.setText(MainActivity.sdt);
        btn_ttvc = (Button) view.findViewById(R.id.btn_nv_ttvc);


        //---------------------------------------------
        btn_ttcanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Thongtincanhan.class);
                startActivity(intent);
            }
        });

        btn_doi_mk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), Doimatkhau.class);
                startActivity(intent1);
            }
        });

       /* btn_ql_dh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent2 = new Intent(getActivity(), Quanlydonhang.class);
//                startActivity(intent2);
            }
        });*/

        btn_cn_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getActivity(), Capnhapsanpham.class);
                startActivity(intent3);
            }
        });

        btn_them_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getActivity(), Themsanpham.class);
                startActivity(intent4);
            }
        });
        btn_dang_xuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
                //restarApp();
            }
        });

        btn_ttvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getActivity(), Giaohang_Nhanvien.class);
                startActivity(intent5);
            }
        });

        return view;
    }

    private void restarApp(){
        //Intent rs = Intent(this.getContext(), MainActivity.class);
    }
}
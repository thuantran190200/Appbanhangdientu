package com.example.banhangdientu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment_thongtin extends Fragment {
    Button btn_ttcn,btn_dktk,btn_dangnhap1, btn_doimk, btn_lichsumuasp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_thongtin, container, false);


        //---------

        btn_dktk = (Button) view.findViewById(R.id.btn_dangkytk);
        btn_dangnhap1 = (Button) view.findViewById(R.id.btn_dangnhap);
        btn_ttcn = (Button) view.findViewById(R.id.btn_ttcn1);
        btn_doimk = (Button) view.findViewById(R.id.btn_doimk1);
        btn_lichsumuasp = (Button) view.findViewById(R.id.btn_lichsumuahang1);

        //---------


        btn_dktk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), Dangky.class);
                startActivity(intent1);
            }
        });

        btn_dangnhap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), Dangnhap.class);
                startActivity(intent2);
            }
        });

        btn_ttcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getActivity(), Dangnhap.class);
                startActivity(intent3);
            }
        });

        btn_doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getActivity(), Dangnhap.class);
                startActivity(intent4);
            }
        });

        btn_lichsumuasp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5= new Intent(getActivity(), Dangnhap.class);
                startActivity(intent5);
            }
        });



        return view;


    }
}
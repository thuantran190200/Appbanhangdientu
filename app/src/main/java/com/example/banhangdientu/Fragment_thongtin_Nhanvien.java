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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_thongtin_Nhanvien#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_thongtin_Nhanvien extends Fragment {

    Button btn_ttcanhan, btn_doi_mk, btn_ql_dh, btn_cn_sp, btn_them_sp, btn_dang_xuat;
    TextView hoten, sdt;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_thongtin_Nhanvien() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_thongtin_Nhanvien.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_thongtin_Nhanvien newInstance(String param1, String param2) {
        Fragment_thongtin_Nhanvien fragment = new Fragment_thongtin_Nhanvien();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
        btn_ql_dh = (Button) view.findViewById(R.id.btn_nv_qldh);
        btn_cn_sp = (Button) view.findViewById(R.id.btn_nv_capnhatsp);
        btn_them_sp = (Button) view.findViewById(R.id.btn_nv_themsanpham);
        btn_dang_xuat = (Button) view.findViewById(R.id.btn_dangxuat);
        hoten.setText(MainActivity.hoten);
        sdt.setText(MainActivity.sdt);


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

        btn_ql_dh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent2 = new Intent(getActivity(), Quanlydonhang.class);
//                startActivity(intent2);
            }
        });

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


        return view;
    }

    private void restarApp(){
        //Intent rs = Intent(this.getContext(), MainActivity.class);
    }
}
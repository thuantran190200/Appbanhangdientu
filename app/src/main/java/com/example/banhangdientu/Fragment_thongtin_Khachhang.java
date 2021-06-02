package com.example.banhangdientu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_thongtin_Khachhang#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_thongtin_Khachhang extends Fragment {

    TextView hoten1, sdt1;
    Button btn_ttcanhan, btn_doi_mk, btn_dang_xuat;

    //----------------------------
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_thongtin_Khachhang() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_thongtin_Khachhang.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_thongtin_Khachhang newInstance(String param1, String param2) {
        Fragment_thongtin_Khachhang fragment = new Fragment_thongtin_Khachhang();
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
        View view = inflater.inflate(R.layout.fragment_thongtin__khachhang, container, false);

        hoten1 = (TextView) view.findViewById(R.id.show_hoten1);
        sdt1 = (TextView) view.findViewById(R.id.show_sdt1);
        hoten1.setText(MainActivity.hoten);
        sdt1.setText(MainActivity.sdt);
        btn_ttcanhan = (Button) view.findViewById(R.id.btn_ttcn);
        btn_dang_xuat = (Button) view.findViewById(R.id.btn_dangxuat_kh);


        //--------------------------------------------------------------------------------
        btn_dang_xuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
                //restarApp();
            }
        });

        btn_ttcanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Thongtincanhan.class);
                startActivity(intent);
            }
        });



        return view;
    }
}
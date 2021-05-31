package com.example.banhangdientu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Toi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Toi extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Toi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Toi.
     */
    // TODO: Rename and change types and number of parameters
    public static Toi newInstance(String param1, String param2) {
        Toi fragment = new Toi();
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
        View view = inflater.inflate(R.layout.fragment_toi, container, false);
        if (MainActivity.ktdangnhap == false){
            Fragment_thongtin thongtin = new Fragment_thongtin();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.ttttt, thongtin);
            fragmentTransaction.commit();
        }

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        if(MainActivity.ktdangnhap == true && MainActivity.loaitk.equals("admin")){
            Fragment_thongtin_Nhanvien thongtinNhanvien = new Fragment_thongtin_Nhanvien();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.ttttt,thongtinNhanvien);
            fragmentTransaction.commit();
        }/*else if(MainActivity.ktdangnhap == true && MainActivity.loaitk.equals("khachhang")) {
            Fragment_thongtin_Khachhang thongtinKhachhang = new Fragment_thongtin_Khachhang();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.ttttt, thongtinKhachhang);
            fragmentTransaction.commit();
        }*/
    }
}
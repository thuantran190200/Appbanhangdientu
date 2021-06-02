package com.example.banhangdientu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Fragment_giohang extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_giohang, container, false);


        if (savedInstanceState == null){
            Fragment_giohang_btn_tinhtien fragment = new Fragment_giohang_btn_tinhtien();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_giohangtrong, fragment);
            fragmentTransaction.commit();
        }



        return view;
    }
}
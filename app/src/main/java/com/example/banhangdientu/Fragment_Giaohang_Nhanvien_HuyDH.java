package com.example.banhangdientu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Fragment_Giaohang_Nhanvien_HuyDH extends Fragment {

    public static ArrayList<Hoadon> listhoadon;
    RecyclerView recyclerView;
    DatabaseReference reference;
    adapter_admin_donhang_recyclerview adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__giaohang__nhanvien__huy_d_h, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_nv_HuyDH);

        return view;
    }
    private void DataFromFirebaseListener() {
        listhoadon = new ArrayList<Hoadon>();
        Query query = reference.orderByChild("trangthaiGH").equalTo("Đã Hủy");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String key = ds.child("id").getValue(String.class);
                    String idUser = ds.child("idUser").getValue(String.class);
                    String ngayTaoDon = ds.child("ngaytaoHD").getValue(String.class);
                    String tenUser = ds.child("hoten").getValue(String.class);
                    String sodienthoai = ds.child("sdt").getValue(String.class);
                    int tongtien = ds.child("tongtien").getValue(Integer.class);
                    String trangthai = ds.child("trangthaiGH").getValue(String.class);
                    String diachi = ds.child("diachi").getValue(String.class);
                    Hoadon hd = new Hoadon(key,ngayTaoDon,"",tenUser,sodienthoai,diachi,trangthai,idUser,tongtien);
                    listhoadon.add(hd);
                }
                adapter.notifyDataSetChanged();
                if (listhoadon.size() == 0) {
                    Fragment_Giaohang fragment = new Fragment_Giaohang();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nv_huyDH, fragment);
                    fragmentTransaction.commit();
                }
                Log.d("MTP", "onDataChange: 9");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        reference = FirebaseDatabase.getInstance().getReference().child("Chitietdonhang");
        DataFromFirebaseListener();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new adapter_admin_donhang_recyclerview(getActivity() , listhoadon);
        recyclerView.setAdapter(adapter);
    }
}
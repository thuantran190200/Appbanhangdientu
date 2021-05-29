package com.example.banhangdientu;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_trangchu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_trangchu extends Fragment {


    DatabaseReference reference;
    ArrayList<Sanpham> listsanpham = new ArrayList<>();
    SanphamAdapter sanphamAdapter;
    GridView gridView;

    //**************************************
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_trangchu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_trangchu.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_trangchu newInstance(String param1, String param2) {
        Fragment_trangchu fragment = new Fragment_trangchu();
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


    //*************************************
    //sử từ đây trở xuống
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        DatafromFirebase();
        gridView = (GridView) view.findViewById(R.id.grib_view);
        sanphamAdapter=  new SanphamAdapter(getContext(), listsanpham);
        gridView.setAdapter(sanphamAdapter);
        sanphamAdapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChitietSanpham.class);
                intent.putExtra("anhsp",listsanpham.get(position).getAnhsp());
                intent.putExtra("tensp",listsanpham.get(position).getTensp());
                intent.putExtra("giasp",listsanpham.get(position).getTensp());
                intent.putExtra("soluongsp",listsanpham.get(position).getTensp());
                intent.putExtra("mota",listsanpham.get(position).getTensp());
                startActivity(intent);
            }
        });




        return view;
    }
    private void DatafromFirebase(){
        listsanpham = new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference().child("Sanpham");
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    //  Log.d("abc", "onDataChange: vao day");
                    String key = ds.getKey();
                    String anhsp = ds.child("anhsp").getValue(String.class);
                    String tensp = ds.child("tensp").getValue(String.class);
                    String giasp = ds.child("giasp").getValue(String.class);
                    String diachi = ds.child("diachi").getValue(String.class);
                    String mota = ds.child("mota").getValue(String.class);
                    String loaisp=ds.child("loaisp").getValue(String.class);
                    String thuonghieu= ds.child("thuonghieusp").getValue(String.class);
                    String sdt1 = ds.child("sdt").getValue(String.class);
                    String soluong= ds.child("spluongsp").getValue(String.class);
                    String madein = ds.child("madein").getValue(String.class);
                    AtomicBoolean isSP = new AtomicBoolean();
                    listsanpham.forEach(sanpham -> {
                        if (sanpham.getId() == key) {
                            isSP.set(true);
                        }
                    });


                    Sanpham sanpham = new Sanpham(key,anhsp, tensp, giasp,soluong,thuonghieu, loaisp, mota, madein, diachi,sdt1);
                    listsanpham.add(sanpham);
                }
                sanphamAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
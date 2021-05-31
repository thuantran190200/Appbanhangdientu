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
 * Use the {@link Fragment_danhmuc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_danhmuc extends Fragment {

    DatabaseReference reference;
    ArrayList<Danhmuc> listdanhmuc = new ArrayList<>();
    AdapterDanhmuc adapterDanhmuc;
    GridView gridView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_danhmuc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_danhmuc.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_danhmuc newInstance(String param1, String param2) {
        Fragment_danhmuc fragment = new Fragment_danhmuc();
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
        View view = inflater.inflate(R.layout.fragment_danhmuc, container, false);

        DatafromFirebase();

        gridView = (GridView) view.findViewById(R.id.grib_view_dm);
        adapterDanhmuc=  new AdapterDanhmuc(getContext(),R.layout.khung_danhmuc, listdanhmuc);
        gridView.setAdapter(adapterDanhmuc);
        adapterDanhmuc.notifyDataSetChanged();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Danhsach_danhmuc.class);
                   intent.putExtra("loaisp",listdanhmuc.get(position).getTendm());
//                intent.putExtra("tensp",listdanhmuc.get(position).getTensp());
                  startActivity(intent);
            }
        });

        return view;
    }
    private void DatafromFirebase(){
        listdanhmuc = new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference().child("Danhmuc");
        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    String anhsp = ds.child("anhdm").getValue(String.class);
                    String tensp = ds.child("loaisp").getValue(String.class);


                    Danhmuc danhmuc = new Danhmuc(tensp,anhsp);
                    listdanhmuc.add(danhmuc);
                }
                adapterDanhmuc.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
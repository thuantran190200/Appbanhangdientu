package com.example.banhangdientu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;


import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    ArrayList<Sanpham> listsanpham;
    private int tongtien;
    private TextView txt_tongtien;

    public GiohangAdapter(Context context, ArrayList<Sanpham> listsanpham, TextView txt_tongtien){
        this.context = context;
        this.listsanpham = listsanpham;
        this.txt_tongtien = txt_tongtien;
    }

    @Override
    public int getCount() {
        return this.listsanpham.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listsanpham.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void notifyDataSetChanged() {

        super.notifyDataSetChanged();
    }

    //@RequiresApi(api = Build.VERSION_CODES.N)
    //@SuppressLint({"SetTextI18n", "InflateParams"})

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

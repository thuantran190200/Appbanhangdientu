package com.example.banhangdientu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterDanhmuc extends BaseAdapter {
    Context context;

    public AdapterDanhmuc(Context context, int layout, ArrayList<Danhmuc> arraylist) {
        this.context = context;
        this.layout = layout;
        this.arraylist = arraylist;
    }

    private int layout;
    private ArrayList<Danhmuc>arraylist;

    public AdapterDanhmuc(Context fragment_danhmuc, ArrayList<Danhmuc> listdanhmuc) {
    }

    @Override

    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return this.arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout,null);
        TextView textView= convertView.findViewById(R.id.show_tendm);
        textView.setText(arraylist.get(position).getTendm());
        ImageView imageView = convertView.findViewById(R.id.show_hinhdm);
        String url = arraylist.get(position).getHinhdm();
        Picasso.with(context).load(url).into(imageView);
        return convertView;
    }
}

package com.example.banhangdientu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SanphamAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<Sanpham> listsanpham;
    private LayoutInflater inflater;
    public SanphamAdapter(Context context, ArrayList<Sanpham> listsanpham){
        this.context = context;
        this.listsanpham = listsanpham;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Sanpham sanpham = (Sanpham) getItem(position);
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.thongtinsanpham, null);
        }
        TextView txt1 = convertView.findViewById(R.id.show_tensp);
        txt1.setText(sanpham.getTensp());
        TextView txt2 = convertView.findViewById(R.id.show_giasp);
        txt2.setText(sanpham.getGiasp());
        ImageView imageView = convertView.findViewById(R.id.show_hinhsp);


        String url = listsanpham.get(position).getAnhsp();
        Picasso.with(context).load(url).into(imageView);
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}

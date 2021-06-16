package com.example.banhangdientu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.banhangdientu.Giaodienchinh.SHARED_PREFS;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"SetTextI18n", "InflateParams"})

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Sanpham currentItem = (Sanpham) getItem(position);
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_sanpham_giohang, null);
        }
        ImageView imageView = convertView.findViewById(R.id.img_hinhgiohang);
        TextView textView = convertView.findViewById(R.id.ten_sp_giohang);
        textView.setText(currentItem.getTensp());
        TextView textView1 = convertView.findViewById(R.id.gia_sp_giohang);
        textView1.setText(String.valueOf(currentItem.giasp));
        Button button = convertView.findViewById(R.id.btn_tangsl);
        TextView textView2 = convertView.findViewById(R.id.soluong_sp_giohang);
        textView2.setText("số lượng: " + currentItem.getSoluong());
        Button button2 = convertView.findViewById(R.id.btn_giamsl);
        Giaodienchinh.listspGiohang.forEach(sp -> {
            if(sp.getId().equals(currentItem.getId()) && sp.getSoluong() >= currentItem.getSoluongsp()){
                button.setEnabled(false);
                button.setBackgroundColor(context.getResources().getColor(R.color.silver));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                currentItem.setSoluong(currentItem.getSoluong()+1);
                if(currentItem.getSoluong() >= currentItem.getSoluongsp()){
                    button.setEnabled(false);
                    button.setBackgroundColor(context.getResources().getColor(R.color.silver));
                }
                textView2.setText("số lượng: " + currentItem.getSoluong());
                Giaodienchinh.listspGiohang.forEach(Sanpham -> {
                    tongtien += (Sanpham.getSoluong()*Sanpham.getGiasp());
                    txt_tongtien.setText("Giá: " + tongtien+" VNĐ");
                });
                saveData();
                tongtien = 0;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(currentItem.getSoluong() > 1) {
                    button.setEnabled(true);
                    button.setBackgroundColor(context.getResources().getColor(R.color.aqua));
                    currentItem.setSoluong(currentItem.getSoluong() - 1);
                    textView2.setText("số lượng: " + currentItem.getSoluong());
                    notifyDataSetChanged();
                    Giaodienchinh.listspGiohang.forEach(Sanpham -> {
                        tongtien += (Sanpham.getSoluong() * Sanpham.getGiasp());
                        txt_tongtien.setText("Giá: " + tongtien+" VNĐ");
                    });
                    saveData();
                    tongtien = 0;
                }else {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    Giaodienchinh.listspGiohang.remove(currentItem);
                                    Giaodienchinh.listspGiohang.forEach(Sanpham -> {
                                        tongtien += (Sanpham.getSoluong()*Sanpham.getGiasp());
                                        txt_tongtien.setText("Giá: " + tongtien+" VNĐ");
                                    });
                                    tongtien = 0;
                                    if(Giaodienchinh.listspGiohang.size() == 0){
                                        txt_tongtien.setText("Giá: " + 0 +" VNĐ");
                                    }
                                    saveData();
                                    notifyDataSetChanged();
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    textView2.setText("số lượng: " + currentItem.getSoluong());
                                    saveData();
                                    notifyDataSetChanged();
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Bạn muốn xóa sản phẩm?").setPositiveButton("Có", dialogClickListener)
                            .setNegativeButton("Không", dialogClickListener).show();
                }

            }
        });
        String url = currentItem.anhsp;
        Picasso.with(context).load(url).into(imageView);
        return convertView;
    }
    private void saveData(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // creating a new variable for gson.
        Gson gson = new Gson();
        // getting data from gson and storing it in a string.
        String json = gson.toJson(Giaodienchinh.listspGiohang);
        //below line is to save data in shared
        //prefs in the form of string.
        editor.putString("listGH", json);
        editor.apply();

    }
}

package com.example.banhangdientu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Chitietdonhang_vanchuyen extends RecyclerView.Adapter<Chitietdonhang_vanchuyen.ViewHolder> {
    private Context mContext;
    private ArrayList<Sanpham> sanpham;
    private ArrayList<Chitiethoadon> chitiet;
    public Chitietdonhang_vanchuyen(Context mContext,ArrayList<Sanpham> sanpham,ArrayList<Chitiethoadon> chitiet){
        this.mContext = mContext;
        this.sanpham = sanpham;
        this.chitiet = chitiet;
    }
    @NonNull
    @Override
    public Chitietdonhang_vanchuyen.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitietvanchuyen,parent,false);
        return new Chitietdonhang_vanchuyen.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Chitietdonhang_vanchuyen.ViewHolder holder, int position) {
        String url = sanpham.get(position).anhsp;
        Picasso.with(mContext).load(url).into(holder.imvHinhSP);
        holder.txtTenSP.setText(sanpham.get(position).getTensp());
        holder.txtSoluong.setText("X"+chitiet.get(position).getSoluong());
        holder.txtGiaSP.setText(sanpham.get(position).getGiasp()+"đ");
    }

    @Override
    public int getItemCount() {
        return sanpham.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvHinhSP;
        TextView txtTenSP,txtGiaSP,txtSoluong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvHinhSP = itemView.findViewById(R.id.imvHinhSP);
            txtTenSP = itemView.findViewById(R.id.txtTenSP);
            txtGiaSP = itemView.findViewById(R.id.txtGiasp);
            txtSoluong = itemView.findViewById(R.id.txtsoluong);
        }
    }
}

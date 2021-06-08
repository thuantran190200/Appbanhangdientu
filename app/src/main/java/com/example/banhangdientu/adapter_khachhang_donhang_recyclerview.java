package com.example.banhangdientu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter_khachhang_donhang_recyclerview extends RecyclerView.Adapter<adapter_khachhang_donhang_recyclerview.ViewHolder> {
    private Context mContext;
    private ArrayList<Hoadon> hoadons ;
    public adapter_khachhang_donhang_recyclerview(Context mContext, ArrayList<Hoadon> hoadons) {
        this.mContext = mContext;
        this.hoadons = hoadons;
    }
    @NonNull
    @Override
    public adapter_khachhang_donhang_recyclerview.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giaohang,parent,false);
        return new adapter_khachhang_donhang_recyclerview.ViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull adapter_khachhang_donhang_recyclerview.ViewHolder holder, int position) {

        holder.txtmadonhang.setText("Mã đơn hàng: "+hoadons.get(position).getId());
        holder.tenkh.setText("Tên khách hàng: "+hoadons.get(position).getHoten());
        holder.diachigh.setText("Địa chỉ GH: "+hoadons.get(position).getDiachi());
        holder.txtngaydukien.setText("Hàng sẽ được nhận sau: "+hoadons.get(position).getNgaytaoHD());
        holder.txttongtien.setText("Số tiền: "+String.valueOf(hoadons.get(position).getTongtien())+" đ");
        holder.rlItemHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Thongtinhoadon_Giaohang_Khachhang.class);
                intent.putExtra("idHD",hoadons.get(position).getId());
                intent.putExtra("tongtien",hoadons.get(position).getTongtien());
                intent.putExtra("hoten",hoadons.get(position).getHoten());
                intent.putExtra("diachi",hoadons.get(position).getDiachi());
                intent.putExtra("sodienthoai",hoadons.get(position).getSdt());
                intent.putExtra("trangthai",hoadons.get(position).getTrangthaiGH());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return hoadons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtmadonhang,txttongtien,txtngaydukien, tenkh, diachigh;
        RelativeLayout rlItemHD;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmadonhang = itemView.findViewById(R.id.gh_madonhang);
            txttongtien = itemView.findViewById(R.id.gh_sotien);
            txtngaydukien = itemView.findViewById(R.id.gh_ngaygiao);
            tenkh = itemView.findViewById(R.id.tenkh);
            diachigh = itemView.findViewById(R.id.diachi_ghkh);
            rlItemHD = itemView.findViewById(R.id.item_khunggh);
        }
    }

}

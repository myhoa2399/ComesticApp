package com.example.myadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comestic.DonHangActivity;
import com.example.comestic.R;
import com.example.model.DonHang;
import com.example.model.GiamGia;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DonHangCuaToiAdapter extends RecyclerView.Adapter<DonHangCuaToiAdapter.ViewHolder> {
    ArrayList<DonHang> listDH;
    Context context;

    public DonHangCuaToiAdapter(ArrayList<DonHang> listDH, Context context) {
        this.listDH = listDH;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.don_hang_cua_toi_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        final DonHang dh = listDH.get(position);
        holder.maDH.setText(dh.getMaDH());
        holder.ngayDH.setText(dh.getNgayDatHang());
        holder.tongTien.setText(decimalFormat.format(dh.getTongTienHang())  +"Đ");




    }



    @Override
    public int getItemCount() {
        return listDH.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView maDH, tongTien, ngayDH;

        public ViewHolder(View itemview) {
            super(itemview);
            maDH = itemview.findViewById(R.id.maDH);
            tongTien =itemview.findViewById(R.id.tong_tien);
            ngayDH = itemview.findViewById(R.id.ngayDH);

        }
    }

}

package com.example.myadapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comestic.ChiTietSanPham;
import com.example.comestic.DonHangActivity;
import com.example.comestic.GioHangActivity;
import com.example.comestic.R;
import com.example.model.GiamGia;
import com.example.model.SanPham;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GiamGiaAdapter extends RecyclerView.Adapter<GiamGiaAdapter.ViewHolder> {
    ArrayList<GiamGia> listGiamGia;
    Context context;

    public GiamGiaAdapter(ArrayList<GiamGia> listGiamGia, Context context) {
        this.listGiamGia = listGiamGia;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.giamgia_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        final GiamGia discount = listGiamGia.get(position);
        holder.tieuDe.setText(discount.getTieuDe());
        holder.ngayBD.setText(discount.getNgayBatDau());
        holder.ngayKT.setText(discount.getNgayKetThuc());
        Picasso.with(context).load(discount.getImg()).into(holder.img);
        holder.img.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transiton_animation));
        holder.tieuDe.setAnimation(AnimationUtils.loadAnimation(context,R.anim.blink_animation));

        holder.giamGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DonHangActivity.class);
                intent.putExtra("giamgia",discount.getMaGiamGia());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });





    }



    @Override
    public int getItemCount() {
        return listGiamGia.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView ngayBD, ngayKT;
        TextView tieuDe;

        CardView giamGia;

        public ViewHolder(View itemview) {
            super(itemview);
            tieuDe =itemview.findViewById(R.id.txtTieuDe);
            img = itemview.findViewById(R.id.imgGiamGia);
            ngayBD = itemview.findViewById(R.id.txtNgayBatDau);
            ngayKT = itemview.findViewById(R.id.txtNgayKetThuc);

            giamGia =itemview.findViewById(R.id.giamGia);

        }
    }

}

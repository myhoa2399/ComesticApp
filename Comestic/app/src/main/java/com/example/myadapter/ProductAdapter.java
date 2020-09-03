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
import com.example.comestic.R;
import com.example.model.SanPham;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<SanPham> listSP;
    Context context;

    public ProductAdapter(ArrayList<SanPham> listSP, Context context) {
        this.listSP = listSP;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        final SanPham sp = listSP.get(position);
        holder.nameProduct.setText(sp.getTenSP());
        holder.priceProduct.setText("Giá: " + decimalFormat.format(sp.getGia())  +"Đ");
        Picasso.with(context).load(sp.getSpURL()).into(holder.imgProduct);




        holder.product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanPham.class);
                intent.putExtra("sanPham",sp);

//                intent.putExtra("maSP", sp.getMaSP());
//                intent.putExtra("tenSP",sp.getTenSP());
//                intent.putExtra("moTa",sp.getMoTaSP());
//                intent.putExtra("gia",sp.getGia());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return listSP.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView nameProduct;
        TextView priceProduct;
        TextView moTa;

        CardView product;

        public ViewHolder(View itemview) {
            super(itemview);

            imgProduct = itemview.findViewById(R.id.imgSanPham);
            nameProduct = itemview.findViewById(R.id.tenSP);
            priceProduct = itemview.findViewById(R.id.gia_product_row);
            product = itemview.findViewById(R.id.product);
        }
    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        convertView = LayoutInflater.from(context).inflate(R.layout.product_layout,parent,false);
//
//        TextView tenSP = convertView.findViewById(R.id.tenSP);
//        TextView moTa = convertView.findViewById(R.id.moTa);
//        TextView gia = convertView.findViewById(R.id.gia);
//        ImageView imgView = convertView.findViewById(R.id.imgSanPham);
//
//        SanPham sp = list.get(position);
//
//        tenSP.setText(sp.getTenSP());
//        moTa.setText(sp.getMoTaSP());
//        gia.setText(String.valueOf(sp.getGia()));
//        Picasso.with(context).load(sp.getSpURL()).into(imgView);
//        return convertView;
//    }
}

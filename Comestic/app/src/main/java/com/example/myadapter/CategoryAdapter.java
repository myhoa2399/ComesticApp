package com.example.myadapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.comestic.ChiTietSanPham;
import com.example.comestic.ProductListActivity;
import com.example.comestic.R;
import com.example.model.LoaiSanPham;
import com.example.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
        ArrayList<LoaiSanPham> listLoaiSP;
        Context context;

    public CategoryAdapter(ArrayList<LoaiSanPham> listLoaiSP, Context context) {
        this.listLoaiSP = listLoaiSP;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =(View) layoutInflater.inflate(R.layout.category_row,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        final LoaiSanPham loaiSP = listLoaiSP.get(position);
        holder.nameCategory.setText(loaiSP.getTenLoaiSP());

//        Picasso.with(context).load(loaiSP.getImg()).into(holder.img);

        holder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductListActivity.class);
                intent.putExtra("category",loaiSP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLoaiSP.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameCategory;
        ImageView img;
        LinearLayout category;

        public ViewHolder(View itemview) {

            super(itemview);
            nameCategory = itemview.findViewById(R.id.tenLoaiSP);
            category = itemview.findViewById(R.id.category);
            img= itemview.findViewById(R.id.imgLoaiSanPham);
        }
    }
}
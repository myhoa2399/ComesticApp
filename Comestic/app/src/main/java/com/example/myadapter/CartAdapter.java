package com.example.myadapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.comestic.DonHangActivity;
import com.example.comestic.GioHangActivity;
import com.example.comestic.R;
import com.example.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    GioHangActivity context;
    ArrayList<SanPham> listSP;
    ArrayList<String> listSL;

    public CartAdapter (GioHangActivity context, ArrayList<SanPham> listSP,ArrayList<String> listSL){
        this.context = context;
        this.listSP = listSP;
        this.listSL = listSL;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.giohang_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final SanPham sp = listSP.get(position);
        String sl = listSL.get(position);

        holder.txtTenSP.setText(sp.getTenSP());
        holder.txtGiaSP.setText(String.valueOf(sp.getGia()));
        holder.elegantNumberButton.setNumber(sl);
        Picasso.with(context).load(sp.getSpURL()).into(holder.img);

        holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                int soLuong = Integer.parseInt(holder.elegantNumberButton.getNumber());
                holder.txtGiaSP.setText(String.valueOf(sp.getGia()*soLuong));

                GioHangActivity.listSoLuongSP.add(position,holder.elegantNumberButton.getNumber());

                int tongTien =0;
                for(int i=0 ; i<context.listSpOrders.size() ;i++){
                    SanPham p = context.listSpOrders.get(i);
                    int soLuong1 = Integer.parseInt(context.listSoLuongSP.get(i)); // số lượng của từng sp trong list
                    tongTien +=soLuong1*p.getGia();
                }


                context.total.setText(String.valueOf(tongTien));

            }
        });



        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog= new Dialog(context);
                Button btnCo , btnKhong;

                dialog.setContentView(R.layout.dialog_delete);
                dialog.setCanceledOnTouchOutside(false);
                btnCo = dialog.findViewById(R.id.btnCo);
                btnKhong = dialog.findViewById(R.id.btnKhong);
                btnCo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GioHangActivity.listSoLuongSP.remove(position);
                        GioHangActivity.listSpOrders.remove(position);
                        notifyItemRemoved(position);

                        int tongTien =0;
                        for(int i=0 ; i<context.listSpOrders.size() ;i++){
                            SanPham p = context.listSpOrders.get(i);
                            int soLuong1 = Integer.parseInt(context.listSoLuongSP.get(i)); // số lượng của từng sp trong list
                            tongTien +=soLuong1*p.getGia();
                        }


                        context.total.setText(String.valueOf(tongTien));

                        dialog.dismiss();
                    }
                });
                btnKhong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();




            }
        });

    }



    @Override
    public int getItemCount() {
        return listSP.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenSP;
        TextView txtGiaSP;
        ImageView img;
        Button btnDelete;
        ElegantNumberButton elegantNumberButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSP =itemView.findViewById(R.id.txtTenSP);
            txtGiaSP = itemView.findViewById(R.id.txtGia);
            img =itemView.findViewById(R.id.imgGioHang);
            elegantNumberButton =itemView.findViewById(R.id.numSoLuong);
            btnDelete =itemView.findViewById(R.id.btnDelete);
        }
    }
}

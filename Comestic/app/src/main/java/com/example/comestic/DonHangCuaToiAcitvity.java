package com.example.comestic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.DonHang;
import com.example.model.SanPham;
import com.example.myadapter.DonHangAdapter;
import com.example.myadapter.DonHangCuaToiAdapter;
import com.example.myadapter.ProductAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DonHangCuaToiAcitvity extends AppCompatActivity {

    private Button btnBack;
    private LinearLayout lnEmptyOrder;

    private TextView donHangDaThanhToan, donHangChuaThanhToan;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private ArrayList<DonHang> list;
    DonHangCuaToiAdapter donHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_cua_toi_acitvity);

        btnBack =findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Orders");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                    DonHang p = dataSnapshot1.getValue(DonHang.class);
                    DonHang p1 = new DonHang(dataSnapshot1.getKey(),p.getMaKH(),p.getNgayDatHang(),p.getTongTienHang(),p.getMaGiamGia(),p.getTienVanChuyen(),
                            p.getTienGiamGia(),p.getTienThanhToan(),p.getDiaChi(),p.getDaGiao());
                    if(p1.getMaKH().trim().equals(MainActivity.txtMaKH.getText().toString().trim())){
                        list.add(p1);
                    }

                }

                if(list.size() == 0 ){
                    lnEmptyOrder.setVisibility(View.VISIBLE);
                }
                else {
                    lnEmptyOrder.setVisibility(View.GONE);
                }
                donHangAdapter = new DonHangCuaToiAdapter(list,getApplicationContext());
                recyclerView.setAdapter(donHangAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DonHangCuaToiAcitvity.this,"Something is wrong!!!",Toast.LENGTH_SHORT).show();
            }
        });

        donHangDaThanhToan = findViewById(R.id.don_hang_da_thanh_toan);
        donHangChuaThanhToan =findViewById(R.id.don_hang_chua_thanh_toan);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        lnEmptyOrder =findViewById(R.id.empty_order);

        donHangDaThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donHangChuaThanhToan.setBackgroundColor(Color.parseColor("#FFFFFF"));
                donHangChuaThanhToan.setTextColor(Color.parseColor("#A19898"));
                donHangDaThanhToan.setBackgroundResource(R.drawable.border_button);
                donHangDaThanhToan.setTextColor(Color.parseColor("#FFFFFF"));
                list = new ArrayList<>();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Orders");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                            DonHang p = dataSnapshot1.getValue(DonHang.class);
                            DonHang p1 = new DonHang(dataSnapshot1.getKey(),p.getMaKH(),p.getNgayDatHang(),p.getTongTienHang(),p.getMaGiamGia(),p.getTienVanChuyen(),
                                    p.getTienGiamGia(),p.getTienThanhToan(),p.getDiaChi(),p.getDaGiao());

                            if(p.getDaGiao() ==1 && p1.getMaKH().trim().equals(MainActivity.txtMaKH.getText().toString().trim())){
                                list.add(p1);
                            }
                        }

                        if(list.size() == 0 ){
                            lnEmptyOrder.setVisibility(View.VISIBLE);
                        }
                        else {
                            lnEmptyOrder.setVisibility(View.GONE);
                        }
                        donHangAdapter = new DonHangCuaToiAdapter(list,getApplicationContext());
                        recyclerView.setAdapter(donHangAdapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(DonHangCuaToiAcitvity.this,"Something is wrong!!!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        donHangChuaThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donHangDaThanhToan.setBackgroundColor(Color.parseColor("#FFFFFF"));
                donHangDaThanhToan.setTextColor(Color.parseColor("#A19898"));
                donHangChuaThanhToan.setBackgroundResource(R.drawable.border_button);
                donHangChuaThanhToan.setTextColor(Color.parseColor("#FFFFFF"));
                list = new ArrayList<>();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Orders");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                            DonHang p = dataSnapshot1.getValue(DonHang.class);
                            DonHang p1 = new DonHang(dataSnapshot1.getKey(),p.getMaKH(),p.getNgayDatHang(),p.getTongTienHang(),p.getMaGiamGia(),p.getTienVanChuyen(),
                                    p.getTienGiamGia(),p.getTienThanhToan(),p.getDiaChi(),p.getDaGiao());

                            if(p1.getDaGiao() ==0 && p1.getMaKH().trim().equals(MainActivity.txtMaKH.getText().toString().trim())){
                                list.add(p1);
                            }
                        }

                        if(list.size() == 0 ){
                            lnEmptyOrder.setVisibility(View.VISIBLE);
                        }
                        else {
                            lnEmptyOrder.setVisibility(View.GONE);
                        }
                        donHangAdapter = new DonHangCuaToiAdapter(list,getApplicationContext());
                        recyclerView.setAdapter(donHangAdapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(DonHangCuaToiAcitvity.this,"Something is wrong!!!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}

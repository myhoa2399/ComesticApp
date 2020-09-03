package com.example.comestic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.LoaiSanPham;
import com.example.model.SanPham;
import com.example.myadapter.ProductAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private static ArrayList<SanPham> list;
    private  TextView text;
    private ListView listView;
    private RecyclerView recyclerView;
    private LoaiSanPham loaiSP;
    ProductAdapter productAdapter;
    TextView cateName;
    ImageView imgCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_display);
//
//        ArrayList<SanPham> list1 = new ArrayList<>();
//        SanPham p = new SanPham("A",111,"A1");
//        SanPham p1 = new SanPham("B",111,"A1");
//        SanPham p2 = new SanPham("C",111,"A1");
//        SanPham p3 = new SanPham("D",111,"A1");
//        SanPham p4 = new SanPham("E",111,"A1");
//        list1.add(p);
//        list1.add(p1);
//        list1.add(p2);
//        list1.add(p3);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<>();
        Intent intent = getIntent();
        loaiSP =(LoaiSanPham) intent.getExtras().getSerializable("category");
        cateName = findViewById(R.id.cateName);
        imgCategory = findViewById(R.id.imgCategory);

        cateName.setText(loaiSP.getTenLoaiSP());
        Picasso.with(getApplicationContext()).load(loaiSP.getImg()).into(imgCategory);

        recyclerView = findViewById(R.id.recyclerView);
//        listView =findViewById(R.id.lv);//product
//        list = new ArrayList<>();
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // productAdapter = new ProductAdapter( list, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Product");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                    SanPham p = dataSnapshot1.getValue(SanPham.class);
                    if(p.getMaLoaiSP()==loaiSP.getMaLoaiSP()){
                        list.add(p);
                    }
//                    productAdapter.notifyDataSetChanged();
                }
                productAdapter = new ProductAdapter(list,getApplicationContext());
                recyclerView.setAdapter(productAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProductListActivity.this,"Something is wrong!!!",Toast.LENGTH_SHORT).show();
            }
        });
//
//        productAdapter = new ProductAdapter(list1,getApplicationContext());
//        recyclerView.setAdapter(productAdapter);

        //re.setAdapter(productAdapter);











    }
}

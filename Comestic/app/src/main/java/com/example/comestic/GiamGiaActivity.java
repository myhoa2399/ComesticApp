package com.example.comestic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.model.GiamGia;
import com.example.model.SanPham;
import com.example.myadapter.GiamGiaAdapter;
import com.example.myadapter.ProductAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GiamGiaActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    private GiamGia giamGia;
    GiamGiaAdapter giamGiaAdapter;

    private Button btnBack;
    private ArrayList<GiamGia> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giam_gia);
        list = new ArrayList<>();
        btnBack = findViewById(R.id.btnBack);

        recyclerView = findViewById(R.id.recyclerGiamGia);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Discount");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                    GiamGia p = dataSnapshot1.getValue(GiamGia.class);
                    list.add(p);
//                    productAdapter.notifyDataSetChanged();
                }
                giamGiaAdapter = new GiamGiaAdapter(list,getApplicationContext());
                recyclerView.setAdapter(giamGiaAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(GiamGiaActivity.this,"Something is wrong!!!",Toast.LENGTH_SHORT).show();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }
}

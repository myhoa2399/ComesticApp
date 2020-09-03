package com.example.comestic;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.model.SanPham;
import com.example.model.User;
import com.example.myadapter.ProductAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public  class Util {
    public static DatabaseReference mDatabase;
    public static ArrayList<SanPham> list_sp =null;

    public static ArrayList<SanPham> layDSSanPham(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Product");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                    SanPham p = dataSnapshot1.getValue(SanPham.class);
                    list_sp.add(p);

                }



            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return list_sp;
    }


}

package com.example.comestic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.SanPham;
import com.example.model.User;
import com.example.myadapter.CartAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    public static ArrayList<SanPham> listSpOrders =new ArrayList<>();
    public static ArrayList<String> listSoLuongSP =new ArrayList<>();
    double tongTien = 0;
    User user;
    public static TextView total; //Tổng tiền hiện thị lên view
    SanPham sp ;
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    private Button btnGiamGia;
    private Button btnDatHang;
    private LinearLayout lnEmptyCart;
    private Button  btnBack;
    private DatabaseReference mDatabase;
    public static TextView txtMaKH;

    public static TextView txtNhapMa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        txtNhapMa =findViewById(R.id.txtNhapMa);
        lnEmptyCart =findViewById(R.id.empty_cart);
        txtMaKH =findViewById(R.id.maKH);
        txtMaKH.setVisibility(View.GONE);

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        if (bundle != null) {
//            String ma = bundle.getString("giamgia");
//            txtNhapMa.setText(ma);
//        }

        if(listSpOrders.size() >0){
            lnEmptyCart.setVisibility(View.GONE);
        }


        final DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        recyclerView = findViewById(R.id.recyclerViewGioHang1);
        cartAdapter = new CartAdapter(this,listSpOrders,listSoLuongSP);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        btnGiamGia =findViewById(R.id.btnLoadGiamGia);



        total = findViewById(R.id.total);
        for(int i=0 ; i<listSpOrders.size() ;i++){
            SanPham p = listSpOrders.get(i);
            int soLuong1 = Integer.parseInt(listSoLuongSP.get(i)); // số lượng của từng sp trong list
            tongTien +=soLuong1*p.getGia();
        }
        total.setText(decimalFormat.format(tongTien)+" đ");


        // Đặt hàng (Conduct Product)
        btnDatHang = findViewById(R.id.btn_dathang);
        getMaKH(MainActivity.email);
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listSoLuongSP.size() ==0){
                    Toast.makeText(GioHangActivity.this, "Vui lòng chọn sản phẩm để tiến hành đặt hàng", Toast.LENGTH_SHORT).show();
                }
                else {

                    if(!txtMaKH.getText().equals("maKH")){
                        String maKH = txtMaKH.getText().toString();

                        Intent intent = new Intent(GioHangActivity.this, DonHangActivity.class);
                        startActivity(intent);
                    }

                }
            }
        });

//        btnGiamGia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intents = new Intent(GioHangActivity.this,GiamGiaActivity.class);
//                startActivity(intents);
//            }
//        });

        btnBack = findViewById(R.id.btnBack);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHangActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
    static  ArrayList<User> listUser;

    private void getMaKH(final String email){

        listUser = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                    User p = dataSnapshot1.getValue(User.class);
                    if(p.getEmail().equals(email)){
                        txtMaKH.setText(dataSnapshot1.getKey());

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(GioHangActivity.this,"Something is wrong!!!",Toast.LENGTH_SHORT).show();
            }
        });




    }


}

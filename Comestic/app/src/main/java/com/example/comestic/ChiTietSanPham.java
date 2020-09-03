package com.example.comestic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.model.ProductImage;
import com.example.model.SanPham;
import com.example.myadapter.SliderAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietSanPham extends AppCompatActivity {
    ElegantNumberButton soLuongSP;
    TextView gia, thongtin, tenSp ,maSP;
    ArrayList<ProductImage> arr;
    SliderAdapter sliderAdapter;
    int id = 0;
    ViewPager2 viewPager2;
    SanPham product;
    Button btnThem ;
    String soLuong;
    private DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //fullscreen
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.chi_tiet_san_pham);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        mData = FirebaseDatabase.getInstance().getReference();


        final Intent intent = getIntent();
        product =(SanPham) intent.getExtras().getSerializable("sanPham");
//        Bundle bundle = intent.getExtras();
        if (product != null) {
            String name = product.getTenSP();
            String masp =String.valueOf(product.getMaSP());
            id = product.getMaSP();
            String descripiton = product.getMoTaSP();
            double price = product.getGia();
            arr = new ArrayList<>();
            viewPager2 = findViewById(R.id.viewPagerImageSlider);

            sliderAdapter = new SliderAdapter(arr, viewPager2, this);

            viewPager2.setAdapter(sliderAdapter);

            mData.child("ProductImage").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dt : dataSnapshot.getChildren()){
                        ProductImage img = dt.getValue(ProductImage.class);

                        if(img.getProductID() == (id)) {
                            arr.add(img);
                            sliderAdapter.notifyDataSetChanged();
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ChiTietSanPham.this, "Error:" +
                            databaseError.getMessage() , Toast.LENGTH_SHORT).show();
                }
            });
            mapping(name, descripiton, price,masp);
            loadSlider();
        }

        soLuongSP = findViewById(R.id.soLuongSP);
        soLuongSP.setNumber("1");
        soLuong = soLuongSP.getNumber();


        soLuongSP.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                soLuong = soLuongSP.getNumber();

            }
        });


        btnThem = findViewById(R.id.btn_dathang);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(MainActivity.email == null){
                   Toast.makeText(ChiTietSanPham.this,"Vui lòng đăng nhập để đặt hàng!",Toast.LENGTH_SHORT).show();
                   Intent intent4 = new Intent(ChiTietSanPham.this,LoginActivity.class);
                   startActivity(intent4);

               }
               else {
                   GioHangActivity.listSpOrders.add(product);
                   GioHangActivity.listSoLuongSP.add(soLuong);
                   Intent intentThem = new Intent(ChiTietSanPham.this,GioHangActivity.class);
                   intentThem.putExtra("sp", product);
                   intentThem.putExtra("soLuong",soLuong);

                   startActivity(intentThem);
               }


            }
        });


    }

    public void mapping(String name, String des, double pri,String maSP1){

        gia = findViewById(R.id.giaSP);
        tenSp = findViewById(R.id.tensanpham);
        thongtin = findViewById(R.id.thongtin);
        maSP =findViewById(R.id.gia);

        tenSp.setText(name);
        thongtin.setText(des);
        maSP.setText(maSP1);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        gia.setText(decimalFormat.format(pri)+" đ");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadSlider() {
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}

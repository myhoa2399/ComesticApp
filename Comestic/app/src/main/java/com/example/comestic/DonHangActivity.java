package com.example.comestic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.ChiTietDonHang;
import com.example.model.DonHang;
import com.example.model.GiamGia;
import com.example.model.SanPham;
import com.example.model.User;
import com.example.myadapter.CartAdapter;
import com.example.myadapter.DonHangAdapter;
import com.example.myadapter.GiamGiaAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DonHangActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    RecyclerView recyclerView;
    DonHangAdapter donHangAdapter;
    public static ArrayList<SanPham> listSpOrders1 =new ArrayList<>();
    public static ArrayList<String> listSoLuongSP1 =new ArrayList<>();
    private Button btnBack;
    private static TextView chonMaGiamGia;
    public static TextView total;
    double tongTien = 0;
    private Button btnDatHang;
    public static TextView tongTienHang, tongThanhToan,tienVanChuyen, tienGiamGia;
    private DonHang donHang;
    FirebaseDatabase database ;
    private TextView diaChi;
    public DonHangActivity(){
        listSpOrders1 = GioHangActivity.listSpOrders;
        listSoLuongSP1 =GioHangActivity.listSoLuongSP;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);

        chonMaGiamGia =findViewById(R.id.chon_ma_giam_gia);
        tongTienHang = findViewById(R.id.tong_tien);
        tongThanhToan = findViewById(R.id.tong_thanh_toan);
        tienVanChuyen =findViewById(R.id.tien_van_chuyen);
        tienGiamGia =findViewById(R.id.tien_giam_gia);
        btnDatHang =findViewById(R.id.btn_dat_hang);
        total =findViewById(R.id.total);

        diaChi =findViewById(R.id.txt_dia_chi);

        final DecimalFormat decimalFormat = new DecimalFormat("###,###,###");


        recyclerView = findViewById(R.id.recyclerViewGioHang1);
        donHangAdapter = new DonHangAdapter(DonHangActivity.this,listSpOrders1,listSoLuongSP1);
        recyclerView.setAdapter(donHangAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String ma = bundle.getString("giamgia");
            chonMaGiamGia.setText(ma);
        }

        layDiaChiKH(GioHangActivity.txtMaKH.getText().toString());

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonHangActivity.this,GioHangActivity.class);
                startActivity(intent);
            }
        });

        total = findViewById(R.id.total);
        for(int i=0 ; i<listSpOrders1.size() ;i++){
            SanPham p = listSpOrders1.get(i);
            int soLuong1 = Integer.parseInt(listSoLuongSP1.get(i)); // số lượng của từng sp trong list
            tongTien +=soLuong1*p.getGia();
        }

        final double ship = Double.parseDouble(tienVanChuyen.getText().toString());
        total.setText(String.valueOf(tongTien));

        double tong = Double.parseDouble(total.getText().toString() ) + ship;
        tongTienHang.setText(String.valueOf(tong));
        tongThanhToan.setText(tongTienHang.getText().toString());

        chonMaGiamGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(DonHangActivity.this,GiamGiaActivity.class);
                startActivity(intents);
            }
        });

        total.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(listSoLuongSP1.size()>0){
                    if(!chonMaGiamGia.getText().toString().trim().equals("Chọn mã giảm giá")){
                        double tienGiam = Double.parseDouble(tienGiamGia.getText().toString());
                        double tong = Double.parseDouble(total.getText().toString() ) + ship - tienGiam;
                        tongTienHang.setText(String.valueOf(tong));
                        tongThanhToan.setText(tongTienHang.getText().toString());
                    }
                    else {
                        double tong = Double.parseDouble(total.getText().toString() ) + ship ;
                        tongTienHang.setText(String.valueOf(tong));
                        tongThanhToan.setText(tongTienHang.getText().toString());
                    }
                }
            }
        });




//Tính tiền giảm giá khi lấy được mã giảm giá
        if(!chonMaGiamGia.getText().toString().equals("Chọn mã giảm giá") && listSoLuongSP1.size()>0){
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Discount");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                            GiamGia p = dataSnapshot1.getValue(GiamGia.class);
                            String maP = p.getMaGiamGia();
                            if(maP != null && maP.equals(chonMaGiamGia.getText().toString())){
                                if(p.getPhanTram() <1){
                                    double tienGiam = Double.parseDouble(total.getText().toString() )  *p.getPhanTram();
                                    tienGiamGia.setText(String.valueOf(tienGiam));
                                    double tong = Double.parseDouble(total.getText().toString() ) + ship - tienGiam;
                                    tongTienHang.setText(String.valueOf(tong));
                                    tongThanhToan.setText(tongTienHang.getText().toString());

                                }
                            }

                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
        }


        if(listSpOrders1.size() == 0){
            tienVanChuyen.setText("đ0");
            tongTienHang.setText("đ0");
            tongThanhToan.setText("đ0");
        }

        String pattern = "dd/MM/yyyy HH:mm:ss";

        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date according to the chosen pattern
                DateFormat df = new SimpleDateFormat(pattern);

        // Get the today date using Calendar object.
                Date today = Calendar.getInstance().getTime();
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
                final String todayAsString = df.format(today);

        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mã khách hàng
                String maKH = GioHangActivity.txtMaKH.getText().toString();

                // Ngày đặt hàng
                String ngayDH = todayAsString;

                // Tổng tiền hàng
                double tongTien = Double.parseDouble(total.getText().toString());

                // Mã giảm giá
                String maGiamGia;

                if(!chonMaGiamGia.getText().toString().equals("Chọn mã giảm giá") ){
                     maGiamGia= chonMaGiamGia.getText().toString();
                }
                else{
                    maGiamGia = "";
                }

                //tiền vận chuyển (ship)

                // tiền giảm giá
                double tienGiamGia1 = 0;
                if(!chonMaGiamGia.getText().toString().trim().equals("Chọn mã giảm giá")){
                    tienGiamGia1 = Double.parseDouble(tienGiamGia.getText().toString());
                }

                // tiền thanh toán
                double tienThanhToan =Double.parseDouble(tongThanhToan.getText().toString());


//                donHang = new DonHang(maKH,ngayDH,tongTien,maGiamGia,ship,123465,tienThanhToan);

                donHang = new DonHang(maKH,ngayDH,tongTien,maGiamGia,ship,tienGiamGia1,tienThanhToan,diaChi.getText().toString());
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Orders");

                mDatabase.push().setValue(donHang, new DatabaseReference.CompletionListener() {

                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if(databaseError == null){

                            for(int i=0; i<listSpOrders1.size() ;i++) {

                                SanPham p = listSpOrders1.get(i);
                                int soLuong = Integer.parseInt(listSoLuongSP1.get(i));

                                mDatabase = FirebaseDatabase.getInstance().getReference().child("Order Details").child(databaseReference.getKey());
                                ChiTietDonHang chiTietDonHang = new ChiTietDonHang(databaseReference.getKey(), p.getMaSP(), soLuong, p.getGia());
                                mDatabase.push().setValue(chiTietDonHang);


                            }


                            Toast.makeText(DonHangActivity.this, "Đặt hàng thành công", Toast.LENGTH_LONG).show();
                            xoaDuLieu();

                            Intent intent = new Intent(DonHangActivity.this,DatHangThanhCongActivity.class);
                            startActivity(intent);


                        }
                        else{
                            Toast.makeText(DonHangActivity.this,"Đặt hàng thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });



    }


    //set list khi đã đặt hàng thành công
    private void xoaDuLieu(){
        GioHangActivity.listSpOrders.clear();
        GioHangActivity.listSoLuongSP.clear();
        listSpOrders1.clear();
        listSoLuongSP1.clear();
    }

    void layDiaChiKH(String maKH){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = mDatabase.child("Users").child(maKH).child("diaChi");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String address = dataSnapshot.getValue(String.class);
                diaChi.setText(address);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}

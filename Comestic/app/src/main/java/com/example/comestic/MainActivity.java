package com.example.comestic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.model.LoaiSanPham;
import com.example.model.SanPham;
import com.example.model.User;
import com.example.myadapter.CategoryAdapter;
import com.example.myadapter.ImageAdapter;
import com.example.myadapter.ProductAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static TextView txtMaKH;
    VideoView mvideoView;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    private static final String TAG ="Main Activity";
    private static ArrayList<LoaiSanPham> listLoaiSP;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private CategoryAdapter categoryAdapter;
    private Toolbar toolbar ;
    TextView tenKH, emailKH;
    public static String email ;
    TextView ten_nav , email_nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);
        Intent intentLogin = getIntent();
//        email = intentLogin.getStringExtra("email");



        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,toolbar,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        NavigationView navView = (NavigationView) findViewById(R.id.nv);
        View headerview = navView.getHeaderView(0);



        ///nav_header
        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email ==null){
                    Intent intent4 = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent4);
                }
                else{

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
                    mDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                                User p = dataSnapshot1.getValue(User.class);
                                if(p.getEmail().equals(email)){
                                    Intent intent4 = new Intent(MainActivity.this,AccountActivity.class);
                                    intent4.putExtra("user",p);
                                    startActivity(intent4);
                                    break;


                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(MainActivity.this,"Something is wrong!!!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

//                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
//                startActivity(intent);
            }
        });

        tenKH = (TextView)headerview.findViewById(R.id.ten_nav);
        emailKH = (TextView) headerview.findViewById(R.id.email_nav);


        setNavHeader();


        getMaKH(email);




        // RecyclerView
        listLoaiSP = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewLoaiSP);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Categories");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                    LoaiSanPham p = dataSnapshot1.getValue(LoaiSanPham.class);
                    listLoaiSP.add(p);
                }

                categoryAdapter = new CategoryAdapter(listLoaiSP,getApplicationContext());
                recyclerView.setAdapter(categoryAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"Something is wrong!!!",Toast.LENGTH_SHORT).show();
            }
        });



        nv = (NavigationView)findViewById(R.id.nv);
        nv.bringToFront();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.trangchu:
                        Intent intents = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intents);
                        break;
                    case R.id.taikhoan:
                        if(email ==null){
                            Intent intent4 = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent4);
                            break;
                        }
                        else{

                            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
                            mDatabase.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                                        User p = dataSnapshot1.getValue(User.class);
                                        if(p.getEmail().equals(email)){
                                            Intent intent4 = new Intent(MainActivity.this,AccountActivity.class);
                                            intent4.putExtra("user",p);
                                            startActivity(intent4);
                                            break;


                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(MainActivity.this,"Something is wrong!!!",Toast.LENGTH_SHORT).show();
                                }
                            });
                            break;
                        }
                    case R.id.giohang:
                        if(email ==null){
                            Toast.makeText(MainActivity.this,"Vui lòng đăng nhập để xem giỏ hàng!",Toast.LENGTH_SHORT).show();
                            Intent intent4 = new Intent(MainActivity.this,LoginActivity.class);
                            startActivity(intent4);
                            break;
                        }
                        else {
                            Intent intent1 = new Intent(MainActivity.this, GioHangActivity.class);
                            startActivity(intent1);
                            break;
                        }
                    case R.id.donhang: {

                        Intent intent = new Intent(MainActivity.this, DonHangCuaToiAcitvity.class);
                        startActivity(intent);
                        break;
                    }

                    case R.id.khuyenmai:
                        Intent intent2 = new Intent(MainActivity.this,PromotionActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.dangxuat:
                        email =null;
                        GioHangActivity.listSpOrders.clear();
                        tenKH.setText("@string/nav_header_title");
                        emailKH.setText("@string/nav_header_subtitle");
                        Toast.makeText(MainActivity.this, "Đăng xuất", Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;
                }
                return true;
            }
        });

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        mViewPager.setAdapter(adapter);

        //load video
        mvideoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.nuochoagooggirl );
        try {
            mvideoView.setVideoURI(uri);

            mvideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
        } catch (NullPointerException techmaster1)
        {
            System.out.println("Couldn't load video" + techmaster1);
        }
        mvideoView.start();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void setNavHeader(){
        if(email != null){
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren() ){
                        User p = dataSnapshot1.getValue(User.class);
                        if(p.getEmail().equals(email)){
                            tenKH.setText(p.getTen());
                            emailKH.setText(p.getEmail());

                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this,"Something is wrong!!!",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



    @Override
    protected void onRestart() {
        super.onRestart();
//        Intent intents = new Intent(MainActivity.this,MainActivity.class);
//        startActivity(intents);
        //setContentView(R.layout.activity_main);
//        mvideoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.nuochoagooggirl );
        try {
            mvideoView.setVideoURI(uri);
        } catch (NullPointerException techmaster1)
        {
            System.out.println("Couldn't load video" + techmaster1);
        }
        mvideoView.start();

        setNavHeader();




    }

    private void getMaKH(final String email) {
        txtMaKH = findViewById(R.id.maKH);
        txtMaKH.setVisibility(View.GONE);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    User p = dataSnapshot1.getValue(User.class);
                    if (p.getEmail().equals(email)) {
                        txtMaKH.setText(dataSnapshot1.getKey());

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Something is wrong!!!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}

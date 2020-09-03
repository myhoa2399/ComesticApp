
package com.example.comestic;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myadapter.PromotionImage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PromotionActivity extends AppCompatActivity {
    private ActionBarDrawerToggle t;
    private DrawerLayout dl;

    private BottomNavigationView promotionNav;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion);
        toolbar = findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFE6E6E6")));

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPagerPromotion);
        PromotionImage adapter = new PromotionImage(this);
        mViewPager.setAdapter(adapter);

    }

//    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//            Fragment selectedFragment = null;
//
//            switch (menuItem.getItemId()){
//                case R.id.nav_cart:
//                    Intent intent1 = new Intent(PromotionActivity.this,OrderActivity.class);
//                    startActivity(intent1);
//                    break;
//                case R.id.nav_home:
//                    Intent intent = new Intent(PromotionActivity.this,MainActivity.class);
//                    startActivity(intent);
//                    break;
//
//                case  R.id.nav_search:
//                    Intent intent2 = new Intent(PromotionActivity.this,MainActivity.class);
//                    startActivity(intent2);
//                    break;
//                case  R.id.nav_promotion:
//                    Intent intent3 = new Intent(PromotionActivity.this,PromotionActivity.class);
//                    startActivity(intent3);
//                    break;
//
//                case R.id.nav_account:
//                    Intent intent4 = new Intent(PromotionActivity.this,AccountActivity.class);
//                    startActivity(intent4);
//            }
//            //getSupportFragmentManager().beginTransaction().replace(R.id.order_fragment, selectedFragment).commit();
//
//            return true;
//        }
//    };


}
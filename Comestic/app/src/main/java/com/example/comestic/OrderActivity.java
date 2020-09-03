package com.example.comestic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;



import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OrderActivity extends AppCompatActivity {
    private ActionBarDrawerToggle t;
    private DrawerLayout dl;

    private BottomNavigationView orderNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        orderNav = (BottomNavigationView) findViewById(R.id.order_nav);
        orderNav.setOnNavigationItemSelectedListener(navListener);
        // orderNav.clearFocus();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.nav_cart:
                    Intent intent1 = new Intent(OrderActivity.this,OrderActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.nav_home:
                    Intent intent = new Intent(OrderActivity.this,MainActivity.class);
                    startActivity(intent);
                    break;

                case  R.id.nav_search:
                    Intent intent2 = new Intent(OrderActivity.this,MainActivity.class);
                    startActivity(intent2);
                    break;
                case  R.id.nav_promotion:
                    Intent intent3 = new Intent(OrderActivity.this,PromotionActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.nav_account:
                    Intent intent4 = new Intent(OrderActivity.this,AccountActivity.class);
                    startActivity(intent4);
            }
            //getSupportFragmentManager().beginTransaction().replace(R.id.order_fragment, selectedFragment).commit();

            return true;
        }
    };


}
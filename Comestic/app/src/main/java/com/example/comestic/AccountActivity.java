package com.example.comestic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.model.LoaiSanPham;
import com.example.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {
    EditText txtNewPass, txtOldPass;
    TextView txtNewPass2, txtName, txtAddress, txtPhone,txtEmail;
    CheckBox checkBox;
    User user;
    ImageView img;
    private Toolbar toolbar ;
    private Button btnUpdate;
    private FirebaseUser account;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);


        toolbar =findViewById(R.id.toolBar1);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ACCOUNT");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // boolean check = false;
        txtOldPass = (EditText) findViewById(R.id.txt_old_pass);
        txtNewPass = (EditText) findViewById(R.id.txt_new_pass);
        txtNewPass2 = (EditText) findViewById(R.id.txt_confirm_pass);
        txtAddress =findViewById(R.id.txtAddress);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.editEmail);
        txtPhone =findViewById(R.id.txtPhoneNumber);
        checkBox = findViewById(R.id.checkBox);
        btnUpdate =findViewById(R.id.btnUpdateAccount);

        Intent intent = getIntent();
        if(intent.getExtras() !=null){
            user = (User) intent.getExtras().getSerializable("user");
            txtName.setText(user.getTen());
            txtPhone.setText(user.getSdt());
            txtEmail.setText(user.getEmail());
            txtAddress.setText(user.getDiaChi());
        }


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    txtNewPass.setVisibility(View.VISIBLE);
                    txtNewPass2.setVisibility(View.VISIBLE);
                    txtOldPass.setVisibility(View.VISIBLE);
                } else {
                    txtOldPass.setVisibility(View.GONE);
                    txtNewPass.setVisibility(View.GONE);
                    txtNewPass2.setVisibility(View.GONE);
                }


            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                account = FirebaseAuth.getInstance().getCurrentUser();
                final String email = account.getEmail();
                final String oldpass = txtOldPass.getText().toString();
                final String newPass = txtNewPass.getText().toString();
                AuthCredential credential = EmailAuthProvider.getCredential(email,oldpass);

                account.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            account.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(AccountActivity.this,"Something went wrong. Please try again later",Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(AccountActivity.this,"Cập nhật password thành công!",Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                        }else {
                            Toast.makeText(AccountActivity.this,"Authentication Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }







}

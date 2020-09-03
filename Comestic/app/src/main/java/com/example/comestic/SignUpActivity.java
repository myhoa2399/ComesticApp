package com.example.comestic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mfirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
//    private FirebaseAuth mAuth;
    private EditText email, passWord,confirmPass, name,address, phone;
    private Button btnSignUp;
    private TextView signIn, exit;
    private User user1 ;
//    private DatabaseReference mDatabase;
    private String e;
    FirebaseDatabase database ;
    private Button  btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signup);
//        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();

        mfirebaseAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference("UserInfor");
        mFirebaseUser = mfirebaseAuth.getCurrentUser();

        email =findViewById(R.id.signin_email);
        passWord = findViewById(R.id.signin_password);
        confirmPass =findViewById(R.id.signup_confirmpassword);
        name =findViewById(R.id.signin_fullname);
        address =findViewById(R.id.signin_diachi);
        phone =findViewById(R.id.signin_phone);
        btnSignUp =findViewById(R.id.btn_sign_up);
        signIn = findViewById(R.id.sign_in_again);
        exit = findViewById(R.id.Exit);
        e=email.getText().toString();
        btnBack = findViewById(R.id.btnBack);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email1 =email.getText().toString();
                final String pass1 = passWord.getText().toString();
                final String confirm1 = confirmPass.getText().toString();
                final String name1=name.getText().toString();
                final String address1 = address.getText().toString();
                final String phone1=phone.getText().toString();


                if(name1.isEmpty()){
                    name.setError("Please enter your full name");
                    name.requestFocus();
                }
                else if(address1.isEmpty()){
                    address.setError("Please enter your Email");
                    address.requestFocus();
                }
                else if(phone1.isEmpty()){
                    phone.setError("Please enter your Email");
                    phone.requestFocus();
                }
                else if(email1.isEmpty()){
                    email.setError("Please enter your Email");
                    email.requestFocus();
                }
                else if(pass1.isEmpty()){
                    passWord.setError("Please enter your password");
                    passWord.requestFocus();
                }
                else if(confirm1.isEmpty()){
                    confirmPass.setError("Please confirm your password");
                    confirmPass.requestFocus();
                }
                else if(!(email1.isEmpty() && pass1.isEmpty() && confirm1.isEmpty() &&name1.isEmpty()&&address1.isEmpty() && phone1.isEmpty())){

                        user1 = new User(name1,address1,email1,phone1);
                        mfirebaseAuth.fetchSignInMethodsForEmail(email.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                if (task.getResult().getSignInMethods().size() == 0) {
//
                                    mfirebaseAuth.createUserWithEmailAndPassword(email1, pass1).
                                            addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if(!task.isSuccessful()){
                                                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(),
                                                                Toast.LENGTH_LONG).show();

                                                    } else {
                                                        if(pass1.equals(confirm1)){
                                                            mFirebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if(task.isSuccessful()){
                                                                        user1 = new User(name1,address1,email1,phone1);


                                                                        FirebaseDatabase.getInstance().getReference("Users")
                                                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                                .setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                Toast.makeText(SignUpActivity.this,"Tạo tài khoản thành công . Vui lòng đăng nhập để vào hệ thống!",
                                                                                        Toast.LENGTH_SHORT).show();
                                                                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);

//                                                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                                                                startActivity(intent);
                                                                                finish();

                                                                            }
                                                                        });
                                                                    } else{

                                                                        Toast.makeText(SignUpActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                                                                    }
                                                                }
                                                            });

                                                        } else {

                                                            Toast.makeText(SignUpActivity.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                }
                                            });
                                }
                                else {

                                    Toast.makeText(SignUpActivity.this, "Email đã được sử dụng!", Toast.LENGTH_SHORT).show();

                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                            }
                        });


//                        Toast.makeText(SignUpActivity.this,"Đăng ký và thêm thông tin người dùng thành công! " +
//                                "Vui lòng đăng nhập để vào trang chủ.",Toast.LENGTH_SHORT).show();

//                        mAuth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//
//                                    mDatabase.child("Users").push().setValue(user1, new DatabaseReference.CompletionListener() {
//                                        @Override
//                                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
//                                                if(databaseError == null){
//
//                                                    Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
//                                                    startActivity(intent);
//                                                }
//                                                else{
//                                                    Toast.makeText(SignUpActivity.this,"Đăng ký thành công, thêm thông tin người dùng thấy bại!",Toast.LENGTH_SHORT).show();
//                                                }
//                                        }
//                                    });
//
//
//                                } else {
//                                    Toast.makeText(SignUpActivity.this,"Sign Up Unsuccessful!",Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });


                    }
                    else {
                        Toast.makeText(SignUpActivity.this,"PassWord does not match with Confirm PassWord",Toast.LENGTH_SHORT).show();
                    }

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_signIn = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent_signIn);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {

    EditText auth_emailEditText,auth_passwordEditText;
    Button auth_loginbtn;
    TextView auth_forgotPasswordText,auth_dontHaveanAcc;

    Button loginBtn;

    EditText email,password;

    TextView forgetPassword, register;


    ProgressDialog spinner;

    FirebaseAuth mAuth;

    @Override
    protected void onResume(){

        super.onResume();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner.setTitle("Login User");
                spinner.setMessage("Please Wait..");
                spinner.setCanceledOnTouchOutside(false);
                spinner.show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }
}
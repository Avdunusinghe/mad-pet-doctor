package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.AuthorizationManagement;
import com.example.model.Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class AuthActivity extends AppCompatActivity {


    Button loginBtn;

    EditText email,password;

    TextView forgetPassword, register;


    ProgressDialog spinner;

    FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        //session management for admin
        AuthorizationManagement authManagement = new AuthorizationManagement(AuthActivity.this);
        String isLoggedInUserName = authManagement.getSession();

        //if user already login
        if(mAuth.getCurrentUser() != null)
        {
            //navigate to home
           // navigateToActivityHome();
        }

        else if(!isLoggedInUserName.equals("E")){
            //user logged in navigate to Admin home
           // navigateToActivityAdmin();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        loginBtn = findViewById(R.id.auth_loginbtn);

        email = findViewById(R.id.auth_emailEditText);
        password = findViewById(R.id.auth_passwordEditText);

        forgetPassword = findViewById(R.id.auth_forgotPasswordText);
        register = findViewById(R.id.auth_dontHaveanAcc);

        spinner = new ProgressDialog((AuthActivity.this));
        mAuth = FirebaseAuth.getInstance();


    }

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

                String inputEmail = email.getText().toString();

                String inputPassword = password.getText().toString();

                if(validateMail()){

                    mAuth.signInWithEmailAndPassword(inputEmail,inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                if(inputEmail.equals("admin@gmail.com")){

                                    spinner.dismiss();

                                    Toast.makeText(getApplicationContext(),
                                            "Admin Login Success",
                                            Toast.LENGTH_SHORT)
                                            .show();

                                    routeAdminActivity();

                                }
                                else{

                                    Toast.makeText(getApplicationContext(),
                                            "Login Success",
                                            Toast.LENGTH_SHORT)
                                            .show();

                                    routeMainActivity();

                                    spinner.dismiss();

                                }


                            }
                            else{

                                Toast.makeText(getApplicationContext(),
                                        task.getException().getMessage(),
                                        Toast.LENGTH_SHORT)
                                        .show();

                                spinner.dismiss();
                            }

                        }
                    });
                }
               /* else{

                    if(inputEmail.equals("admin") && inputPassword.equals("admin")){

                        Admin admin = new Admin("Admin","admin");

                        AuthorizationManagement authManagement= new AuthorizationManagement(AuthActivity.this);

                        authManagement.saveSession(admin);

                        Toast.makeText(getApplicationContext(),
                                "Admin Login success",
                                Toast.LENGTH_SHORT)
                                .show();

                        spinner.dismiss();

                        routeAdminActivity();
                    }
                    else {

                        Toast.makeText(getApplicationContext(),
                                "Username or Password is Incorrect",
                                Toast.LENGTH_SHORT)
                                .show();

                        spinner.dismiss();
                    }
                }*/
            }
        });


        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                routeForgetPasswordActivity();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                routeCreateAccountActivity();
            }
        });
    }





    public boolean validateMail()
    {
        //check email
        String emailInput = email.getText().toString();
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if(Pattern.compile(emailPattern).matcher(emailInput).matches())
        {
            return true;
        }
        else {
            return false;
        }

    }

    public void routeMainActivity()
    {
        Intent intent = new Intent(AuthActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void routeAdminActivity()
    {
        Intent intent = new Intent(AuthActivity.this, AdminHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void routeCreateAccountActivity()
    {
        Intent intent = new Intent(AuthActivity.this, CreateAccount.class);
        startActivity(intent);
    }

    public void routeForgetPasswordActivity()
    {
        Intent intent = new Intent(AuthActivity.this, ResetPasswordEmailActivity.class);
        startActivity(intent);
    }

}
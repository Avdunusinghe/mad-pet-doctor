package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {

    EditText inputName, inputEmail, inputMobileNumber, inputPassword;
    Button  saveBtn;
    private FirebaseAuth mAuth;
    User user;
    ProgressDialog spinner;

    DatabaseReference db;

    private void clearControls(){
        inputName.setText("");
        inputEmail.setText("");
        inputMobileNumber.setText("");
        inputPassword.setText("");
    }
    @Override
    protected void onStart() {
        super.onStart();

        //if user already login
        if(mAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(CreateAccount.this, MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        inputName = findViewById(R.id.createAcc_nameEditText);
        inputEmail = findViewById(R.id.createAcc_emailEditText);
        inputMobileNumber = findViewById(R.id.createAcc_mobileNoEditText);
        inputPassword = findViewById(R.id.createAcc_passwordEditTest);

        spinner = new ProgressDialog(CreateAccount.this);
        mAuth = FirebaseAuth.getInstance();
        saveBtn = findViewById(R.id.createAccbtn);

    }

    @Override
    protected void onResume(){
        super.onResume();

        //create user account

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check fields are empty
                if(TextUtils.isEmpty((inputName.getText().toString()))){
                    Toast.makeText(getApplicationContext(),"Please enter Name", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(inputEmail.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please enter a Your Email",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(inputMobileNumber.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please enter a Mobile Number",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(inputPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please enter a Password", Toast.LENGTH_SHORT).show();
                }
                else{

                    boolean validateEmail = validateEmail();

                    boolean validateMobileNo = validateMobileNumber();
                    String name = inputName.getText().toString().trim();
                    String email = inputEmail.getText().toString().trim();
                    String phoneNumber = inputMobileNumber.getText().toString().trim();
                    String password = inputPassword.getText().toString().trim();
                        /*user.setEmail(txtEmail.getText().toString().trim());
                        user.setPhoneNumber(txtMobileNumber.getText().toString().trim());
                        user.setPassword(txtPassword.getText().toString().trim());*/




                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){

                                        Toast.makeText(CreateAccount.this,"Registration  Successfully",Toast.LENGTH_SHORT).show();
                                        //routeAuthActivity();
                                        Intent intent = new Intent(CreateAccount.this,AuthActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else{

                                        Toast.makeText(CreateAccount.this,"Error has been occured",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }


            }
        });

    }

    public boolean validateMobileNumber()
    {
        String phone = inputMobileNumber.getText().toString();

        if(phone.length() == 10)
        {
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter valid phone number", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    public boolean validateEmail()
    {
        String email = inputEmail.getText().toString();
        String EmalFormat = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if(Pattern.compile(EmalFormat).matcher(email).matches())
        {
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

}
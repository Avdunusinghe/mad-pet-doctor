package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity {

    EditText txtName, txtEmail, txtMobileNumber, txtPassword;
    Button  saveBtn;

    User user;

    DatabaseReference db;

    private void clearControls(){
        txtName.setText("");
        txtEmail.setText("");
        txtMobileNumber.setText("");
        txtPassword.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        txtName = findViewById(R.id.createAcc_nameEditText);
        txtEmail = findViewById(R.id.createAcc_emailEditText);
        txtMobileNumber = findViewById(R.id.createAcc_mobileNoEditText);
        txtPassword = findViewById(R.id.createAcc_passwordEditTest);

        saveBtn = findViewById(R.id.createAccbtn);

        user = new User();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveUser(v);
            }
        });



    }

    public void saveUser(View view){

        db = FirebaseDatabase.getInstance().getReference().child("User");

        try{
            if(TextUtils.isEmpty((txtName.getText().toString()))){
                Toast.makeText(getApplicationContext(),"Please enter Name", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(txtEmail.getText().toString())){
                Toast.makeText(getApplicationContext(),"Please enter a Your Email",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(txtMobileNumber.getText().toString())){
                Toast.makeText(getApplicationContext(),"Please enter a Mobile Number",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(txtPassword.getText().toString())){
                Toast.makeText(getApplicationContext(),"Please enter a Password",Toast.LENGTH_SHORT).show();
            }
            else{

                user.setName(txtName.getText().toString().trim());
                user.setEmail(txtEmail.getText().toString().trim());
                user.setPhoneNumber(txtMobileNumber.getText().toString().trim());
                user.setPassword(txtPassword.getText().toString().trim());

                db.push().setValue(user);

                Toast.makeText(getApplicationContext(),"Registration  Successfully",Toast.LENGTH_SHORT).show();
                clearControls();

            }

        } catch(Exception e){

            Toast.makeText(getApplicationContext(),"Details Invalid",Toast.LENGTH_SHORT).show();

        }
    }
}
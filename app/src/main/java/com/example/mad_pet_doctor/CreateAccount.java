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
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {

    EditText inputName, inputEmail, inputMobileNumber, inputPassword, inputConfirmPassword;

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

        inputConfirmPassword = findViewById(R.id.createAcc_ConfirmPasswordEditText);

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

                    boolean validatePassword = validatePassword();

                    String name = inputName.getText().toString().trim();

                    String email = inputEmail.getText().toString().trim();

                    String phoneNumber = inputMobileNumber.getText().toString().trim();

                    String password = inputPassword.getText().toString().trim();


                    spinner.setTitle("Register New User");

                    spinner.setMessage("Please Wait while Validate the Details");

                    spinner.setCanceledOnTouchOutside(false);

                    spinner.show();


                    mAuth.createUserWithEmailAndPassword(email, password)

                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){

                                        User user = new User();

                                        user.setName(name);

                                        user.setEmail(email);

                                        user.setPassword(phoneNumber);

                                        FirebaseDatabase.getInstance().getReference("User")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if(task.isSuccessful()){

                                                    spinner.dismiss();

                                                    Toast.makeText(CreateAccount.this,
                                                            "Registration  Successfully",
                                                            Toast.LENGTH_SHORT)
                                                            .show();

                                                    routeAuthActivity();

                                                }
                                                else{
                                                    spinner.dismiss();

                                                    Toast.makeText(CreateAccount.this,
                                                            "Error has been Occured",
                                                            Toast.LENGTH_SHORT)
                                                            .show();
                                                }

                                            }
                                        });

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

    public boolean validatePassword()
    {
        String password = inputPassword.getText().toString();
        String confirmPassword = inputConfirmPassword.getText().toString();

        if(password.equals(confirmPassword))
        {
            return true;
        }

        else if(password.length() < 6){
            Toast.makeText(getApplicationContext(), "Passwords length must grater than or equal to 6", Toast.LENGTH_SHORT).show();
            return false;
        }

        else {
            Toast.makeText(getApplicationContext(), "Passwords are Not matching", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void routeAuthActivity(){

        Intent intent = new Intent(CreateAccount.this, AuthActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

}
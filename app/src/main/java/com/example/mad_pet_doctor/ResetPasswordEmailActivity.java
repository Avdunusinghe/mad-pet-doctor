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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ResetPasswordEmailActivity extends AppCompatActivity {

    EditText inputEmail;

    Button sendEmailBtn;

    TextView login;

    ProgressDialog spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_email);

        inputEmail = findViewById(R.id.resetPassword_emailEditText);

        sendEmailBtn = findViewById(R.id.send_emailBtn);

        login = findViewById(R.id.resetPassword_authTextView);

        spinner = new ProgressDialog(ResetPasswordEmailActivity.this);
    }

    @Override
    protected void onResume(){
        super.onResume();

        sendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validateEmail = validateEmail();

                if(validateEmail){

                    spinner.setTitle("Forget Password");

                    spinner.setMessage("Please Wait while send the verification email to the user");

                    spinner.setCanceledOnTouchOutside(false);

                    spinner.show();

                    String userEmail = inputEmail.getText().toString();

                    FirebaseAuth.getInstance()
                            .sendPasswordResetEmail(userEmail)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){

                                        Toast.makeText(getApplicationContext(),
                                                "Successfully Send an verification email",
                                                Toast.LENGTH_SHORT).
                                                show();

                                        routeAuthActivity();

                                        spinner.dismiss();

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

                }else{

                    Toast.makeText(getApplicationContext(),
                            "Please enter valid email address",
                            Toast.LENGTH_SHORT)
                            .show();
                }




            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                routeAuthActivity();
            }
        });
    }

    public boolean validateEmail() {
        String emailInput = inputEmail.getText().toString();

        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if( emailInput.isEmpty()){

            Toast.makeText(getApplicationContext(),
                    "Please enter an email",
                    Toast.LENGTH_SHORT)
                    .show();

            return false;

        }
        else if(Pattern.compile(emailPattern).matcher(emailInput).matches()) {

            return true;
        }
        else {

            Toast.makeText(getApplicationContext(),
                    "Please enter valid email",
                    Toast.LENGTH_SHORT).
                    show();

            return false;
        }

    }

    public void routeAuthActivity()
    {
        Intent intent = new Intent(ResetPasswordEmailActivity.this, AuthActivity.class);
        startActivity(intent);
    }
}
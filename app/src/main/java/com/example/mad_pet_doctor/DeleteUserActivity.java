package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DeleteUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        final EditText email = findViewById(R.id.deleteUserEmailEditText);
        final EditText password = findViewById(R.id.deleteUserPasswordEditText);
        Button submit = findViewById(R.id.deleteUserSubmitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteUser(email.getText().toString(), password.getText().toString());

            }
        });

    }


    private void deleteUser(String email,String password){

        final FirebaseUser loggedInUser = FirebaseAuth.getInstance().getCurrentUser();

        AuthCredential credential = EmailAuthProvider.getCredential(email, password);

        if(loggedInUser != null){

            loggedInUser.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            loggedInUser.delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){

                                                Toast.makeText(getApplicationContext(),
                                                        "Succefully Delete Account",
                                                        Toast.LENGTH_SHORT)
                                                        .show();

                                                Intent intent = new Intent
                                                        (DeleteUserActivity.this, AuthActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                        }
                    });
        }
    }
}
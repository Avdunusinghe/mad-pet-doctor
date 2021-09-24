package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CilentProfileViewActivity extends AppCompatActivity {

    TextView name,email,mobileNumber;
    Button updateProfileButton, deleteUserAccount;
    FirebaseAuth mAuth;
    String userId;
    boolean emailVerified;

    /*nameViewText  emailTextView
    mobileTextView viewProfileUpdateButton*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cilent_profile_view);

        updateProfileButton = findViewById(R.id.viewProfileUpdateButton);

        name = findViewById(R.id.nameViewText);
        email = findViewById(R.id.emailTextView);
        mobileNumber = findViewById(R.id.mobileTextView);
        deleteUserAccount = findViewById(R.id.userDeleteButton);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        if(user != null)
        {
            userId = user.getUid();
        }

        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference().child("User").child(userId);

        dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChildren()){

                    name.setText(snapshot.child("name").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                    mobileNumber.setText(snapshot.child("phoneNumber").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        deleteUserAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteUserAccount();


            }
        });


        mAuth = FirebaseAuth.getInstance();

    }
    @Override
    protected void onResume(){
        super.onResume();


        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.sendEmailVerification();

                Intent intent = new Intent(CilentProfileViewActivity.this,ClientProfile.class);

                startActivity(intent);
            }
        });




    }


    private  void deleteUserAccount(){

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){


                    Toast.makeText(getApplicationContext(),
                            "Succefully Delete Account",
                            Toast.LENGTH_SHORT)
                            .show();

                    Intent intent = new Intent(CilentProfileViewActivity.this, AuthActivity.class);
                    startActivity(intent);

                }
            }
        });
    }


}
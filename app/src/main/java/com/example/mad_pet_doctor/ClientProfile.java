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
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClientProfile extends AppCompatActivity {

    String userId;

    EditText name,phoneNumber;

    TextView email;

    Button updateUserBtn;

    DatabaseReference dbRef;

    ProgressDialog spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        //initializing
        name = findViewById(R.id.editProfile_nameEditText);

        email = findViewById(R.id.editProfile_emailEditText);

        phoneNumber = findViewById(R.id.editProfile_movileNumberEditText);

        updateUserBtn = findViewById(R.id.clientProfileSavebtn);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        getUserDetails();

    }

    @Override
    protected void onResume() {
        super.onResume();

        updateUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update userdetails
                updateUserProfile();
            }
        });





    }

    public void getUserDetails(){

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("User").child((userId));

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChildren()){

                    name.setText(snapshot.child("name").getValue().toString());

                    email.setText(snapshot.child("email").getValue().toString());

                    phoneNumber.setText(snapshot.child("phoneNumber").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateUserProfile(){

        if(validateMobileNumber()){

            dbRef = FirebaseDatabase.getInstance().getReference().child("User").child(userId);

            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    dbRef.child("name").setValue(name.getText().toString().trim());

                    dbRef.child("phoneNumber").setValue(name.getText().toString().trim());

                    spinner.setTitle("Update Profile");

                    spinner.setMessage("Please Wait");

                    spinner.setCanceledOnTouchOutside(false);

                    spinner.show();

                    Toast.makeText(getApplicationContext(),
                            "Succefully updated detils",
                            Toast.LENGTH_SHORT)
                            .show();
                    Intent intent = new Intent(ClientProfile.this, ClientProfile.class);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }


    public boolean validateMobileNumber()
    {
        String userInputPhoneNumber = phoneNumber.getText().toString();

        if(userInputPhoneNumber.length() == 10)
        {
            return true;
        }
        else {

            Toast.makeText(getApplicationContext(),
                    "Please enter valid phone number",
                    Toast.LENGTH_SHORT)
                    .show();

            return false;
        }

    }


}
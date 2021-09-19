package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.ContactModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactUsActivity extends AppCompatActivity {


    private ImageView Hoslogo, AnimalPic;
    private TextView ContHead, ContEmail, ContactNo, Name, Email ,Message;
    private EditText NameEdt, EmaiEdt, MessageEdt;
    private Button ContSubmitBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String ContactId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus);
        Hoslogo = findViewById(R.id.hos_logo1);
        AnimalPic = findViewById(R.id.cont_photo);
        ContHead = findViewById(R.id.cont_heading);
        ContEmail = findViewById(R.id.cont_email);
        ContactNo = findViewById(R.id.cont_phone);
        Name = findViewById(R.id.cont_label1);
        Email= findViewById(R.id.cont_label2);
        Message = findViewById(R.id.cont_label3);
        NameEdt = findViewById(R.id.cont_input_1);
        EmaiEdt = findViewById(R.id.cont_input_2);
        MessageEdt = findViewById(R.id.icont_input_3);
        ContSubmitBtn = findViewById(R.id.bton3);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Contacts");

        ContSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = NameEdt.getText().toString();
                String Email = EmaiEdt.getText().toString();
                String Message = MessageEdt.getText().toString();
                ContactId = Name;
                ContactModal contactModal = new ContactModal(Name, Email,Message, ContactId);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        databaseReference.child(ContactId).setValue(contactModal);
                        Toast.makeText(ContactUsActivity.this, "Message is  Added..", Toast.LENGTH_SHORT ).show();
                        startActivity(new Intent(ContactUsActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {
                        Toast.makeText(ContactUsActivity.this, "Error occurs to add message" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
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

import com.example.model.BookingModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PetBookFormActivity extends AppCompatActivity {

    private ImageView hosLogo , petpic1, petpic2;
    private TextView petHeading , AnimalId, AnimalName, Ownername, OwnerPhoneNo, Address;
    private EditText AnimalIdEdt, AnimalNameEdt, OwnerNameEdt, OwnerPhoneEdt, AddressEdt;
    private Button NextBtn;
    private String BookingId;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petbookform);
        hosLogo = findViewById(R.id.hos_logo1);
        petpic1 = findViewById(R.id.pet_pic1);
        petpic2 = findViewById(R.id.pet_pic2);
        petHeading = findViewById(R.id.appo_head);
        AnimalId = findViewById(R.id.pet_label1);
        AnimalName = findViewById(R.id.pet_label2);
        Ownername = findViewById(R.id.pet_label3);
        OwnerPhoneNo = findViewById(R.id.pet_label4);
        Address = findViewById(R.id.pet_label5);
        AnimalIdEdt = findViewById(R.id.pet_input1);
        AnimalNameEdt = findViewById(R.id.pet_input2);
        OwnerNameEdt = findViewById(R.id.pet_input3);
        OwnerPhoneEdt = findViewById(R.id.pet_input4);
        AddressEdt = findViewById(R.id.pet_input5);
        NextBtn = findViewById(R.id.bton17);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Boookings");

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String AnimalId = AnimalIdEdt.getText().toString();
                String AnimalName = AnimalNameEdt.getText().toString();
                String OwnerName = OwnerNameEdt.getText().toString();
                String OwnerPhone = OwnerPhoneEdt.getText().toString();
                String Address = AddressEdt.getText().toString();
                BookingId = AnimalName;
                BookingModal bookingModal = new BookingModal(AnimalId, AnimalName,OwnerName, OwnerPhone, Address, BookingId);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        databaseReference.child(BookingId).setValue(bookingModal);
                        Toast.makeText(PetBookFormActivity.this, "Booking information is  Added..", Toast.LENGTH_SHORT ).show();
                        startActivity(new Intent(PetBookFormActivity.this, AppointmentDetailsActivity.class));

                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {
                        Toast.makeText(PetBookFormActivity.this, "Error occurs to booking" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
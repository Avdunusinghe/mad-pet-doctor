package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.BookingModal;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class BookingConfirmationActivity extends AppCompatActivity {

    private ImageView HosLogo;
    private Chip HomeChip1,HomeChip2;
    private TextView Heading, Slogan, Address,Country, Phone , Mail, ConfirmHeading,  RefNo , RefOutput, AnimalName , PetNameOutput, OwnerName , OwnerNameOutput,
    AppDate, DateOutput,  AppTime, TimeOutput, TotalFee, FeeOutput, MessageLine;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String bookingId;
    private BookingModal bookingModal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookingconfirmation);

        HosLogo = findViewById(R.id.hos_logo1);
        Heading = findViewById(R.id.dailyrep_1);
        Slogan = findViewById(R.id.daailyrep_2);
        HomeChip1 = findViewById(R.id.chip5);
        HomeChip2 = findViewById(R.id.chip9);
        Address = findViewById(R.id.confirm_3);
        Country = findViewById(R.id.confirm_4);
        Phone = findViewById(R.id.confirm_5);
        Mail = findViewById(R.id.confirm_6);
        ConfirmHeading = findViewById(R.id.confirm_7);
        RefNo = findViewById(R.id.tab1);
        RefOutput = findViewById(R.id.tab2);
        AnimalName = findViewById(R.id.tab5);
        PetNameOutput = findViewById(R.id.tab6);
        OwnerName = findViewById(R.id.tab7);
        OwnerNameOutput = findViewById(R.id.tab8);
        AppDate = findViewById(R.id.tab9);
        DateOutput = findViewById(R.id.tab10);
        AppTime = findViewById(R.id.tab11);
        TimeOutput = findViewById(R.id.tab12);
        TotalFee = findViewById(R.id.tab13);
        FeeOutput = findViewById(R.id.tab14);
        MessageLine = findViewById(R.id.confirm_8);
        firebaseDatabase= FirebaseDatabase.getInstance();
        bookingModal = getIntent().getParcelableExtra("Booking");

        HomeChip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingConfirmationActivity.this, ActivityMainSideBar.class));
            }
        });
        HomeChip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingConfirmationActivity.this, ActivityMainSideBar.class));
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Boookings");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot datasnapshot : snapshot.getChildren()) {

                    BookingModal bookingModal;
                    bookingModal = datasnapshot.getValue(BookingModal.class);
                    assert bookingModal != null;
                    String petId = bookingModal.getBookingId();
                    String petname = bookingModal.getAnimalName();
                    String ownername = bookingModal.getOwnerName();
                    String appdate = bookingModal.getAppointmentDate();
                    String apptime = bookingModal.getAppointmentTime();

                    RefOutput.setText(petId);
                    PetNameOutput.setText(petname);
                    OwnerNameOutput.setText(ownername);
                    DateOutput.setText(appdate);
                    TimeOutput.setText(apptime);
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }

        });

    }}

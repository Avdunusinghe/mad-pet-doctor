package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.BookingModal;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingConfirmationActivity extends AppCompatActivity {

    private ImageView Hoslogo;
    private TextView Heading, Slogan, Address,Country, Phone , Mail, ConfirmHeading,  RefNo , RefOutput, AnimalName , PetNameOutput, OwnerName , OwnerNameOutput,
    AppDate, DateOutput,  AppTime, TimeOutput, TotalFee, FeeOutput, MessageLine;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String BookingId;
    private BookingModal bookingModal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookingconfirmation);

        Hoslogo.findViewById(R.id.hos_logo1);
        Heading.findViewById(R.id.dailyrep_1);
        Slogan.findViewById(R.id.daailyrep_2);
        Address.findViewById(R.id.confirm_3);
        Country.findViewById(R.id.confirm_4);
        Phone.findViewById(R.id.confirm_5);
        Mail.findViewById(R.id.confirm_6);
        ConfirmHeading.findViewById(R.id.confirm_7);
        RefNo.findViewById(R.id.tab1);
        RefOutput.findViewById(R.id.tab2);
        AnimalName.findViewById(R.id.tab5);
        PetNameOutput.findViewById(R.id.tab6);
        OwnerName.findViewById(R.id.tab7);
        OwnerNameOutput.findViewById(R.id.tab8);
        AppDate.findViewById(R.id.tab9);
        DateOutput.findViewById(R.id.tab10);
        AppTime.findViewById(R.id.tab11);
        TimeOutput.findViewById(R.id.tab12);
        TotalFee.findViewById(R.id.tab13);
        FeeOutput.findViewById(R.id.tab14);
        MessageLine.findViewById(R.id.confirm_8);
        firebaseDatabase= FirebaseDatabase.getInstance();

        bookingModal = getIntent().getParcelableExtra("Bookings");
        if(bookingModal != null){
            PetNameOutput.setText(bookingModal.getAnimalName());
            OwnerNameOutput.setText(bookingModal.getOwnerName());
            DateOutput.setText(bookingModal.getAppointmentDate());
            TimeOutput.setText(bookingModal.getAppointmentTime());
            BookingId = bookingModal.getBookingId();
        }

        databaseReference = firebaseDatabase.getReference("Boookings").child(BookingId);

    }
}
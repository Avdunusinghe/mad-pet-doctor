package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.BookingModal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppointmentDetailsActivity extends AppCompatActivity {

    private ImageView Hoslogo ;
    private TextView input1 , input2,  input3, input4 , input5, input6 , input7 , AnimalName, OwnerName , AppDate , AppTime , PayDetails , AppFee , ServiceCharge , TotalFee ;
    private Button confirmBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String BookingId;
    private BookingModal bookingModal;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointmentdetails);
        Hoslogo = findViewById(R.id.hos_logo1);
        AnimalName = findViewById(R.id.app_label1);
        OwnerName = findViewById(R.id.app_label2);
        AppDate = findViewById(R.id.app_label3);
        AppTime = findViewById(R.id.app_label4);
        PayDetails = findViewById(R.id.app_label5);
        AppFee = findViewById(R.id.app_label6);
        ServiceCharge = findViewById(R.id.app_label7);
        TotalFee  = findViewById(R.id.app_label8);
        input1 =findViewById(R.id.input_label1);
        input2 = findViewById(R.id.input_label2);
        input3 = findViewById(R.id.input_label3);
        input4 = findViewById(R.id.input_label5);
        input5 = findViewById(R.id.input_label6);
        input6 = findViewById(R.id.input_label7);
        input7 = findViewById(R.id.input_label);
        confirmBtn = findViewById(R.id.bton17);
        firebaseDatabase = FirebaseDatabase.getInstance();


        //retrieve

        bookingModal = getIntent().getParcelableExtra("Bookings");
        if(bookingModal != null){
            input1.setText(bookingModal.getAnimalName());
            input2.setText(bookingModal.getOwnerName());
            input3.setText(bookingModal.getAppointmentDate());
            input4.setText(bookingModal.getAppointmentTime());
            BookingId = bookingModal.getBookingId();
        }

        databaseReference = firebaseDatabase.getReference("Boookings").child(BookingId);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppointmentDetailsActivity.this, CardPayActivity.class);
                startActivity(i);
            }
        });


    }
}
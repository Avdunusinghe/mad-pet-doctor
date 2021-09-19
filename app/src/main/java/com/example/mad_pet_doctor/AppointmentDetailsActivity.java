package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class AppointmentDetailsActivity extends AppCompatActivity {

    private TextView Hoslogo , AnimalId, OwnerName , AppDate , AppTime , PayDetails , AppFee , ServiceCharge , TotalFee;
    private TextView input1 , input2,  input3, input4 , input5, input6 , input7  ;
    private Button confirmBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointmentdetails);
        Hoslogo = findViewById(R.id.app_head);
        AnimalId = findViewById(R.id.app_label1);
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
        mAuth = FirebaseAuth.getInstance();

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intend i;
               // i = new Intend ( AppointmentDetailsActivity.this, CardPayActivity.class );
               // startActivity(i);
            }
        });


    }
}
package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Doctors extends AppCompatActivity {

    private Button profileBtn, appointmentBtn, chatBtn, healthTipsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctors);
        profileBtn = findViewById(R.id.button27);
        appointmentBtn = findViewById(R.id.button28);
        chatBtn = findViewById(R.id.button29);
        healthTipsBtn = findViewById(R.id.button30);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend1 = new Intent(Doctors.this, DoctorEditList.class);
                startActivity(intend1);
            }
        });

        appointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend1 = new Intent(Doctors.this, MyAppointmentReport.class);
                startActivity(intend1);
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend1 = new Intent(Doctors.this, ChatWithDoc.class);
                startActivity(intend1);
            }
        });

        healthTipsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend1 = new Intent(Doctors.this, HealthTips.class);
                startActivity(intend1);
            }
        });
    }
}
package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyAppointmentReport extends AppCompatActivity {

    private Button appointmentBtn, reportBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myappointment_report);
        appointmentBtn = findViewById(R.id.button10);
        reportBtn = findViewById(R.id.button31);

        appointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend1 = new Intent(MyAppointmentReport.this, MyAppointmnt.class);
                startActivity(intend1);
            }
        });

        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend1 = new Intent(MyAppointmentReport.this, DailyReport.class);
                startActivity(intend1);
            }
        });
    }
}
package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class MedicalProfActivity extends AppCompatActivity {

    private ImageView Hoslogo ;
    private ImageButton Schedulepic, DocRegpic, Reportpic;
    private Button ScheduleBtn, DocRegistrationBtn , ReportBtn;
    private TextView medicalprofHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_prof);
        Hoslogo = findViewById(R.id.hos_logo1);
        Schedulepic=findViewById(R.id.imageButton5);
        DocRegpic = findViewById(R.id.imageButton6);
        Reportpic = findViewById(R.id.imageButton7);
        ScheduleBtn = findViewById(R.id.prof_btn4);
        DocRegistrationBtn= findViewById(R.id.prof_btn5);
        ReportBtn = findViewById(R.id.prof_btn3);
        medicalprofHeading = findViewById(R.id.med_heading);

        //enter schedule page button implementation
        ScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MedicalProfActivity.this, EnterScheduleActivity.class);
                startActivity(i);
            }
        });

        //Doctor Registration page button implementation
        DocRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MedicalProfActivity.this,MedCenterDailyReportActivity.class);
                startActivity(i);
            }
        });

        //Reports page button implementation
        ReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MedicalProfActivity.this,  DailyReportsActivity.class);
                startActivity(i);
            }
        });

        //enter schedule page image button implementation
         Schedulepic.setOnClickListener(new View.OnClickListener() {
             public void onClick(View view) {
                 Intent intend1 = new Intent(MedicalProfActivity.this, EnterScheduleActivity.class);
                 startActivity(intend1);
             }
         });

        //Doctor Registration page image button implementation
         DocRegpic.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intend2 = new Intent(MedicalProfActivity.this, DoctoreRegistration.class);
                 startActivity(intend2);
             }
         });
        //Reports page image button implementation
         Reportpic.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intend3 = new Intent(MedicalProfActivity.this,  DailyReportsActivity.class);
                 startActivity(intend3);
             }
         });
    }
    }

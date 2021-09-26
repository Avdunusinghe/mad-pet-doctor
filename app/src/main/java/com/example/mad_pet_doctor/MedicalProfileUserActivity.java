package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

public class MedicalProfileUserActivity extends AppCompatActivity {

    private ImageView Hoslogo;
    private TextView UserMedProfheading;
    private Button SearchBtn, AppointmentBtn;
    private ImageButton SearchPic, Appointmentpic;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_prof_user);
        Hoslogo = findViewById(R.id.hos_logo1);
        UserMedProfheading = findViewById(R.id.med_heading);
        SearchBtn = findViewById(R.id.prof_btn4);
        AppointmentBtn = findViewById(R.id.prof_btn5);
        SearchPic = findViewById(R.id.imageButton2);
        Appointmentpic = findViewById(R.id.imageButton3);

        //Search page button implementation
        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intendone = new Intent(MedicalProfileUserActivity.this, SearchView.class);
                startActivity(intendone);
            }
        });

        //Appointments page button implementation
        AppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intendtwo = new Intent(MedicalProfileUserActivity.this,  AppointmentsActivity.class);
                startActivity(intendtwo);
            }
        });
        //Search page  image button implementation
        SearchPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intendone = new Intent(MedicalProfileUserActivity.this, SearchView.class);
                startActivity(intendone);
            }
        });

        //Appointments page image button implementation
        Appointmentpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intendtwo = new Intent(MedicalProfileUserActivity.this,  AppointmentsActivity.class);
                startActivity(intendtwo);
            }
        });


    }
}
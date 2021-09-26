package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminHome extends AppCompatActivity {
//medicalCenterIcon, admin_doctorIcon,admin_cardUserIcon

    ImageView  medicalCenters,users,doctors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        medicalCenters = findViewById(R.id.medicalCenterIcon);
        users = findViewById(R.id.admin_cardUserIcon);
        doctors = findViewById(R.id.admin_doctorIcon);

        medicalCenters.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, MedicalCenterList.class));
                overridePendingTransition(0,0);
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, UserListActivity.class));
                overridePendingTransition(0,0);
            }
        });

        doctors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, DoctorListActivity.class));
                overridePendingTransition(0,0);
            }
        });


    }


}




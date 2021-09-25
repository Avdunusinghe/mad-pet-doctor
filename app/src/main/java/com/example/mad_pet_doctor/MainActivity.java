package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.maps.MapView;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private View View_bar;
    private Chip HomeChip1, HomeChip2;
    private ImageView HomeImage, UserIcon;
    private TextView UserName;
    private TextView SubHeading1,SubHeading2;
    private ImageView Image1, Image2;
    private TextView DocName1, DocName2, MedicalCenter1, MedicalCenter2;
    private ImageButton LocationButton, HospitalButton;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        View_bar = findViewById(R.id.view);
        HomeChip1 = findViewById(R.id.chip9);
        HomeChip2 = findViewById(R.id.chip13);
        HomeImage = findViewById(R.id.imageView55);
        UserIcon = findViewById(R.id.usericon);
        UserName = findViewById(R.id.usernameid);

        SubHeading1 = findViewById(R.id.subheading1);
        SubHeading2 = findViewById(R.id.subheading2);
        Image1 = findViewById(R.id.HomeImage1);
        Image2 = findViewById(R.id.HomeImage2);
        DocName1 = findViewById(R.id.docname1);
        DocName2 = findViewById(R.id.docname2);
        MedicalCenter1 = findViewById(R.id.medicalcenter1);
        MedicalCenter2 = findViewById(R.id.medicalcenter2);
        LocationButton = findViewById(R.id.locationButton);
        HospitalButton = findViewById(R.id.hospitalButton);

        HomeChip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityMainSideBar.class));
            }
        });
        HomeChip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityMainSideBar.class));
            }
        });
        LocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Doctors.class));
            }
        });
        HospitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MedicalProfActivity.class));
            }
        });

    }

    public void SAVE(View view) {
    }
}
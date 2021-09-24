package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.maps.MapView;
import com.google.android.material.chip.Chip;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityMainSideBar extends AppCompatActivity {

    private Toolbar HPToolBar;
    private Chip HomeChip3,HomeChip4;
    private ImageView HomeImage2, UserIcon2;
    private TextView UserName2;
    //private SearchView SearchBar;
    private NavigationView NavigationViewBar;
    private ImageView NavigationImage;
    private Button NavButton1,NavButton2,NavButton3,NavButton4,NavButton5,NavButton6,NavButton7,NavButton8,NavButton9;
    private MapView SideBarMapView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sidebar);

        //HPToolBar = findViewById(R.id.toolbar2);
        HomeChip3 = findViewById(R.id.chip9);
        HomeChip4 = findViewById(R.id.chip13);
        HomeImage2 = findViewById(R.id.HomeHeadingImage);
        UserIcon2 = findViewById(R.id.usericon);
        UserName2 = findViewById(R.id.usernameid);
        NavigationViewBar = findViewById(R.id.NavigationBar);
        NavigationImage = findViewById(R.id.navigationimage);
        NavButton1 = findViewById(R.id.navButton1);
        NavButton2 = findViewById(R.id.navButton2);
        NavButton3 = findViewById(R.id.navButton3);
        NavButton4 = findViewById(R.id.navButton4);
        NavButton5 = findViewById(R.id.navButton5);
        NavButton6 = findViewById(R.id.navButton6);
        NavButton7 = findViewById(R.id.navButton7);
        NavButton8 = findViewById(R.id.navButton8);
        NavButton9 = findViewById(R.id.navButton9);


        //Navigation Button Implementations
        NavButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMainSideBar.this, Doctors.class));
            }
        });

        NavButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMainSideBar.this, PetCardDetails.class));
            }
        });

        NavButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMainSideBar.this, MedicalCenterList.class));
            }
        });

        NavButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMainSideBar.this, VaccineReport.class));
            }
        });

        NavButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMainSideBar.this, DetailsOfPets.class));
            }
        });

        /*
        NavButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMainSideBar.this, Doctors.class));
            }
        });*/

        NavButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMainSideBar.this, BlogMenu.class));
            }
        });

        NavButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMainSideBar.this, ContactUsActivity.class));
            }
        });

        NavButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityMainSideBar.this, AuthActivity.class));
            }
        });
    }
}
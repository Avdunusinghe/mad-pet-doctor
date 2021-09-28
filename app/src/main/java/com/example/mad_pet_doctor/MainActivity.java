package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.maps.MapView;
import com.google.android.material.chip.Chip;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    private Toolbar HPToolBar;
    private Chip HomeChip3,HomeChip4;
    private ImageView HomeImage2, UserIcon2;
    private TextView UserName2;
    private NavigationView NavigationViewBar;
    private ImageView NavigationImage;
    private Button NavButton1,NavButton2,NavButton3,NavButton4,NavButton5,NavButton6,NavButton7,NavButton8,NavButton9;
    private MapView SideBarMapView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.usericon);



        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CilentProfileViewActivity.class));
                overridePendingTransition(0,0);
            }
        });
    }
}
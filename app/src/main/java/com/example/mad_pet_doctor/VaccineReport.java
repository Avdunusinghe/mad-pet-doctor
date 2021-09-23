package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.list_view.UserList;
import com.example.list_view.VaccineReportList;
import com.example.model.User;
import com.example.model.VaccineDetailsModal;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VaccineReport extends AppCompatActivity {

    private TextView VRHeading;
    private ImageView VRImage;
    private ImageButton VRAddButton;
    private Button DoneButton;
    private String VaccineReportID;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private VaccineDetailsModal vaccineDetailsModal;
    private Chip HomeChip1, HomeChip2;

    ListView vaccineReportListView;
    List<VaccineDetailsModal> vaccineReportList;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccine_report_list);

        Intent intent = getIntent();
        vaccineReportList = new ArrayList<>();
        db = FirebaseDatabase.getInstance().getReference("VaccineDetails");
        vaccineReportListView = (ListView)findViewById(R.id.VRlistview);

        //FirebaseDatabase Get Instance
        firebaseDatabase = FirebaseDatabase.getInstance();

        //Heading, Image, ImageButton
        VRHeading =  findViewById(R.id.vaccineReportHeading);
        VRImage = findViewById(R.id.vaccineReportImage);
        VRAddButton = findViewById(R.id.VaccineReportAddButton);
        DoneButton = findViewById(R.id.savebutton);
        HomeChip1 = findViewById(R.id.chip12);
        HomeChip2 = findViewById(R.id.chip15);

        HomeChip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccineReport.this, ActivityMainSideBar.class));
            }
        });
        HomeChip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccineReport.this, ActivityMainSideBar.class));
            }
        });

        VRAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccineReport.this, VaccineDetails.class));
            }
        });

        DoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccineReport.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                vaccineReportList.clear();

                for(DataSnapshot VaccineDetailDataSnapshot:snapshot.getChildren()){

                    VaccineDetailsModal vaccineDetailsModals = VaccineDetailDataSnapshot.getValue(VaccineDetailsModal.class);

                    vaccineReportList.add(vaccineDetailsModals);
                }

                VaccineReportList vaccineReportAdapter = new VaccineReportList(VaccineReport.this,vaccineReportList);
                vaccineReportListView.setAdapter(vaccineReportAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
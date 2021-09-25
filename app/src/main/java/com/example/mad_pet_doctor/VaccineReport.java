package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.VaccineDetailsModal;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

public class VaccineReport extends AppCompatActivity {

    private TextView VRHeading;
    private ImageView VRImage;
    private ImageButton VRAddButton;
    private TextView vrName,vrDate,vrNoofVaccines, vrPrice,vrTotalPrice;
    private Button DoneButton;
    private String VRID;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Chip HomeChip1, HomeChip2;

    private RecyclerView vrRecycleView;
    private ArrayList<VaccineDetailsModal> vaccineDetailsModalArrayList;
    private VaccineReportAdapter vaccineReportAdapter;
    private VaccineDetailsModal vaccineDetailsModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccine_report_list);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("VaccineDetails");

        VRHeading =  findViewById(R.id.vaccineReportHeading);
        VRImage = findViewById(R.id.vaccineReportImage);
        VRAddButton = findViewById(R.id.VaccineReportAddButton);
        vrName = findViewById(R.id.VR_vaccinename);
        vrDate =findViewById(R.id.VR_vaccinedate);
        vrNoofVaccines =findViewById(R.id.VR_noofvaccines);
        vrPrice =findViewById(R.id.VR_price);
        vrTotalPrice =findViewById(R.id.VR_totalprice);
        vrRecycleView = findViewById(R.id.VR_RecycleViewId);

        vaccineDetailsModalArrayList = new ArrayList<>();
        vaccineReportAdapter = new VaccineReportAdapter(vaccineDetailsModalArrayList,this);
        vrRecycleView.setLayoutManager(new LinearLayoutManager(this));
        vrRecycleView.setAdapter(vaccineReportAdapter);
        getAllVaccineReports();

        vaccineDetailsModal = getIntent().getParcelableExtra("VaccineDetails");
        if (vaccineDetailsModal != null){
            vrName.setText(vaccineDetailsModal.getVaccine_Name_Edt());
            vrDate.setText(vaccineDetailsModal.getVaccine_Date_Edt());
            vrNoofVaccines.setText(vaccineDetailsModal.getNo_of_Vaccines());
            vrPrice.setText(vaccineDetailsModal.getVaccine_Price_Edt());
            vrTotalPrice.setText(vaccineDetailsModal.getVaccine_Total_price());
            VRID = vaccineDetailsModal.getVaccine_Name_Edt();
        }

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

    private void getAllVaccineReports(){
        vaccineDetailsModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot,
                                     @Nullable String previousChildName) {
                vaccineDetailsModalArrayList.add(snapshot.getValue(VaccineDetailsModal.class));
                vaccineReportAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot,
                                       @Nullable String previousChildName) {
                vaccineReportAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                vaccineReportAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot,
                                     @Nullable String previousChildName) {
                vaccineReportAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
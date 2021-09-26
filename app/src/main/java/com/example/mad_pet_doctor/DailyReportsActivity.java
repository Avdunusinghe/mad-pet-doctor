package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.CardPaymentsModal;
import com.example.model.DailyReportModal;
import com.example.model.ScheduleModal;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class DailyReportsActivity extends AppCompatActivity {

    private ImageView Hoslogo, ReportsImage;
    private TextView Headings, Date;
    private Chip HomeChip1,HomeChip2;
    private FloatingActionButton addBtn;
    public RecyclerView dailyreportsapp;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DailyReportModal dailyReportModal;
    private ArrayList<DailyReportModal> dailyReportModalArrayList;
    private DailyReportsAdapter dailyReportsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailyreports);

        Hoslogo=  findViewById(R.id.hos_logo1);
        ReportsImage= findViewById(R.id.rep_name1);
        Headings = findViewById(R.id.dailyrep_1);
        Date= findViewById(R.id.daailyrep_2);
        HomeChip1 = findViewById(R.id.chip5);
        HomeChip2 = findViewById(R.id.chip9);
        addBtn = findViewById(R.id.caldaily);
        dailyreportsapp = findViewById(R.id.idtableSchedule);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("DailyReports");
        dailyReportModalArrayList= new ArrayList<>();
        dailyReportsAdapter = new DailyReportsAdapter(dailyReportModalArrayList,this);
        dailyreportsapp.setLayoutManager(new LinearLayoutManager(this));
        dailyreportsapp.setAdapter(dailyReportsAdapter);
        getAllschedules();
        //add button
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DailyReportsActivity.this, MedCenterDailyReportActivity.class));
            }
        });

        HomeChip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DailyReportsActivity.this, ActivityMainSideBar.class));
            }
        });
        HomeChip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DailyReportsActivity.this, ActivityMainSideBar.class));
            }
        });

    }

    private void getAllschedules() {
        dailyReportModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                dailyReportModalArrayList.add(snapshot.getValue(DailyReportModal.class));
                dailyReportsAdapter.notifyDataSetChanged();;
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable  String previousChildName) {
                dailyReportsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {
                dailyReportsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                dailyReportsAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    }






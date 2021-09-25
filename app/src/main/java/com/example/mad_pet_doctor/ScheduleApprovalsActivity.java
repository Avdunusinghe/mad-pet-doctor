package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.ScheduleModal;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleApprovalsActivity extends AppCompatActivity {

    private ImageView HosLogo;
    private TextView Heading, DocNameEdt, DateEdt, TimeEdt, Column4, Column5;
    private FloatingActionButton AddBtn;
    private Chip HomeChip1,HomeChip2;
    private RecyclerView table;
    private ImageButton UpdateBtn, DeleteBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String ScheduleId;
    private ScheduleModal scheduleModal;
    private ArrayList<ScheduleModal> scheduleModalArrayList;
    private TableAdapter tableAdapter;


    @Override
   protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduleapprovals);

        //declare
        table = findViewById(R.id.idtableSchedule);
        HosLogo=findViewById(R.id.hos_logo1);
        Heading=findViewById(R.id.dailyrep_1);
        HomeChip1 = findViewById(R.id.chip5);
        HomeChip2 = findViewById(R.id.chip9);
        DocNameEdt=findViewById(R.id.apptable2);
        DateEdt=findViewById(R.id.apptable3);
        TimeEdt=findViewById(R.id.apptable4);
        //Column4=findViewById(R.id.apptable5);
        Column5=findViewById(R.id.apptable6);
        AddBtn=findViewById(R.id.addbton);
        //UpdateBtn=findViewById(R.id.icondell4);
        DeleteBtn=findViewById(R.id.icondel14);

        //dbconnection
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("Schedules");

        //arraylist
        scheduleModalArrayList = new ArrayList<>();
        tableAdapter = new TableAdapter(scheduleModalArrayList,this,  this::onCourseClick);
        table.setLayoutManager(new LinearLayoutManager(this));

        //table
        table.setAdapter(tableAdapter);
        getAllschedules();
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScheduleApprovalsActivity.this, EnterScheduleActivity.class));
            }
        });


        HomeChip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScheduleApprovalsActivity.this, ActivityMainSideBar.class));
            }
        });
        HomeChip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScheduleApprovalsActivity.this, ActivityMainSideBar.class));
            }
        });

    }

    private void getAllschedules() {
        scheduleModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable String previousChildName) {
                scheduleModalArrayList.add(snapshot.getValue(ScheduleModal.class));
                tableAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable  String previousChildName) {
                tableAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {
                tableAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                tableAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }


    public void onCourseClick(int position){
        startActivity(new Intent(ScheduleApprovalsActivity.this , UpdateScheduleActivity.class));


    }



    }







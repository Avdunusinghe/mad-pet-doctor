package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.model.ScheduleModal;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAppointmnt extends AppCompatActivity {

    private TextView Heading, PatientNameEdt, DateEdt, TimeEdt, Column4, Column5;
    private RecyclerView table;
    private ImageButton ConfirmBtn, IgnoreBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String ScheduleId;
    private ScheduleModal scheduleModal;
    private ArrayList<ScheduleModal> scheduleModalArrayList;
    private TableAdapterAppointment tableAdapterAppointment;
    ProgressDialog spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_appointmnt);
        table = findViewById(R.id.idtableSchedule);
        Heading=findViewById(R.id.dailyrep_1);
        PatientNameEdt=findViewById(R.id.apptable2);
        DateEdt=findViewById(R.id.apptable3);
        TimeEdt=findViewById(R.id.apptable4);
        //Column4=findViewById(R.id.apptable5);
        Column5=findViewById(R.id.apptable6);
        ConfirmBtn=findViewById(R.id.confirm);
        IgnoreBtn=findViewById(R.id.ignore);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("Schedules");
        scheduleModalArrayList = new ArrayList<>();
        tableAdapterAppointment = new TableAdapterAppointment(scheduleModalArrayList,this,  this::onCourseClick);
        table.setLayoutManager(new LinearLayoutManager(this));
        table.setAdapter(tableAdapterAppointment);
        getAllSchedules();
    }

    private void getAllSchedules() {
        scheduleModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                scheduleModalArrayList.add(snapshot.getValue(ScheduleModal.class));
                tableAdapterAppointment.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                tableAdapterAppointment.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                tableAdapterAppointment.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                tableAdapterAppointment.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void onCourseClick(int position){
        startActivity(new Intent(MyAppointmnt.this , MyAppointmnt.class));
        spinner.setTitle("Ignore Appointment");
        spinner.setMessage("Please Wait while deleting.");
        spinner.setCanceledOnTouchOutside(false);
        spinner.show();
    }
}
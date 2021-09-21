package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.ScheduleModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ScheduleApprovalsActivity extends AppCompatActivity {

    private ImageView HosLogo;
    private TextView Heading, DocNameEdt, DateEdt, TimeEdt, Column4, Column5;
    private FloatingActionButton AddBtn;
    private TableLayout Table;
    private ImageButton UpdateBtn, DeleteBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String ScheduleId;
    private ScheduleModal scheduleModal;



    @Override
   protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduleapprovals);

        HosLogo.findViewById(R.id.hos_logo1);
        Heading.findViewById(R.id.dailyrep_1);
        DocNameEdt.findViewById(R.id.apptable2);
        DateEdt.findViewById(R.id.apptable3);
        TimeEdt.findViewById(R.id.apptable4);
        Column4.findViewById(R.id.apptable5);
        Column5.findViewById(R.id.apptable6);
        AddBtn.findViewById(R.id.addbton);
        UpdateBtn.findViewById(R.id.icondell);
        DeleteBtn.findViewById(R.id.icondel1);
        firebaseDatabase = FirebaseDatabase.getInstance();

        scheduleModal = getIntent().getParcelableExtra("Schedules");
        if (scheduleModal != null){
            DocNameEdt.setText(scheduleModal.getDoctorName());
            DateEdt.setText(scheduleModal.getDate());
            TimeEdt.setText(scheduleModal.getTime());
            ScheduleId = scheduleModal.getScheduleId();
        }

        databaseReference = firebaseDatabase.getReference("PetCards");
        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScheduleApprovalsActivity.this, UpdateScheduleActivity.class);
            }
        });

        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScheduleApprovalsActivity.this, UpdateScheduleActivity.class);
            }
        });
    }


}




package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.ScheduleModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EnterScheduleActivity extends AppCompatActivity {

    private ImageView hoslogo , schedulepic;
    private TextView schedHeading, Docname, Date, Time;
    private EditText DocNameEdt, DateEdt, TimeEdt;
    private Button submitBtn;
    private FirebaseDatabase firebaseDatabase ;
    private DatabaseReference databaseReference;
    private String ScheduleId;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterschedule);
        hoslogo = findViewById(R.id.hos_logo1);
        schedulepic = findViewById(R.id.sched_pic);
        schedHeading = findViewById(R.id.sched_name);
        Docname = findViewById(R.id.sched_label2);
        Date = findViewById(R.id.sched_label3);
        Time = findViewById(R.id.sched_label4);
        DocNameEdt = findViewById(R.id.editTextTextPersonName6);
        DateEdt = findViewById(R.id.Date);
        TimeEdt = findViewById(R.id.Time);
        submitBtn = findViewById(R.id.bton7);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Schedules");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DoctorName = DocNameEdt.getText().toString();
                String Date = DateEdt.getText().toString();
                String Time = TimeEdt.getText().toString();
                ScheduleId = DoctorName;

                ScheduleModal scheduleModal = new ScheduleModal(DoctorName,Date,Time,ScheduleId);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        databaseReference.child(ScheduleId).setValue(scheduleModal);
                        Toast.makeText(EnterScheduleActivity.this, "Schedule is  Added..", Toast.LENGTH_SHORT ).show();
                        startActivity(new Intent(EnterScheduleActivity.this, MedicalProfActivity.class));

                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {
                        Toast.makeText(EnterScheduleActivity.this, "Error occurs to add schedule" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });


    }
}
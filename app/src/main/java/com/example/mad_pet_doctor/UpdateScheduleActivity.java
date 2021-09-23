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

import java.util.HashMap;
import java.util.Map;

public class UpdateScheduleActivity extends AppCompatActivity {

    private ImageView hoslogo , schedulepic;
    private TextView schedHeading, Docname, Date, Time,Id;
    private EditText DocNameEdt, DateEdt, TimeEdt,IDEdt;
    private Button UpdateBtn, DeleteBtn;
    private FirebaseDatabase firebaseDatabase ;
    private DatabaseReference databaseReference;
    private String scheduleId;
    private ScheduleModal scheduleModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterschedule);
        hoslogo = findViewById(R.id.hos_logo1);
        schedulepic = findViewById(R.id.sched_pic);
        schedHeading = findViewById(R.id.sched_name);
        Id = findViewById(R.id.sched_label111);
        Docname = findViewById(R.id.sched_label2);
        Date = findViewById(R.id.sched_label3);
        Time = findViewById(R.id.sched_label4);
        IDEdt=findViewById(R.id.editTextTextPersonName4);
        DocNameEdt = findViewById(R.id.editTextTextPersonName6);
        DateEdt = findViewById(R.id.Date);
        TimeEdt = findViewById(R.id.Time);
        UpdateBtn = findViewById(R.id.bton7);
        DeleteBtn = findViewById(R.id.btondelete);
        firebaseDatabase = FirebaseDatabase.getInstance();
        scheduleModal = getIntent().getParcelableExtra("Schedule");

        if (scheduleModal != null){
            IDEdt.setText(scheduleModal.getId());
            DocNameEdt.setText(scheduleModal.getDoctorName());
            DateEdt.setText(scheduleModal.getDate());
            TimeEdt.setText(scheduleModal.getTime());
            scheduleId = scheduleModal.getScheduleId();
        }

        databaseReference = firebaseDatabase.getReference("Schedules").child("scheduleId");

        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = IDEdt.getText().toString();
                String doctorName = DocNameEdt.getText().toString();
                String date = DateEdt.getText().toString();
                String time = TimeEdt.getText().toString();


                Map<String, Object> map = new HashMap<>();
                map.put("Id", id);
                map.put("DoctorName" , doctorName );
                map.put("Date", date);
                map.put("Time", time);
                map.put("ScheduleId", scheduleId);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(map);
                        Toast.makeText(UpdateScheduleActivity.this,"Schedules Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UpdateScheduleActivity.this, MedicalProfileUserActivity.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(UpdateScheduleActivity.this, "Schedule Updated Fail.. "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DeleteSchedule(); }
        });
    }

    private void DeleteSchedule(){
        databaseReference.removeValue();
        Toast.makeText(this, "Schedule Deleted..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UpdateScheduleActivity.this, MedicalProfActivity.class));
    }
}


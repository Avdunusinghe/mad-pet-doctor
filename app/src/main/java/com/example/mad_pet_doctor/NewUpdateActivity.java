package com.example.mad_pet_doctor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.model.BookingModal;
import com.example.model.ScheduleModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewUpdateActivity extends AppCompatActivity {

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
        setContentView(R.layout.updateschedule);
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
        firebaseDatabase = FirebaseDatabase.getInstance();
        scheduleModal = getIntent().getParcelableExtra("Schedule");
        if(scheduleModal!=null){
            IDEdt.setText(scheduleModal.getId());
            DocNameEdt.setText(scheduleModal.getDoctorName());
            DateEdt.setText(scheduleModal.getDate());
            TimeEdt.setText(scheduleModal.getTime());
            scheduleId = scheduleModal.getScheduleId();
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Schedules");


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot datasnapshot : snapshot.getChildren()) {

                    ScheduleModal scheduleModal;
                    scheduleModal = datasnapshot.getValue(ScheduleModal.class);
                    assert scheduleModal != null;
                    String Id = scheduleModal.getId();
                    String Docname = scheduleModal.getDoctorName();
                    String date = scheduleModal.getDate();
                    String time = scheduleModal.getTime();

                    IDEdt.setText(Id);
                    DocNameEdt.setText(Docname);
                    DateEdt.setText(date);
                    TimeEdt.setText(time);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }}

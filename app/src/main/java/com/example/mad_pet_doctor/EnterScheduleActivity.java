package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.model.ScheduleModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EnterScheduleActivity extends AppCompatActivity {

    private ImageView hoslogo , schedulepic;
    private TextView schedHeading, Docname, Date, Time,Id;
    private EditText IDEdt, DocNameEdt, DateEdt, TimeEdt;
    private Button submitBtn;
    private FirebaseDatabase firebaseDatabase ;
    private DatabaseReference databaseReference;
    private String ScheduleId;
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        submitBtn = findViewById(R.id.bton7);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Schedules");



        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        DateEdt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EnterScheduleActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        TimeEdt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EnterScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        TimeEdt.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty((IDEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter Id", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((DocNameEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter doctor name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((DateEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please select date", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((TimeEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please select time", Toast.LENGTH_SHORT).show();
                }else {
                    String Id = IDEdt.getText().toString();
                    String DoctorName = DocNameEdt.getText().toString();
                    String Date = DateEdt.getText().toString();
                    String Time = TimeEdt.getText().toString();
                    ScheduleId = Id;

                ScheduleModal scheduleModal = new ScheduleModal(Id , DoctorName,Date,Time,ScheduleId);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull  DataSnapshot snapshot) {
                        databaseReference.child(ScheduleId).setValue(scheduleModal);
                        Toast.makeText(EnterScheduleActivity.this, "Schedule is  Added..", Toast.LENGTH_SHORT ).show();
                        startActivity( new Intent(EnterScheduleActivity.this, MedicalProfileUserActivity.class));


                    }

                    @Override
                    public void onCancelled(@NonNull  DatabaseError error) {
                        Toast.makeText(EnterScheduleActivity.this, "Error occurs to add schedule" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        }
        });
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        DateEdt.setText(sdf.format(myCalendar.getTime()));
    }
}
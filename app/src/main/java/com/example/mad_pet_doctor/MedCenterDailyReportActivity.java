package com.example.mad_pet_doctor;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.model.DailyReportModal;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MedCenterDailyReportActivity extends AppCompatActivity {

    private EditText totalPatient, drCharge,dateEdt;
    private TextView totalFee;
    private Chip HomeChip1,HomeChip2;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String ReportId;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.med_daily_reports);
        HomeChip1 = findViewById(R.id.chip5);
        HomeChip2 = findViewById(R.id.chip9);
        dateEdt = findViewById(R.id.totalPatient2);
        totalPatient = findViewById(R.id.totalPatient);
        drCharge = findViewById(R.id.drCharge);
        totalFee = (TextView) findViewById(R.id.totalFee);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("DailyReports");

        HomeChip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedCenterDailyReportActivity.this, ActivityMainSideBar.class));
            }
        });
        HomeChip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedCenterDailyReportActivity.this, ActivityMainSideBar.class));
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dateEdt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(MedCenterDailyReportActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateEdt.setText(sdf.format(myCalendar.getTime()));
    }


    public void calculateBtn(View view) {
        String Id = databaseReference.push().getKey();
        String date = dateEdt.getText().toString();
        int num1 = Integer.parseInt(totalPatient.getText().toString());
        int num2 = Integer.parseInt(drCharge.getText().toString());

        int total = num1 * num2;

        totalFee.setText(String.valueOf(total));
        ReportId=Id;
        DailyReportModal dailyReportModal = new DailyReportModal(Id, date, num1, num2, total);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(ReportId).setValue(dailyReportModal);
                Toast.makeText(MedCenterDailyReportActivity.this, "Daily Reports Added..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MedCenterDailyReportActivity.this, DailyReportsActivity.class));

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(MedCenterDailyReportActivity.this, "error occurs" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyAppointmnt extends AppCompatActivity {

    Button confirm1, confirm2, confirm3, cancel1, cancel2, cancel3;
    ProgressDialog spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_appointmnt);
        confirm1 = findViewById(R.id.confirm1);
        cancel1 = findViewById(R.id.cancel1);
        confirm2 = findViewById(R.id.confirm2);
        cancel2 = findViewById(R.id.cancel2);
        confirm3 = findViewById(R.id.confirm3);
        cancel3 = findViewById(R.id.cancel3);
        spinner = new ProgressDialog(MyAppointmnt.this);
    }

    public void confirm1(View view) {
        spinner.setTitle("Confirm Appointment");
        spinner.setMessage("Appointment has been confirmed.");
        spinner.setCanceledOnTouchOutside(false);
        spinner.show();
        Toast.makeText(MyAppointmnt.this, "Appointment has been confirmed.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MyAppointmnt.this, MyAppointmentReport.class));
    }

    public void cancel1(View view) {
        spinner.setTitle("Cancel Appointment");
        spinner.setMessage("Appointment has been cancelled.");
        spinner.setCanceledOnTouchOutside(false);
        spinner.show();
        Toast.makeText(MyAppointmnt.this, "Appointment has been cancelled.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MyAppointmnt.this, MyAppointmentReport.class));
    }

    public void confirm2(View view) {
        spinner.setTitle("Confirm Appointment");
        spinner.setMessage("Appointment has been confirmed.");
        spinner.setCanceledOnTouchOutside(false);
        spinner.show();
        Toast.makeText(MyAppointmnt.this, "Appointment has been confirmed.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MyAppointmnt.this, MyAppointmentReport.class));
    }

    public void cancel2(View view) {
        spinner.setTitle("Cancel Appointment");
        spinner.setMessage("Appointment has been cancelled.");
        spinner.setCanceledOnTouchOutside(false);
        spinner.show();
        Toast.makeText(MyAppointmnt.this, "Appointment has been cancelled.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MyAppointmnt.this, MyAppointmentReport.class));
    }

    public void confirm3(View view) {
        spinner.setTitle("Confirm Appointment");
        spinner.setMessage("Appointment has been confirmed.");
        spinner.setCanceledOnTouchOutside(false);
        spinner.show();
        Toast.makeText(MyAppointmnt.this, "Appointment has been confirmed.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MyAppointmnt.this, MyAppointmentReport.class));
    }

    public void cancel3(View view) {
        spinner.setTitle("Cancel Appointment");
        spinner.setMessage("Appointment has been cancelled.");
        spinner.setCanceledOnTouchOutside(false);
        spinner.show();
        Toast.makeText(MyAppointmnt.this, "Appointment has been cancelled.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MyAppointmnt.this, MyAppointmentReport.class));
    }
}
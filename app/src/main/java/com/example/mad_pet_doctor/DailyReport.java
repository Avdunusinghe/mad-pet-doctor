package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DailyReport extends AppCompatActivity {

    private EditText totalPatient, drCharge;
    private TextView totalFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_report);
        totalPatient = findViewById(R.id.totalPatient);
        drCharge = findViewById(R.id.drCharge);
        totalFee = (TextView) findViewById(R.id.totalFee);
    }


    public void calculateBtn(View view) {
        int num1 = Integer.parseInt(totalPatient.getText().toString());
        int num2 = Integer.parseInt(drCharge.getText().toString());

        int total = num1 * num2;

        totalFee.setText(String.valueOf(total));
    }
}
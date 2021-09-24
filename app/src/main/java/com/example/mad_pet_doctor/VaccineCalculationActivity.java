/*package com.example.mad_pet_doctor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.VaccineDetailsModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VaccineCalculationActivity extends AppCompatActivity {

    private TextView VDHeading;
    private TextView VDCalculationSubHeading;
    private TextView TVoldprice, TVnewprice, TVtotalprice;
    private EditText EToldprice, ETnewprice,ETtotalprice;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String VaccineReportCalId;
    private Button CalculationButton;
    private VaccineDetailsModal vaccineDetailsModal;
    private ArrayList<VaccineDetailsModal> vaccineDetailsModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccinecalculation);
        TVoldprice = findViewById(R.id.book_doc5);
        TVnewprice = findViewById(R.id.book_doc);
        TVtotalprice = findViewById(R.id.book_doc2);
        EToldprice = findViewById(R.id.totalPatient2);
        ETnewprice = findViewById(R.id.totalPatient);

        firebaseDatabase = FirebaseDatabase.getInstance();
        vaccineDetailsModal = getIntent().getParcelableExtra("VaccineReport");

        public void CalculationButton(View v) {
        int a = 0;
        int oldtotals;

        while (vaccineDetailsModal != null){
            oldtotals = Integer.parseInt(TVtotalprice.getText().toString());
            a = a + oldtotals;
        }


        databaseReference = firebaseDatabase.getReference("VaccineReport").child("VaccineReportCalId");


        int num1 = Integer.parseInt(EToldprice.setText( ));
        int num2 = Integer.parseInt(drCharge.getText().toString());

        int total = num1 * num2;

        totalFee.setText(String.valueOf(total));
        VaccineReportCalId=Id;
        VaccineDetailsModal vaccineDetailsModal = new VaccineDetailsModal(vaccine_Name_Edt, vaccine_Date_Edt,  vaccine_Price_Edt);

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
} */
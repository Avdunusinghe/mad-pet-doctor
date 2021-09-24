package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.VaccineDetailsModal;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class VaccineDetails extends AppCompatActivity {

    private TextView VaccineDetailsHeading;
    private ImageView VaccineDetailsPageImage;
    private TextView PetID, VaccineName, VaccineDate;
    private TextView NoOfVaccines, VaccinePrice, VaccineTotalPrice;
    private TextView PriceResult;

    private EditText PetIdEdt, VaccineNameEdt, VaccineDateEdt;
    private EditText NoOfVaccinesEdt, VaccinePriceEdt;
    private Button CALCULATION,SAVE;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String petName;
    private VaccineDetailsModal vaccineDetailsModal;
    final Calendar myCalendar = Calendar.getInstance();
    private Chip HomeChip1, HomeChip2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccine_details);

        VaccineDetailsHeading = findViewById(R.id.vaccinedetail);
        VaccineDetailsPageImage = findViewById(R.id.imageView10);
        VaccineName = findViewById(R.id.vaccinename);
        VaccineDate = findViewById(R.id.vaccinedate);
        NoOfVaccines =findViewById(R.id.noofvaccines);
        VaccinePrice = findViewById(R.id.price);
        VaccineTotalPrice =findViewById(R.id.totalprice);
        PriceResult = findViewById(R.id.totalFee);

        VaccineNameEdt = findViewById(R.id.vaccinenameinput);
        VaccineDateEdt = findViewById(R.id.vaccinedateinput);
        NoOfVaccinesEdt = findViewById(R.id.noofvaccineinput);
        VaccinePriceEdt = findViewById(R.id.priceinput);
        HomeChip1 = findViewById(R.id.chip9);
        HomeChip2 = findViewById(R.id.chip13);
        SAVE = findViewById(R.id.SAVE);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("VaccineDetails");



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

        HomeChip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccineDetails.this, ActivityMainSideBar.class));
            }
        });
        HomeChip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccineDetails.this, ActivityMainSideBar.class));
            }
        });

        VaccineDateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(VaccineDetails.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty((VaccineNameEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please Enter Vaccine Name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty((VaccineDateEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please Enter Vaccine Date", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty((PriceResult.getText().toString()))) {
                  Toast.makeText(getApplicationContext(), "Please Enter Vaccine Price", Toast.LENGTH_SHORT).show();
                }
                else {
                    String Vaccine_Name_Edt = VaccineNameEdt.getText().toString();
                    String Vaccine_Date_Edt = VaccineDateEdt.getText().toString();
                    int Vaccine_Price_Edt = Integer.parseInt(PriceResult.getText().toString());
                    petName = Vaccine_Name_Edt;

                    VaccineDetailsModal vaccine_details_Model = new VaccineDetailsModal(petName, Vaccine_Date_Edt, Vaccine_Price_Edt);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child(petName).setValue(vaccine_details_Model);
                            Toast.makeText(VaccineDetails.this, "Vaccine Details Added.....", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(VaccineDetails.this, VaccineReport.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(VaccineDetails.this, "Error is " + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        VaccineDateEdt.setText(sdf.format(myCalendar.getTime()));
    }

    public void calculateBtn(View view) {

        int NOVaccines = Integer.parseInt(NoOfVaccinesEdt.getText().toString());
        NoOfVaccinesEdt.setText("");

        int Amount = Integer.parseInt(VaccinePriceEdt.getText().toString());
        VaccinePriceEdt.setText("");

        //int num1 = Integer.parseInt(NoOfVaccinesEdt.setText());
        //int num2 = Integer.parseInt(drCharge.getText().toString());

        int total = NOVaccines * Amount;

        PriceResult.setText(String.valueOf(total));
        //ReportId = Id;
        //VaccineDetailsModal vaccineDetailsModal = new VaccineDetailsModal(Id, date, num1, num2, total);
    }
}
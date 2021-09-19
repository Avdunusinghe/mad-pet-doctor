package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.model.DoctorReg;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctoreRegistration extends AppCompatActivity {

    private TextInputEditText fullNameEdt, docLicenseNoEdt, qualificationEdt, medicalCenterEdt, addressEdt, telNoEdt, emailEdt;
    private ImageButton docPicBtn;
    private RadioButton yesBtn;
    private RadioButton noBtn;
    private Button submitBtn;
    private FirebaseDatabase fireBaseDatabase;
    private DatabaseReference databaseReference;
    private String doctorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctore_registration);
        docPicBtn = findViewById(R.id.imageButton8);
        fullNameEdt = findViewById(R.id.textInputEditText2);
        docLicenseNoEdt = findViewById(R.id.editText5);
        qualificationEdt = findViewById(R.id.editText6);
        medicalCenterEdt = findViewById(R.id.editText7);
        addressEdt = findViewById(R.id.editText8);
        telNoEdt = findViewById(R.id.editText9);
        emailEdt = findViewById(R.id.editText10);
        yesBtn = findViewById(R.id.radioButton);
        noBtn = findViewById(R.id.radioButton2);
        submitBtn = findViewById(R.id.button4);
        fireBaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = fireBaseDatabase.getReference("DoctorReg");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = fullNameEdt.getText().toString();
                String docLicenseNo = docLicenseNoEdt.getText().toString();
                String qualification = qualificationEdt.getText().toString();
                String medicalCenter = medicalCenterEdt.getText().toString();
                String address = addressEdt.getText().toString();
                String telNo = telNoEdt.getText().toString();
                String email = emailEdt.getText().toString();
                doctorId = fullName;

                DoctorReg DoctorReg = new DoctorReg(doctorId,fullName,docLicenseNo,qualification,medicalCenter,address,telNo,email);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(doctorId).setValue(DoctorReg);
                        Toast.makeText(DoctoreRegistration.this, "Doctor Registered Successfully.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DoctoreRegistration.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(DoctoreRegistration.this, "Error occurred. Please Try again."+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
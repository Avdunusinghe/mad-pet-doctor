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

import java.util.HashMap;
import java.util.Map;

public class DoctorProfile extends AppCompatActivity {

    private TextInputEditText fullNameEdt, docLicenseNoEdt, qualificationEdt, medicalCenterEdt, addressEdt, telNoEdt, emailEdt;
    private ImageButton docPicBtn;
    private RadioButton yesBtn, noBtn;
    private Button updateBtn,deleteBtn;
    private FirebaseDatabase fireBaseDatabase;
    private DatabaseReference databaseReference;
    private String doctorId;
    private DoctorReg doctorReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_profile);
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
        updateBtn = findViewById(R.id.auth_loginbtn);
        deleteBtn = findViewById(R.id.buton2);
        fireBaseDatabase = FirebaseDatabase.getInstance();
        doctorReg = getIntent().getParcelableExtra("DoctorRegs");

        if (doctorReg != null){
            fullNameEdt.setText(doctorReg.getFullName());
            docLicenseNoEdt.setText(doctorReg.getDocLicenseNo());
            qualificationEdt.setText(doctorReg.getQualification());
            medicalCenterEdt.setText(doctorReg.getMedicalCenter());
            addressEdt.setText(doctorReg.getAddress());
            telNoEdt.setText(doctorReg.getTelNo());
            emailEdt.setText(doctorReg.getEmail());
            doctorId = doctorReg.getDoctorId();
        }

        databaseReference = fireBaseDatabase.getReference("DoctorReg").child(doctorId);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = fullNameEdt.getText().toString();
                String docLicenseNo = docLicenseNoEdt.getText().toString();
                String qualification = qualificationEdt.getText().toString();
                String medicalCenter = medicalCenterEdt.getText().toString();
                String address = addressEdt.getText().toString();
                String telNo = telNoEdt.getText().toString();
                String email = emailEdt.getText().toString();

                Map<String, Object> map = new HashMap<>();
                map.put("fullName", fullName);
                map.put("docLicenseNo", docLicenseNo);
                map.put("qualification", qualification);
                map.put("medicalCenter", medicalCenter);
                map.put("address", address);
                map.put("telNo", telNo);
                map.put("email", email);
                map.put("doctorId", doctorId);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(map);
                        Toast.makeText(DoctorProfile.this, "Doctor Profile Updated.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DoctorProfile.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(DoctorProfile.this, "Error occurred. Please Try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDoctor();
            }
        });
    }

    private void deleteDoctor(){
        databaseReference.removeValue();
        Toast.makeText(this, "doctor is Deleted.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(DoctorProfile.this, MainActivity.class));
    }
}
package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.model.MedicalCenterReg;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MedicalCenterRegistration extends AppCompatActivity {

    private TextInputEditText medicalCenterNoEdt, nameEdt, addressEdt, telNoEdt, emailEdt;
    private ImageButton medicalPicBtn;
    private Button submitBtn;
    private FirebaseDatabase fireBaseDatabase;
    private DatabaseReference databaseReference;
    private String medicalCenterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicalcenter_reg);
        medicalPicBtn = findViewById(R.id.imageButton);
        medicalCenterNoEdt = findViewById(R.id.textInputEditText3);
        nameEdt = findViewById(R.id.editText);
        addressEdt = findViewById(R.id.editText2);
        telNoEdt = findViewById(R.id.editText3);
        emailEdt = findViewById(R.id.editText4);
        submitBtn = findViewById(R.id.button3);
        fireBaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = fireBaseDatabase.getReference("MedicalCenterReg");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicalCenterNo = medicalCenterNoEdt.getText().toString();
                String name = nameEdt.getText().toString();
                String address = addressEdt.getText().toString();
                String telNo = telNoEdt.getText().toString();
                String email = emailEdt.getText().toString();
                medicalCenterId = name;

                MedicalCenterReg MedicalCenterReg = new MedicalCenterReg(medicalCenterId, medicalCenterNo, name, address, telNo, email);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(medicalCenterId).setValue(MedicalCenterReg);
                        Toast.makeText(MedicalCenterRegistration.this, "Medical Center Registered Successfully.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MedicalCenterRegistration.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MedicalCenterRegistration.this, "Error occurred. Please Try again."+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
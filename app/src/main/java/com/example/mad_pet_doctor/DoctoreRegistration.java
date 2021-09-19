package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.util.regex.Pattern;

public class DoctoreRegistration extends AppCompatActivity {

    private TextInputEditText fullNameEdt, docLicenseNoEdt, qualificationEdt, medicalCenterEdt, addressEdt, telNoEdt, emailEdt;
    private ImageButton docPicBtn;
    private RadioButton yesBtn, noBtn;
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
                if (TextUtils.isEmpty((fullNameEdt.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty((docLicenseNoEdt.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Please enter license no", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty((qualificationEdt.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Please enter qualifications", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty((medicalCenterEdt.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Please enter medical center", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty((addressEdt.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Please enter address", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty((telNoEdt.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Please enter tel no", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty((emailEdt.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean validateEmail = validateEmail();
                    boolean validateMobileNo = validateTelNo();
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
            }

            private boolean validateEmail() {
                String email = emailEdt.getText().toString();
                String EmalFormat = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                if(Pattern.compile(EmalFormat).matcher(email).matches())
                {
                    return true;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

            private boolean validateTelNo() {
                String phone = telNoEdt.getText().toString();

                if(phone.length() == 10)
                {
                    return true;
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please enter valid phone number", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });
    }
}
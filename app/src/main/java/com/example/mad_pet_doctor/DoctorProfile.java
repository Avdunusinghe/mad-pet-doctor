package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.regex.Pattern;

public class DoctorProfile extends AppCompatActivity {

    private TextInputEditText fullNameEdt, docLicenseNoEdt, qualificationEdt, medicalCenterEdt, addressEdt, telNoEdt, emailEdt;
    private RadioGroup houseCallYesOrNoGroup;
    private RadioButton houseCallYesOrNoBtn;
    Button updateBtn,deleteBtn;
    ProgressDialog spinner;
    private FirebaseDatabase fireBaseDatabase;
    private DatabaseReference databaseReference;
    private String doctorId;
    private DoctorReg doctorReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_profile);
        fullNameEdt = findViewById(R.id.textInputEditText2);
        docLicenseNoEdt = findViewById(R.id.editText5);
        qualificationEdt = findViewById(R.id.editText6);
        medicalCenterEdt = findViewById(R.id.editText7);
        addressEdt = findViewById(R.id.editText8);
        telNoEdt = findViewById(R.id.editText9);
        emailEdt = findViewById(R.id.editText10);
        houseCallYesOrNoGroup = (RadioGroup) findViewById(R.id.radioGroup2);
        updateBtn = findViewById(R.id.auth_loginbtn);
        deleteBtn = findViewById(R.id.button2);
<<<<<<< HEAD
        spinner = new ProgressDialog(DoctorProfile.this);
=======
>>>>>>> 72a3f752d33ed3127dacf7c5bf56d8dd43fe0e5a
        fireBaseDatabase = FirebaseDatabase.getInstance();
        doctorReg = getIntent().getParcelableExtra("Doctor");

        if (doctorReg != null) {
            fullNameEdt.setText(doctorReg.getFullName());
            docLicenseNoEdt.setText(doctorReg.getDocLicenseNo());
            qualificationEdt.setText(doctorReg.getQualification());
            medicalCenterEdt.setText(doctorReg.getMedicalCenter());
            addressEdt.setText(doctorReg.getAddress());
            telNoEdt.setText(doctorReg.getTelNo());
            emailEdt.setText(doctorReg.getEmail());
            doctorId = doctorReg.getDoctorId();

            databaseReference = fireBaseDatabase.getReference("Doctor").child(doctorId);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty((fullNameEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((docLicenseNoEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter license no", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((qualificationEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter qualifications", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((medicalCenterEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter medical center", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((addressEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter address", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((telNoEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter tel no", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((emailEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                } else {
                    boolean validateEmail = validateEmail();
                    boolean validateMobileNo = validateTelNo();
                    String fullName = fullNameEdt.getText().toString();
                    String docLicenseNo = docLicenseNoEdt.getText().toString();
                    String qualification = qualificationEdt.getText().toString();
                    String medicalCenter = medicalCenterEdt.getText().toString();
                    String address = addressEdt.getText().toString();
                    String telNo = telNoEdt.getText().toString();
                    String email = emailEdt.getText().toString();
                    spinner.setTitle("Update Doctor");
                    spinner.setMessage("Please Wait while Validate the Details");
                    spinner.setCanceledOnTouchOutside(false);
                    spinner.show();

                    int selectedValue = houseCallYesOrNoGroup.getCheckedRadioButtonId();
                    houseCallYesOrNoBtn = (RadioButton) findViewById(selectedValue);
                    Toast.makeText(DoctorProfile.this, houseCallYesOrNoBtn.getText(), Toast.LENGTH_SHORT).show();

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
                            startActivity(new Intent(DoctorProfile.this, Doctors.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(DoctorProfile.this, "Error occurred. Please Try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDoctor();
            }
        });
    }

    private boolean validateEmail() {
        String email = emailEdt.getText().toString();
        String EmalFormat = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (Pattern.compile(EmalFormat).matcher(email).matches()) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validateTelNo() {
        String phone = telNoEdt.getText().toString();

        if (phone.length() == 10) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Please enter valid phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void deleteDoctor(){
        databaseReference.removeValue();
        Toast.makeText(this, "doctor is Deleted.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(DoctorProfile.this, MainActivity.class));
    }
}
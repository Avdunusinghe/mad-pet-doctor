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
import android.widget.Toast;

import com.example.model.MedicalCenterReg;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class MedicalCenterRegistration extends AppCompatActivity {

    private TextInputEditText medicalCenterNoEdt, nameEdt, addressEdt, telNoEdt, emailEdt;
    private ImageButton medicalPicBtn;
    private Button submitBtn;
    ProgressDialog spinner;
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
        spinner = new ProgressDialog(MedicalCenterRegistration.this);
        fireBaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = fireBaseDatabase.getReference("MedicalCenter");

        ImageButton medicalPicBtn = (ImageButton) findViewById(R.id.imageButton);
        medicalPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Upload a picture.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty((medicalCenterNoEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter medical center no", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((nameEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((addressEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter address", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((telNoEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter tel no", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((emailEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                } else {
                    boolean validateEmail = validateEmail();
                    boolean validateMobileNo = validateTelNo();
                    String medicalCenterNo = medicalCenterNoEdt.getText().toString();
                    String name = nameEdt.getText().toString();
                    String address = addressEdt.getText().toString();
                    String telNo = telNoEdt.getText().toString();
                    String email = emailEdt.getText().toString();
                    spinner.setTitle("Register New Medical Center");
                    spinner.setMessage("Please Wait while Validate the Details");
                    spinner.setCanceledOnTouchOutside(false);
                    spinner.show();
                    String id = databaseReference.push().getKey();

                    MedicalCenterReg MedicalCenterReg = new MedicalCenterReg(id, medicalCenterNo, name, address, telNo, email);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child(id).setValue(MedicalCenterReg);
                            Toast.makeText(MedicalCenterRegistration.this, "Medical Center Registered Successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MedicalCenterRegistration.this, MainActivity.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MedicalCenterRegistration.this, "Error occurred. Please Try again." + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
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

        if (phone.length() == 10) {
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Please enter valid phone number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
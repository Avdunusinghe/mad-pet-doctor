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
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.PetCardModal;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PetCard extends AppCompatActivity {
    private TextView PetCardHeading;
    private ImageView PetCardImage;
    private TextView petid, petname, breed, petdateofbirth, petage, petgender, petweight,petownername;
    private EditText PetID , PetName, Breed, PetDateOfBirth, PetAge,PetGender, PetWeight, PetOwnerName;
    private Button SAVE;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Chip HomeChip1, HomeChip2;
    private String petId;
    private PetCardModal petCardModal;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_card);
        PetCardHeading = findViewById(R.id.petcard);
        PetCardImage = findViewById(R.id.imageView17);
        petid = findViewById(R.id.TVPetId);
        petname = findViewById(R.id.TVPetName);
        breed = findViewById(R.id.TVBreed);
        petdateofbirth = findViewById(R.id.TVPetDOB);
        petage = findViewById(R.id.TVPetAge);
        petgender = findViewById(R.id.TVPetGender);
        petweight = findViewById(R.id.TVPetWeight);
        petownername = findViewById( R.id.TVPetOwnerName);
        PetID = findViewById(R.id.ETPetId);
        PetName = findViewById(R.id.ETPetName);
        Breed = findViewById(R.id.ETPetBreed);
        PetDateOfBirth = findViewById(R.id.ETPetDOB);
        PetAge = findViewById(R.id.ETPetAge);
        PetGender = findViewById(R.id.ETPetGender);
        PetWeight = findViewById(R.id.ETPetWeight);
        PetOwnerName = findViewById(R.id.ETPetOwnerName);
        HomeChip1 = findViewById(R.id.chip9);
        HomeChip2 = findViewById(R.id.chip13);
        SAVE = findViewById(R.id.SAVE);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("PetCards");

        HomeChip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetCard.this, ActivityMainSideBar.class));
            }
        });
        HomeChip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetCard.this, ActivityMainSideBar.class));
            }
        });

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

        PetDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(PetCard.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty((PetID.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please Enter PET ID", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((PetName.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please Enter PET NAME", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((Breed.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please Enter BREED", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((PetDateOfBirth.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please Enter Date of Birth", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((PetAge.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please Enter Age", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((PetGender.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please Enter Gender", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((PetWeight.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please Enter Weight", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((PetOwnerName.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please Enter Owner Name", Toast.LENGTH_SHORT).show();
                } else {
                    String Pet_ID = PetID.getText().toString();
                    String Pet_Name = PetName.getText().toString();
                    String Pet_Breed = Breed.getText().toString();
                    String Pet_DateOfBirth = PetDateOfBirth.getText().toString();
                    String Pet_Age = PetAge.getText().toString();
                    String Pet_Gender = PetGender.getText().toString();
                    String Pet_Weight = PetWeight.getText().toString();
                    String Pet_OwnerName = PetOwnerName.getText().toString();
                    petId = Pet_ID;

                    PetCardModal petcardModal = new PetCardModal(petId, Pet_Name, Pet_Breed, Pet_DateOfBirth, Pet_Age, Pet_Gender, Pet_Weight, Pet_OwnerName);
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child(petId).setValue(petcardModal);
                            Toast.makeText(PetCard.this, "Pet Card Added.....", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PetCard.this, PetCardDetails.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(PetCard.this, "Error is " + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        PetDateOfBirth.setText(sdf.format(myCalendar.getTime()));
    }

}
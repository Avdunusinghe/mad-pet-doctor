package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.model.BookingModal;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.type.Date;
import com.google.type.DateTime;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class  PetBookFormActivity extends AppCompatActivity {

    private ImageView hosLogo , petpic1;
    private Chip HomeChip1,HomeChip2;
    private TextView petHeading , AnimalName, Ownername, OwnerPhoneNo, Address, AppDate , AppTime;
    private EditText  AnimalNameEdt, OwnerNameEdt, OwnerPhoneEdt, AddressEdt , AppDateEdt  , AppTimeEdt ;
    private Button NextBtn ;
    private String BookingId;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petbookform);
        hosLogo = findViewById(R.id.hos_logo1);
        petpic1 = findViewById(R.id.pet_pic1);
        petHeading = findViewById(R.id.appo_head);
        HomeChip1 = findViewById(R.id.chip5);
        HomeChip2 = findViewById(R.id.chip9);
        AnimalName = findViewById(R.id.pet_label2);
        Ownername = findViewById(R.id.pet_label3);
        OwnerPhoneNo = findViewById(R.id.pet_label4);
        Address = findViewById(R.id.pet_label5);
        AppDate = findViewById(R.id.pet_label6);
        AppTime = findViewById(R.id.pet_label7);
        AnimalNameEdt = findViewById(R.id.pet_input1);
        OwnerNameEdt = findViewById(R.id.pet_input2);
        OwnerPhoneEdt = findViewById(R.id.pet_input3);
        AddressEdt = findViewById(R.id.pet_input4);
        AppDateEdt = findViewById(R.id.pet_input5);
        AppTimeEdt = findViewById(R.id.pet_input6);
        NextBtn = findViewById(R.id.bton17);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Boookings");


        HomeChip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetBookFormActivity.this, ActivityMainSideBar.class));
            }
        });
        HomeChip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetBookFormActivity.this, ActivityMainSideBar.class));
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };



        AppDateEdt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(PetBookFormActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        AppTimeEdt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PetBookFormActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        AppTimeEdt.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty((AnimalNameEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter Animal name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((OwnerNameEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter Owner name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((OwnerPhoneEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter Phone number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((AddressEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please enter address", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((AppDateEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please select date", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty((AppTimeEdt.getText().toString()))) {
                    Toast.makeText(getApplicationContext(), "Please select Time", Toast.LENGTH_SHORT).show();
                } else {
                    String Id = databaseReference.push().getKey();
                    String AnimalName = AnimalNameEdt.getText().toString();
                    String OwnerName = OwnerNameEdt.getText().toString();
                    String OwnerPhone = OwnerPhoneEdt.getText().toString();
                    String Address = AddressEdt.getText().toString();
                    String AppointmentDate = AppDateEdt.getText().toString();
                    String AppointmentTime = AppTimeEdt.getText().toString();
                    BookingId = Id;
                    BookingModal bookingModal = new BookingModal(AnimalName, OwnerName, OwnerPhone, Address, AppointmentDate, AppointmentTime, BookingId);

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child(BookingId).setValue(bookingModal);
                            Toast.makeText(PetBookFormActivity.this, "Booking information is  Added..", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PetBookFormActivity.this, AppointmentDetailsActivity.class));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(PetBookFormActivity.this, "Error occurs to booking" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        AppDateEdt.setText(sdf.format(myCalendar.getTime()));
    }
}
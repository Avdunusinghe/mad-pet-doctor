package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.list_view.DoctorList;

import com.example.model.DoctorReg;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoctorListActivity extends AppCompatActivity {

    ListView doctorListView;

    List<DoctorReg> doctorList;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        Intent intent = getIntent();

        doctorList = new ArrayList<>();

        db = FirebaseDatabase.getInstance().getReference("Doctor");

        doctorListView = (ListView)findViewById(R.id.doctorlist_view);
    }

    @Override
    public void onStart() {
        super.onStart();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                doctorList.clear();

                for (DataSnapshot doctorDataSnapshot : snapshot.getChildren()) {

                    DoctorReg doctors = doctorDataSnapshot.getValue(DoctorReg.class);

                    doctorList.add(doctors);
                }

                DoctorList doctorListAdapter = new DoctorList(DoctorListActivity.this, doctorList);
                doctorListView.setAdapter(doctorListAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
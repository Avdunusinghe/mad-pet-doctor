package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.list_view.MedicalCenterListGenarator;
import com.example.model.DoctorReg;
import com.example.model.MedicalCenterReg;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MedicalCenterList extends AppCompatActivity {

    ListView medicalCenterView;

    List<MedicalCenterReg> medicalCenterList;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_center_list);

        Intent intent = getIntent();

        medicalCenterList = new ArrayList<>();

        db = FirebaseDatabase.getInstance().getReference("MedicalCenter");

        medicalCenterView = (ListView)findViewById(R.id.medical_centers_listview);
    }

    @Override
    public void onStart(){
        super.onStart();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                medicalCenterList.clear();

                for (DataSnapshot medicalCenterDataSnapshot : snapshot.getChildren()) {

                    MedicalCenterReg medicalCenters = medicalCenterDataSnapshot.getValue(MedicalCenterReg.class);

                    medicalCenterList.add(medicalCenters);
                }

                MedicalCenterListGenarator medicalCenterListAdapater = new MedicalCenterListGenarator(MedicalCenterList.this,medicalCenterList);
                medicalCenterView.setAdapter(medicalCenterListAdapater);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public boolean updateMedicalCenter(String id,String medicalCenterNumber, String name, String address, String mobileNumber, String email){

        db = FirebaseDatabase.getInstance().getReference("MedicalCenter").child(id);

        MedicalCenterReg medicalCenterReg = new MedicalCenterReg(id,name,medicalCenterNumber,address,mobileNumber,email);
        db.setValue(medicalCenterReg);

        Toast.makeText(getApplicationContext(),
                "MedicalCenter Updated",
                Toast.LENGTH_LONG)
                .show();

        return true;
    }

    private void showUpdateDeleteMedicalCenterDialog(final String id,String medicalCenterNumber, String name, String address, String mobileNumber, String email){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_medical_center_dialog, null);



    }
}
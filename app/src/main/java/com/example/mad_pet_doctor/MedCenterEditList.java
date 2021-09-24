package com.example.mad_pet_doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.MedicalCenterReg;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MedCenterEditList extends AppCompatActivity {
    private TextView Heading, MedNameEdt, AddressEdt, TelNoEdt, Column4, Column5;
    private FloatingActionButton AddBtn;
    private RecyclerView table;
    private ImageButton UpdateBtn, DeleteBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String MedicalCenterId;
    private MedicalCenterReg medicalCenterReg;
    private ArrayList<MedicalCenterReg> medicalCenterRegArrayList;
    private TableAdapterMed tableAdapterMed;

    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medi_list);
        table = findViewById(R.id.idtableSchedule);
        Heading=findViewById(R.id.dailyrep_1);
        MedNameEdt=findViewById(R.id.apptable2);
        AddressEdt=findViewById(R.id.apptable3);
        TelNoEdt=findViewById(R.id.apptable4);
        Column4=findViewById(R.id.apptable5);
        Column5=findViewById(R.id.apptable6);
        AddBtn=findViewById(R.id.addbton);
        UpdateBtn=findViewById(R.id.icondell4);
        DeleteBtn=findViewById(R.id.icondel14);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("MedicalCenter");
        medicalCenterRegArrayList = new ArrayList<>();
        tableAdapterMed = new TableAdapterMed(medicalCenterRegArrayList,this,  this::onCourseClick);
        table.setLayoutManager(new LinearLayoutManager(this));
        table.setAdapter(tableAdapterMed);
        getAllMedicalCenters();
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MedCenterEditList.this, MedicalCenterRegistration.class));
            }
        });
    }

    private void getAllMedicalCenters() {
        medicalCenterRegArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                medicalCenterRegArrayList.add(snapshot.getValue(MedicalCenterReg.class));
                tableAdapterMed.notifyDataSetChanged();;

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable  String previousChildName) {
                tableAdapterMed.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {
                tableAdapterMed.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                tableAdapterMed.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onCourseClick(int position){
        startActivity(new Intent(MedCenterEditList.this , MedicalProfActivity.class));
    }
}

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

import com.example.model.DoctorReg;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DoctorEditList extends AppCompatActivity {
    private TextView Heading, DoctorNameEdt, MedicalCenterEdt, TelNoEdt, Column4, Column5;
    private FloatingActionButton AddBtn;
    private RecyclerView table;
    private ImageButton UpdateBtn, DeleteBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String DoctorId;
    private DoctorReg doctorReg;
    private ArrayList<DoctorReg> doctorRegArrayList;
    private TableAdapterDoc tableAdapterDoc;

    @Override
    protected  void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_getlist);
        table = findViewById(R.id.idtableSchedule);
        Heading=findViewById(R.id.dailyrep_1);
        DoctorNameEdt=findViewById(R.id.apptable2);
        MedicalCenterEdt=findViewById(R.id.apptable3);
        TelNoEdt=findViewById(R.id.apptable4);
        Column4=findViewById(R.id.apptable5);
        Column5=findViewById(R.id.apptable6);
        AddBtn=findViewById(R.id.addbton);
        UpdateBtn=findViewById(R.id.icondell4);
        DeleteBtn=findViewById(R.id.icondel14);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("Doctor");
        doctorRegArrayList = new ArrayList<>();
        tableAdapterDoc = new TableAdapterDoc(doctorRegArrayList,this,  this::onCourseClick);
        table.setLayoutManager(new LinearLayoutManager(this));
        table.setAdapter(tableAdapterDoc);
        getAllDoctors();
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorEditList.this, DoctoreRegistration.class));
            }
        });
    }

    private void getAllDoctors() {
        doctorRegArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                doctorRegArrayList.add(snapshot.getValue(DoctorReg.class));
                tableAdapterDoc.notifyDataSetChanged();;

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable  String previousChildName) {
                tableAdapterDoc.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {
                tableAdapterDoc.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                tableAdapterDoc.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onCourseClick(int position){
        startActivity(new Intent(DoctorEditList.this , DoctorProfile.class));
    }
}

package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.model.ScheduleModal;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AppointmentsActivity extends AppCompatActivity {

    private RecyclerView scheduleApp;
    private SearchView SearchBar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<ScheduleModal> scheduleModalArrayList;
    private ScheduleAdapter scheduleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointments);
        scheduleApp = findViewById(R.id.idAppSchedule);
        SearchBar = findViewById(R.id.searchView2);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("Schedules");
        scheduleModalArrayList = new ArrayList<>();
        scheduleAdapter = new ScheduleAdapter(scheduleModalArrayList,this, (ScheduleAdapter.CourseClickInterface) this);
        scheduleApp.setLayoutManager(new LinearLayoutManager(this));
        scheduleApp.setAdapter(scheduleAdapter);


    }

    private void getAllschedules(){
        scheduleModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                scheduleModalArrayList.add(snapshot.getValue(ScheduleModal.class));
                scheduleAdapter.notifyDataSetChanged();;

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable  String previousChildName) {
                scheduleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {
                scheduleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                scheduleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }

    public void onCourseClick(int position){
        Intent i = new Intent(AppointmentsActivity.this , PetBookFormActivity.class);
        startActivity(i);

    }
}
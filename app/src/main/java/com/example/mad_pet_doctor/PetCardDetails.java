package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.PetCardModal;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PetCardDetails extends AppCompatActivity {
    private TextView pcdHeading;
    private ImageView pcdImage;
    private TableRow TRHeading;
    private TextView pcdpetid, pcdpetname, pcdpetownername,pcdupdate, pcddelete;
    private TextView pcdbreed, pcddateofbirth,pcdage,pcdgender, pcdweight;
    private RecyclerView pcdRecycleView;
    private ImageButton pcdUpdateButton, pcdDeleteButton;
    private Chip HomeChip1, HomeChip2;
    private ImageButton addPetcard;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<PetCardModal> petCardDetailsArrayList;
    private PetCardDetailsAdapter petCardDetailsAdapter;
    private PetCardModal petCardModals;
    private String petId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_card_details);
        pcdHeading = findViewById(R.id.petCardDetailHeading);
        pcdImage = findViewById(R.id.petCardDetailsImage);
        TRHeading = findViewById(R.id.PCDRowHeading);
        pcdpetid = findViewById(R.id.pcdPetID);
        pcdpetname = findViewById(R.id.pcdPetName);
        pcdpetownername = findViewById(R.id.pcdPetOwnerName);
        //pcdupdate = findViewById(R.id.pcdUpdateButton);
        pcddelete =findViewById(R.id.pcdDeleteButton);
        pcdRecycleView = findViewById(R.id.PCDRecycleViewId);

        //pcdUpdateButton = findViewById(R.id.PCDUpdateButton);
        pcdDeleteButton = findViewById(R.id.PCDDeleteButton);
        HomeChip1 = findViewById(R.id.chip12);
        HomeChip2 = findViewById(R.id.chip15);
        addPetcard = findViewById(R.id.petCardAddButton);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("PetCards");
        petCardDetailsArrayList = new ArrayList<>();
        petCardDetailsAdapter = new PetCardDetailsAdapter(petCardDetailsArrayList,this);
        pcdRecycleView.setLayoutManager(new LinearLayoutManager(this));
        pcdRecycleView.setAdapter(petCardDetailsAdapter);
        getAllPetCardDetails();

        petCardModals = getIntent().getParcelableExtra("PetCards");
        if (petCardModals != null){
            pcdpetid.setText(petCardModals.getPet_ID());
            pcdpetname.setText(petCardModals.getPet_Name());
            pcdbreed.setText(petCardModals.getPet_Breed());
            pcddateofbirth.setText(petCardModals.getPet_DateOfBirth());
            pcdage.setText(petCardModals.getPet_Age());
            pcdgender.setText(petCardModals.getPet_Gender());
            pcdweight.setText(petCardModals.getPet_Weight());
            pcdpetownername.setText(petCardModals.getPet_OwnerName());
            petId = petCardModals.getPet_ID();
        }

        HomeChip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetCardDetails.this, ActivityMainSideBar.class));
            }
        });
        HomeChip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetCardDetails.this, ActivityMainSideBar.class));
            }
        });
        addPetcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PetCardDetails.this, PetCard.class));
            }
        });
    }

    private void getAllPetCardDetails(){
        petCardDetailsArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot,
                                     @Nullable String previousChildName) {
                petCardDetailsArrayList.add(snapshot.getValue(PetCardModal.class));
                petCardDetailsAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot,
                                        @Nullable String previousChildName) {
                petCardDetailsAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                petCardDetailsAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot,
                                     @Nullable String previousChildName) {
                petCardDetailsAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
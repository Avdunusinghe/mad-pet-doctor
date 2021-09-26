package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

        medicalCenterView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int id, long l) {
                MedicalCenterReg medicalCenterReg = medicalCenterList.get(id);

                showUpdateDeleteMedicalCenterDialog(medicalCenterReg.getMedicalCenterId(),
                        medicalCenterReg.getMedicalCenterNo(),
                        medicalCenterReg.getName(),
                        medicalCenterReg.getAddress(),
                        medicalCenterReg.getTelNo(),
                        medicalCenterReg.getEmail());

                return true;


            }
        });
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

    private boolean deleteMedicalCenter(String id){

        db = FirebaseDatabase.getInstance().getReference("MedicalCenter").child(id);

        db.removeValue();

        Toast.makeText(getApplicationContext(),
                "MedicalCenter has Been Deleted",
                Toast.LENGTH_LONG)
                .show();

        return true;


    }

    private void showUpdateDeleteMedicalCenterDialog(final String id,String medicalCenterNumber, String name, String address, String mobileNumber, String email){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_medical_center_dialog, null);

        //final Spinner spinnerGenre = (Spinner) dialogView.findViewById(R.id.spinner_medical_center);

        final EditText editTextMedicalCenterName = (EditText) dialogView.findViewById(R.id.mc_up_name_et);
        final EditText editTextMedicalCenterNumber = (EditText) dialogView.findViewById(R.id.mc_up_number_et);
        final EditText editTextMedicalCenterAddress = (EditText) dialogView.findViewById(R.id.mc_up_address_et);
        final EditText editTextMedicalCenterMobileNumber = (EditText) dialogView.findViewById(R.id.mc_up_mobile_et);
        final EditText editTextMedicalCenterEmail = (EditText) dialogView.findViewById(R.id.mc_up_email_et);

        final Button buttonUpdateMedicalCenter = (Button) dialogView.findViewById(R.id.buttonUpdateMc);
        final Button buttonDeleteMedicalCenter = (Button) dialogView.findViewById(R.id.buttonDeleteMc);

        dialogBuilder.setTitle(name);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        buttonUpdateMedicalCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String medicalCenterName = editTextMedicalCenterName.toString().toString().trim();
                String medicalCenterNumber = editTextMedicalCenterNumber.toString().toString().trim();
                String medicalCenterAddress = editTextMedicalCenterAddress.toString().toString().trim();
                String medicalCenterMobileNumber = editTextMedicalCenterMobileNumber.toString().toString().trim();
                String medicalCenterEmail = editTextMedicalCenterEmail.toString().toString().trim();

                if(!TextUtils.isEmpty(medicalCenterName)){

                    updateMedicalCenter(id,medicalCenterName, medicalCenterNumber, medicalCenterAddress, medicalCenterMobileNumber, medicalCenterEmail);
                    alertDialog.dismiss();
                }
            }
        });

        buttonDeleteMedicalCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteMedicalCenter(id);
                alertDialog.dismiss();
            }
        });




    }
}
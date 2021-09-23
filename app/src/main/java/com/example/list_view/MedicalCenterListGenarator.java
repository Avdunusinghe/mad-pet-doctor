package com.example.list_view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mad_pet_doctor.R;
import com.example.model.MedicalCenterReg;

import java.util.List;

public class MedicalCenterListGenarator extends ArrayAdapter<MedicalCenterReg> {

    private Activity context;

    List<MedicalCenterReg> medicalCenters;

    public MedicalCenterListGenarator(Activity context, List<MedicalCenterReg>medicalCenters){

        super(context, R.layout.activity_medical_center_list,medicalCenters);

        this.context = context;

        this.medicalCenters = medicalCenters;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent ){

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_medical_center_list_detail, null, true);

        TextView textViewMedicalCenterName = (TextView) listViewItem.findViewById(R.id.medicalcenter_list_name_textview);

        TextView textViewMedicalCenterAddress = (TextView) listViewItem.findViewById(R.id.medicalcenter_list_address_textview);

        TextView textViewMedicalCenterEmail = (TextView) listViewItem.findViewById(R.id.medicalcenter_list_mobilenumber_textview);

        TextView textViewMedicalCenterMobileNumber = (TextView) listViewItem.findViewById(R.id.medicalcenter_list_email_textview);

        MedicalCenterReg medicalCenter = medicalCenters.get(position);

        textViewMedicalCenterName.setText(medicalCenter.getName());

        textViewMedicalCenterAddress.setText(medicalCenter.getAddress());

        textViewMedicalCenterEmail.setText(medicalCenter.getEmail());

        textViewMedicalCenterMobileNumber.setText(medicalCenter.getTelNo());


        return listViewItem;
    }
}
package com.example.list_view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mad_pet_doctor.R;
import com.example.model.DoctorReg;

import java.util.List;

public class DoctorList extends ArrayAdapter<DoctorReg> {

    private Activity context;

    List<DoctorReg> doctors;

    public DoctorList(Activity context, List<DoctorReg> doctors){

        super(context, R.layout.activity_doctor_list,doctors);

        this.context = context;

        this.doctors = doctors;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_doctor_list_detail, null, true);

        TextView textViewDoctorName = (TextView) listViewItem.findViewById(R.id.doctor_list_name_textview);

        TextView textViewMedicalCenter = (TextView) listViewItem.findViewById(R.id.doctor_list_medicalcenter_textview);

        TextView textViewDoctorEmail = (TextView) listViewItem.findViewById(R.id.doctor_list_email_textview);

        TextView textViewDoctorMobileNumber = (TextView) listViewItem.findViewById(R.id.doctor_list_mobilenumber_textview);

        DoctorReg doctor = doctors.get(position);

        textViewDoctorName.setText(doctor.getFullName());

        textViewMedicalCenter.setText(doctor.getMedicalCenter());

        textViewDoctorEmail.setText(doctor.getEmail());

        textViewDoctorMobileNumber.setText(doctor.getTelNo());


        return listViewItem;
    }

}
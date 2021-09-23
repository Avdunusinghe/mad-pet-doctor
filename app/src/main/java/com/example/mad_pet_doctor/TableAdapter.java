package com.example.mad_pet_doctor;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;

import android.widget.ImageButton;

import android.widget.TextView;


import com.example.model.ScheduleModal;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends ArrayAdapter<ScheduleModal> {
    private List<ScheduleModal> scheduleModalArrayList;
    private Activity context;
    private int position;

    public TableAdapter(Activity context, List<ScheduleModal> scheduleModalArrayList) {
        super(context, R.layout.tableview,scheduleModalArrayList);
        this.scheduleModalArrayList = scheduleModalArrayList;
        this.context = context;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

       View listViewItem = inflater.inflate(R.layout.tableview,null,true);

       TextView textViewDoctorNames = (TextView)listViewItem.findViewById(R.id.apptable26);
       TextView textViewDates = (TextView)listViewItem.findViewById(R.id.apptable27);
       TextView textViewTimes = (TextView)listViewItem.findViewById(R.id.apptable28);
       ImageButton UpdateBtn = listViewItem.findViewById(R.id.icondell4);
       ImageButton DeleteBtn = listViewItem.findViewById(R.id.icondel14);


    ScheduleModal scheduleModal = scheduleModalArrayList.get(position);

       textViewDoctorNames.setText(scheduleModal.getDoctorName());
        textViewDates.setText(scheduleModal.getDate());
        textViewTimes.setText(scheduleModal.getTime());


        return listViewItem;
        }
    }



package com.example.mad_pet_doctor;

import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ImageButton;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.ScheduleModal;

import java.util.ArrayList;
import java.util.List;

public class TableAdapterAppointment extends RecyclerView.Adapter<TableAdapterAppointment.ViewHolder> {
    private ArrayList<ScheduleModal> scheduleModalArrayList;
    private Context context;
    int lastPos = -1;
    private TableAdapterAppointment.CourseClickInterface courseClickInterface;


    public TableAdapterAppointment(ArrayList<ScheduleModal> scheduleModalArrayList, Context context, TableAdapterAppointment.CourseClickInterface courseClickInterface) {
        this.scheduleModalArrayList = scheduleModalArrayList;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }

    @Override
    public TableAdapterAppointment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.table_view2,parent,false);
        return new TableAdapterAppointment.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  TableAdapterAppointment.ViewHolder holder, int position) {
        ScheduleModal scheduleModal = scheduleModalArrayList.get(position);
        holder.DoctorName.setText(scheduleModal.getDoctorName());
        holder.Date.setText(scheduleModal.getDate());
        holder.Time.setText(scheduleModal.getTime());
        setAnimation(holder.itemView, position);
        holder.ConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseClickInterface.onCourseClick(position);
            }
        });

        holder.IgnoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                View row = (View) v.getParent();
                ViewGroup container = ((ViewGroup)row.getParent());
                container.removeView(row);
                container.invalidate();
            }
        });
    }


    private void setAnimation(View itemView, int position) {
        if(position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {

        return scheduleModalArrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView DoctorName , Date, Time;
        private ImageButton ConfirmBtn, IgnoreBtn;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            DoctorName = itemView.findViewById(R.id.apptable26);
            Date = itemView.findViewById(R.id.apptable27);
            Time = itemView.findViewById(R.id.apptable28);
            ConfirmBtn= itemView.findViewById(R.id.icondell4);
            IgnoreBtn= itemView.findViewById(R.id.icondel14);

        }
    }

    public interface CourseClickInterface{
        void onCourseClick(int position);
    }
}

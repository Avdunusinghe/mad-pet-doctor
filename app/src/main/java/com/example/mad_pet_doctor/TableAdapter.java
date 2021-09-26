package com.example.mad_pet_doctor;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    private ArrayList<ScheduleModal> scheduleModalArrayList;
    private Context context;
    int lastPos = -1;
   // private TableAdapter.CourseClickInterface courseClickInterface;


    public TableAdapter(ArrayList<ScheduleModal> scheduleModalArrayList, Context context) {
        this.scheduleModalArrayList = scheduleModalArrayList;
        this.context = context;

    }

    @Override
    public TableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tableview,parent,false);
        return new TableAdapter.ViewHolder(view);
    }

   @Override
    public void onBindViewHolder(@NonNull  TableAdapter.ViewHolder holder, int position) {

        ScheduleModal scheduleModal = scheduleModalArrayList.get(position);
        holder.DoctorName.setText(scheduleModal.getDoctorName());
        holder.Date.setText(scheduleModal.getDate());
        holder.Time.setText(scheduleModal.getTime());
        setAnimation(holder.itemView, position);

        holder.UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // courseClickInterface.onCourseClick(position);

            }
        });

        holder.DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // courseClickInterface.onCourseClick(position);
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
        private ImageButton UpdateBtn, DeleteBtn;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            DoctorName = itemView.findViewById(R.id.apptable26);
            Date = itemView.findViewById(R.id.apptable27);
            Time = itemView.findViewById(R.id.apptable28);
            UpdateBtn= itemView.findViewById(R.id.icondell4);
            DeleteBtn= itemView.findViewById(R.id.icondel14);

        }
    }

    /*public interface CourseClickInterface{
        void onCourseClick(int position);
    }*/
}

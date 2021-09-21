package com.example.mad_pet_doctor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.ScheduleModal;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
      private ArrayList<ScheduleModal> scheduleModalArrayList;
      private Context context;
      int lastPos = -1;
    private CourseClickInterface courseClickInterface;

    public ScheduleAdapter(ArrayList<ScheduleModal> scheduleModalArrayList, Context context, CourseClickInterface courseClickInterface) {
        this.scheduleModalArrayList = scheduleModalArrayList;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }


    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.updateschedule,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ScheduleAdapter.ViewHolder holder, int position) {
        ScheduleModal scheduleModal = scheduleModalArrayList.get(position);
        holder.DoctorName.setText(scheduleModal.getDoctorName());
        holder.Date.setText(scheduleModal.getDate());
        holder.Time.setText(scheduleModal.getTime());
        holder.BookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               courseClickInterface.onCourseClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleModalArrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView DoctorName , Date, Time;
        private Button BookBtn, CallBtn;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            DoctorName = itemView.findViewById(R.id.book_doc);
            Date = itemView.findViewById(R.id.book_doc3);
            Time = itemView.findViewById(R.id.book_doc4);
            BookBtn= itemView.findViewById(R.id.buton);
            CallBtn= itemView.findViewById(R.id.call_btn2);
        }
    }

    public interface CourseClickInterface{
        void onCourseClick(int position);
    }
}

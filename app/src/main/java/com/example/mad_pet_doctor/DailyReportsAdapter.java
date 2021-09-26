package com.example.mad_pet_doctor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.CardPaymentsModal;
import com.example.model.DailyReportModal;


import java.util.ArrayList;

public class DailyReportsAdapter  extends RecyclerView.Adapter<DailyReportsAdapter.ViewHolder> {
    private ArrayList<DailyReportModal> dailyReportModalArrayList;
    private Context context;
    int lastPos = -1;



    public DailyReportsAdapter(ArrayList<DailyReportModal> dailyReportModalArrayList, Context context) {
        this.dailyReportModalArrayList = dailyReportModalArrayList;
        this.context = context;

    }

    @Override
    public DailyReportsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dailyreportstableview,parent,false);
        return new DailyReportsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        DailyReportModal dailyReportModal = dailyReportModalArrayList.get(position);
        holder.Date.setText(dailyReportModal.getDate());
        holder.NoOfAppointments.setText(Integer.toString(dailyReportModal.getNumberOfAppointments()));
        holder.FeePerOne.setText(Integer.toString(dailyReportModal.getAppointmentFee()));
        holder.totalFee.setText(Integer.toString(dailyReportModal.getFee()));
        setAnimation(holder.itemView, position);
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

        return dailyReportModalArrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView Date, NoOfAppointments, FeePerOne, totalFee;


        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
           Date = itemView.findViewById(R.id.table4);
           NoOfAppointments = itemView.findViewById(R.id.tablenew4);
           FeePerOne= itemView.findViewById(R.id.table5);
           totalFee=itemView.findViewById(R.id.table6);


        }
    }

    public interface CourseClickInterface{
        void onCourseClick(int position);
    }
}

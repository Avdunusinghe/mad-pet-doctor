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
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.DoctorReg;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class TableAdapterDoc extends RecyclerView.Adapter<TableAdapterDoc.ViewHolder> {
    private ArrayList<DoctorReg> doctorRegArrayList;
    private Context context;
    int lastPos = -1;
    private TableAdapterDoc.CourseClickInterface courseClickInterface;
    private DatabaseReference databaseReference;

    public TableAdapterDoc(ArrayList<DoctorReg> doctorRegArrayList, Context context, TableAdapterDoc.CourseClickInterface courseClickInterface) {
        this.doctorRegArrayList = doctorRegArrayList;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }

    @Override
    public TableAdapterDoc.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tableview,parent,false);
        return new TableAdapterDoc.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  TableAdapterDoc.ViewHolder holder, int position) {
        DoctorReg doctorReg = doctorRegArrayList.get(position);
        holder.DoctorName.setText(doctorReg.getFullName());
        holder.MedicalCenter.setText(doctorReg.getMedicalCenter());
        holder.TelNo.setText(doctorReg.getTelNo());
        setAnimation(holder.itemView, position);

        holder.DeleteBtn.setOnClickListener(new View.OnClickListener() {
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
        return doctorRegArrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView DoctorName , MedicalCenter, TelNo;
        private ImageButton  DeleteBtn;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            DoctorName = itemView.findViewById(R.id.apptable26);
            MedicalCenter = itemView.findViewById(R.id.apptable27);
            TelNo = itemView.findViewById(R.id.apptable28);
            DeleteBtn= itemView.findViewById(R.id.icondel14);

        }
    }

    public interface CourseClickInterface{
        void onCourseClick(int position);
    }
}

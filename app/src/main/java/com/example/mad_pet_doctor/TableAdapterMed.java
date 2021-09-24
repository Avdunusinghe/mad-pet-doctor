package com.example.mad_pet_doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.MedicalCenterReg;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class TableAdapterMed extends RecyclerView.Adapter<TableAdapterMed.ViewHolder>{
    private ArrayList<MedicalCenterReg> medicalCenterRegArrayList;
    private Context context;
    int lastPos = -1;
    private TableAdapterMed.CourseClickInterface courseClickInterface;
    private DatabaseReference databaseReference;

    public TableAdapterMed(ArrayList<MedicalCenterReg> medicalCenterRegArrayList, Context context, TableAdapterMed.CourseClickInterface courseClickInterface) {
        this.medicalCenterRegArrayList = medicalCenterRegArrayList;
        this.context = context;
        this.courseClickInterface = courseClickInterface;
    }

    @Override
    public TableAdapterMed.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tableview,parent,false);
        return new TableAdapterMed.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  TableAdapterMed.ViewHolder holder, int position) {
        MedicalCenterReg medicalCenterReg = medicalCenterRegArrayList.get(position);
        holder.Name.setText(medicalCenterReg.getName());
        holder.Address.setText(medicalCenterReg.getAddress());
        holder.TelNo.setText(medicalCenterReg.getTelNo());
        setAnimation(holder.itemView, position);
        holder.UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseClickInterface.onCourseClick(position);
            }
        });

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
        return medicalCenterRegArrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView Name , Address, TelNo;
        private ImageButton UpdateBtn, DeleteBtn;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.apptable26);
            Address = itemView.findViewById(R.id.apptable27);
            TelNo = itemView.findViewById(R.id.apptable28);
            UpdateBtn= itemView.findViewById(R.id.icondell4);
            DeleteBtn= itemView.findViewById(R.id.icondel14);

        }
    }

    public interface CourseClickInterface{
        void onCourseClick(int position);
    }
}

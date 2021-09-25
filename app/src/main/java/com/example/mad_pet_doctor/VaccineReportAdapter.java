package com.example.mad_pet_doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.PetCardModal;
import com.example.model.VaccineDetailsModal;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VaccineReportAdapter extends RecyclerView.Adapter<VaccineReportAdapter.ViewHolder> {
    private ArrayList<VaccineDetailsModal> vaccineDetailsModalArrayList;
    private Context context;
    int lastPos = -1;

    public VaccineReportAdapter(ArrayList<VaccineDetailsModal> vaccineDetailsModalArrayList, Context context) {
        this.vaccineDetailsModalArrayList = vaccineDetailsModalArrayList;
        this.context = context;
    }

    @Override
    public VaccineReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vaccine_report_list_details,parent,false);
        return new VaccineReportAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VaccineDetailsModal vaccineDetailsModal = vaccineDetailsModalArrayList.get(position);
        holder.VRName.setText(vaccineDetailsModal.getVaccine_Name_Edt());
        holder.VRDate.setText(vaccineDetailsModal.getVaccine_Date_Edt());
        holder.VRNofvaccines.setText(Integer.toString(vaccineDetailsModal.getNo_of_Vaccines()));
        holder.VRPrice.setText(Integer.toString(vaccineDetailsModal.getVaccine_Price_Edt()));
        holder.VRTotalprice.setText(Integer.toString(vaccineDetailsModal.getVaccine_Total_price()));
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
        return vaccineDetailsModalArrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView VRName , VRDate, VRNofvaccines,VRPrice, VRTotalprice;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            VRName = itemView.findViewById(R.id.VR_Name);
            VRDate = itemView.findViewById(R.id.VR_Date);
            VRNofvaccines = itemView.findViewById(R.id.VR_NoofVaccines);
            VRPrice = itemView.findViewById(R.id.VR_Price);
            VRTotalprice = itemView.findViewById(R.id.VR_TotalPrice);
        }
    }

}
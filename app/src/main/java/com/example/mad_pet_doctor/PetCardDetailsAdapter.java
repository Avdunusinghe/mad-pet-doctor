package com.example.mad_pet_doctor;

import android.content.Context;
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

import com.example.model.PetCardModal;
import com.example.model.VaccineDetailsModal;

import java.util.ArrayList;

public class PetCardDetailsAdapter  extends RecyclerView.Adapter<PetCardDetailsAdapter.ViewHolder> {
    private ArrayList<PetCardModal> petCardModalArrayList;
    private Context context;
    int lastPos = -1;

    public PetCardDetailsAdapter(ArrayList<PetCardModal> petCardModalArrayList, Context context) {
        this.petCardModalArrayList = petCardModalArrayList;
        this.context = context;
    }

    @Override
    public PetCardDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pet_card_details_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  PetCardDetailsAdapter.ViewHolder holder, int position) {
        PetCardModal petCardModal = petCardModalArrayList.get(position);
        holder.PetIdPCD.setText(petCardModal.getPet_ID());
        holder.PetNamePCD.setText(petCardModal.getPet_Name());
        holder.PetOwnerNamePCD.setText(petCardModal.getPet_OwnerName());
        setAnimation(holder.itemView, position);

        holder.DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        return petCardModalArrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView PetIdPCD , PetNamePCD, PetOwnerNamePCD;
        private ImageButton DeleteButton;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            PetIdPCD = itemView.findViewById(R.id.PCDId);
            PetNamePCD = itemView.findViewById(R.id.PCDName);
            PetOwnerNamePCD = itemView.findViewById(R.id.PCDOwnerName);
            DeleteButton = itemView.findViewById(R.id.PCDDeleteButton);
        }
    }

}

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


import java.util.ArrayList;

public class DailyReportsAdapter  extends RecyclerView.Adapter<DailyReportsAdapter.ViewHolder> {
    private ArrayList<CardPaymentsModal> cardPaymentModalArrayList;
    private Context context;
    int lastPos = -1;



    public DailyReportsAdapter(ArrayList<CardPaymentsModal> cardPaymentModalArrayList, Context context) {
        this.cardPaymentModalArrayList = cardPaymentModalArrayList;
        this.context = context;

    }

    @Override
    public DailyReportsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dailyreportstableview,parent,false);
        return new DailyReportsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  DailyReportsAdapter.ViewHolder holder, int position) {
        CardPaymentsModal cardPaymentsModal = cardPaymentModalArrayList.get(position);
        holder.RefNo.setText(cardPaymentsModal.getPaymentId());
        holder.CardHolderName.setText(cardPaymentsModal.getCardHolderName());
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

        return cardPaymentModalArrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView CardHolderName, RefNo;


        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            CardHolderName = itemView.findViewById(R.id.table5);
            RefNo = itemView.findViewById(R.id.table1);


        }
    }

    public interface CourseClickInterface{
        void onCourseClick(int position);
    }
}

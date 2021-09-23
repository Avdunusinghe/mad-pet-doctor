package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.CardPaymentsModal;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayDeque;

public class CardPayActivity extends AppCompatActivity {

     private ImageView Hoslogo, CardPic1, CardPic2;
     private EditText CardHolderEdt, CardNoEdt , ExpDateEdt, CVcEdt;
     private CheckBox Confirmbox;
     private TextView CardPayHeading, CardholderName, CardNo , Expdate , CVC;
     private Button PayBtn;
     private FirebaseDatabase firebasedatabase;
     private DatabaseReference databaseReference;
     private String PaymentId;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardpay);

         Hoslogo = findViewById(R.id.hos_logo);
         CardPic1 = findViewById(R.id.cardpic2);
         CardPic2 = findViewById(R.id.cardpic1);
         CardHolderEdt = findViewById(R.id.input_1);
         CardNoEdt = findViewById(R.id.input_2);
         ExpDateEdt = findViewById(R.id.editTextDate2);
         CVcEdt = findViewById(R.id.input_4);
         Confirmbox = findViewById(R.id.card_checkBox);
         CardPayHeading = findViewById(R.id.card_head);
         CardholderName = findViewById(R.id.cardlabel2);
         CardNo = findViewById(R.id.cardlabel);
         Expdate = findViewById(R.id.cardlabel11);
         CVC = findViewById(R.id.cardlabel4);
         PayBtn = findViewById(R.id.bton6);
         firebasedatabase = FirebaseDatabase.getInstance();
         databaseReference = firebasedatabase.getReference("CardPayments");

         PayBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (TextUtils.isEmpty((CardHolderEdt.getText().toString()))) {
                     Toast.makeText(getApplicationContext(), "Please enter CardHolder name", Toast.LENGTH_SHORT).show();
                 } else if (TextUtils.isEmpty((CardNoEdt.getText().toString()))) {
                     Toast.makeText(getApplicationContext(), "Please enter CardNumber", Toast.LENGTH_SHORT).show();
                 } else if (TextUtils.isEmpty((ExpDateEdt.getText().toString()))) {
                     Toast.makeText(getApplicationContext(), "Please enter expiration  date", Toast.LENGTH_SHORT).show();
                 } else if (TextUtils.isEmpty((CVcEdt.getText().toString()))) {
                     Toast.makeText(getApplicationContext(), "Please enter CVC no", Toast.LENGTH_SHORT).show();
                 }else {

                     String Id =databaseReference.push().getKey();
                     String CardHoldername = CardHolderEdt.getText().toString();
                     String CardNumber = CardNoEdt.getText().toString();
                     String ExpiredDate = ExpDateEdt.getText().toString();
                     String CVCNumber = CVcEdt.getText().toString();
                     PaymentId = Id;
                     CardPaymentsModal coursepaymentsmodal = new CardPaymentsModal(CardHoldername, CardNumber, ExpiredDate, CVCNumber, PaymentId);

                     databaseReference.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                             databaseReference.child(PaymentId).setValue(coursepaymentsmodal);
                             Toast.makeText(CardPayActivity.this, "Payment Details Added..", Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(CardPayActivity.this, BookingConfirmationActivity.class));

                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {
                             Toast.makeText(CardPayActivity.this, "error occurs" + error.toString(), Toast.LENGTH_SHORT).show();

                         }
                     });

                 }
             }
         });




    }
}
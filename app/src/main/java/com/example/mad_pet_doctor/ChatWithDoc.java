package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChatWithDoc extends AppCompatActivity {

    private Button chatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_with_doc);
        chatBtn = findViewById(R.id.button11);

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend1 = new Intent(ChatWithDoc.this, ChatWindow.class);
                startActivity(intend1);
            }
        });
    }
}
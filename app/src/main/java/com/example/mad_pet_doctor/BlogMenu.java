package com.example.mad_pet_doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BlogMenu extends AppCompatActivity {

    private Button readMoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blog_menu);
        readMoreBtn = findViewById(R.id.button12);

        readMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend1 = new Intent(BlogMenu.this, BlogContent.class);
                startActivity(intend1);
            }
        });
    }
}
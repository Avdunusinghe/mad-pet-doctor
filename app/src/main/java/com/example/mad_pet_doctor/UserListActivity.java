package com.example.mad_pet_doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.list_view.UserList;
import com.example.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.Internal;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {


    ListView userListView;

    List<User> userList;

    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        Intent intent = getIntent();

        userList = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference("User");

        //userListView = (ListView)findViewById(R.id.user_listview);


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                userList.clear();

                for(DataSnapshot userDataSnapshot:snapshot.getChildren()){

                    User users = userDataSnapshot.getValue(User.class);
                    userList.add(users);
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
package com.example.list_view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mad_pet_doctor.R;
import com.example.model.User;


import java.util.ArrayList;
import java.util.List;

public class UserList extends ArrayAdapter<User> {

    private Activity context;

    List<User> users;

    public UserList(Activity context, List<User> users){

        super(context, R.layout.activity_user_list,users);

        this.context = context;

        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_user_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.userRecordName);

        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.userRecordEmail);

        ImageView deleteButtom = (ImageView) listViewItem.findViewById(R.id.deletebtn1);

        User user = users.get(position);
        textViewName.setText(user.getName());
        textViewEmail.setText(user.getEmail());

        return listViewItem;
    }

}

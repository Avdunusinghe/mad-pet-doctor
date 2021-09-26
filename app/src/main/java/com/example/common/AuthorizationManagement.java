package com.example.common;

import android.content.SharedPreferences;
import android.content.Context;

import com.example.model.Admin;

public class AuthorizationManagement {

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor ;

    String SHARED_PREF_NAME = "session";

    String SESSION_KEY = "session_user";

    public AuthorizationManagement(Context context){

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
    }

    public void saveSession(Admin user){
        //save session of user
        String username = user.getUsername();
        editor.putString(SESSION_KEY, username).commit();

    }

    public String getSession() {
        //return user who's session is saved
        return sharedPreferences.getString(SESSION_KEY, "E");
    }

    public void removeSession() {
        //assign 0 as user is not logged in
        editor.putString(SESSION_KEY, "E").commit();
    }

}

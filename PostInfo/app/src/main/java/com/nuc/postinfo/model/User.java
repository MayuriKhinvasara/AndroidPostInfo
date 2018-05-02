package com.nuc.postinfo.model;

import android.util.Log;

import com.nuc.postinfo.util.Utility;

/**
 * Created by mayurik on 01/05/2018.
 */

public class User {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int id;
    public String name;
    public String username;
    public String email;





    public String getusername() {
        Log.d(Utility.LOG_TAG, " getusername = "+username);
        return username;
    }

    public void setusername(String username) {
        Log.d(Utility.LOG_TAG, " getusername = "+this.username);
        this.username = username;
    }


}

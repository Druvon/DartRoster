package com.highgatestudios.dartroster;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by JBrien on 8/17/2017.
 */

public class Roster {
    public String Name;

    public String[] Positions = new String[18];

    public Roster(){
        for(int i = 0;i<Positions.length;i++){
            Positions[i] = "";
        }
    }
}

package com.highgatestudios.dartroster;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by JBrien on 8/17/2017.
 */

public class Rosters {
    private ArrayList<Roster> rosters;

    public void AddRoster(Activity activity, Roster roster){
        rosters = GetRosters(activity);
        rosters.add(roster);
    }

    public void UpdateRoster(Activity activity, Roster roster){
        ArrayList<Roster> newRosters = new ArrayList<>();

        rosters = GetRosters(activity);
        for(int i = 0;i<rosters.size();i++){
            Roster savedRoster = rosters.get(i);
            if(savedRoster.Name.equalsIgnoreCase(roster.Name)){
                newRosters.add(roster);
            }
            else{
                newRosters.add(savedRoster);
            }
        }

        rosters.clear();
        for(int i = 0;i < newRosters.size();i++){
            rosters.add(newRosters.get(i));
        }

        SaveRosters(activity);
    }
    public Roster GetRoster(Activity activity, String name) {
        rosters = GetRosters(activity);
        for(int i = 0;i<rosters.size();i++){
            Roster savedRoster = rosters.get(i);
            if(savedRoster.Name.equalsIgnoreCase(name)){
                return savedRoster;
            }
        }

        return null;
    }

    public ArrayList<Roster> GetRosters(Activity activity){
        try {
            SharedPreferences sharedPref = activity.getSharedPreferences(activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            String rostersJson = sharedPref.getString("rosters", null);

            if(rostersJson == null)
                return new ArrayList<Roster>();

            Gson gson = new Gson();
            Type typeOfList = new TypeToken<ArrayList<Roster>>(){}.getType();
            rosters = gson.fromJson(rostersJson, typeOfList);
            return rosters;
        }
        catch(Exception e)
        {
        }

        return null;
    }

    public void SaveRosters(Context context){
        try {
            GsonBuilder gsonb = new GsonBuilder();
            Gson mGson = gsonb.create();
            String writeValue = new Gson().toJson(rosters);

            SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("rosters", writeValue);
            editor.commit();
        }
        catch(Exception e) {
        }
    }
}

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

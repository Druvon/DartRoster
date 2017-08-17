package com.highgatestudios.dartroster;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JBrien on 8/9/2017.
 */

public class Team {

    ArrayList<Player> players = new ArrayList<Player>();

    public void AddPlayer(Activity activity, Player player){
        players = GetTeam(activity);
        player.Name = player.Name.trim();
        players.add(player);
    }

    public void RemovePlayer(Activity activity, String playerName){
        players = GetTeam(activity);

        Player p = GetPlayer(activity, playerName);

        if(p == null) return;

        players.remove(p);

        SaveTeam(activity);
    }

    public Player GetPlayer(Activity context, String playerName){
        players = GetTeam(context);
        for(Player p : players){
            if(p.Name.equalsIgnoreCase(playerName))
                return p;
        }
        return null;
    }
    public void UpdatePlayer(Activity context, String playerName, Player player){
        players = GetTeam(context);

        Player p  = GetPlayer(context, playerName);
        if(p == null) return;

        player.Name = player.Name.trim();
        p.Name = player.Name;

        SaveTeam(context);
    }

    public ArrayList<Player> GetTeam(Activity activity){
        try {
            SharedPreferences sharedPref = activity.getSharedPreferences(activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            String team = sharedPref.getString("team", null);

            if(team == null)
                return new ArrayList<Player>();

            Gson gson = new Gson();
            Type typeOfList = new TypeToken<ArrayList<Player>>(){}.getType();
            ArrayList<Player> players = gson.fromJson(team, typeOfList);
            return players;
        }
        catch(Exception e)
        {
        }

        return null;
    }

    public void SaveTeam(Context context){
        try {
            GsonBuilder gsonb = new GsonBuilder();
            Gson mGson = gsonb.create();
            String writeValue = new Gson().toJson(players);

            SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("team", writeValue);
            editor.commit();
        }
        catch(Exception e)
        {
        }
    }
}

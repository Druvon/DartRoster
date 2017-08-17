package com.highgatestudios.dartroster;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EditRoster extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_roster);

        getSupportActionBar().setTitle("Create Roster");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        HandleListView();
    }

    private void HandleListView(){
        ArrayList<String> format = new ArrayList<String>();

        format.add("1st 501");
        format.add("2nd 501");
        format.add("3rd 501");
        format.add("1st Cricket");
        format.add("2nd Cricket");
        format.add("3rd Cricket");
        format.add("1st 301");
        format.add("2nd 301");
        format.add("3rd 301");
        format.add("4th 301");
        format.add("5th 301");
        format.add("6th 301");


        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                format.toArray(new String[format.size()]));

        ListView playerView = (ListView)findViewById(R.id.roster);
        playerView.setAdapter(itemsAdapter);

        playerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

            }
        });
    }
}

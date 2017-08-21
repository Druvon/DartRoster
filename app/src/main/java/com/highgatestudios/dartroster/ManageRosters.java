package com.highgatestudios.dartroster;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ManageRosters extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_add_roster:
                    GetNewRosterName();
                    return true;
                case R.id.navigation_done_rosters:
                    finish();
                    return true;
            }
            return false;
        }

    };

    private void GetNewRosterName(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Roster");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String name = input.getText().toString();
                name = name.trim();
                String validationError = new Validation().ValidatePlayerName(name);

                if(validationError != null){
                    // do something
                    ShowValidationError(validationError);
                    return;
                }

                //TODO: validate if roster name exists

                //TODO: save new roster and move to edit roster
                Roster roster = new Roster();
                roster.Name = name;
                Rosters rosters = new Rosters();
                rosters.AddRoster(ManageRosters.this, roster);
                rosters.SaveRosters(ManageRosters.this);

                LoadRosters();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void LoadRosters(){
        ArrayList<Roster> rosters = new Rosters().GetRosters(this);

        if(rosters == null) return;

        RosterAdapter adapter = new RosterAdapter(this, rosters);

        ListView view = (ListView)findViewById(R.id.rosters);
        view.setAdapter(adapter);
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TextView name = (TextView)view.findViewById(R.id.name);
                String item = name.getText().toString();
                EditRoster(item);
            }
        });


    }

    public void EditRoster(String name){
        Intent intent = new Intent(ManageRosters.this, EditRoster.class);
        intent.putExtra("name", name);
        startActivityForResult(intent, 0);
    }

    public void ShowValidationError(String error){
        AlertDialog.Builder builder = new AlertDialog.Builder(ManageRosters.this);
        builder.setMessage(error).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_rosters);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_rosters);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        LoadRosters();

    }

}

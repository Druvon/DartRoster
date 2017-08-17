package com.highgatestudios.dartroster;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_manageTeam:

                    startActivityForResult(new Intent(HomeActivity.this, ManageTeam.class), 0);

                    return true;
                case R.id.navigation_manageRosters:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onResume() {
        CheckTeamContext();
        super.onResume();
    }

    private void CheckTeamContext(){

        Team team = new Team();
        ArrayList<Player> players = team.GetTeam(this);

        if(players == null || players.size() == 0){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("It looks you do not have a team setup.\nYou must setup a team before continuing.")
                    .setPositiveButton("OK", dialogClickListener).show();

        }
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            MoveToManageTeam();
        }
    };

    private void MoveToManageTeam(){

        startActivityForResult(new Intent(this, ManageTeam.class), 0);

    }
}


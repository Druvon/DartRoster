package com.highgatestudios.dartroster;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ManageTeam extends AppCompatActivity {

    String currentPlayer;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_add_player:
                    GetNewPlayerName();
                    return true;
                case R.id.navigation_done:
                    finish();
                    return true;
            }
            return false;
        }

    };

    public void BeginEditPlayer(Player player){
        EditPlayer(player);
    }

    private void EditPlayer(final Player player){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Player");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        input.setText(player.Name);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String updatedName = input.getText().toString();
                updatedName = updatedName.trim();

                String validationError = new Validation().ValidatePlayerName(updatedName);

                if(validationError != null){
                    // do something
                    ShowValidationError(validationError);
                    return;
                }

                String oldName = player.Name;
                player.Name = updatedName;

                new Team().UpdatePlayer(ManageTeam.this, oldName, player);

                LoadPlayers();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LoadPlayers();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Team team = new Team();
        ArrayList<Player> players = team.GetTeam(this);
        if(players.size() == 0){
            GetNewPlayerName();
        }

    }

    private void GetNewPlayerName(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Player");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String newPlayer = input.getText().toString();
                newPlayer = newPlayer.trim();
                String validationError = new Validation().ValidatePlayerName(newPlayer);

                if(validationError != null){
                    // do something
                    ShowValidationError(validationError);
                    return;
                }

                Team team = new Team();
                team.GetTeam(ManageTeam.this);
                Player player = team.GetPlayer(ManageTeam.this, newPlayer);
                if(player != null){
                    ShowValidationError("Player already exists");
                    return;
                }


                player = new Player();
                player.Name = newPlayer;
                team.AddPlayer(ManageTeam.this, player);

                team.SaveTeam(ManageTeam.this);

                LoadPlayers();

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

    public void ShowValidationError(String error){
        AlertDialog.Builder builder = new AlertDialog.Builder(ManageTeam.this);
        builder.setMessage(error).show();
    }

    private void LoadPlayers(){
        ArrayList<Player> players = new Team().GetTeam(this);

        if(players == null) return;

        ArrayList<String> playerNames = new ArrayList<String>();

        for(Player p : players){
            playerNames.add(p.Name);
        }


        PlayerAdapter playerAdapter = new PlayerAdapter(this, players, ManageTeam.this);

        ListView playerView = (ListView)findViewById(R.id.players);
        playerView.setAdapter(playerAdapter);

    }

    public void DeletePlayer(Player player){
        currentPlayer = player.Name;

        AlertDialog.Builder builder = new AlertDialog.Builder(ManageTeam.this);
        builder.setMessage("Are you sure you want to delete " + player.Name + "?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    new Team().RemovePlayer(ManageTeam.this, currentPlayer);
                    LoadPlayers();
                    Snackbar.make(findViewById(R.id.players), currentPlayer + " successfully deleted", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

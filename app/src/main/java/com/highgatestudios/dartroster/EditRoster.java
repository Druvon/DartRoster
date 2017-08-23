package com.highgatestudios.dartroster;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        String rosterName = this.getIntent().getStringExtra("name");
        toolbar.setTitle(rosterName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LoadPlayers();

        LoadRosterPositions();
    }
    private void SetSelectedPlayer(String player){
        TextView selectedPlayer = (TextView)findViewById(R.id.selectedPlayer);
        selectedPlayer.setText(player);
    }

    private void LoadPlayers(){

        ListView mListView = (ListView) findViewById(R.id.player_selection);
// 1
        final ArrayList<Player> players = new Team().GetTeam(this);
// 2
        final ArrayList<PlayerSelect> listItems = new ArrayList<PlayerSelect>();

        for(int i = 0; i < players.size(); i++){
            Player player = players.get(i);
            PlayerSelect playerSelect = new PlayerSelect();
            playerSelect.Name = player.Name;
            listItems.add(playerSelect);
        }
// 4
        PlayerSelectAdapter adapter = new PlayerSelectAdapter(this, listItems, this);

        //PlayerSelectAdapter adapter = new PlayerSelectAdapter(this, listItems, this);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }
        });
    }

    private void LoadRosterPositions(){

        ListView mListView = (ListView) findViewById(R.id.roster);

        final ArrayList<RosterPosition> listItems = new ArrayList<RosterPosition>();

        AddPosition("#1 501 #1 Shooter", listItems);
        AddPosition("#1 501 #2 Shooter", listItems);
        AddPosition("#2 501 #1 Shooter", listItems);
        AddPosition("#2 501 #2 Shooter", listItems);
        AddPosition("#3 501 #1 Shooter", listItems);
        AddPosition("#3 501 #2 Shooter", listItems);
        AddPosition("#1 Cricket #1 Shooter", listItems);
        AddPosition("#1 Cricket #2 Shooter", listItems);
        AddPosition("#2 Cricket #1 Shooter", listItems);
        AddPosition("#2 Cricket #2 Shooter", listItems);
        AddPosition("#3 Cricket #1 Shooter", listItems);
        AddPosition("#3 Cricket #2 Shooter", listItems);
        AddPosition("#1 301", listItems);
        AddPosition("#2 301", listItems);
        AddPosition("#3 301", listItems);
        AddPosition("#4 301", listItems);
        AddPosition("#5 301", listItems);
        AddPosition("#6 301", listItems);

        RosterPositionAdapter adapter = new RosterPositionAdapter(this, listItems, this);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            }
        });
    }

    private void AddPosition(String positionName, ArrayList<RosterPosition> list){
        RosterPosition position = new RosterPosition();
        position.Name = positionName;
        list.add(position);
    }
}

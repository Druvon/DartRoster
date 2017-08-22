package com.highgatestudios.dartroster;

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

    }
    private void SetSelectedPlayer(String player){
        TextView selectedPlayer = (TextView)findViewById(R.id.selectedPlayer);
        selectedPlayer.setText(player);
    }

    private void LoadPlayers(){

        ListView mListView = (ListView) findViewById(R.id.players);
// 1
        final ArrayList<Player> players = new Team().GetTeam(this);
// 2
        final String[] listItems = new String[players.size()];
// 3
        for(int i = 0; i < players.size(); i++){
            Player player = players.get(i);
            listItems[i] = player.Name;
        }
// 4
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView selectedPlayer = (TextView)view.findViewById(R.id.selectedPlayer);

                String player = listItems[i];
                SetSelectedPlayer(player);
            }
        });
    }
}

package com.highgatestudios.dartroster;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EditRoster extends AppCompatActivity {

    PlayerSelect _currentPlayer;
    PlayerSelectAdapter _adapter;
    String[] _positions = new String[18];
    ArrayList<Player> players;
    ArrayList<PlayerSelect> listItems;

    Button[] _buttons = new Button[18];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_roster);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        String rosterName = this.getIntent().getStringExtra("name");
        toolbar.setTitle(rosterName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ClearPositions();

        LoadPlayers();

        SetupButtons();

    }

    private void ClearPositions(){
        for(int i =0;i<18;i++){
            _positions[i] = "";
        }
    }
    private void LoadPlayers(){

        ListView mListView = (ListView) findViewById(R.id.player_selection);
// 1
        players = new Team().GetTeam(this);
// 2
        listItems = new ArrayList<PlayerSelect>();

        for(int i = 0; i < players.size(); i++){
            Player player = players.get(i);
            PlayerSelect playerSelect = new PlayerSelect();
            playerSelect.Name = player.Name;
            listItems.add(playerSelect);
        }
// 4
        _adapter = new PlayerSelectAdapter(this, listItems, this);

        //PlayerSelectAdapter adapter = new PlayerSelectAdapter(this, listItems, this);
        mListView.setAdapter(_adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                _currentPlayer = listItems.get(i);

            }
        });
    }

    private void SetPosition(String name, int position){
        if(position <= 5){
            RemovePlayerFrom501(name);
        }
        else if(position > 5 && position <= 11){
            RemovePlayerFromCricket(name);
        }
        else {
            RemovePlayerFrom301(name);
        }

        _positions[position] = name;

        RefreshButtons();

        RefreshPlayerSelectionView();
    }

    private void RefreshPlayerSelectionView(){

        for(int i = 0;i<listItems.size();i++){
            PlayerSelect playerSelect = listItems.get(i);
            playerSelect.Playing501 = false;
            playerSelect.PlayingCricket = false;
            playerSelect.Playing301 = false;
        }

        for(int i = 0;i<6;i++){
            SetIsPlaying501(_positions[i]);
        }
        for(int i = 6;i<12;i++){
            SetIsPlayingCricket(_positions[i]);
        }
        for(int i = 12;i<18;i++){
            SetIsPlaying301(_positions[i]);
        }
    }

    private void SetIsPlaying501(String name){
        if(name.isEmpty()) return;

        for(int i = 0;i<listItems.size();i++){
            PlayerSelect playerSelect = listItems.get(i);
            if(playerSelect.Name.equalsIgnoreCase(name)){
                playerSelect.Playing501 = true;
            }
        }
    }
    private void SetIsPlayingCricket(String name){
        if(name.isEmpty()) return;

        for(int i = 0;i<listItems.size();i++){
            PlayerSelect playerSelect = listItems.get(i);
            if(playerSelect.Name.equalsIgnoreCase(name)){
                playerSelect.PlayingCricket = true;
            }
        }
    }
    private void SetIsPlaying301(String name){
        if(name.isEmpty()) return;

        for(int i = 0;i<listItems.size();i++){
            PlayerSelect playerSelect = listItems.get(i);
            if(playerSelect.Name.equalsIgnoreCase(name)){
                playerSelect.Playing301 = true;
            }
        }
    }


    private void RemovePlayerFrom501(String player){
        for(int i = 0;i<=5;i++){
            if(_positions[i].equalsIgnoreCase(player)){
                _positions[i] = "";
            }
        }
    }
    private void RemovePlayerFromCricket(String player){
        for(int i = 6;i<=11;i++){
            if(_positions[i].equalsIgnoreCase(player)){
                _positions[i] = "";
            }
        }
    }
    private void RemovePlayerFrom301(String player){
        for(int i = 12;i<=17;i++){
            if(_positions[i].equalsIgnoreCase(player)){
                _positions[i] = "";
            }
        }
    }

    private void RefreshButtons(){

        for(int i = 0; i < _positions.length; i++){
            _buttons[i].setText(_positions[i]);
        }
        _adapter.notifyDataSetChanged();
    }

    private void SetupButtons(){
        SetupButton(R.id.btn_501_1_1, 0);
        SetupButton(R.id.btn_501_1_2, 1);
        SetupButton(R.id.btn_501_2_1, 2);
        SetupButton(R.id.btn_501_2_2, 3);
        SetupButton(R.id.btn_501_3_1, 4);
        SetupButton(R.id.btn_501_3_2, 5);
        SetupButton(R.id.btn_cricket_1_1, 6);
        SetupButton(R.id.btn_cricket_1_2, 7);
        SetupButton(R.id.btn_cricket_2_1, 8);
        SetupButton(R.id.btn_cricket_2_2, 9);
        SetupButton(R.id.btn_cricket_3_1, 10);
        SetupButton(R.id.btn_cricket_3_2, 11);
        SetupButton(R.id.btn_301_1, 12);
        SetupButton(R.id.btn_301_2, 13);
        SetupButton(R.id.btn_301_3, 14);
        SetupButton(R.id.btn_301_4, 15);
        SetupButton(R.id.btn_301_5, 16);
        SetupButton(R.id.btn_301_6, 17);
    }
    private void SetupButton(int buttonId, final int position){
        _buttons[position] = (Button)findViewById(buttonId);
        _buttons[position].setTag(position);

        _buttons[position].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(_currentPlayer == null) return;
                Button b = (Button)v;
                Integer buttonTag = (Integer)b.getTag();
                SetPosition(_currentPlayer.Name, buttonTag);
            }
        });

    }
}

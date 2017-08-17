package com.highgatestudios.dartroster;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class PlayerAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Player> mDataSource;
    private ManageTeam _manageTeam;

    public PlayerAdapter(Context context, ArrayList<Player> items, ManageTeam manageTeam) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _manageTeam = manageTeam;
    }

    //1
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.listview_item_player, parent, false);

        // 1
        final Player player = (Player) getItem(position);

        // Get title element
        TextView playerView = (TextView) rowView.findViewById(R.id.playerName);
        ImageView editPlayer = (ImageView)rowView.findViewById(R.id.editPlayer);
        ImageView deletePlayer = (ImageView)rowView.findViewById(R.id.deletePlayer);

        editPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _manageTeam.BeginEditPlayer(player);
            }
        });
        deletePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _manageTeam.DeletePlayer(player);
            }
        });

        // 2
        playerView.setText(player.Name);


        return rowView;
    }
}

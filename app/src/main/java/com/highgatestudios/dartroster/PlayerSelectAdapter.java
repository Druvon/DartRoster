package com.highgatestudios.dartroster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class PlayerSelectAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<PlayerSelect> mDataSource;
    private EditRoster _editRoster;

    public PlayerSelectAdapter(Context context, ArrayList<PlayerSelect> items, EditRoster editRoster) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        _editRoster = editRoster;
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
        View rowView = mInflater.inflate(R.layout.listview_item_playerselect, parent, false);

        // 1
        final PlayerSelect player = (PlayerSelect) getItem(position);

        // Get title element
        TextView playerView = (TextView) rowView.findViewById(R.id.playerName);

        // 2
        playerView.setText(player.Name);


        return rowView;
    }
}

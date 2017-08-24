package com.highgatestudios.dartroster;

import android.content.Context;
import android.content.res.ColorStateList;
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
    public PlayerSelect getItem(int position) {
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
        TextView countView = (TextView) rowView.findViewById(R.id.count);

        // 2
        playerView.setText(player.Name);

        int playCount = 0;
        if(player.Playing501) playCount++;
        if(player.PlayingCricket) playCount++;
        if(player.Playing301) playCount++;

        if(player.Playing501){
            ImageView _501 = (ImageView) rowView.findViewById(R.id._501);
            _501.setBackgroundTintList(_501.getContext().getResources().getColorStateList(R.color.background501));

        }
        if(player.PlayingCricket){
            ImageView _cricket = (ImageView) rowView.findViewById(R.id._cricket);
            _cricket.setBackgroundTintList(_cricket.getContext().getResources().getColorStateList(R.color.backgroundCricket));

        }
        if(player.Playing301){
            ImageView _301 = (ImageView) rowView.findViewById(R.id._301);
            _301.setBackgroundTintList(_301.getContext().getResources().getColorStateList(R.color.background301));

        }

        countView.setText(Integer.toString(playCount));

        return rowView;
    }
}

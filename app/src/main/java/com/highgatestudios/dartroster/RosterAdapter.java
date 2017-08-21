package com.highgatestudios.dartroster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JBrien on 8/14/2017.
 */

public class RosterAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Roster> mDataSource;

    public RosterAdapter(Context context, ArrayList<Roster> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View rowView = mInflater.inflate(R.layout.listview_item_roster, parent, false);

        // 1
        final Roster roster = (Roster) getItem(position);

        // Get title element
        TextView nameView = (TextView) rowView.findViewById(R.id.name);

        // 2
        nameView.setText(roster.Name);


        return rowView;
    }
}

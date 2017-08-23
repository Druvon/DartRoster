package com.highgatestudios.dartroster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class RosterPositionAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<RosterPosition> mDataSource;
    private EditRoster _editRoster;

    public RosterPositionAdapter(Context context, ArrayList<RosterPosition> items, EditRoster editRoster) {
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
        View rowView = mInflater.inflate(R.layout.listview_item_rosterposition, parent, false);
        // 1
        final RosterPosition rosterPosition = (RosterPosition) getItem(position);

        // Get title element
        TextView nameView = (TextView) rowView.findViewById(R.id.roster_position);

        // 2
        nameView.setText(rosterPosition.Name);


        return rowView;
    }
}

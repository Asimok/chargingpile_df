package com.example.chargingpile.cdg_info;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.chargingpile.R;

import java.util.ArrayList;


public class cdg_info_adapter extends BaseAdapter {
    public int count;
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<cdg_info_datainfo> data;

    public cdg_info_adapter(Context context, int count, ArrayList<cdg_info_datainfo> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.resource = resource;
        this.count = count;
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
    }


    @Override
    public int getCount() {
        return data.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context, R.layout.show_cdg_info, null);


        TextView bikeShedId = view.findViewById(R.id.bikeShedId);
        TextView longitude = view.findViewById(R.id.longitude);
        TextView latitude = view.findViewById(R.id.latitude);
        TextView bikeshedName = view.findViewById(R.id.bikeshedName);
        TextView type = view.findViewById(R.id.type);

        bikeShedId.setText(data.get(position).getGrp());
        longitude.setText(data.get(position).getUuid());
        latitude.setText(data.get(position).getNo());
        bikeshedName.setText(data.get(position).getCpID());
        type.setText(data.get(position).getBikeshedId());


        return view;
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }
}

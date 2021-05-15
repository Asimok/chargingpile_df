package com.example.chargingpile.MoneyStandard_info;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.chargingpile.R;

import java.util.ArrayList;


public class money_info_adapter extends BaseAdapter {
    public int count;
    protected Context context;
    protected LayoutInflater inflater;
    protected int resource;
    protected ArrayList<money_datainfo> data;

    public money_info_adapter(Context context, int count, ArrayList<money_datainfo> data) {
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

        View view = View.inflate(context, R.layout.show_money, null);


        TextView price = view.findViewById(R.id.price);
        TextView time = view.findViewById(R.id.time);
        TextView uuid = view.findViewById(R.id.uuid);
        TextView bsId = view.findViewById(R.id.bsId);

        price.setText(data.get(position).getPrice());
        time.setText(data.get(position).getTime());
        bsId.setText(data.get(position).getBsId());
        uuid.setText(data.get(position).getUuid());



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

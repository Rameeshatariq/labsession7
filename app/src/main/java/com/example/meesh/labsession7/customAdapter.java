package com.example.meesh.labsession7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by meesh on 2/22/2018.
 */

public class customAdapter extends ArrayAdapter<DataModel>{
    public static LayoutInflater layoutInflater = null;

    static class viewHolder {
        TextView name;
        TextView email;
        TextView phone;
    }

    public customAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DataModel> objects) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder = new viewHolder();
        DataModel data = getItem(position);
        View rowView;
        rowView = layoutInflater.inflate(R.layout.customlist, null);
        viewHolder.name = (TextView) rowView.findViewById(R.id.textView);
        viewHolder.email = (TextView) rowView.findViewById(R.id.textView1);
        viewHolder.phone = (TextView) rowView.findViewById(R.id.textView2);
        viewHolder.name.setText(data.getName());
        viewHolder.email.setText(data.getEmail());
        viewHolder.phone.setText(data.getPhone());

        return rowView;
    }
}
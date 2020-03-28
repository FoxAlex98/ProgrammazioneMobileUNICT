package com.aee.mytodolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    public ArrayList<String> tdi;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull Object[] objects){
        super(context, resource, objects);
    }

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> objects){
        super(context, resource, objects);
        this.tdi = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //pompa per "gonfiare" le viste
        LayoutInflater inf = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inf.inflate(R.layout.custom_item_list, null);
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        tv.setText(getItem(position).toString());

        //si può fare in modo più diretto così
        //tv.setText(tdi.get(position));

        ImageView iv = (ImageView) convertView.findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tdi.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}

package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    private ArrayList<ItemData> todolist;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemData> objects) {
        super(context, resource, objects);
        this.todolist = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //colui che ci permette di costruire le view
        LayoutInflater inf = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inf.inflate(R.layout.custom_item_list,null);

        TextView itemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
        TextView itemDescr = (TextView) convertView.findViewById(R.id.itemDescr);
        //CheckBox itemCheckBox = (CheckBox) convertView.findViewById(R.id.itemCheckBox);
        ImageView imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);

        ItemData item = (ItemData) getItem(position);
        itemTitle.setText(item.getTitle());
        itemDescr.setText(item.getDescr());


        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todolist.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View.OnKeyListener;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public EditText myEditText;
    public ArrayList<String> todoItems;
    public ArrayAdapter<String> aa;
    public ListView myListView;

    private static int indexUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indexUp = -1;

        myListView = (ListView) findViewById(R.id.myListView);
        myEditText = (EditText) findViewById(R.id.myEditText);

        todoItems = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
        myListView.setAdapter(aa);

        myEditText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    if(indexUp == -1)
                        todoItems.add(0, myEditText.getText().toString());
                    else {
                        todoItems.set(indexUp, myEditText.getText().toString());
                        indexUp = -1;
                    }

                    myEditText.setText("");
                    aa.notifyDataSetChanged();
                    return true;
                }
                return false;

            }
        });

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                aa.notifyDataSetChanged();
                return false;
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myEditText.setText(todoItems.get(position));
                indexUp = position;
            }
        });
    }



}

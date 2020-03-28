package com.aee.mytodolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ToDoList extends AppCompatActivity {

    public EditText myEditText;
    public ArrayList<String> myToDoList;
    public CustomAdapter myArrayAdapter;
    public ListView myListView;
    private static int indexUp;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        indexUp = -1;
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.myListView);
        myEditText = (EditText) findViewById(R.id.myEditView);

        myToDoList = new ArrayList<String>();

        int resID = R.layout.custom_item_list;

        myArrayAdapter = new CustomAdapter(this, resID, myToDoList);
        myListView.setAdapter(myArrayAdapter);

        //myArrayAdapter.notifyDataSetChanged();



        myEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                    if(keyCode == KeyEvent.KEYCODE_ENTER){
                        if(indexUp == -1)
                            myToDoList.add(0,myEditText.getText().toString());
                        else
                        {
                            myToDoList.set(indexUp,myEditText.getText().toString());
                            indexUp=-1;
                        }
                        myEditText.setText("");
                        myArrayAdapter.notifyDataSetChanged();
                        return true;
                    }
                return false;
            }
        });

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myToDoList.remove(position);
                myArrayAdapter.notifyDataSetChanged();
                return true;
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myEditText.setText(myToDoList.get(position));
                indexUp = position;

            }
        });

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                view.findViewById(R.id.iv).setVisibility(View.VISIBLE);
                return true;
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        Toast t;
        t = Toast.makeText(getApplicationContext(),"OnStart", Toast.LENGTH_SHORT);
        t.show();
    }


}

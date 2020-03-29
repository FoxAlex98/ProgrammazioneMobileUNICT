package com.aee.mytodolist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ToDoList extends AppCompatActivity {

    public EditText myEditText;
    public ArrayList<String> myToDoList;
    public CustomAdapter myArrayAdapter;
    public ListView myListView;
    public Button insButton, delButton, upButton;
    public static int indexUp;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        indexUp = -1;
        setContentView(R.layout.activity_main);

        myListView = (ListView) findViewById(R.id.myListView);
        myEditText = (EditText) findViewById(R.id.myEditView);
        insButton = (Button) findViewById(R.id.InsertButton);
        delButton = (Button) findViewById(R.id.DeleteButton);
        upButton = (Button) findViewById(R.id.UpdateButton);

        myToDoList = new ArrayList<String>();

        int resID = R.layout.custom_item_list;

        myArrayAdapter = new CustomAdapter(this, resID, myToDoList);
        myListView.setAdapter(myArrayAdapter);

        //Listeners

        MyClickListener mc = new MyClickListener(myEditText,myToDoList,myArrayAdapter);

        insButton.setOnClickListener(mc);
        delButton.setOnClickListener(mc);
        upButton.setOnClickListener(mc);

        myEditText.setOnKeyListener(new MyKeyListener(myEditText,myToDoList,myArrayAdapter));

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
        loadData();
        Toast.makeText(getApplicationContext(),"OnStart", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onResume(){
        super.onResume();
        loadData();
        Toast.makeText(getApplicationContext(), "OnResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop(){
        super.onStop();
        saveData();
        Toast.makeText(getApplicationContext(), "OnStop", Toast.LENGTH_SHORT).show();
    }


    public void saveData(){

        try {
            FileOutputStream fos = openFileOutput("tdlfile", Context.MODE_PRIVATE);

            for(int i=0;i<myToDoList.size();i++){
                try{
                    String s = myToDoList.get(i) + "#";
                    fos.write(s.getBytes());
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }
            }
            fos.close();
            Toast.makeText(getApplicationContext(), "Salvataggio Completato", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadData(){
        try{
            FileInputStream fis = new FileInputStream("tdlfile");
            StringBuffer fileBuffer = new StringBuffer("");

            byte[] buffer = new byte[1024];
            String[] list = null;

            if(fis.read(buffer) != -1){
                String items = new String(buffer);
                list = items.split("#");
            }

            for(int i=0;i<list.length-1;i++)
                myToDoList.add(list[i]);

            Toast.makeText(getApplicationContext(), "Caricamento completato", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

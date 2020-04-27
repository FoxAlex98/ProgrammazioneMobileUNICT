package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public EditText myEditText;
    public ListView myListView;
    public ArrayList<ItemData> toDoList;
    //public ArrayAdapter<String> aa; // "ponte" tra sorgente dati e ListView
    public CustomAdapter aa;
    public Button btnAdd;
    public int indexItem;

    //nell'ottica di MVC: la sorgente dati toDoList è il Model, myListView è la View, l'ArrayAdapter aa è il Controller

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        indexItem = -1;
        myEditText = (EditText) findViewById(R.id.myEditText);
        myListView = (ListView) findViewById(R.id.myListView);
        btnAdd = (Button) findViewById(R.id.btnAdd);


        toDoList = new ArrayList<ItemData>();

        //int itemID = android.R.layout.simple_list_item_1;
        int itemID = R.layout.custom_item_list;

        //aa = new ArrayAdapter<String>(this, itemID, toDoList);
        aa = new CustomAdapter(this, itemID, toDoList);
        myListView.setAdapter(aa);

        /*
        MyKeyListener myKeyListener = new MyKeyListener(myEditText,toDoList,aa);
        myEditText.setOnKeyListener(myKeyListener);
        */

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTextInput = myEditText.getText().toString();
                if(!editTextInput.isEmpty()) {
                    if(indexItem == -1) {
                        ItemData newItem = new ItemData(editTextInput,"",false);
                        toDoList.add(0, newItem);
                    }
                    else {
                        toDoList.get(indexItem).setTitle(editTextInput);
                        indexItem = -1;
                    }
                    myEditText.setText("");
                    aa.notifyDataSetChanged();
                }

            }
        });

        /*
        myEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN) {
                    if(keyCode == KeyEvent.KEYCODE_ENTER) {
                        if(indexItem == -1)
                            toDoList.add(0, myEditText.getText().toString());
                        else {
                            toDoList.set(indexItem, myEditText.getText().toString());
                            indexItem = -1;
                        }
                        myEditText.setText("");
                        aa.notifyDataSetChanged();
                        return true;
                    }
                }
                return false;
            }
        });
         */

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myEditText.setText(toDoList.get(position).getTitle());
                indexItem = position;
                return true;
            }
        });


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent request = new Intent(getApplicationContext(), DescrActivity.class);
                request.putExtra("itempos",position);
                startActivityForResult(request,1);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        aa.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast t = Toast.makeText(getApplicationContext(), "OnStart",Toast.LENGTH_SHORT);
        t.show();

        /*
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast t = Toast.makeText(getApplicationContext(), "OnResume",Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast t = Toast.makeText(getApplicationContext(), "OnPause",Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast t = Toast.makeText(getApplicationContext(), "OnStop",Toast.LENGTH_SHORT);
        t.show();

        /*
        try {
            saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast t = Toast.makeText(getApplicationContext(), "OnDestroy",Toast.LENGTH_SHORT);
        t.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK) {
            int itempos = data.getIntExtra("itempos",0);
            String descr = data.getStringExtra("descr");

            ItemData item = toDoList.get(itempos);
            item.setDescr(descr);
            toDoList.set(itempos,item);
        }
    }

    /*
    public void saveData() throws IOException {
        String fileName = "todolist.txt";
        FileOutputStream fout;

        fout = openFileOutput(fileName, Context.MODE_PRIVATE);
        for (int i=0; i<toDoList.size();i++) {
            String item = toDoList.get(i).toString() + ";";
            fout.write(item.getBytes());
        }
        fout.close();
    }

    public void loadData() throws IOException {
        String fileName = "todolist.txt";
        FileInputStream fin;

        fin = openFileInput(fileName);
        byte[] buffer = new byte[1024];
        String[] list = {};
        if(fin.read(buffer) != -1) {
            String A = new String(buffer);
            list = A.split(";");
        }
        toDoList.removeAll(toDoList);
        for (int i=0; i<list.length-1;i++) {
            toDoList.add(list[i]);
        }
        aa.notifyDataSetChanged();
    }
     */
}

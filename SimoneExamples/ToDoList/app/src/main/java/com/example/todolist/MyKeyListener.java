package com.example.todolist;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;

public class MyKeyListener implements View.OnKeyListener {

    private EditText myEditText;
    private ArrayList<String> toDoList;
    private ArrayAdapter<String> aa;

    public MyKeyListener(EditText myEditText, ArrayList<String> toDoList, ArrayAdapter<String> aa) {
        this.myEditText = myEditText;
        this.toDoList = toDoList;
        this.aa = aa;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN) {
            if(keyCode == KeyEvent.KEYCODE_ENTER) {
                toDoList.add(0, myEditText.getText().toString());
                myEditText.setText("");
                aa.notifyDataSetChanged();
                return true;
            }
        }
        return false;
    }
}

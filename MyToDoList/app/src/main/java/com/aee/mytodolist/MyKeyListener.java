package com.aee.mytodolist;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;

public class MyKeyListener implements View.OnKeyListener {

    private EditText et;
    private ArrayList<String> al;
    private ArrayAdapter<String> aa;

    public MyKeyListener(EditText et, ArrayList<String> al, ArrayAdapter<String> aa){
        this.et=et;
        this.al=al;
        this.aa=aa;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
            if(ToDoList.indexUp == -1){
                al.add(0, et.getText().toString());
            }
            else{
                al.set(ToDoList.indexUp,et.getText().toString());
                ToDoList.indexUp = -1;
            }
            aa.notifyDataSetChanged();
            et.setText("");
            return true;
        }
        return false;
    }
}

package com.aee.mytodolist;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MyClickListener implements View.OnClickListener {

    private EditText et;
    private ArrayList<String> al;
    private ArrayAdapter<String> aa;

    public MyClickListener(EditText et, ArrayList<String> al, ArrayAdapter<String> aa) {
        this.et = et;
        this.al = al;
        this.aa = aa;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.InsertButton){
            if(!et.getText().toString().isEmpty()){
                addTask();
            }
        }
        if(v.getId() == R.id.DeleteButton){
            if(ToDoList.indexUp != -1){
                if(!et.getText().toString().isEmpty()){
                    removeTask();
                }
            }
            else
                et.setText("");

        }
        if(v.getId() == R.id.UpdateButton && ToDoList.indexUp!=-1){
            if(!et.getText().toString().isEmpty()){
                editTask();
            }
        }
    }

    public void addTask(){
        al.add(0,et.getText().toString());
        aa.notifyDataSetChanged();
        et.setText("");
    }

    public void editTask(){
        al.set(ToDoList.indexUp,et.getText().toString());
        ToDoList.indexUp = -1;
        aa.notifyDataSetChanged();
        et.setText("");
    }

    public void removeTask(){
        al.remove(ToDoList.indexUp);
        ToDoList.indexUp = -1;
        aa.notifyDataSetChanged();
        et.setText("");
    }

}

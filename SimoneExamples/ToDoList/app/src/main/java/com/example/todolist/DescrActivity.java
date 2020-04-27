package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DescrActivity extends AppCompatActivity {

    private EditText editDescr;
    private Button btnDescr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descr);
        final Intent i = getIntent();

        editDescr = (EditText) findViewById(R.id.editDescr);
        btnDescr = (Button) findViewById(R.id.btnDescr);

        btnDescr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent response = new Intent();
                response.putExtra("descr", editDescr.getText().toString());
                response.putExtra("itempos", i.getIntExtra("itempos",0));
                setResult(RESULT_OK,response);
                finish();
            }
        });

    }
}

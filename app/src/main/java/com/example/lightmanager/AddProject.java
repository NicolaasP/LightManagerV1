package com.example.lightmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import static com.example.lightmanager.MainActivity.projectName;
import static com.example.lightmanager.Areas.areas;
import static com.example.lightmanager.Areas.a;
import static com.example.lightmanager.Areas.as;

public class AddProject extends AppCompatActivity {
    private String[] projs;
    private EditText prName;
    private Button add;
    protected static volatile boolean clear = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        setTitle("New Project");
        prName = findViewById(R.id.projName);
        add = findViewById(R.id.addPB);
        setListeners();
    }

    private void setListeners(){
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onAdd();
            }
        });

        prName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                key(keyEvent.getKeyCode());
                return true;
            }
        });
    }

    private void key(int code){
        if(code == 66) onAdd();
    }

    private void list(){
        projs = new File(getFilesDir() + "/Projects/").list();
    }

    private void onAdd(){
        list();
        String name = prName.getText().toString();
        if(name == null || name.equals("")){
            Toast.makeText(this, "Please enter a project name!", Toast.LENGTH_LONG).show();
            return;
        }
        for(String p:projs){
            if(p.equals(name + ".txt")) {
                Toast.makeText(this, "Project with name: " + name + " already exists!", Toast.LENGTH_LONG).show();
                return;
            }
        }
        clear = true;
        projectName = name;
        Intent intent = new Intent(this, Areas.class);
        startActivity(intent);
        finish();
    }
}

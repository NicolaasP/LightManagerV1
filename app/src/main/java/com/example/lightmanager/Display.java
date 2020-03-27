package com.example.lightmanager;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.lightmanager.Areas.areas;
import static com.example.lightmanager.Areas.as;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static com.example.lightmanager.MainActivity.projectName;

public class Display extends AppCompatActivity {
    private ListView lv;
    private Button save;
    public static volatile boolean disp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        setTitle(projectName);
        save = findViewById(R.id.save);
        lv = findViewById(R.id.lv);
        setAdapter();
        setListeners();
    }

    private void setAdapter(){
        disp = true;
        ArrayAdapter<Area> adapter = new ArrayAdapter<Area>(this, R.layout.list_btxt, R.id.li, as);
        lv.setAdapter(adapter);
    }

    private void setListeners(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    private void save(){
        try {
            File folder = new File(getFilesDir() + "/Projects/");
            folder.mkdir();
            ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(folder + "/" + projectName + ".txt"));
            oOut.writeObject(areas);
            Toast.makeText(this, "Project: " + projectName + " saved!", Toast.LENGTH_LONG).show();
        }catch(FileNotFoundException e){
            Toast.makeText(this, "Could not create file: " + projectName, Toast.LENGTH_LONG).show();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }catch(IOException e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}

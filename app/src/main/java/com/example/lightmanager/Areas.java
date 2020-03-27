package com.example.lightmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import static com.example.lightmanager.MainActivity.projectName;
import static com.example.lightmanager.MainActivity.areas1;
import static com.example.lightmanager.AddProject.clear;

import static com.example.lightmanager.Display.disp;



public class Areas extends AppCompatActivity {

    protected static volatile Area[] areas = new Area[0];
    protected static volatile Area a = null;
    protected static volatile Area[] as = null;
    private boolean add = false;
    private Button addArea;
    private Button fin;
    private ListView lv;
    private Button load;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if(projectName == null || projectName.equals("")) super.onBackPressed();
        setContentView(R.layout.activity_areas);
        setTitle(projectName);
        addArea = findViewById(R.id.AddArea);
        lv = findViewById(R.id.listView);
        fin = findViewById(R.id.fin);
        load = findViewById(R.id.loadB);
        delete = findViewById(R.id.deleteB);
        if(areas1 != null) areas = areas1;
        createListeners();
        setAdapter();
    }

    private void createListeners(){
        addArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newArea();
            }
        });

        fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onSele(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                del();
            }
        });

    }

    private void onSele(int i){
        a = areas[i];
        Intent intent = new Intent(getApplicationContext(), LightSelecter.class);
        startActivity(intent);
    }

    private void newArea(){
        Intent intent = new Intent(this, AddArea.class);
        startActivity(intent);
        add = true;
    }


    private void addArea(Area area){
        Area[] temp = new Area[areas.length + 1];
        for(int i = 0; i < areas.length; i++) temp[i] = areas[i];
        temp[areas.length] = area;
        areas = new Area[temp.length];
        for(int i = 0; i < areas.length; i++) areas[i] = temp[i];

        setAdapter();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(clear){
            areas = new Area[0];
            a = null;
            as = null;
            clear = false;
        }
        if(a != null && add){
            addArea(a);
            a = null;
            add = false;
        }
        setAdapter();
    }

    private void done(){
        as = areas;
        Intent i = new Intent(this, Display.class);
        startActivity(i);
    }

    private void setAdapter(){
        disp = false;
        ArrayAdapter<Area> adapter = new ArrayAdapter<Area>(getApplicationContext(), R.layout.list_btxt, R.id.li, areas);
        lv.setAdapter(adapter);
    }

    private void del(){
        Intent intent = new Intent(this, Delete.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        areas = null;
        a = null;
        as = null;
        super.onBackPressed();
    }

}

//TODO Google sheets api
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity{
    private Button newP;
    private Button about;
    private ListView projs;
    private String[] projects;
    private static final String VERSION = "2.1";
    protected static Area[] areas1 = new Area[0];
    protected static String projectName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Light Manager");
        newP = findViewById(R.id.addProject);
        projs = findViewById(R.id.projects);
        about = findViewById(R.id.about);
        setListeners();
        setAdapter();
    }

    private void setListeners(){
        newP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdd();
            }
        });
        projs.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView av, View view, int i, long l){
                onSele(i);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ab();
            }
        });
    }

    private void ab(){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    private void list(){
        File file = new File(getFilesDir() + "/Projects/");
        projects = file.list();
        for(int i = 0; i < projects.length; i++)
            projects[i] = projects[i].substring(0, projects[i].indexOf("."));
    }

    private void setAdapter(){
        list();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_btxt, R.id.li, projects);
        projs.setAdapter(adapter);
    }

    private void onAdd(){
        Intent intent = new Intent(this, AddProject.class);
        startActivity(intent);
    }

    private void onSele(int i){
        projectName = projects[i];
        areas1 = null;
        File f = new File(getFilesDir() + "/Projects/" + projectName + ".txt");
        if(!f.exists()) Toast.makeText(this, "File: " + f.getAbsolutePath() + " does not exist!", Toast.LENGTH_LONG).show();
        try{
            ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(f));
            areas1 = (Area[]) oIn.readObject();
            Intent intent = new Intent(this, Areas.class);
            startActivity(intent);
        }catch(FileNotFoundException e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }catch(IOException e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }catch(ClassNotFoundException e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();
    }
}
package com.example.lightmanager;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.lightmanager.Areas.areas;
import static com.example.lightmanager.MainActivity.projectName;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


public class Load extends AppCompatActivity {
    private ListView lv;
    private String[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(projectName == null || projectName.equals("")) super.onBackPressed();
        setContentView(R.layout.activity_load);
        setTitle("Load File");
        lv = findViewById(R.id.loadLV);
        setListener();
        setAdapter();
    }

    private void list(){
            File f = new File(getFilesDir() + "/Projects");
            String[] fs = f.list();
            for(int i = 0; i < fs.length; i++){
                fs[i] = fs[i].substring(0, fs[i].indexOf("."));
            }
            files = fs;
    }

    private void setListener() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onSele(i);
            }
        });
    }

    private void onSele(int i){
        File f = new File(getFilesDir() + "/Projects/" + files[i] + ".txt");
        loadFile(f);
    }

    private void setAdapter(){
        list();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_btxt, R.id.li, files);
        lv.setAdapter(adapter);
    }

    protected void loadFile(File f){
        if(!f.exists()) Toast.makeText(this, "File: " + f.getAbsolutePath() + " does not exist!", Toast.LENGTH_LONG).show();
        try{
            ObjectInputStream oIn = new ObjectInputStream(new FileInputStream(f));
            areas = (Area[]) oIn.readObject();
            super.onBackPressed();
        }catch(FileNotFoundException e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }catch(IOException e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }catch(ClassNotFoundException e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}

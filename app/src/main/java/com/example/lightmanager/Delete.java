package com.example.lightmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.lightmanager.Areas.areas;
import static com.example.lightmanager.AddProject.clear;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class Delete extends AppCompatActivity {
    private ListView lv;
    private String[] files;
    private File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        setTitle("Delete");
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
        f = new File(getFilesDir() + "/Projects/" + files[i] + ".txt");
        if(!f.exists()) Toast.makeText(this, "File: " + f.getAbsolutePath() + " does not exist!", Toast.LENGTH_LONG).show();
        else alert();
    }

    private void setAdapter(){
        list();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_btxt, R.id.li, files);
        lv.setAdapter(adapter);
    }

    private void alert(){
        String name = f.getName();
        new AlertDialog.Builder(this).setTitle("Are you sure")
                .setMessage("Are you sure you want to delete: " + name + "?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        del();
                    }
                }).setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void del(){
        String name = f.getName();
        if(f.delete()) Toast.makeText(this, name + " deleted!", Toast.LENGTH_LONG).show();
        else Toast.makeText(this, "could not delete " + name, Toast.LENGTH_LONG).show();
        clear = true;
        setAdapter();
    }

}

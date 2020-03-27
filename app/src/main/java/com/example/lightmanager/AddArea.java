package com.example.lightmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.lightmanager.Areas.a;

public class AddArea extends AppCompatActivity {

    private EditText t;
    private EditText luxT;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_area);
        setTitle("Add Area");
        button = findViewById(R.id.add);
        t = findViewById(R.id.edtxt);
        luxT = findViewById(R.id.luxTxt);
        listeners();
        t.requestFocus();
    }

    private void listeners(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAreaCl();
            }
        });

        t.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == 66) next();
                return false;
            }
        });

        luxT.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == 66) addAreaCl();
                return false;
            }
        });
    }

    private void next(){
        luxT.requestFocus();
    }

    private void addAreaCl(){
        String lux = luxT.getText().toString();
        lux = lux.replaceAll("\n", "");
        String text = t.getText().toString().replaceAll("\n", "");
        if(!(text == null || text.equals(""))) {
            if(!(lux == null || lux.equals(""))) {
                a = new Area(text, lux);
                super.onBackPressed();
            }else Toast.makeText(this, "Please enter a LUX reading", Toast.LENGTH_LONG).show();
        }else Toast.makeText(this, "Please enter an area name", Toast.LENGTH_LONG).show();
    }
}

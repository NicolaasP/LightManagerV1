package com.example.lightmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import static com.example.lightmanager.Areas.a;

public class Custom extends AppCompatActivity {
    private EditText cLight;
    private Button cLighB;
    private EditText height;
    private NumberPicker picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        cLight = findViewById(R.id.cLight);
        cLighB = findViewById(R.id.cLightB);
        height = findViewById(R.id.cH);
        picker = findViewById(R.id.num);
        picker.setMaxValue(99);
        picker.setMinValue(1);
        setListeners();
    }

    private void setListeners(){
        cLighB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdd();
            }
        });
        height.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == 66) enter();
                return false;
            }
        });
    }

    private void onAdd(){
        String name = cLight.getText().toString().replaceAll("\n", "");
        int am = picker.getValue();
        String h = height.getText().toString().replaceAll("\n", "");
        if(name == null || name.equals("")){
            Toast.makeText(this, "Please enter a Light name", Toast.LENGTH_LONG).show();
            return;
        }
        if(h == null || h.equals("")){
            Toast.makeText(this, "Please enter the height of the light", Toast.LENGTH_LONG).show();
            return;
        }
        Lights l = new Lights(name, am, h);
        a.addLight(l);
        Toast.makeText(this, "Custom light: " + name + " added!", Toast.LENGTH_LONG).show();
    }

    private void enter(){
        onAdd();
    }
}

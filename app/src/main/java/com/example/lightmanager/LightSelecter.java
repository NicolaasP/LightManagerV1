package com.example.lightmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;
import static com.example.lightmanager.Areas.a;

public class LightSelecter extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Lights light;
    private NumberPicker picker;
    private EditText h;
    private Spinner spinner;
    private Button adB;
    private Button cus;
    private String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_selecter);
        setTitle("Add lights");
        h = findViewById(R.id.lightHieght);
        adB = findViewById(R.id.addLight);
        spinner = findViewById(R.id.spinner);
        picker = findViewById(R.id.num);
        cus = findViewById(R.id.custom);
        picker.setMinValue(1);
        picker.setMaxValue(99);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lights, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setListener();
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void setListener(){
        adB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        h.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == 66) add();
                return false;
            }
        });
        cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cLight();
            }
        });
    }

    private void cLight(){
        Intent intent = new Intent(this, Custom.class);
        startActivity(intent);
    }

    private void add(){
        int am = picker.getValue();
        String height = h.getText().toString();
        if(!(s == null || s.equals(""))) {
            if(!(height == null || height.equals(""))){
                    light = new Lights(s, am, height);
                    a.addLight(light);
                    Toast.makeText(this, am + " X " + light.getLightName() + " added!", Toast.LENGTH_LONG).show();
            }else Toast.makeText(this, "Please enter height of the light", Toast.LENGTH_LONG).show();
        }else Toast.makeText(this, "Please select a light type", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        s = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

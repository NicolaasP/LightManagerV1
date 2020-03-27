package com.example.lightmanager;

import java.io.Serializable;

public class Lights implements Serializable {

    private String lightName;
    private int  amount;
    private String lightH;
    private String lightLUX;

    public Lights(String name, int amount, String lightH){
        this.lightName = name;
        this.amount = amount;
        this.lightH = lightH;
    }

    public String getLightName() {
        return lightName;
    }

    public int getAmount() {
        return amount;
    }

    public String getLightH() {
        return lightH;
    }

    public String getLightLUX() {
        return lightLUX;
    }


}

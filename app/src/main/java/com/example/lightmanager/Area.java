package com.example.lightmanager;
import java.io.Serializable;

import static com.example.lightmanager.Display.disp;

public class Area implements Serializable {

    private String areaName;
    private String lux;
    private Lights[] lights;

    public Area(String areaName, String lux) {
        this.areaName = areaName;
        this.lux = lux;
    }

    public Area(String areaName, Lights[] lights, String lux) {
        this.areaName = areaName;
        this.lights = lights;
        this.lux = lux;
    }

    public String getAreaName() {
        return areaName;
    }

    public Lights[] getLights() {
        return lights;
    }

    public String getLux() { return lux; }

    public void addLight(Lights light) {
        if(lights != null){
        Lights[] temp = new Lights[lights.length + 1];
        for(int i = 0; i < lights.length; i++) {

            temp[i] = lights[i];
        }
        temp[lights.length] = light;
        lights = new Lights[temp.length];
        for(int i = 0; i < lights.length; i++) {
            lights[i] = temp[i];
        }
        }else{
            lights = new Lights[1];
            lights[0] = light;
        }
    }

    @Override
    public String toString(){
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        StackTraceElement s = st[2];
        int tot = 0;
        if(lights != null) {
            for(int i = 0; i < lights.length; i++){
                if(lights[i] != null) tot += lights[i].getAmount();
            }
        }
        if(!disp) {
            if (tot != 1) return areaName + " contains: " + tot + " Lights";
            return areaName + " contains: 1 Light";
        }
        String out = areaName + ":\nTotal: " + tot + " lights\nLUX reading: " + lux + "\n\n";
        if(lights != null){
            for(Lights l:lights){
            if(l != null)
                out += l.getAmount() + "X " + l.getLightName()+ "\nLight Height: " + l.getLightH() + "\n\n";
            }
        }
        return out;
    }
}

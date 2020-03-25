package com.example.restoran;

import java.util.Random;

public class Rezervacije {
    private int pin;
    private String imeRestorana,imeOsobe,brOsoba;
    String datum,vrijeme;
    public Rezervacije(){
        Random r= new Random();
        pin=r.nextInt(9999)+1;
    }
    public Rezervacije(String imeRestorana, String imeOsobe, String datum,String vrijeme, String brOsoba){

        setImeRestorana(imeRestorana);
        setImeOsobe(imeOsobe);
        setDatum(datum);
        setVrijeme(vrijeme);
        setBrOsoba(brOsoba);
    }

    public void setBrOsoba(String brOsoba){
        this.brOsoba=brOsoba;
    }
    public String getBrOsoba(){
        return brOsoba;
    }
    public void setImeRestorana(String imeRestorana){
        this.imeRestorana=imeRestorana;
    }
    public String getImeRestorana(){
        return imeRestorana;
    }
    public void setImeOsobe(String imeOsobe){
        this.imeOsobe=imeOsobe;
    }
    public String getImeOsobe(){
        return imeOsobe;
    }
    public void setDatum(String datum){
        this.datum=datum;
    }
    public String getDatum(){
        return datum;
    }
    public void setVrijeme(String vrijeme){
        this.vrijeme=vrijeme;
    }
    public String getVrijeme(){
        return vrijeme;
    }
    public int getPin(){
        return pin;
    }
    public void setPin(int pin){this.pin=pin;}

}

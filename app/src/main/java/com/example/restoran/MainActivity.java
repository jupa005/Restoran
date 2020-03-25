package com.example.restoran;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import com.google.gson.Gson;




public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref, sp2;
    private Gson gson;
    private EditText pin;
    private EditText imeRestorana, imeOsoba, brOsoba;
    private TextView datum,vrijeme;
    private String imeRestorana2,imeOsoba2,brOsoba2,datum2,vrijeme2;
    private int pin2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Rezervacije Restorana");
        pin = findViewById(R.id.PIN);
        imeRestorana = findViewById(R.id.imerestorana);
        imeOsoba = findViewById(R.id.imecovjeka);
        brOsoba = findViewById(R.id.brOsoba);
        datum = findViewById(R.id.datum);
        vrijeme = findViewById(R.id.vrijeme);
        //Pristupi inicijalnom spremištu tekuće aktivnosti
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        gson = new Gson();
    }
    public void birajDatum(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void birajVrijeme(View v){
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void novaRez(View v){
        Intent i = new Intent(this, UnosRezervacije.class);
        startActivityForResult(i,1);
    }
    public void spremi(){

        // Stvori objekt tipa Editor koji omogućuje mijenjanje inicijalnog spremišta
        SharedPreferences.Editor editor = sharedPref.edit();
        // Serijaliziraj objekt tipa Korisnik
        Rezervacije rez = new Rezervacije();
        rez.setImeRestorana(imeRestorana2);
        rez.setVrijeme(vrijeme2);
        rez.setDatum(datum2);
        rez.setImeOsobe(imeOsoba2);
        rez.setBrOsoba(brOsoba2);
        rez.setPin(pin2);

        String json = gson.toJson(rez);
        // Zapiši u inicijalno spremište objekt tipa Korisnik s ključem "korisnik"
        // u JSON formatu (niz znakova)
        editor.putString("rezervacije", json);
        // Zapiši podatak u spremište
        editor.commit();
        Toast.makeText(MainActivity.this, "Podatak "+json + " zapisan!", Toast.LENGTH_SHORT).show();
    }

    public void citaj(View v){

        Rezervacije rez = gson.fromJson(sharedPref.getString("rezervacije",""), Rezervacije.class);
        Toast.makeText(MainActivity.this, "blabla" + rez, Toast.LENGTH_LONG).show();
        if (rez == null){
            Toast.makeText(MainActivity.this, "Rezervacija ne postoji u spremištu!", Toast.LENGTH_SHORT).show();
            return;
        }
        imeOsoba.setText(rez.getImeOsobe());
        brOsoba.setText(String.valueOf(rez.getBrOsoba()));
        datum.setText(rez.getDatum());
        vrijeme.setText(rez.getVrijeme());
        imeRestorana.setText(rez.getImeRestorana());

    }
    public void brisi(View v){
        // Stvori objekt tipa Editor koji omogućuje mijenjanje spremišta
        SharedPreferences.Editor editor = sharedPref.edit();
        if (!sharedPref.contains("rezervacije")){
            Toast.makeText(MainActivity.this, "Podatak s ključem \"rezervacije\" ne postoji!", Toast.LENGTH_SHORT).show();
            return;
        }
        editor.remove("rezervacije");
        // Potvrdi brisanje
        editor.commit();
        imeOsoba.setText("");
        brOsoba.setText("");
        datum.setText("");
        vrijeme.setText("");
        imeRestorana.setText("");
        Toast.makeText(MainActivity.this, "Podatak s ključem \"rezervacije\"obrisan!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /* Prezentacija Aktivnosti na Merlinu */
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {




                       imeRestorana2= data.getStringExtra("imeRestorana");
                        datum2 = data.getStringExtra("datum");
                        vrijeme2=data.getStringExtra("vrijeme");
                        brOsoba2=data.getStringExtra("brOsoba");
                        imeOsoba2=data.getStringExtra("imeOsoba");
                        pin2=(int)Math.random()*9999+1;



                /* dodaj novu rezervaciju u listu */


                /* Ispiši novokreiranu rezervaciju na ekran */
                pin.setText(pin2+"");
                imeRestorana.setText(imeRestorana2+"");
                datum.setText(datum2+"");
                vrijeme.setText(vrijeme2+"");
                brOsoba.setText(brOsoba2+"");
                imeOsoba.setText(imeOsoba2+"");

                /* spremi sve rezervacije u datoteku */
                spremi();
            }
        }
    }
}

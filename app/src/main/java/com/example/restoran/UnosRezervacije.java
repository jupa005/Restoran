package com.example.restoran;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class UnosRezervacije extends AppCompatActivity {
    private SharedPreferences sharedPref, sp2;
    private Gson gson;
    private EditText imeRestorana, imeOsoba, brOsoba;
    private TextView datum,vrijeme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_rezervacije);
        setTitle("Unos nove rezervacije");
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

    public void unos(View v){
        Intent intent = getIntent();
        intent.putExtra("imeRestorana", imeRestorana.getText().toString());
        intent.putExtra("datum", datum.getText().toString());
        intent.putExtra("vrijeme", vrijeme.getText().toString());
        intent.putExtra("brOsoba", brOsoba.getText().toString());
        intent.putExtra("imeOsoba", imeOsoba.getText().toString());
        Toast.makeText(UnosRezervacije.this, "Unos uspješan!", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, intent);
        finish();
        }






}

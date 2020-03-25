package com.example.restoran;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            Locale locale = getResources().getConfiguration().locale;
            Locale.setDefault(locale);

            // Stvori novi objekt tipa DatePickerDialog i vrati ga
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        // kada se izabere datum
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // jedan način formatiranja - može i bolje
            String dat = String.format("%02d.%02d.%04d", day, month+1, year);
            // ažuriraj polje izabraniDatum kod pozivatelja s izabranim datumom
            ((TextView) getActivity().findViewById(R.id.datum)).setText(dat);
        }
    }


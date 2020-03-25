package com.example.restoran;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        // Stvori novi objekt tipa TimePickerDialog i vrati ga
        // Posljednji parametar - vrijeme 24h (true) ili AM/PM (false)
        return new TimePickerDialog(getActivity(), this, hour, min, true);
    }

    // kada se izabere vrijeme
    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        String vrijeme = String.format("%02d:%02d", hour, minute);
        // ažuriraj polje izabrani na aktivnosti pozivatelja s izabranim vremenom
        ((TextView) getActivity().findViewById(R.id.vrijeme)).setText(vrijeme);
    }
}

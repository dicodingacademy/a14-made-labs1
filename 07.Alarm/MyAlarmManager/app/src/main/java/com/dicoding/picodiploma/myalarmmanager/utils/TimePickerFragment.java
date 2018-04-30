package com.dicoding.picodiploma.myalarmmanager.utils;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    DialogTimeListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            listener = (DialogTimeListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (listener != null) {
            listener = null;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar calendar = Calendar.getInstance();
        // Jam hari ini
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        // Menit dari jam hari ini
        int minute = calendar.get(Calendar.MINUTE);

        // True untuk format 24 jam, false untuk format 12 jam (am / pm)
        boolean formatHour24 = true;

        return new TimePickerDialog(getActivity(), this, hour, minute, formatHour24);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        listener.onDialogTimeSet(getTag(), hourOfDay, minute);
    }

    public interface DialogTimeListener {
        void onDialogTimeSet(String tag, int hourOfDay, int minute);
    }
}

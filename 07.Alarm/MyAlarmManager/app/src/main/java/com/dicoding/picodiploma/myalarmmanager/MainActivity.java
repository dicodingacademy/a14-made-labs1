package com.dicoding.picodiploma.myalarmmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dicoding.picodiploma.myalarmmanager.utils.DatePickerFragment;
import com.dicoding.picodiploma.myalarmmanager.utils.TimePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    TextView tvOnceDate;
    TextView tvOnceTime;
    EditText edtOnceMessage;
    ImageButton btnOnceDate;
    ImageButton btnOnceTime;
    Button btnSetOnce;

    TextView tvRepeatingTime;
    EditText edtRepeatingMessage;
    ImageButton  btnRepeatingTime;
    Button btnSetRepeating;
    Button btnCancelRepeating;

    private AlarmReceiver alarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisiasi view untuk one time alarm
        tvOnceDate = findViewById(R.id.tv_once_date);
        btnOnceDate = findViewById(R.id.btn_once_date);
        tvOnceTime = findViewById(R.id.tv_once_time);
        btnOnceTime = findViewById(R.id.btn_once_time);
        edtOnceMessage = findViewById(R.id.edt_once_message);
        btnSetOnce = findViewById(R.id.btn_set_once_alarm);

        // Listener one time alarm
        btnOnceDate.setOnClickListener(this);
        btnOnceTime.setOnClickListener(this);
        btnSetOnce.setOnClickListener(this);

        // Inisiasi view untuk repeating alarm
        tvRepeatingTime = findViewById(R.id.tv_repeating_time);
        btnRepeatingTime = findViewById(R.id.btn_repeating_time);
        edtRepeatingMessage = findViewById(R.id.edt_repeating_message);
        btnSetRepeating = findViewById(R.id.btn_set_repeating_alarm);
        btnCancelRepeating = findViewById(R.id.btn_cancel_repeating_alarm);

        // Listener repeating alarm
        btnRepeatingTime.setOnClickListener(this);
        btnSetRepeating.setOnClickListener(this);
        btnCancelRepeating.setOnClickListener(this);

        alarmReceiver = new AlarmReceiver();
    }

    private final static String DATE_PICKER_TAG = "DatePicker";
    private final static String TIME_PICKER_ONCE_TAG = "TimePickerOnce";
    private final static String TIME_PICKER_REPEAT_TAG = "TimePickerRepeat";

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_once_date:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), DATE_PICKER_TAG);
                break;
            case R.id.btn_once_time:
                TimePickerFragment timePickerFragmentOne = new TimePickerFragment();
                timePickerFragmentOne.show(getSupportFragmentManager(), TIME_PICKER_ONCE_TAG);
                break;
            case R.id.btn_set_once_alarm:
                String onceDate = tvOnceDate.getText().toString();
                String onceTime = tvOnceTime.getText().toString();
                String onceMessage = edtOnceMessage.getText().toString();

                alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME,
                        onceDate,
                        onceTime,
                        onceMessage);
                break;
            case R.id.btn_repeating_time:
                TimePickerFragment timePickerFragmentRepeat = new TimePickerFragment();
                timePickerFragmentRepeat.show(getSupportFragmentManager(), TIME_PICKER_REPEAT_TAG);
                break;
            case R.id.btn_set_repeating_alarm:
                String repeatTime = tvRepeatingTime.getText().toString();
                String repeatMessage = edtRepeatingMessage.getText().toString();
                alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING,
                        repeatTime, repeatMessage);
                break;
            case R.id.btn_cancel_repeating_alarm:
                alarmReceiver.cancelAlarm(this, AlarmReceiver.TYPE_REPEATING);
                break;
        }
    }

    @Override
    public void onDialogDateSet(String tag, int year, int month, int dayOfMonth) {

        // Siapkan date formatter-nya terlebih dahulu
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Set text dari textview once
        tvOnceDate.setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public void onDialogTimeSet(String tag, int hourOfDay, int minute) {

        // Siapkan time formatter-nya terlebih dahulu
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        // Set text dari textview berdasarkan tag
        switch (tag) {
            case TIME_PICKER_ONCE_TAG:
                tvOnceTime.setText(dateFormat.format(calendar.getTime()));
                break;
            case TIME_PICKER_REPEAT_TAG:
                tvRepeatingTime.setText(dateFormat.format(calendar.getTime()));
                break;
            default:
                break;
        }
    }
}

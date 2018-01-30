package com.dicoding.myalarmmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvOneDate, tvOneTime;
    TextView tvRepeatingTime;
    EditText edtOneMessage, edtRepeatingMessage;
    Button btnOneDate, btnOneTime, btnSetOne, btnRepeatingTime, btnSetRepeating, btnCancelRepeating;

    private Calendar calOneDate, calOneTime, calRepeatTime;

    private AlarmReceiver alarmReceiver;
    private AlarmPreference alarmPreference;
    DatePickerDialog oneDatePicker;
    TimePickerDialog oneTimePicker;
    TimePickerDialog repeatTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MyAlarmManager");
        // Inisiasi view untuk one time alarm
        tvOneDate = (TextView) findViewById(R.id.tv_one_date);
        btnOneDate = (Button) findViewById(R.id.btn_one_date);

        tvOneTime = (TextView) findViewById(R.id.tv_one_time);
        btnOneTime = (Button) findViewById(R.id.btn_one_time);

        edtOneMessage = (EditText) findViewById(R.id.edt_one_message);

        btnSetOne = (Button) findViewById(R.id.btn_set_one_alarm);

        // Inisiasi view untuk repeating alarm
        tvRepeatingTime = (TextView) findViewById(R.id.tv_repeating_time);
        btnRepeatingTime = (Button) findViewById(R.id.btn_repeating_time);

        edtRepeatingMessage = (EditText) findViewById(R.id.edt_repeating_message);

        btnSetRepeating = (Button) findViewById(R.id.btn_set_repeating_alarm);
        btnCancelRepeating = (Button) findViewById(R.id.btn_cancel_repeating_alarm);

        // Listener one time alarm
        btnOneDate.setOnClickListener(this);
        btnOneTime.setOnClickListener(this);
        btnSetOne.setOnClickListener(this);

        // Listener repeating alarm
        btnRepeatingTime.setOnClickListener(this);
        btnSetRepeating.setOnClickListener(this);
        btnCancelRepeating.setOnClickListener(this);

        calOneDate = Calendar.getInstance();
        calOneTime = Calendar.getInstance();
        calRepeatTime = Calendar.getInstance();

        alarmPreference = new AlarmPreference(this);
        alarmReceiver = new AlarmReceiver();

        // Ambil data dari preference one time
        if (!TextUtils.isEmpty(alarmPreference.getOneTimeDate())) {
            setOneTimeText();
        }

        // Ambil data dari preference repeat
        if (!TextUtils.isEmpty(alarmPreference.getRepeatingTime())) {
            setRepeatingText();
        }

        oneDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calOneDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                tvOneDate.setText(dateFormat.format(calOneDate.getTime()));
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE));


        oneTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calOneTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calOneTime.set(Calendar.MINUTE, minute);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                tvOneTime.setText(dateFormat.format(calOneTime.getTime()));
            }
        }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);

        repeatTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calRepeatTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calRepeatTime.set(Calendar.MINUTE, minute);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                tvRepeatingTime.setText(dateFormat.format(calRepeatTime.getTime()));
            }
        }, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one_date:
                oneDatePicker.show();
                break;
            case R.id.btn_one_time:
                oneTimePicker.show();
                break;
            case R.id.btn_repeating_time:
                repeatTimePicker.show();
                break;
            case R.id.btn_set_one_alarm:
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String oneTimeDate = dateFormat.format(calOneDate.getTime());

                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String oneTimeTime = timeFormat.format(calOneTime.getTime());
                String oneTimeMessage = edtOneMessage.getText().toString();

                alarmPreference.setOneTimeDate(oneTimeDate);
                alarmPreference.setOneTimeMessage(oneTimeMessage);
                alarmPreference.setOneTimeTime(oneTimeTime);

                alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME,
                        alarmPreference.getOneTimeDate(),
                        alarmPreference.getOneTimeTime(),
                        alarmPreference.getOneTimeMessage());
                break;
            case R.id.btn_set_repeating_alarm:
                timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

                String repeatTimeTime = timeFormat.format(calRepeatTime.getTime());
                String repeatTimeMessage = edtRepeatingMessage.getText().toString();
                alarmPreference.setRepeatingTime(repeatTimeTime);
                alarmPreference.setRepeatingMessage(repeatTimeMessage);

                alarmReceiver.setRepeatingAlarm(this, AlarmReceiver.TYPE_REPEATING,
                        alarmPreference.getRepeatingTime(), alarmPreference.getRepeatingMessage());
                break;
            case R.id.btn_cancel_repeating_alarm:
                alarmReceiver.cancelAlarm(this, AlarmReceiver.TYPE_REPEATING);
                break;

        }
    }

    private void setOneTimeText() {
        tvOneTime.setText(alarmPreference.getOneTimeTime());
        tvOneDate.setText(alarmPreference.getOneTimeDate());
        edtOneMessage.setText(alarmPreference.getOneTimeMessage());
    }

    private void setRepeatingText() {
        tvRepeatingTime.setText(alarmPreference.getRepeatingTime());
        edtRepeatingMessage.setText(alarmPreference.getRepeatingMessage());
    }

}

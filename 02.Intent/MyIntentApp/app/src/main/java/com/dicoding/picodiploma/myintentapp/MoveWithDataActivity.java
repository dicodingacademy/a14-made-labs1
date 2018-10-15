package com.dicoding.picodiploma.myintentapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MoveWithDataActivity extends AppCompatActivity {
    public static final String EXTRA_AGE = "extra_age";
    public static final String EXTRA_NAME = "extra_name";

    TextView tvDataReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_data);

        tvDataReceived = (TextView) findViewById(R.id.tv_data_received);

        /*
        Data dari intent bisa kita dapatkan dengan memanggil get dan disesuaikan dengan tipe datanya
         */
        String name = getIntent().getStringExtra(EXTRA_NAME);
        int age = getIntent().getIntExtra(EXTRA_AGE, 0);

        String text = "Name : " + name + ", Your Age : " + age;
        tvDataReceived.setText(text);
    }
}

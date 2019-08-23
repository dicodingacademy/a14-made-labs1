package com.dicoding.picodiploma.myintentapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MoveWithObjectActivity extends AppCompatActivity {
    public static final String EXTRA_PERSON = "extra_person";
    private TextView tvObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_object);

        tvObject = findViewById(R.id.tv_object_received);

        /*
        Object parcelable bisa kita dapatkan dengan memanggil getParcelableExtra
         */
        Person person = getIntent().getParcelableExtra(EXTRA_PERSON);

        String text = "Name : " + person.getName() + ",\nEmail : " + person.getEmail() + ",\nAge : " + person.getAge() + ",\nLocation : " + person.getCity();
        tvObject.setText(text);
    }
}

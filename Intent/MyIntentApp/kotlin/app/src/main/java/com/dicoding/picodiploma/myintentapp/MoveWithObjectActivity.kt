package com.dicoding.picodiploma.myintentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MoveWithObjectActivity : AppCompatActivity() {
    private lateinit var tvObject: TextView

    companion object {
        const val EXTRA_PERSON = "extra_person"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        tvObject = findViewById(R.id.tv_object_received)

        /*
        Object parcelable bisa kita dapatkan dengan memanggil getParcelableExtra
         */
        val person = intent.getParcelableExtra<Person>(EXTRA_PERSON)

        val text = "Name : ${person.name},\nEmail : ${person.email},\nAge : ${person.age},\nLocation : ${person.city}"
        tvObject.text = text
    }


}

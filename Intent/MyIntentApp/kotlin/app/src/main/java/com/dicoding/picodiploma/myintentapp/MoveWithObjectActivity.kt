package com.dicoding.picodiploma.myintentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_move_with_object.*

class MoveWithObjectActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PERSON = "extra_person"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        /*
        Object parcelable bisa kita dapatkan dengan memanggil getParcelableExtra
         */
        val person = intent.getParcelableExtra<Person>(EXTRA_PERSON)

        val text = "Name : ${person.name},\nEmail : ${person.email},\nAge : ${person.age},\nLocation : ${person.city}"
        tv_object_received.text = text
    }


}

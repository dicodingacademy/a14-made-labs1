package com.dicoding.picodiploma.myflexiblefragment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()

        val mHomeFragment = HomeFragment()

        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        /*
        Kode di bawah ini digunakan untuk memvalidasi apakah suatu fragment adalah instance dari suatu kelas
         */
        if (fragment !is HomeFragment) {
            mFragmentTransaction.add(R.id.frame_container, mHomeFragment, HomeFragment::class.java.simpleName)

            Log.d("MyFlexibleFragment", "Fragment Name :" + HomeFragment::class.java.simpleName)

            mFragmentTransaction.commit()
        }
    }
}

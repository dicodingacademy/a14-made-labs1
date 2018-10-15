package com.dicoding.picodiploma.myflexiblefragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        HomeFragment mHomeFragment = new HomeFragment();

        Fragment fragment = mFragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        /*
        Kode di bawah ini digunakan untuk memvalidasi apakah suatu fragment adalah instance dari suatu kelas
         */
        if (!(fragment instanceof HomeFragment)) {
            mFragmentTransaction.add(R.id.frame_container, mHomeFragment, HomeFragment.class.getSimpleName());

            Log.d("MyFlexibleFragment", "Fragment Name :" + HomeFragment.class.getSimpleName());

            mFragmentTransaction.commit();
        }
    }
}

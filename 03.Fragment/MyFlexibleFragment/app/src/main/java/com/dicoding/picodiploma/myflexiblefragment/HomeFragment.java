package com.dicoding.picodiploma.myflexiblefragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnCategory = view.findViewById(R.id.btn_category);
        btnCategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_category) {
            /*
            Method addToBackStack akan menambahkan fragment ke backstack

            Behaviour dari back button :
            jika ada fragment di dalam backstack maka fragment yang akan di close / remove
            jika sudah tidak ada fragment di dalam backstack maka activity yang akan di close / finish
             */
            FragmentManager mFragmentManager = getFragmentManager();
            if (mFragmentManager != null) {
                CategoryFragment mCategoryFragment = new CategoryFragment();
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.frame_container, mCategoryFragment, CategoryFragment.class.getSimpleName());
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }
        }
    }
}

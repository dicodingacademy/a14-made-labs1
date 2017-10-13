package com.dicoding.associate.myflexiblefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements View.OnClickListener {

    Button btnDetailCategory;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        btnDetailCategory = (Button)view.findViewById(R.id.btn_detail_category);
        btnDetailCategory.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_detail_category){
            DetilCategoryFragment mDetilCategoryFragment = new DetilCategoryFragment();

            Bundle mBundle = new Bundle();
            mBundle.putString(DetilCategoryFragment.EXTRA_NAME, "Lifestyle");
            String description = "Kategori ini akan berisi produk-produk lifestyle";

            mDetilCategoryFragment.setArguments(mBundle);
            mDetilCategoryFragment.setDescription(description);

            /*
            Method addToBackStack akan menambahkan fragment ke backstack
            Behaviour dari back button akan cek fragment dari backstack,
            jika ada fragment di dalam backstack maka fragment yang akan di close / remove
            jika sudah tidak ada fragment di dalam backstack maka activity yang akan di close / finish
             */
            FragmentManager mFragmentManager = getFragmentManager();
            FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.frame_container, mDetilCategoryFragment,
                    DetilCategoryFragment.class.getSimpleName());
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        }
    }
}

package com.dicoding.picodiploma.myflexiblefragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class HomeFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCategory = view.findViewById<Button>(R.id.btn_category)
        btnCategory.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_category) {
            /*
            Method addToBackStack akan menambahkan fragment ke backstack

            Behaviour dari back button :
            jika ada fragment di dalam backstack maka fragment yang akan di close / remove
            jika sudah tidak ada fragment di dalam backstack maka activity yang akan di close / finish
             */
            val mFragmentManager = fragmentManager
            if (mFragmentManager != null) {
                val mCategoryFragment = CategoryFragment()
                val mFragmentTransaction = mFragmentManager.beginTransaction()
                mFragmentTransaction.replace(R.id.frame_container, mCategoryFragment, CategoryFragment::class.java.simpleName)
                mFragmentTransaction.addToBackStack(null)
                mFragmentTransaction.commit()
            }
        }
    }
}

package com.dicoding.picodiploma.myflexiblefragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_detail_category.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_detail_category) {
            val mDetailCategoryFragment = DetailCategoryFragment()

            val mBundle = Bundle()
            mBundle.putString(DetailCategoryFragment.EXTRA_NAME, "Lifestyle")
            val description = "Kategori ini akan berisi produk-produk lifestyle"

            mDetailCategoryFragment.arguments = mBundle
            mDetailCategoryFragment.description = description

            /*
            Method addToBackStack akan menambahkan fragment ke backstack
            Behaviour dari back button akan cek fragment dari backstack,
            jika ada fragment di dalam backstack maka fragment yang akan di close / remove
            jika sudah tidak ada fragment di dalam backstack maka activity yang akan di close / finish
             */
            fragmentManager?.commit {
                replace(R.id.frame_container, mDetailCategoryFragment, DetailCategoryFragment::class.java.simpleName)
                addToBackStack(null)
            }
        }
    }
}

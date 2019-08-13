package com.dicoding.picodiploma.myflexiblefragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_option_dialog.*

class OptionDialogFragment : DialogFragment(), View.OnClickListener {

    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_choose.setOnClickListener(this)
        btn_close.setOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        /*
        Saat attach maka set optionDialogListener dengan listener dari detailCategoryFragment
         */
        val fragment = parentFragment

        if (fragment is DetailCategoryFragment) {
            val detailCategoryFragment = fragment as DetailCategoryFragment?
            this.optionDialogListener = detailCategoryFragment?.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()

        /*
        Saat detach maka set null pada optionDialogListener
         */
        this.optionDialogListener = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_close -> dialog?.cancel()

            R.id.btn_choose -> {
                val checkedRadioButtonId = rg_options.checkedRadioButtonId
                if (checkedRadioButtonId != -1) {
                    var coach: String? = null
                    when (checkedRadioButtonId) {
                        R.id.rb_saf -> coach = rb_saf.text.toString().trim { it <= ' ' }

                        R.id.rb_mou -> coach = rb_mou.text.toString().trim { it <= ' ' }

                        R.id.rb_lvg -> coach = rb_lvg.text.toString().trim { it <= ' ' }

                        R.id.rb_moyes -> coach = rb_moyes.text.toString().trim { it <= ' ' }
                    }

                    if (optionDialogListener != null) {
                        optionDialogListener?.onOptionChosen(coach)
                    }
                    dialog?.dismiss()
                }
            }
        }
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }
}

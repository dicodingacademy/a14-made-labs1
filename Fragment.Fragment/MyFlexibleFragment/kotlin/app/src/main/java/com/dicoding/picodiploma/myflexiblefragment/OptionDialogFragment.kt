package com.dicoding.picodiploma.myflexiblefragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_option_dialog.*

class OptionDialogFragment : DialogFragment(), View.OnClickListener {

    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbSaf: RadioButton
    private lateinit var rbMou: RadioButton
    private lateinit var rbLvg: RadioButton
    private lateinit var rbMoyes: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnChoose.setOnClickListener(this)
        btnClose = view.findViewById(R.id.btn_close)
        btnClose.setOnClickListener(this)
        rgOptions = view.findViewById(R.id.rg_options)
        rbSaf = view.findViewById(R.id.rb_saf)
        rbLvg = view.findViewById(R.id.rb_lvg)
        rbMou = view.findViewById(R.id.rb_mou)
        rbMoyes = view.findViewById(R.id.rb_moyes)
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
                val checkedRadioButtonId = rgOptions.checkedRadioButtonId
                if (checkedRadioButtonId != -1) {
                    var coach: String? = null
                    when (checkedRadioButtonId) {
                        R.id.rb_saf -> coach = rbSaf.text.toString().trim()

                        R.id.rb_mou -> coach = rbMou.text.toString().trim()

                        R.id.rb_lvg -> coach = rbLvg.text.toString().trim()

                        R.id.rb_moyes -> coach = rbMoyes.text.toString().trim()
                    }
                    optionDialogListener?.onOptionChosen(coach)
                    dialog?.dismiss()
                }
            }
        }
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }
}

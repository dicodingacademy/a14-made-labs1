package com.dicoding.picodiploma.myintentapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_move_for_result.*

class MoveForResultActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE = 110
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        btn_choose.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_choose) {
            if (rg_number.checkedRadioButtonId != 0) {
                var value = 0
                when (rg_number.checkedRadioButtonId) {
                    R.id.rb_50 -> value = 50

                    R.id.rb_100 -> value = 100

                    R.id.rb_150 -> value = 150

                    R.id.rb_200 -> value = 200
                }

                /*
                Intent ini digunakan untuk mengirimkan kembali ke activity induk
                Perhatikan bahwa kita mencantumkan RESULT_CODE ke dalam metode setResult
                 */

                val resultIntent = Intent()
                resultIntent.putExtra(EXTRA_SELECTED_VALUE, value)
                setResult(RESULT_CODE, resultIntent)
                finish()
            }
        }
    }
}
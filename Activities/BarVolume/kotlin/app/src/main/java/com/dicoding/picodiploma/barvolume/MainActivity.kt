package com.dicoding.picodiploma.barvolume

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_calculate.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tv_result.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tv_result.text.toString())
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_calculate) {
            val inputLength = edt_length.text.toString().trim { it <= ' ' }
            val inputWidth = edt_width.text.toString().trim { it <= ' ' }
            val inputHeight = edt_height.text.toString().trim { it <= ' ' }

            var isEmptyFields = false
            var isInvalidDouble = false

            /*
            Validasi apakah inputan masih ada yang kosong
             */
            when {
                inputLength.isEmpty() -> {
                    isEmptyFields = true
                    edt_length.error = "Field ini tidak boleh kosong"
                }
                inputWidth.isEmpty() -> {
                    isEmptyFields = true
                    edt_width.error = "Field ini tidak boleh kosong"
                }
                inputHeight.isEmpty() -> {
                    isEmptyFields = true
                    edt_height.error = "Field ini tidak boleh kosong"
                }
            }

            /*
            Validasi apakah inputan berupa double
             */

            val length = toDouble(inputLength)
            val width = toDouble(inputWidth)
            val height = toDouble(inputHeight)

            when {
                length == null -> {
                    isInvalidDouble = true
                    edt_length.error = "Field ini harus berupa nomer yang valid"
                }
                width == null -> {
                    isInvalidDouble = true
                    edt_width.error = "Field ini harus berupa nomer yang valid"
                }
                height == null -> {
                    isInvalidDouble = true
                    edt_height.error = "Field ini harus berupa nomer yang valid"
                }
            }

            /*
            Jika semua inputan valid maka tampilkan hasilnya
             */
            if (!isEmptyFields && !isInvalidDouble) {
                val volume = length as Double * width as Double * height as Double
                tv_result.text = volume.toString()
            }
        }
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }
}
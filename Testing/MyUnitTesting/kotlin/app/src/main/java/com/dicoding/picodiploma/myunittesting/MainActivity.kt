package com.dicoding.picodiploma.myunittesting

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        tvResult = findViewById(R.id.tv_result)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        btnCalculate.setOnClickListener {
            val inputLength = edtLength.text.toString().trim { it <= ' ' }
            val inputWidth = edtWidth.text.toString().trim { it <= ' ' }
            val inputHeight = edtHeight.text.toString().trim { it <= ' ' }

            var isEmptyFields = false
            /*
            Validasi apakah inputan masih ada yang kosong
             */
            when {
                inputLength.isEmpty() -> {
                    isEmptyFields = true
                    edtLength.error = "Field ini tidak boleh kosong"
                }
                inputWidth.isEmpty() -> {
                    isEmptyFields = true
                    edtWidth.error = "Field ini tidak boleh kosong"
                }
                inputHeight.isEmpty() -> {
                    isEmptyFields = true
                    edtHeight.error = "Field ini tidak boleh kosong"
                }
            }

            if (!isEmptyFields) {
                val length = inputLength.toDouble()
                val width = inputWidth.toDouble()
                val height = inputHeight.toDouble()

                viewModel.calculateVolume(length, width, height)
                tvResult.text = viewModel.volume.toString()
            }
        }
    }
}
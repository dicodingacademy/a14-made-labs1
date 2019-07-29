package com.dicoding.picodiploma.myintentapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnMoveActivity: Button
    private lateinit var btnMoveWithDataActivity: Button
    private lateinit var btnMoveWithObject: Button
    private lateinit var btnDialPhone: Button
    private lateinit var btnMoveForResult: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMoveActivity = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)

        btnMoveWithObject = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        btnDialPhone = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        btnMoveForResult = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_move_activity -> {
                /*
                Intent untuk memulai activity baru
                 */
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data -> {
                /*
                Intent untuk mengirimkan data ke activity lain
                 */
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodingAcademy Boy")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5)
                startActivity(moveWithDataIntent)
            }

            R.id.btn_move_activity_object -> {
                /*
                Intent untuk mengirimkan object ke activity lain, perlu diingat bahwa object Person adalah parcelable
                 */
                val person = Person(
                        "DicodingAcademy",
                        5,
                        "academy@dicoding.com",
                        "Bandung"
                )

                val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }

            R.id.btn_dial_number -> {
                /*
                Intent action untuk menjalankan action dial
                 */
                val phoneNumber = "081210841382"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_for_result -> {
                /*
                Intent for result bermanfaat ketika kita ingin mendapatkan nilai balikan dari activity lainnya
                Perhatikan bahwa kita mengirimkan intent beserta REQUEST_CODE
                 */
                val moveForResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                startActivityForResult(moveForResultIntent, REQUEST_CODE)
            }
        }
    }


    /*
    Callback onActvityResult dipanggil setelah kode finish() di dalam MoveForResultActivity dipanggil
    Perhatikan pengecekan request code yang diterima
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        /*
        Perhatikan bahwa ada 2 kode yaitu request_code dan result_code
        request_code yaitu code yang dicantumkan ke dalam intent saat memulai (pada saat startactivity)
        result_code yaitu code yang dicantumkan ke dalam intent di activity yang dibuka (biasanya pada saat sebelum activity ditutup)
         */
        if (requestCode == REQUEST_CODE) {
            if (resultCode == MoveForResultActivity.RESULT_CODE) {
                val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
                tvResult.text = "Hasil : $selectedValue"
            }
        }

    }

}

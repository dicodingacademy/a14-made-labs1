package com.dicoding.picodiploma.mytestingapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete
import java.util.*
import java.util.concurrent.Future

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnSetValue: Button
    private lateinit var tvText: TextView
    private lateinit var imgPreview: ImageView

    private var names = ArrayList<String>()
    private var delayAsync: Future<Unit>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText = findViewById(R.id.tv_text)
        btnSetValue = findViewById(R.id.btn_set_value)
        imgPreview = findViewById(R.id.img_preview)

        btnSetValue.setOnClickListener(this)

        names.add("Narenda Wicaksono")
        names.add("Kevin")
        names.add("Yoza")

        //imgPreview.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fronalpstock_big));
        Glide.with(this).load(R.drawable.fronalpstock_big).into(imgPreview)
    }

    override fun onClick(view: View) {
        when {
            view.id == R.id.btn_set_value -> {
                val name = StringBuilder()
                for (i in names.indices) {
                    name.append(names[i]).append("\n")
                }
                tvText.text = name.toString()

                delayAsync = doAsync {
                    Thread.sleep(3000000)
                    onComplete {
                        Log.d("DelayAsync", "Done")
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        delayAsync?.cancel(true)
    }
}

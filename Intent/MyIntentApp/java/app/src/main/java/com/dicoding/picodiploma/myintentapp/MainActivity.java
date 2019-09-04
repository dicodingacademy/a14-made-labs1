package com.dicoding.picodiploma.myintentapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvResult;

    private final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMoveActivity = findViewById(R.id.btn_move_activity);
        btnMoveActivity.setOnClickListener(this);

        Button btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data);
        btnMoveWithDataActivity.setOnClickListener(this);

        Button btnMoveWithObject = findViewById(R.id.btn_move_activity_object);
        btnMoveWithObject.setOnClickListener(this);

        Button btnDialPhone = findViewById(R.id.btn_dial_number);
        btnDialPhone.setOnClickListener(this);

        Button btnMoveForResult = findViewById(R.id.btn_move_for_result);
        btnMoveForResult.setOnClickListener(this);

        tvResult = findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_move_activity:
                /*
                Intent untuk memulai activity baru
                 */
                Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveIntent);
                break;

            case R.id.btn_move_activity_data:
                /*
                Intent untuk mengirimkan data ke activity lain
                 */
                Intent moveWithDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodingAcademy Boy");
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5);
                startActivity(moveWithDataIntent);
                break;

            case R.id.btn_move_activity_object:
                /*
                Intent untuk mengirimkan object ke activity lain, perlu diingat bahwa object Person adalah parcelable
                 */
                Person person = new Person();
                person.setName("DicodingAcademy");
                person.setAge(5);
                person.setEmail("academy@dicoding.com");
                person.setCity("Bandung");

                Intent moveWithObjectIntent = new Intent(MainActivity.this, MoveWithObjectActivity.class);
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person);
                startActivity(moveWithObjectIntent);

                break;

            case R.id.btn_dial_number:
                /*
                Intent action untuk menjalankan action dial
                 */
                String phoneNumber = "081210841382";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(dialPhoneIntent);
                break;

            case R.id.btn_move_for_result:
                /*
                Intent for result bermanfaat ketika kita ingin mendapatkan nilai balikan dari activity lainnya
                Perhatikan bahwa kita mengirimkan intent beserta REQUEST_CODE
                 */
                Intent moveForResultIntent = new Intent(MainActivity.this, MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
        }
    }


    /*
    Callback onActvityResult dipanggil setelah kode finish() di dalam MoveForResultActivity dipanggil
    Perhatikan pengecekan request code yang diterima
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*
        Perhatikan bahwa ada 2 kode yaitu request_code dan result_code
        request_code yaitu code yang dicantumkan ke dalam intent saat memulai (pada saat startactivity)
        result_code yaitu code yang dicantumkan ke dalam intent di activity yang dibuka (biasanya pada saat sebelum activity ditutup)
         */
        if (requestCode == REQUEST_CODE && resultCode == MoveForResultActivity.RESULT_CODE) {
            int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0);
            tvResult.setText(String.format("Hasil : %s", selectedValue));
        }
    }
}

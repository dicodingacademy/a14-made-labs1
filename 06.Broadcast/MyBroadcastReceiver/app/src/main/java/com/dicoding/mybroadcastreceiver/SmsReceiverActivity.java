package com.dicoding.mybroadcastreceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SmsReceiverActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tvSmsFrom;
    TextView tvSmsMessage;
    Button btnClose;

    public static final String EXTRA_SMS_NO = "extra_sms_no";
    public static final String EXTRA_SMS_MESSAGE = "extra_sms_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_receiver);

        setTitle("Incoming Message");

        tvSmsFrom = (TextView)findViewById(R.id.tv_no);
        tvSmsMessage = (TextView)findViewById(R.id.tv_message);
        btnClose = (Button)findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);

        String senderNo = getIntent().getStringExtra(EXTRA_SMS_NO);
        String senderMessage = getIntent().getStringExtra(EXTRA_SMS_MESSAGE);

        tvSmsFrom.setText("from : "+senderNo);
        tvSmsMessage.setText(senderMessage);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_close){
            finish();
        }
    }
}

package com.dicoding.mybroadcastreceiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    Button btnDownload;
    Button btnCheckPermission;

    public static final String ACTION_DOWNLOAD_STATUS = "download_status";
    private BroadcastReceiver downloadReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownload = (Button)findViewById(R.id.btn_download);
        btnCheckPermission = (Button)findViewById(R.id.btn_permission);
        btnDownload.setOnClickListener(this);
        btnCheckPermission.setOnClickListener(this);

        downloadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Download Selesai", Toast.LENGTH_SHORT).show();
            }
        };
        IntentFilter downloadIntentFilter = new IntentFilter(ACTION_DOWNLOAD_STATUS);

        registerReceiver(downloadReceiver, downloadIntentFilter);
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_download){
            Intent downloadServiceIntent = new Intent(this, DownloadService.class);
            startService(downloadServiceIntent);
        }else if (v.getId() == R.id.btn_permission){
            PermissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downloadReceiver != null){
            unregisterReceiver(downloadReceiver);
        }
    }

    final int SMS_REQUEST_CODE = 101;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
        if(requestCode == SMS_REQUEST_CODE){
            if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Grant permission accept sms berhasil",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Sms receiver permission di tolak",Toast.LENGTH_SHORT).show();
            }
        }
    }
}

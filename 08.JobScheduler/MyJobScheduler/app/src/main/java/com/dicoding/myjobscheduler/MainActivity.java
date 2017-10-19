package com.dicoding.myjobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnStart, btnCancel;
    private int jobId = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button)findViewById(R.id.btn_start);
        btnCancel = (Button)findViewById(R.id.btn_cancel);

        btnStart.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                startJob();
                break;

            case R.id.btn_cancel:
                cancelJob();
                break;
        }
    }

    private void startJob(){
        ComponentName mServiceComponent = new ComponentName(this, GetCurrentWeatherJobService.class);

        JobInfo.Builder builder = new JobInfo.Builder(jobId, mServiceComponent);

        // Kondisi network,
        // NETWORK_TYPE_ANY, berarti tidak ada ketentuan tertentu
        // NETWORK_TYPE_UNMETERED, adalah network yang tidak dibatasi misalnya wifi
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

        // Kondisi device, secara default sudah pada false
        // false, berarti device tidak perlu idle ketika job ke trigger
        // true, berarti device perlu dalam kondisi idle ketika job ke trigger
        builder.setRequiresDeviceIdle(false);

        // Kondisi charging
        // false, berarti device tidak perlu di charge
        // true, berarti device perlu dicharge
        builder.setRequiresCharging(false);

        // Periode interval sampai ke trigger
        // Dalam milisecond, 1000ms = 1detik
        builder.setPeriodic(100);

        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());

        Toast.makeText(this, "Job Service started", Toast.LENGTH_SHORT).show();
    }

    private void cancelJob(){
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.cancel(jobId);
        Toast.makeText(this, "Job Service canceled", Toast.LENGTH_SHORT).show();
        finish();
    }
}

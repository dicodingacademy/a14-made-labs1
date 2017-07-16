package com.dicoding.mygcmnetworkmanager;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;

/**
 * Created by sidiqpermana on 10/5/16.
 */

public class SchedulerTask {
    private GcmNetworkManager mGcmNetworkManager;

    public SchedulerTask(Context context){
        mGcmNetworkManager = GcmNetworkManager.getInstance(context);
    }

    public void createPeriodicTask() {
        com.google.android.gms.gcm.Task periodicTask = new PeriodicTask.Builder()
                .setService(SchedulerService.class)
                .setPeriod(60)
                .setFlex(10)
                .setTag(SchedulerService.TAG_TASK_WEATHER_LOG)
                .setPersisted(true)
                .build();

        mGcmNetworkManager.schedule(periodicTask);
    }

    public void cancelPeriodicTask(){
        if (mGcmNetworkManager != null){
            mGcmNetworkManager.cancelTask(SchedulerService.TAG_TASK_WEATHER_LOG, SchedulerService.class);
        }
    }
}

package com.dicoding.myserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;

public class BoundService extends Service {

    MyBinder mBinder = new MyBinder();

    final long startTime = System.currentTimeMillis();
    CountDownTimer mTimer = new CountDownTimer(1000000, 1000) {
        @Override
        public void onTick(long l) {

            long elapsedTime = System.currentTimeMillis() - startTime;
            Log.e(TAG, "onTick: "+elapsedTime );
        }

        @Override
        public void onFinish() {

        }
    };

    final String TAG = BoundService.class.getSimpleName();
    public BoundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e(TAG, "onCreate: " );
        mTimer.start();

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: " );
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
        mTimer.cancel();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e(TAG, "onRebind: " );
    }

    public class MyBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
}

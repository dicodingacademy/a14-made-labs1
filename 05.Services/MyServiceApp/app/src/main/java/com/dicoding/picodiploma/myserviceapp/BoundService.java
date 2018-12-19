package com.dicoding.picodiploma.myserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

public class BoundService extends Service {

    final String TAG = BoundService.class.getSimpleName();
    MyBinder mBinder = new MyBinder();
    final long startTime = System.currentTimeMillis();

    /*
    Countdown timer akan berjalan sampai 100000 milisecond,
    dengan interval setiap 1000 milisecond akan menampilkan log
     */
    CountDownTimer mTimer = new CountDownTimer(100000, 1000) {
        @Override
        public void onTick(long l) {

            long elapsedTime = System.currentTimeMillis() - startTime;
            Log.d(TAG, "onTick: " + elapsedTime);
        }

        @Override
        public void onFinish() {

        }
    };

    public BoundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    /*
    Method yang akan dipanggil ketika service diikatkan ke activity
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        mTimer.start();
        return mBinder;
    }

    /*
    Ketika semua ikatan sudah dilepas maka ondestroy akan secara otomatis dipanggil
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    /*
    Method yang akan dipanggil ketika service dilepas dari activity
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        mTimer.cancel();
        return super.onUnbind(intent);
    }

    /*
    Method yang akan dipanggil ketika service diikatkan kembali
     */
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind: ");
    }

    class MyBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
}

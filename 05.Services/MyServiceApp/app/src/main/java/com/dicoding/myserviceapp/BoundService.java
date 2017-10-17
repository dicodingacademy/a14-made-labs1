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

    final String TAG = BoundService.class.getSimpleName();
    MyBinder mBinder = new MyBinder();
    final long startTime = System.currentTimeMillis();

    /*
    Countdown timer akan berjalan sampai 1000000 milisecond,
    dengan interval setiap 1000 milisecond akan menampilkan log
     */
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

    public BoundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e(TAG, "onCreate: " );
        mTimer.start();
    }

    /*
    Method yang akan dipanggil ketika service di ikatkan ke activity
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: " );
        return mBinder;
    }

    /*
    Ketika semua ikatan sudah di lepas maka ondestroy akan secara otomatis dipanggil
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
        mTimer.cancel();
    }

    /*
    Method yang akan dipanggil ketika service di lepas dari activity
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    /*
    Method yang akan dipanggil ketika service di ikatkan kembali
     */
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

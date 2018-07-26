package com.dicoding.picodiploma.myserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.lang.ref.WeakReference;

public class OriginService extends Service implements DummyAsyncCallback {

    static final String TAG = OriginService.class.getSimpleName();

    public OriginService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");

        DummyAsync dummyAsync = new DummyAsync(this);
        dummyAsync.execute();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void preAsync() {
        // do nothing

        Log.d(TAG, "preAsync: Mulai.....");
    }

    @Override
    public void postAsync() {

        // Pemanggilan stopSelf akan menghentikan service
        Log.d(TAG, "postAsync: Selesai.....");
        stopSelf();
    }

    private static class DummyAsync extends AsyncTask<Void, Void, Void> {

        WeakReference<DummyAsyncCallback> callback;

        DummyAsync(DummyAsyncCallback callback) {
            this.callback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: ");
            callback.get().preAsync();

        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d(TAG, "doInBackground: ");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d(TAG, "onPostExecute: ");
            callback.get().postAsync();
        }
    }


}


/*
Callback ke kelas DummyAsync
 */
interface DummyAsyncCallback {

    void preAsync();

    void postAsync();
}
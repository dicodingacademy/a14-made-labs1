package com.dicoding.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String DEMO_ASYNC = "DemoAsync";

    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = (TextView)findViewById(R.id.tv_status);

        DemoAsync demoAsync = new DemoAsync();

        // Execute asynctask dengan parameter string 'Halo Ini Demo AsyncTask'
        demoAsync.execute("Halo Ini Demo AsyncTask");
    }

    /**
     * 3 parameter generic <String, Void, String>
     * 1. Params, parameter input yang bisa dikirimkan
     * 2. Progress, digunakan untuk publish informasi sudah sampai mana proses background berjalan
     * 3. Result, object yang dikirimkan ke onPostExecute / hasil dari proses doInBackground
     */
    private class DemoAsync extends AsyncTask<String, Void, String> {

        /*
        onPreExecute digunakan untuk persiapan asynctask
        berjalan di Main Thread, bisa akses ke view karena masih di dalam Main Thread
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvStatus.setText("status : onPreExecute");
        }

        /*
        doInBackground digunakan untuk menjalankan proses secara async
        berjalan di background thread, tidak bisa akses ke view karena sudah beda thread
         */
        @Override
        protected String doInBackground(String... params) {
            Log.d(DEMO_ASYNC, "status : doInBackground");

            try{
                Thread.sleep(5000);
            }catch (Exception e){
                Log.d(DEMO_ASYNC, e.getMessage());
            }

            /*
            params[0] adalah 'Halo Ini Demo AsyncTask'
             */
            return params[0];
        }

        /*
        onPostExecute dijalankan ketika proses doInBackground telah selesai
        berjalan di Main Thread
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            tvStatus.setText("status : onPostExecute : "+s);
        }
    }

}

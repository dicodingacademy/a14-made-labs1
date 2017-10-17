package com.dicoding.mydeepnavigation;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOpenDetail = (Button)findViewById(R.id.btn_open_detail);
        btnOpenDetail.setOnClickListener(this);

        DelayAsync delayAsync =  new DelayAsync();
        delayAsync.execute();

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_open_detail){
            /*
            Intent yang akan dikirimkan ke halaman detail
             */
            Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
            detailIntent.putExtra(DetailActivity.EXTRA_TITLE, "Hola, Good News");
            detailIntent.putExtra(DetailActivity.EXTRA_MESSAGE, "Now you can learn android in dicoding");
            startActivity(detailIntent);
        }
    }

    /*
    Flow yang akan dijalankan
    Flow : Activity->AsyncTask->Notifikasi->HalamanDetail
     */
    private class DelayAsync extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
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
            showNotification(MainActivity.this, "Hi, how are you?",
                    "Do you have any plan this weekend? Let's hangout", 110);
        }
    }

    /**
     * Tampilan notifikasi dan ditambahkan intent untuk redirect ke halaman detail
     * @param context context activity
     * @param title judul notifikasi
     * @param message pesan notifikasi
     * @param notifId id dari notifikasi
     */
    private void showNotification(Context context, String title, String message, int notifId){
        Intent notifDetailIntent = new Intent(this, DetailActivity.class);
        /*
        Intent yang akan dikirimkan ke halaman detail
        */
        notifDetailIntent.putExtra(DetailActivity.EXTRA_TITLE, title);
        notifDetailIntent.putExtra(DetailActivity.EXTRA_MESSAGE, message);

        PendingIntent pendingIntent = TaskStackBuilder.create(this)
                .addParentStack(DetailActivity.class)
                .addNextIntent(notifDetailIntent)
                .getPendingIntent(110, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_email_black_24dp)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.black))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(alarmSound)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManagerCompat.notify(notifId, builder.build());
    }
}

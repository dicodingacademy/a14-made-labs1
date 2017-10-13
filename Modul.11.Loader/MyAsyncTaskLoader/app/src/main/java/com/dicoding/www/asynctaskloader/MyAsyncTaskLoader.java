package com.dicoding.www.asynctaskloader;


import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Emeth on 10/31/2016.
 */

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<WeatherItems>> {
    private ArrayList<WeatherItems> mData;
    private boolean hasResult = false;


    public MyAsyncTaskLoader(final Context context) {
        super(context);
        onContentChanged();
    }

    //Ketika data loading,
    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (hasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(final ArrayList<WeatherItems> data) {
        mData = data;
        hasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (hasResult) {
            onReleaseResources(mData);
            mData = null;
            hasResult = false;
        }
    }

    private static String ID_JAKARTA = "1642911";
    private static String ID_BANDUNG ="1650357" ;
    private static String ID_SEMARANG = "1627896" ;

    private static String API_KEY = "ISIKAN DENGAN API KEY MU";

    @Override
    public ArrayList<WeatherItems> loadInBackground() {
         SyncHttpClient client = new SyncHttpClient();

        final ArrayList<WeatherItems> weatherItemses = new ArrayList<>();
        String url = "http://api.openweathermap.org/data/2.5/group?id=" + ID_BANDUNG + "," + ID_JAKARTA + "," + ID_SEMARANG + "&units=metric&appid=" + API_KEY;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();

                //Menggunakan synchronous karena pada dasarnya thread yang digunakan sudah asynchronous dan method
                //loadInBackground mengembalikan nilai balikan

                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("list");

                    for (int i = 0 ; i < list.length() ; i++){
                        JSONObject weather = list.getJSONObject(i);
                        WeatherItems weatherItems = new WeatherItems(weather);
                        weatherItemses.add(weatherItems);
                    }


                }catch (Exception e){

                    //Jika terjadi error pada saat parsing maka akan masuk ke catch()

                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                //Jika response gagal maka , do nothing
            }
        });

        return weatherItemses;
    }

    protected void onReleaseResources(ArrayList<WeatherItems> data) {
        //nothing to do.
    }

    public ArrayList<WeatherItems> getResult() {
        return mData;
    }

}

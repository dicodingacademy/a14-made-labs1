package com.dicoding.picodiploma.myasynctask;


interface MyAsyncCallback {
    void onPreExecute();

    void onPostExecute(String text);
}

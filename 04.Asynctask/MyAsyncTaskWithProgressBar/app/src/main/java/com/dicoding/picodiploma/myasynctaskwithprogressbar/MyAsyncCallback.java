package com.dicoding.picodiploma.myasynctaskwithprogressbar;

interface MyAsyncCallback {
    void onPreExecute();

    void onPostExecute(String text);
}


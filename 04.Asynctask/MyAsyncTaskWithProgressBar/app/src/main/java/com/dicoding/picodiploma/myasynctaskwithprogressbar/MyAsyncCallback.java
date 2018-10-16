package com.dicoding.picodiploma.myasynctaskwithprogressbar;

interface MyAsyncCallback {
    void onPreExecute();

    void onUpdateProgress();

    void onPostExecute(String text);
}


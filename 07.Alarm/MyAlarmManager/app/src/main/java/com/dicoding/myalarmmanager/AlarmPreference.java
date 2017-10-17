package com.dicoding.myalarmmanager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sidiqpermana on 9/27/16.
 */

public class AlarmPreference {
    private final String PREF_NAME = "AlarmPreference";
    private final String KEY_ONE_TIME_DATE = "oneTimDate";
    private final String KEY_ONE_TIME_TIME = "oneTimeTime";
    private final String KEY_ONE_TIME_MESSAGE = "oneTimeMessage";
    private final String KEY_REPEATING_TIME = "repeatingTime";
    private final String KEY_REPEATING_MESSAGE = "repeatingMessage";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    public AlarmPreference(Context context){
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setOneTimeDate(String date){
        editor = mSharedPreferences.edit();
        editor.putString(KEY_ONE_TIME_DATE, date);
        editor.commit();
    }

    public String getOneTimeDate(){
        return mSharedPreferences.getString(KEY_ONE_TIME_DATE, null);
    }

    public void setOneTimeTime(String time){
        editor = mSharedPreferences.edit();
        editor.putString(KEY_ONE_TIME_TIME, time);
        editor.commit();
    }

    public String getOneTimeTime(){
        return mSharedPreferences.getString(KEY_ONE_TIME_TIME, null);
    }

    public void setOneTimeMessage(String message){
        editor = mSharedPreferences.edit();

        editor.putString(KEY_ONE_TIME_MESSAGE, message);
        editor.commit();
    }

    public String getOneTimeMessage(){
        return mSharedPreferences.getString(KEY_ONE_TIME_MESSAGE, null);
    }

    public void setRepeatingTime(String time){
        editor = mSharedPreferences.edit();
        editor.putString(KEY_REPEATING_TIME, time);
        editor.commit();
    }

    public String getRepeatingTime(){
        return mSharedPreferences.getString(KEY_REPEATING_TIME, null);
    }

    public void setRepeatingMessage(String message){
        editor = mSharedPreferences.edit();
        editor.putString(KEY_REPEATING_MESSAGE, message);
        editor.commit();
    }

    public String getRepeatingMessage(){
        return mSharedPreferences.getString(KEY_REPEATING_MESSAGE, null);
    }

    public void clear(){
        editor = mSharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}

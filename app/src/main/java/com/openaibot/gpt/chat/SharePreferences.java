package com.openaibot.gpt.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharePreferences {
    private static SharedPreferences preferences;
    public static void saveString(Context context, String key, String value){
        if(preferences == null){
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static String getString(Context context, String key){
        if(preferences == null){
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return preferences.getString(key, "1");
    }
}

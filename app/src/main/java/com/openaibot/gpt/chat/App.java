package com.openaibot.gpt.chat;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            // Initialize the Mobile Ads SDK.
            MobileAds.initialize(this, initializationStatus -> {
            });
        }
        catch (Exception e){}
    }
}

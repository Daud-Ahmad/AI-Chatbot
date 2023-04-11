package com.openaibot.gpt.chat.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.utils.Ads;
import com.openaibot.gpt.chat.utils.Constants;

import java.util.Objects;

public class LetGoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_let_go);
    }

    public void onCrdLetsStart(View view){
        Constants.interShowCounter++;
        if (Ads.mInterstitialAd != null && Constants.interShowCounter % Constants.counter == 0) {
            Ads.mInterstitialAd.show(this);
            Ads.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Ads.mInterstitialAd = null;
                    Ads.loadInterstitialAd(LetGoActivity.this);
                    startActivity(new Intent(LetGoActivity.this, HomeActivity.class));
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    Ads.mInterstitialAd = null;
                }
            });

        } else {
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        if(Constants.isAppCloseAdsShow.equals("true") &&  Ads.mInterstitialAd != null){
            Ads.mInterstitialAd.show(this);
            Ads.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    finish();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                }

                @Override
                public void onAdShowedFullScreenContent() {
                    Ads.mInterstitialAd = null;
                }
            });
        }
        else {
            super.onBackPressed();
        }
    }
}
package com.openaibot.gpt.chat.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.SharePreferences;
import com.openaibot.gpt.chat.dao.BookmarkDatabase;
import com.openaibot.gpt.chat.models.FirebaseDataModel;
import com.openaibot.gpt.chat.models.HistoryModel;
import com.openaibot.gpt.chat.utils.Ads;
import com.openaibot.gpt.chat.utils.Constants;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String app_link = getIntent().getStringExtra("new_app_link");
        if(app_link != null && !app_link.isEmpty()){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + app_link)));
            finish();
            return;
        }

        try {
            MobileAds.initialize(this, new OnInitializationCompleteListener() {
                @Override
                public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
                }
            });
        }
        catch (Exception e){}

        Ads.loadInterstitialAd(this);

        Constants.totalCoins = Integer.parseInt(SharePreferences.getString(this, Constants.COINS_KEY));
        BookmarkDatabase mDatabase = BookmarkDatabase.Companion.getDatabase(this);

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Constants.historyModelArrayList.clear();
                    ArrayList<HistoryModel> list = (ArrayList<HistoryModel>) mDatabase.userDao().getAllDua();
                    Constants.historyModelArrayList.addAll(list);
                } catch (Exception e) {
                }
            }
        }.start();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("data");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    FirebaseDataModel firebaseDataModel = dataSnapshot.getValue(FirebaseDataModel.class);

                    try {
                        Constants.rv_coin = Integer.parseInt(firebaseDataModel.getRv_coin());
                    }
                    catch (Exception e){}
                    try {
                        Constants.one_coin= Integer.parseInt(firebaseDataModel.getOne_coin());
                    }
                    catch (Exception e){}
                    try {
                        Constants.five_coin= Integer.parseInt(firebaseDataModel.getFive_coin());
                    }
                    catch (Exception e){}
                    try {
                        Constants.eight_coin= Integer.parseInt(firebaseDataModel.getEight_coin());
                    }
                    catch (Exception e){}
                    try {
                        Constants.counter = Integer.parseInt(firebaseDataModel.getCounter());
                        Constants.interShowCounter = Constants.counter - 1;
                    }
                    catch (Exception e){}
                }
                catch (Exception e){
                    Log.e("", "");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("", "");
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LetGoActivity.class));
                finish();
            }
        }, 3000);
    }
}
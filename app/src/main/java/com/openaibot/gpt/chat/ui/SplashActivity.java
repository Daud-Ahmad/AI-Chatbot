package com.openaibot.gpt.chat.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.openaibot.gpt.chat.BuildConfig;
import com.openaibot.gpt.chat.dao.BookmarkDatabase;
import com.openaibot.gpt.chat.models.FirebaseDataModel;
import com.openaibot.gpt.chat.models.HistoryModel;
import com.openaibot.gpt.chat.utils.Ads;
import com.openaibot.gpt.chat.utils.AlertDialogueUtils;
import com.openaibot.gpt.chat.utils.Constants;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.SharePreferences;

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

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {
            }
        });

        Ads.loadInterstitialAd(this);

        Constants.totalCoins = Integer.parseInt(SharePreferences.getString(this, Constants.COINS_KEY));
        BookmarkDatabase mDatabase = BookmarkDatabase.Companion.getDatabase(this);
        int ratingKey = Integer.parseInt(SharePreferences.getString(this, Constants.RATING_KEY));
        SharePreferences.saveString(this, Constants.RATING_KEY, String.valueOf(ratingKey + 1));

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
                    FirebaseDataModel firebaseDataModel = (FirebaseDataModel) dataSnapshot.getValue(FirebaseDataModel.class);

                    try {
                        Constants.is_new_version = firebaseDataModel.getIs_new_version();
                        Constants.is_app_blocked = firebaseDataModel.getIs_app_blocked();
                        Constants.app_link = firebaseDataModel.getApp_link();
                        Constants.new_version = firebaseDataModel.getNew_version();
                    }
                    catch (Exception e){
                        Log.e("","");
                    }

                    try {
                        if(firebaseDataModel.getToken() != null && firebaseDataModel.getToken().length() > 5){
                            Constants.token = "Bearer " + firebaseDataModel.getToken();
                        }
                    }
                    catch (Exception e){}
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
                if(Constants.is_new_version.equalsIgnoreCase("true")){
                    if(!Constants.new_version.isEmpty() && !Constants.new_version.equals(BuildConfig.VERSION_NAME)){
                        AlertDialogueUtils.showUpdateAlert(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                                    }
                                }, SplashActivity.this
                        );
                    }
                    else {
                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                        finish();
                    }
                }
                else if(Constants.is_app_blocked.equalsIgnoreCase("true")){
                    AlertDialogueUtils.showUpdateAlert(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Constants.app_link)));
                                }
                            }, SplashActivity.this
                    );
                }
                else {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }
            }
        }, 3000);
    }

//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        if(isCallUpdate){
//            isCallUpdate = false;
//            if(Constants.new_version.equals(BuildConfig.VERSION_NAME)){
//                AlertDialogueUtils.hideUpdateDialogue();
//                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//                finish();
//            }
//        }
//    }
}
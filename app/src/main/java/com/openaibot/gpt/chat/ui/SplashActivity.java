package com.openaibot.gpt.chat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.openaibot.gpt.chat.dao.BookmarkDatabase;
import com.openaibot.gpt.chat.models.HistoryModel;
import com.openaibot.gpt.chat.utils.Constants;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.SharePreferences;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        }, 3000);
    }
}
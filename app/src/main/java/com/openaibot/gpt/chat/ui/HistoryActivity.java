package com.openaibot.gpt.chat.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.databinding.ActivityHistoryBinding;
import com.openaibot.gpt.chat.ui.adapters.HistoryAdapter1;
import com.openaibot.gpt.chat.utils.Ads;

public class HistoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHistoryBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_history);

        Ads.loadNativeAds(this, binding.nativeAdPlaceHolder);

        binding.rvHistory.setLayoutManager(new LinearLayoutManager(this));
        HistoryAdapter1 historyAdapter = new HistoryAdapter1(this);
        binding.rvHistory.setAdapter(historyAdapter);
    }

    public void onBtnBack(View view){
        finish();
    }
}
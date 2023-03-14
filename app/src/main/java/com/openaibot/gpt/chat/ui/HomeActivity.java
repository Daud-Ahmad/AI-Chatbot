package com.openaibot.gpt.chat.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.databinding.ActivityHomeBinding;
import com.openaibot.gpt.chat.models.GenresModel;
import com.openaibot.gpt.chat.ui.adapters.GenresAdapter;
import com.openaibot.gpt.chat.ui.adapters.HistoryAdapter;
import com.openaibot.gpt.chat.utils.Ads;
import com.openaibot.gpt.chat.utils.AlertDialogueUtils;
import com.openaibot.gpt.chat.utils.Constants;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity implements GenresAdapter.CallBack {

    private ActivityHomeBinding binding;
    private HistoryAdapter historyAdapter;
    public int NOTIFICATION_REQUEST_CODE = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        Glide.with(this)
                .load(R.drawable.gift)
                .into(binding.gift);

        Ads.loadNativeAds(this, binding.nativeAdPlaceHolder);

        isNotificationPermissionGranted();

        binding.rvHistory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        historyAdapter = new HistoryAdapter(this);
        binding.rvHistory.setAdapter(historyAdapter);

        if(Constants.historyModelArrayList.size() == 0){
            binding.lblHistory.setVisibility(View.GONE);
            binding.lblMore.setVisibility(View.GONE);
            binding.rvHistory.setVisibility(View.GONE);
        }

        binding.rvGenres.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<GenresModel> arrayList = new ArrayList<>();
        arrayList.add(new GenresModel(true, "Education"));
        arrayList.add(new GenresModel(false, "Entertain"));
        arrayList.add(new GenresModel(false, "Idea, Business"));
        arrayList.add(new GenresModel(false, "  Health  "));
        binding.rvGenres.setAdapter(new GenresAdapter(arrayList,this, this));

        initTaskViewData(0);

        askRatings();
    }

    public void onClickCoins(View view){
        AlertDialogueUtils.onClickCoins(this, binding.lblCoins);
    }


    public void onClickMore(View view){
        Constants.interShowCounter++;
        if (Ads.mInterstitialAd != null && Constants.interShowCounter % Constants.counter == 0) {
            Ads.mInterstitialAd.show(this);
            Ads.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Ads.mInterstitialAd = null;
                    Ads.loadInterstitialAd(HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
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
            startActivity(new Intent(HomeActivity.this, HistoryActivity.class));
        }
    }


    public void onCrdChoose1(View view){
        Constants.interShowCounter++;
        if (Ads.mInterstitialAd != null && Constants.interShowCounter % Constants.counter == 0) {
            Ads.mInterstitialAd.show(this);
            Ads.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Ads.mInterstitialAd = null;
                    Ads.loadInterstitialAd(HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, ChatActivity.class)
                            .putExtra("title", binding.lblTitle1.getText().toString())
                            .putExtra("desc", binding.lblMsg1.getText().toString()));
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
            startActivity(new Intent(this, ChatActivity.class)
                    .putExtra("title", binding.lblTitle1.getText().toString())
                    .putExtra("desc", binding.lblMsg1.getText().toString()));
        }
    }

    public void onCrdChoose2(View view){
        Constants.interShowCounter++;
        if (Ads.mInterstitialAd != null && Constants.interShowCounter % Constants.counter == 0) {
            Ads.mInterstitialAd.show(this);
            Ads.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Ads.mInterstitialAd = null;
                    Ads.loadInterstitialAd(HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, ChatActivity.class)
                            .putExtra("title", binding.lblTitle2.getText().toString())
                            .putExtra("desc", binding.lblMsg2.getText().toString()));
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
            startActivity(new Intent(this, ChatActivity.class)
                    .putExtra("title", binding.lblTitle2.getText().toString())
                    .putExtra("desc", binding.lblMsg2.getText().toString()));
        }
    }

    public void onCrdChoose3(View view){
        Constants.interShowCounter++;
        if (Ads.mInterstitialAd != null && Constants.interShowCounter % Constants.counter == 0) {
            Ads.mInterstitialAd.show(this);
            Ads.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Ads.mInterstitialAd = null;
                    Ads.loadInterstitialAd(HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, ChatActivity.class)
                            .putExtra("title", binding.lblTitle3.getText().toString())
                            .putExtra("desc", binding.lblMsg3.getText().toString()));
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
            startActivity(new Intent(this, ChatActivity.class)
                    .putExtra("title", binding.lblTitle3.getText().toString())
                    .putExtra("desc", binding.lblMsg3.getText().toString()));
        }
    }

    public void onCrdChoose4(View view){
        Constants.interShowCounter++;
        if (Ads.mInterstitialAd != null && Constants.interShowCounter % Constants.counter == 0) {
            Ads.mInterstitialAd.show(this);
            Ads.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Ads.loadInterstitialAd(HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, ChatActivity.class)
                            .putExtra("title", binding.lblTitle4.getText().toString())
                            .putExtra("desc", binding.lblMsg4.getText().toString()));
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
            startActivity(new Intent(this, ChatActivity.class)
                    .putExtra("title", binding.lblTitle4.getText().toString())
                    .putExtra("desc", binding.lblMsg4.getText().toString()));
        }
    }

    public void onCrdChoose5(View view){
        Constants.interShowCounter++;
        if (Ads.mInterstitialAd != null && Constants.interShowCounter % Constants.counter == 0) {
            Ads.mInterstitialAd.show(this);
            Ads.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Ads.loadInterstitialAd(HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, ChatActivity.class)
                            .putExtra("title", binding.lblTitle5.getText().toString())
                            .putExtra("desc", binding.lblMsg5.getText().toString()));
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
            startActivity(new Intent(this, ChatActivity.class)
                    .putExtra("title", binding.lblTitle5.getText().toString())
                    .putExtra("desc", binding.lblMsg5.getText().toString()));
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        checkUpdate();

        String coins = Constants.totalCoins + " " + getString(R.string.remaining_messages);
        binding.lblCoins.setText(coins);
        if(Constants.historyModelArrayList.size() == 0){
            binding.lblHistory.setVisibility(View.GONE);
            binding.lblMore.setVisibility(View.GONE);
            binding.rvHistory.setVisibility(View.GONE);
        }
        else {
            binding.lblHistory.setVisibility(View.VISIBLE);
            binding.lblMore.setVisibility(View.VISIBLE);
            binding.rvHistory.setVisibility(View.VISIBLE);
            try {
                historyAdapter.notifyDataSetChanged();
            }
            catch (Exception e){}
        }
    }

    public void onClickMessage(View view){
        Constants.interShowCounter++;
        if (Ads.mInterstitialAd != null && Constants.interShowCounter % Constants.counter == 0) {
            Ads.mInterstitialAd.show(this);
            Ads.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Ads.mInterstitialAd = null;
                    Ads.loadInterstitialAd(HomeActivity.this);
                    startActivity(new Intent(HomeActivity.this, ChatActivity.class));
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
            startActivity(new Intent(this, ChatActivity.class));
        }
    }

    private void initTaskViewData(int index){
        switch (index){
            case 0:
                binding.lblTitle1.setText(getString(R.string.edu_title_1));
                binding.lblMsg1.setText(getString(R.string.edu_desc_1));

                binding.lblTitle2.setText(getString(R.string.edu_title_2));
                binding.lblMsg2.setText(getString(R.string.edu_desc_2));

                binding.lblTitle3.setText(getString(R.string.edu_title_3));
                binding.lblMsg3.setText(getString(R.string.edu_desc_3));

                binding.lblTitle4.setText(getString(R.string.edu_title_4));
                binding.lblMsg4.setText(getString(R.string.edu_desc_4));

                binding.lblTitle5.setText(getString(R.string.edu_title_5));
                binding.lblMsg5.setText(getString(R.string.edu_desc_5));
                break;

            case 1:
                binding.lblTitle1.setText(getString(R.string.ent_title_1));
                binding.lblMsg1.setText(getString(R.string.ent_desc_1));

                binding.lblTitle2.setText(getString(R.string.ent_title_2));
                binding.lblMsg2.setText(getString(R.string.ent_desc_2));

                binding.lblTitle3.setText(getString(R.string.ent_title_3));
                binding.lblMsg3.setText(getString(R.string.ent_desc_3));

                binding.lblTitle4.setText(getString(R.string.ent_title_4));
                binding.lblMsg4.setText(getString(R.string.ent_desc_4));

                binding.lblTitle5.setText(getString(R.string.ent_title_5));
                binding.lblMsg5.setText(getString(R.string.ent_desc_5));
                break;

            case 2:
                binding.lblTitle1.setText(getString(R.string.idea_title_1));
                binding.lblMsg1.setText(getString(R.string.idea_desc_1));

                binding.lblTitle2.setText(getString(R.string.idea_title_2));
                binding.lblMsg2.setText(getString(R.string.idea_desc_2));

                binding.lblTitle3.setText(getString(R.string.idea_title_3));
                binding.lblMsg3.setText(getString(R.string.idea_desc_3));


                binding.lblTitle4.setText(getString(R.string.idea_title_4));
                binding.lblMsg4.setText(getString(R.string.idea_desc_4));

                binding.lblTitle5.setText(getString(R.string.idea_title_5));
                binding.lblMsg5.setText(getString(R.string.idea_desc_5));
                break;

            case 3:
                binding.lblTitle1.setText(getString(R.string.health_title_1));
                binding.lblMsg1.setText(getString(R.string.health_desc_1));

                binding.lblTitle2.setText(getString(R.string.health_title_2));
                binding.lblMsg2.setText(getString(R.string.health_desc_2));

                binding.lblTitle3.setText(getString(R.string.health_title_3));
                binding.lblMsg3.setText(getString(R.string.health_desc_3));

                binding.lblTitle4.setText(getString(R.string.health_title_4));
                binding.lblMsg4.setText(getString(R.string.health_desc_4));

                binding.lblTitle5.setText(getString(R.string.health_title_5));
                binding.lblMsg5.setText(getString(R.string.health_desc_5));

        }
    }

    @Override
    public void onCallBack(int position) {
        initTaskViewData(position);
    }

    @Override
    public void onBackPressed() {
        if(Ads.mInterstitialAd != null){
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

    public boolean isNotificationPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 33) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_REQUEST_CODE);
                return false;
            }
        } else {
            return true;
        }
    }
}
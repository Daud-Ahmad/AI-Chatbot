package com.openaibot.gpt.chat.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.SharePreferences;
import com.openaibot.gpt.chat.databinding.DialogueCoinBinding;
import com.openaibot.gpt.chat.databinding.DialogueLoadingBinding;
import com.openaibot.gpt.chat.databinding.DialogueUpdateBinding;
import com.openaibot.gpt.chat.ui.PackagesActivity;

public class AlertDialogueUtils {
    private static AlertDialog alertDialogMessage;
    private static AlertDialog loadingDialog;

    private static AlertDialog updateDialog;

    public static void showCoinsAlert(
            View.OnClickListener unlimitedListener ,
            View.OnClickListener watchAdsListener ,
            Activity activity)
    {
        if(!activity.isFinishing()) {
            DialogueCoinBinding customAlertDialogueBinding = DialogueCoinBinding.inflate(LayoutInflater.from(activity));
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialogStyleNew);
            builder.setView(customAlertDialogueBinding.getRoot());

            customAlertDialogueBinding.btnGetUnlimited.setOnClickListener(unlimitedListener);
            customAlertDialogueBinding.btnWatchAds.setOnClickListener(watchAdsListener);

            if(!SharePreferences.getString(activity, Constants.is_inter_for_reward).equals("1") && Constants.totalCoins > 0){
                customAlertDialogueBinding.actvTitle.setVisibility(View.GONE);
                customAlertDialogueBinding.btnWatchAds.setVisibility(View.GONE);
            }

            customAlertDialogueBinding.actvTitle.setText("Let's watch one video ad to earn " + Constants.rv_coin + " more messages");
            customAlertDialogueBinding.imageViewCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(alertDialogMessage!=null && alertDialogMessage.isShowing())
                    {
                        alertDialogMessage.dismiss();
                    }
                }
            });

            alertDialogMessage = builder.create();
            alertDialogMessage.setCancelable(false);
            alertDialogMessage.setCanceledOnTouchOutside(false);
            if (activity != null && !activity.isFinishing()) {
                alertDialogMessage.show();
            }
        }
    }

    public static void showUpdateAlert(
            View.OnClickListener updateListener ,
            Activity activity)
    {
        if(!activity.isFinishing()) {
            DialogueUpdateBinding customAlertDialogueBinding = DialogueUpdateBinding.inflate(LayoutInflater.from(activity));
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialogStyleNew);
            builder.setView(customAlertDialogueBinding.getRoot());
            customAlertDialogueBinding.btnUpdate.setOnClickListener(updateListener);
            updateDialog = builder.create();
            updateDialog.setCancelable(false);
            updateDialog.setCanceledOnTouchOutside(false);
            if (activity != null && !activity.isFinishing()) {
                updateDialog.show();
            }
        }
    }

    public static void showLoadingAlert(
            Activity activity)
    {
        if(!activity.isFinishing()) {
            DialogueLoadingBinding dialogueLoadingBinding = DialogueLoadingBinding.inflate(LayoutInflater.from(activity));
            AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyDialogStyleNew);
            builder.setView(dialogueLoadingBinding.getRoot());

            loadingDialog = builder.create();
            loadingDialog.setCancelable(false);
            loadingDialog.setCanceledOnTouchOutside(false);
            if (activity != null && !activity.isFinishing()) {
                loadingDialog.show();
            }
        }
    }

    public static void hideCoinsDialogue()
    {
        if(alertDialogMessage!=null && alertDialogMessage.isShowing())
        {
            alertDialogMessage.dismiss();
        }
    }

    public static void hideLoadingDialogue()
    {
        if(loadingDialog!=null && loadingDialog.isShowing())
        {
            loadingDialog.dismiss();
        }
    }

    public static void hideUpdateDialogue()
    {
        if(updateDialog!=null && updateDialog.isShowing())
        {
            updateDialog.dismiss();
        }
    }

    public static void onClickCoins(Activity context, TextView lblCoins){
        AlertDialogueUtils.showCoinsAlert(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialogueUtils.hideCoinsDialogue();
                        context.startActivity(new Intent(context, PackagesActivity.class));

                    }
                },new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialogueUtils.hideCoinsDialogue();
                        if(Constants.is_inter_for_reward.equals("true") && Ads.mInterstitialAd != null){
                            Ads.mInterstitialAd.show(context);
                            Ads.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    Ads.mInterstitialAd = null;
                                    Ads.loadInterstitialAd(context);
                                    SharePreferences.saveString(context, Constants.is_inter_for_reward, "true");
                                    try {
                                        Constants.totalCoins = Constants.totalCoins + Constants.rv_coin;
                                        SharePreferences.saveString(context, Constants.COINS_KEY, String.valueOf(Constants.totalCoins));
                                    }
                                    catch (Exception e){}
                                    try {
                                        String value = Constants.totalCoins + " " + context.getString(R.string.remaining_messages);
                                        lblCoins.setText(value);
                                    }
                                    catch (Exception e){}
                                    try {
                                        Toast.makeText(context, "Rewarded", Toast.LENGTH_SHORT).show();
                                    }
                                    catch (Exception e){}
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
                            showLoadingAlert(context);
                            Ads.loadRewardedAd(context, lblCoins);
                        }
                    }
                }, context);
    }
}

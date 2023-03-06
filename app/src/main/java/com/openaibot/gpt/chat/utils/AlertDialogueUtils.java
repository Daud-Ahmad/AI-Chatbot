package com.openaibot.gpt.chat.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.SharePreferences;
import com.openaibot.gpt.chat.databinding.DialogueCoinBinding;
import com.openaibot.gpt.chat.databinding.DialogueLoadingBinding;
import com.openaibot.gpt.chat.ui.PackagesActivity;

public class AlertDialogueUtils {
    private static AlertDialog alertDialogMessage;
    private static AlertDialog loadingDialog;

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
                        showLoadingAlert(context);
                        Ads.loadRewardedAd(context, lblCoins);
                    }
                }, context);
    }
}

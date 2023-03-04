package com.openaibot.gpt.chat.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.databinding.DialogueCoinBinding;

public class AlertDialogueUtils {
    private static AlertDialog alertDialogMessage;

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

    public static void hideCoinsDialogue()
    {
        if(alertDialogMessage!=null && alertDialogMessage.isShowing())
        {
            alertDialogMessage.dismiss();
        }
    }
}

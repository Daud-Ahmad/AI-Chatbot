package com.openaibot.gpt.chat.ui;

import static com.google.android.play.core.install.model.UpdateAvailability.UPDATE_AVAILABLE;

import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.openaibot.gpt.chat.BuildConfig;
import com.openaibot.gpt.chat.utils.AlertDialogueUtils;
import com.openaibot.gpt.chat.utils.Constants;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onPostResume() {
        super.onPostResume();
        checkUpdate();
    }

    public void checkUpdate(){
        AlertDialogueUtils.hideUpdateDialogue();
        if(Constants.is_new_version.equalsIgnoreCase("true")){
            if(!Constants.new_version.isEmpty() && !Constants.new_version.equals(BuildConfig.VERSION_NAME)){
                AlertDialogueUtils.showUpdateAlert(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                            }
                        }, BaseActivity.this
                );
            }
        }
        else if(Constants.is_app_blocked.equalsIgnoreCase("true")){
            AlertDialogueUtils.showUpdateAlert(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Constants.app_link)));
                        }
                    }, BaseActivity.this
            );
        }
    }

    public void checkForUpdate(){
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if(appUpdateInfo.updateAvailability() == UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)){
                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                appUpdateInfo,
                                AppUpdateType.IMMEDIATE,
                                BaseActivity.this,
                                50
                        );
                    } catch (IntentSender.SendIntentException e) {
                    }
                }
            }
        });
    }
}

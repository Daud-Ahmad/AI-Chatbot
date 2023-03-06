package com.openaibot.gpt.chat.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
import com.openaibot.gpt.chat.BuildConfig;
import com.openaibot.gpt.chat.utils.AlertDialogueUtils;
import com.openaibot.gpt.chat.utils.Constants;

public class BaseActivity extends AppCompatActivity {

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

    void askRatings() {
        ReviewManager manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(this, reviewInfo);
                flow.addOnCompleteListener(task2 -> {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                });
            }
        });
    }
}

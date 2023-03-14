package com.openaibot.gpt.chat.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.openaibot.gpt.chat.R;
import com.openaibot.gpt.chat.ui.SplashActivity;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class FirebaseMessagingServices extends FirebaseMessagingService{
    String FCM_PARAM = "picture";
    private final String  CHANNEL_NAME = "FCM";
    private final String CHANNEL_DESC = "Firebase Cloud Messaging";
    private int numMessages = 0;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        RemoteMessage.Notification notification = message.getNotification();
        Map<String, String> data = message.getData();
        if (notification != null) {
            sendNotification(notification, data);
        }
    }

    private void sendNotification(
            RemoteMessage.Notification notification, Map<String, String> data) {
        String myCustomKey = data.get("new_app_link");
        Bundle bundle = new Bundle();
        bundle.putString(FCM_PARAM, data.get(FCM_PARAM));
        Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("new_app_link", myCustomKey);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, getString(R.string.notification_channel_id))
                        .setContentTitle(notification.getTitle())
                        .setContentText(notification.getBody())
                        .setAutoCancel(true)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) //.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.win))
                        .setContentIntent(pendingIntent)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                        .setLights(Color.RED, 1000, 300)
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setNumber(++numMessages)
                        .setSmallIcon(R.mipmap.ic_launcher_round);
        try {
            String picture = data.get(FCM_PARAM);
            if (picture != null && !picture.isEmpty()) {
                URL url = new URL(picture);
                Bitmap bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                notificationBuilder.setStyle(
                        new NotificationCompat.BigPictureStyle().bigPicture(bigPicture)
                                .setSummaryText(notification.getBody())
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    getString(R.string.notification_channel_id),
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription(CHANNEL_DESC);
            channel.setShowBadge(true);
            channel.canShowBadge();
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            long[] array = {100, 200, 300, 400, 500};
            channel.setVibrationPattern(array);
            assert(notificationManager != null);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0, notificationBuilder.build());
    }
}

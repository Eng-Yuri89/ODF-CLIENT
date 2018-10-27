package com.nanotrick.orderfood.Helper;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;

import com.nanotrick.orderfood.R;

/**
 * Created by NANOTRICK on 2018. 10. 29..
 */

public class NotificationHelper extends ContextWrapper {

    private static final String NANOTRICK_CHANNEL_ID = "com.nanotrick.orderfood.NANOTRICK";
    private static final String NANOTRICK_CHANNEL_NAME = "OrderFood";

    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) // Api 가 26 이상인 경우에만 이 함수를 사용
            createChanel();
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChanel() {
        NotificationChannel edtChannel = new NotificationChannel(NANOTRICK_CHANNEL_ID
                , NANOTRICK_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT);
        edtChannel.enableLights(false);
        edtChannel.enableVibration(true);
        edtChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(edtChannel);
    }

    public NotificationManager getManager() {
        if (manager == null)
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        return manager;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder edtEatItChannelNotification(String title, String body, PendingIntent contentIntent, Uri soundUri) {
        return new android.app.Notification.Builder(getApplicationContext(), NANOTRICK_CHANNEL_ID)
                .setContentIntent(contentIntent)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(soundUri)
                .setAutoCancel(false);

    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder edtEatItChannelNotification(String title, String body, Uri soundUri) {
        return new android.app.Notification.Builder(getApplicationContext(), NANOTRICK_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(soundUri)
                .setAutoCancel(false);

    }

}

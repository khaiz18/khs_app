package com.alex.common.app;

import com.google.firebase.messaging.FirebaseMessagingService;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
         import android.app.PendingIntent;
         import android.content.Context;
         import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
         import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.RemoteMessage;
import com.rabdorms.forcemobilestudios.R;
import com.rabdorms.forcemobilestudios.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "News";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        super.onMessageReceived(remoteMessage);
        Log.d("msg", "onMessageReceived: " + remoteMessage.getData().get("Message Key"));
        Intent intent = new Intent(this, notification.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = "Default"; //was Default
        NotificationCompat.Builder builder = new  NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.app_icon)
                .setContentTitle(remoteMessage.getData().get("title"))

                .setContentText(remoteMessage.getData().get("Message Key")).setAutoCancel(true).setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        SharedPreferences test = getSharedPreferences("MyPrefsFile",0);
        SharedPreferences.Editor editor = test.edit();
        editor.putString("testing",remoteMessage.getData().get("Message Key").toString());
        editor.commit();


        manager.notify(0, builder.build());
    }

















    //This method is only generating push notification
    private void sendNotification(String title, String messageBody, String click_action) {

        Intent intent ;


        if(click_action.equals("SOMEACTIVITY")) {
            intent = new Intent(this, notification.class);



            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }else if (click_action.equals("MAIN")) {
            intent = new Intent(this, notification.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        }else{

            intent = new Intent(this, MainActivity.class);

        }



        
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT); //Flag_One_Shot



        long[] pattern = {500,500,500,500,500};

      //  PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,
          //      PendingIntent.FLAG_ONE_SHOT);








/*
       Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.id.safari)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setVibrate(pattern)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);



        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
        getApplicationContext().startActivity(intent);
*/




    }



}
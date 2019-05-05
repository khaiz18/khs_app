package com.alex.common.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import com.google.firebase.messaging.RemoteMessage;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.rabdorms.forcemobilestudios.R;

public class notification extends AppCompatActivity {
    private TextView textView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drc_notification_alert);


        TextView text = (TextView) findViewById(R.id.textView1);

        try {
            SharedPreferences data = getSharedPreferences("MyPrefsFile",0);
            text.setText("DRC NOTIFICATION : \n" + data.getString("testing", "novalue"));
            Log.e("main",data.getString("testing","novalue"));
        }catch (Exception e){
            e.printStackTrace();
        }


        if (text != null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                Object value;
                for (String key : getIntent().getExtras().keySet()) {
                    if (key.equals("Message Key")) {
                        value = getIntent().getExtras().get(key); // value will represend your message body... Enjoy It
                        text.setText("DRC NOTIFICATION : \n" + value);
                        Log.d("NotificationTag", key + "____" + value);
                    }


                }

            }
        }
    }

}







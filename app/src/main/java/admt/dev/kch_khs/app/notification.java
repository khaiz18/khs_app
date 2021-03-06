package admt.dev.kch_khs.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import admt.dev.kch_khs.util.DatabaseHelper;

import admt.dev.kch_khs.R;
import admt.dev.kch_khs.util.AboutActivity;
import admt.dev.kch_khs.util.Activity_Settings;
import admt.dev.kch_khs.util.MainActivity;


public class notification extends AppCompatActivity implements View.OnClickListener {
    private TextView textView1;
    public static Object value;
    Button btn;
   DatabaseHelper mdatabasehelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drc_notification_alert);
        mdatabasehelper = new DatabaseHelper(this);
        final Button dismiss = (Button)findViewById(R.id.dissmiss_button);
        dismiss.setOnClickListener(this);



        TextView text = (TextView) findViewById(R.id.textView1);

        try {
            SharedPreferences data = getSharedPreferences("MyPrefsFile",0);
            text.setText("KHS NOTIFICATION : \n" + data.getString("testing", "novalue"));
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
                        value = getIntent().getExtras().get(key); // value will represent your message body... Enjoy It
                        text.setText("KHS NOTIFICATION : \n" + value);
                        Log.d("NotificationTag", key + "____" + value);
                        String ivalue = value.toString();
                        mdatabasehelper.addData(ivalue);
                    }


                }

            }
        }





    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override public void onClick(View v) {


        final Button dismiss = (Button)findViewById(R.id.dissmiss_button);

        dismiss.setOnClickListener(this);




            if (v.equals(dismiss)) {
                startActivity(new Intent(notification.this, MainActivity.class));
                finishAffinity();


            }





    }



}







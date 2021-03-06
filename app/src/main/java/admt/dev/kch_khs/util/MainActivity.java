package admt.dev.kch_khs.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import admt.dev.kch_khs.util.fbell;
import admt.dev.kch_khs.app.MyFirebaseMessagingService;
import admt.dev.kch_khs.app.notification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import admt.dev.kch_khs.R;
import admt.dev.kch_khs.adapter.ResourceAdatper;
import admt.dev.kch_khs.util.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_date, textView6;
    LinearLayout ll_cal, ll_about, ll_res, ll_ath, ll_clubs, ll_con, sBell;
    ListView lv_resource;
    ImageView img_home_back;
    ResourceAdatper list_adapter;
    String[] title;
    String[] info;
    String[] domain;
    Activity activity;
    TextView label, d_message;


    NotificationBadge mBadge;
    private static final String TABLE_NAME = "fnotification";



    public ListView mlistview;
    DatabaseHelper mDatabaseHelper;
    private String name;
    View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseMessaging.getInstance().subscribeToTopic("khs");




        mlistview = (ListView) findViewById(R.id.fbell_list);


        getSupportActionBar().hide();
        setContentView(admt.dev.kch_khs.R.layout.activity_main);

        ll_cal = (LinearLayout) findViewById(admt.dev.kch_khs.R.id.ll_cal);
        ll_about = (LinearLayout) findViewById(admt.dev.kch_khs.R.id.ll_about);
        ll_res = (LinearLayout) findViewById(admt.dev.kch_khs.R.id.ll_res);
        ll_ath = (LinearLayout) findViewById(admt.dev.kch_khs.R.id.ll_ath);
        ll_clubs = (LinearLayout) findViewById(admt.dev.kch_khs.R.id.ll_clubs);
        ll_con = (LinearLayout) findViewById(admt.dev.kch_khs.R.id.ll_con);
        tv_date = (TextView) findViewById(admt.dev.kch_khs.R.id.tv_date);
        tv_date.setSelected(true);
        tv_date.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tv_date.setSingleLine(true);
        sBell = (LinearLayout) findViewById(R.id.sBell);

        ll_cal.setOnClickListener(this);
        ll_about.setOnClickListener(this);
        ll_res.setOnClickListener(this);
        ll_ath.setOnClickListener(this);
        ll_clubs.setOnClickListener(this);
        ll_con.setOnClickListener(this);
        sBell.setOnClickListener(this);
        label = (TextView) findViewById(R.id.textView5);
        d_message = (TextView) findViewById(R.id.textView6);


        // textView.setText(title[0]);











      //  ArrayList<String> listData = new ArrayList<>();

      //android.widget.ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
       // mlistview.setAdapter(adapter);


            /*tv_date.setText("rwar");
            final ValueAnimator animator = ValueAnimator.ofFloat(1.0f, -1.0f);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(9000L);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    final float progress = (float) animation.getAnimatedValue();
                    final float width = tv_date.getWidth();
                    final float translationX = width * progress;
                    tv_date.setTranslationX(translationX);
                }
            });
            animator.start();*/

        dbticker();

        dbBadge();
        fetchData process = new fetchData(this);
        process.execute();


    }


   /* @Override
    protected void onPause() {
        super.onPause();
        dbticker();
        dbBadge();
    }*/

    @Override
    protected void onResume() {
        super.onResume();
       dbticker();
        dbBadge();
    }

        public void dbBadge() {



                DatabaseHelper db = new DatabaseHelper(this);

                Cursor cursor = db.getData();
                cursor.moveToFirst();

                if (cursor.moveToFirst()) {
                    for (int i = 0; i < cursor.getCount(); i++) {
                        cursor.moveToNext();
                    }
                }

                cursor.close();
                db.close();
            mBadge = (NotificationBadge) findViewById(R.id.badge);

            mBadge.setNumber(cursor.getCount());



            }







    public void dbticker(){

        DatabaseHelper db = new DatabaseHelper(this);

        Cursor cursor = db.getData();
        cursor.moveToFirst();
        List<String> myFloats = new ArrayList<>();
       // String[] myFloats = {" ", " " , " ", " "};
        String tempFloat;




        if (cursor.moveToFirst())
        {
            for (int i = 0; i < cursor.getCount(); i++)
            {

                //string.append(myFloats.get(i).toString());
                    //myFloats.get() = cursor.getString(1);

                myFloats.add((cursor.getString(1)));
                cursor.moveToNext();


            }
        }
        cursor.close();
        db.close();



        if(myFloats.size() > 4){

            String temp;
            temp = myFloats.get(5);
            myFloats.add(0, temp);
            myFloats.remove(5);


        }

        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");//formating according to my need
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(cal.getTime());
        String date = formatter.format(today);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        //tv_date.setText("\t \t \t \t \t \t"+ myFloats.get(0)  + "\t \t \t \t \t \t" + myFloats.get(1) + "\t \t \t \t \t \t" + myFloats.get(2) + "\t \t \t \t \t \t" + myFloats.get(3) );
        if(myFloats.size()==0) {
            tv_date.setText(dayOfTheWeek + " " + date);
        }else if(myFloats.size() >= 1) {

            int test = myFloats.size() - 1;
            //String test2 = myFloats.lastIndexOf(test);
            tv_date.setText(myFloats.get(test).toString());
        }


            final float startSize = 1; // Size in pixels
            final float endSize = 1;
            final ValueAnimator animator = ValueAnimator.ofFloat(1.0f, -1.0f);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(9000L);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    final float progress = (float) animation.getAnimatedValue();
                    final float width = tv_date.getWidth();
                    final float translationX = width * progress;
                    tv_date.setTranslationX(translationX);
                }
            });


            animator.start();


    }








    private void setDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        int year = c.getYear() + 1900;
        int day = c.getDate();
        String monthname = (String) android.text.format.DateFormat.format("MMMM", new Date());
        String weekDay = dayFormat.format(c.getTime());

        tv_date.setText(weekDay + " " + monthname + " " + String.valueOf(day) + ", " + String.valueOf(year) + "wASsssup");
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case admt.dev.kch_khs.R.id.ll_cal:
                intent = new Intent(MainActivity.this, admt.dev.kch_khs.util.CalendarActivity.class);
                startActivity(intent);
                break;
            case admt.dev.kch_khs.R.id.ll_about:
                intent = new Intent(MainActivity.this, Activity_Settings.class);
                startActivity(intent);
                break;
            case admt.dev.kch_khs.R.id.ll_res:
                intent = new Intent(MainActivity.this, admt.dev.kch_khs.util.ResourceActivity.class);
                startActivity(intent);
                break;
            case admt.dev.kch_khs.R.id.ll_ath:
                intent = new Intent(MainActivity.this, AthleticsActivity.class);
                startActivity(intent);
                break;
            case admt.dev.kch_khs.R.id.ll_clubs:
                intent = new Intent(MainActivity.this, ClubsActivity.class);
                startActivity(intent);
                break;
            case admt.dev.kch_khs.R.id.ll_con:
                intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
                break;
            case R.id.sBell:
                intent = new Intent(MainActivity.this, fbell.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(admt.dev.kch_khs.R.string.app_name);
        builder.setMessage("Do you want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public class fetchData extends AsyncTask<Void, Void, Void> {

        String data = "";
        String label_M="";
        String daily_M = "";
        String[] title_temp;
        String[] info_temp;
        String[] domain_temp;
        int num = 0;
        private ProgressDialog dialog;

        public fetchData(MainActivity activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Loading data, please wait...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("https://script.google.com/macros/s/AKfycbzTWtJ6J_kPfsKuOd5th1xHAvcSYx7596om1Z5WuIAiw5zi0FM/exec");
                // https://script.google.com/macros/s/AKfycbwUL9kaUSG_DTLUsnwhGfT_QaECSUHqqvE7Zb8_WqsqJa6V61lj/exec
                //https://script.google.com/macros/s/AKfycbzLKHCHMVd3VciwauCGQaBAPoy1ItdCBwYzjaY7loB7spBJgp8/exec

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    if (line != null)
                        data = data + line;
                }

                JSONObject jo_original = new JSONObject(data);
                JSONArray jsonArray = jo_original.getJSONArray("MessageKHS");
                JSONObject jo_each = (JSONObject) jsonArray.get(0);

                title_temp = new String[jsonArray.length()];
                info_temp = new String[jsonArray.length()];
                domain_temp = new String[jsonArray.length()];
                label_M = jo_each.getString("labelName");
                daily_M = jo_each.getString("dailyMessage");







            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

          /*  title = new String[num];
            info = new String[num];
            domain = new String[num];
            for (int i = 0; i < num; i++) {
                title[i] = title_temp[i];
                info[i] = info_temp[i];
                domain[i] = domain_temp[i];
            }*/
            label.setText(label_M);
            d_message.setText(daily_M);

        }


    }
}



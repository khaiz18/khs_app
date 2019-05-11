package admt.dev.kch_khs.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.text.SpannableString;
import android.text.TextUtils;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import admt.dev.kch_khs.R;
import admt.dev.kch_khs.adapter.ResourceAdatper;
import admt.dev.kch_khs.util.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_date;
    LinearLayout ll_cal, ll_about, ll_res, ll_ath, ll_clubs, ll_con, sBell;
    ListView lv_resource;
    ImageView img_home_back;
    ResourceAdatper list_adapter;
    String[] title;
    String[] info;
    String[] domain;
    Activity activity;

    public ListView mlistview;
    DatabaseHelper mDatabaseHelper;
    String yo = "hi";
    private String name;
    View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseMessaging.getInstance().subscribeToTopic("test");




        mlistview = (ListView) findViewById(R.id.fbell_list);


        String Username;


        ArrayList<String> listItem;
        ArrayAdapter adapter;











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

        dbtest();
        }

     //   MainActivity.fetchData process = new fetchData(this);
     //   process.execute();

    public void dbtest(){

        DatabaseHelper db = new DatabaseHelper(this);

        Cursor cursor = db.getData();
        cursor.moveToFirst();
        String[] myFloats = {" ", " " , " ", " "};

        if (cursor.moveToFirst())
        {
            for (int i = 0; i < cursor.getCount(); i++)
            {
                myFloats[i] = cursor.getString(1);
                cursor.moveToNext();
            }
        }
        cursor.close();
       // tv_date.setText(myFloats[0] + " " + myFloats[1] );
      //  tv_date.setText(cursor.getString(1).toString());


        tv_date.setText("\t \t \t \t \t \t"+ myFloats[0]  + "\t \t \t \t \t \t" + myFloats[1] + "\t \t \t \t \t \t" + myFloats[2] + "\t \t \t \t \t \t" + myFloats[3] );

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
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
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
                URL url = new URL("https://script.google.com/macros/s/AKfycbxZxrG1Fhq6qMuxXSFNGneKXUZDkMXvDsFysdYRDKOdhi-iqgN5/exec");
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
                JSONArray jsonArray = jo_original.getJSONArray("athletics");

                title_temp = new String[jsonArray.length()];
                info_temp = new String[jsonArray.length()];
                domain_temp = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject JO = (JSONObject) jsonArray.get(i);
                    if (!(JO.getString("sport").equals("") || JO.getString("sport") == null)) {
                        title_temp[num] = JO.getString("sport");
                        info_temp[num] = JO.getString("name");
                        domain_temp[num] = JO.getString("kind");
                        Log.d("test", "doInBackground: " + domain_temp[1]);
                        num++;
                    }


                }


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

            title = new String[num];
            info = new String[num];
            domain = new String[num];
            for (int i = 0; i < num; i++) {
                title[i] = title_temp[i];
                info[i] = info_temp[i];
                domain[i] = domain_temp[i];
            }

            //   list_adapter = new ResourceAdatper((ResourceActivity) activity, title, info, domain);

            //   lv_resource.setAdapter(list_adapter);

          /*  tv_date.setText(title[0]);
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
        }


    }
}



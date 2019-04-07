package admt.dev.kch_khs.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import admt.dev.kch_khs.adapter.CalendarAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView img_home_back_calendar;
    ListView lv_calendar;
    CalendarAdapter list_adapter;
    String[] kind, day, week, dec, month;
    Activity activity;
    String[] title;
    String[] descr;
    String[] ename;
    String[] loc;
    String[] stime;

    String[] etime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* String s= "KHS_APP powered by NHTS";
        SpannableString ss1=  new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 0,7, 0); // set size
        setTitle(ss1 );*/
        getSupportActionBar().hide();

        setContentView(admt.dev.kch_khs.R.layout.activity_calendar);


        activity = this;

        lv_calendar = (ListView) findViewById(admt.dev.kch_khs.R.id.lv_calendar);
        img_home_back_calendar = (ImageView) findViewById(admt.dev.kch_khs.R.id.img_home_back_calendar);
        img_home_back_calendar.setOnClickListener(this);

        fetchData process = new fetchData(this);
        process.execute();
    }

    @Override
    public void onClick(View v) {
        switch (admt.dev.kch_khs.R.id.img_home_back_calendar) {
            case admt.dev.kch_khs.R.id.img_home_back_calendar:
                finish();
                break;
        }
    }

    public class fetchData extends AsyncTask<Void, Void, Void> {

        String data = "";
        String[] title_temp;
        String [] month_temp;
        String [] day_temp;
        String[] info_temp;
        String[] event_temp;
        String[] loc_temp;
        String[] stime_temp;
        String[] etime_temp;



        int num = 0;

        private ProgressDialog dialog;

        public fetchData(CalendarActivity activity) {
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
                URL url = new URL("https://script.google.com/macros/s/AKfycbwP2T0638xwm_aeDt74_aBVWASkOoiJ5Y4XGmNGSK0janSOU2iz/exec");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    if (line != null)
                        data = data + line;
                    Log.d("Debug Calendar", data.toString());

                }


                JSONObject jo_original = new JSONObject(data);
                Log.d("Debug object", jo_original.toString());
                JSONArray jsonArray = jo_original.getJSONArray("test");
                Log.d("Debug  array", jsonArray.toString());

                title_temp = new String[jsonArray.length()];
                month_temp = new String[jsonArray.length()];
                day_temp = new String[jsonArray.length()];
                info_temp = new String[jsonArray.length()];
                event_temp = new String[jsonArray.length()];
                loc_temp = new String[jsonArray.length()];
                stime_temp = new String[jsonArray.length()];
                etime_temp = new String[jsonArray.length()];


                //          domain_temp = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject JO = (JSONObject) jsonArray.get(i);
                    if (!(JO.getString("start").equals("") || JO.getString("start") == null)) {
                        title_temp[num] = JO.getString("start");
                        Log.d("title temp", title_temp[num].toString());
                        month_temp[num] = JO.getString("month");

                        day_temp[num] = JO.getString("day");


                        info_temp[num] = JO.getString("descr");

                        event_temp[num] = JO.getString("title");

                        loc_temp[num] = JO.getString("loc");
                        Log.d("location temp", loc_temp[num].toString());

                        stime_temp[num] = JO.getString("stime");
                        Log.d("stime temp", stime_temp[num].toString());

                        etime_temp[num] = JO.getString("etime");



//                        domain_temp[num] = JO.getString("domain");
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
            descr = new String[num];
            month = new String[num];
            day = new String[num];
            ename = new String[num];
            loc = new String[num];
            stime = new String[num];
            etime = new String[num];


            //   domain = new String[num];
            for (int i = 0; i < num; i++) {

                title[i] = title_temp[i];

                month[i] = month_temp[i];
                day[i] = day_temp[i];

                descr[i] = info_temp[i];
                ename[i] = event_temp[i];
                loc[i] = loc_temp[i];
                stime[i] = stime_temp[i];
                etime[i] = etime_temp[i];

            }



            list_adapter = new CalendarAdapter((CalendarActivity) activity, title, descr, ename, loc, stime, etime, month, day);
            lv_calendar.setAdapter(list_adapter);
        }
    }
}

   /* private void setData() {
        kind = new String[42];
        day = new String[42];
        week = new String[42];
        dec = new String[42];

        kind[0] = "November";
        kind[29] = "December";

        dec[2] = "Veterans day: Federal Holiday observed";
        dec[4] = "Veterans day: Federal Holiday observed";
        dec[7] = "No School for Students(Teacher Professional)";
        dec[9] = "No School for Students(Teacher Professional)";
        dec[16] = "Thanksgiving Day: Federal Holiday";
        dec[18] = "Thanksgiving Day: Federal Holiday";
        dec[19] = "Friday: Recess Day";
        dec[21] = "Friday: Recess Day";
        dec[26] = "Mandatory Winter sports meeting";
        dec[41] = "Earliest Day for Accelerated Withdrawal for Credit";

        day[1] = "12";
        day[3] = "13";
        day[5] = "14";
        day[6] = "15";
        day[8] = "16";
        day[10] = "17";
        day[11] = "18";
        day[12] = "19";
        day[13] = "20";
        day[14] = "21";
        day[15] = "22";
        day[17] = "23";
        day[20] = "24";
        day[22] = "25";
        day[23] = "26";
        day[24] = "27";
        day[25] = "28";
        day[27] = "29";
        day[28] = "30";
        day[30] = "1";
        day[31] = "2";
        day[32] = "3";
        day[33] = "4";
        day[34] = "5";
        day[35] = "6";
        day[36] = "7";
        day[37] = "8";
        day[38] = "9";
        day[39] = "10";
        day[40] = "11";

        week[1] = "Monday";
        week[3] = "Tuesday";
        week[5] = "Wednesday";
        week[6] = "Thursday";
        week[8] = "Friday";
        week[10] = "Saturday";
        week[11] = "Sunday";
        week[12] = "Monday";
        week[13] = "Tuesday";
        week[14] = "Wednesday";
        week[15] = "Thursday";
        week[17] = "Friday";
        week[20] = "Saturday";
        week[22] = "Sunday";
        week[23] = "Monday";
        week[24] = "Tuesday";
        week[25] = "Wednesday";
        week[27] = "Thursday";
        week[28] = "Friday";
        week[30] = "Saturday";
        week[31] = "Sunday";
        week[32] = "Monday";
        week[33] = "Tuesday";
        week[34] = "Wednesday";
        week[35] = "Thursday";
        week[36] = "Friday";
        week[37] = "Saturday";
        week[38] = "Sunday";
        week[39] = "Monday";
        week[40] = "Tuesday";

      //  list_adapter = new CalendarAdapter((CalendarActivity) activity, kind, day, week, dec, title, descr);
      //  lv_calendar.setAdapter(list_adapter);
    }
}*/

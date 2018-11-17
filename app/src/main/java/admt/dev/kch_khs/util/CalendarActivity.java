package admt.dev.kch_khs.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import admt.dev.kch_khs.adapter.CalendarAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView img_home_back_calendar;
    ListView lv_calendar;
    CalendarAdapter list_adapter;
    String [] kind, day, week, dec;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        switch(admt.dev.kch_khs.R.id.img_home_back_calendar){
            case admt.dev.kch_khs.R.id.img_home_back_calendar:
                finish();
                break;
        }
    }

    public class fetchData extends AsyncTask<Void,Void,Void> {

        String data = "";
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
                URL url = new URL("https://script.google.com/macros/s/AKfycbzlRttplfG-sm2jUQNadN7cVx2r6HfFb3tE5g-UM0eGlHq3Id8/exec");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    if(line != null)
                        data = data + line;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
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

            setData();
        }
    }

    private void setData() {
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

        list_adapter = new CalendarAdapter((CalendarActivity) activity, kind, day, week, dec);
        lv_calendar.setAdapter(list_adapter);
    }
}

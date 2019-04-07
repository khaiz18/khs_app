package admt.dev.kch_khs.util;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import admt.dev.kch_khs.adapter.AthleticsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AthleticsActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView img_home_back;
    ListView lv_athletics;
    AthleticsAdapter list_adapter;
    String[] kind;
    String[] sport;
    String[] name;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      /*  String s= "KHS_APP powered by NHTS";
        SpannableString ss1=  new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 0,7, 0); // set size
        setTitle(ss1 );*/
        getSupportActionBar().hide();

        setContentView(admt.dev.kch_khs.R.layout.activity_athletics);

        activity = this;

        lv_athletics = (ListView) findViewById(admt.dev.kch_khs.R.id.lv_athletics);
        img_home_back = (ImageView) findViewById(admt.dev.kch_khs.R.id.img_home_back_athletics);
        img_home_back.setOnClickListener(this);

        fetchData process = new fetchData(this);
        process.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case admt.dev.kch_khs.R.id.img_home_back_athletics:
                finish();
                break;
        }
    }

    public class fetchData extends AsyncTask<Void,Void,Void> {

        String data = "";
        String[] kind_temp1, kind_temp2, kind_temp3;
        String[] sport_temp1, sport_temp2, sport_temp3;
        String[] name_temp1, name_temp2, name_temp3;
        int num1=0, num2=0, num3=0;
        private ProgressDialog dialog;

        public fetchData(AthleticsActivity activity) {
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
                URL url = new URL("https://script.google.com/macros/s/AKfycbw1pvTEA59Sj1ySps6j1V6dUj76WNpu62BK0HYSNn9Go38esi0A/exec");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    if(line != null)
                        Log.d("Debug", data.toString());
                        data = data + line;
                }

                JSONObject jo_original = new JSONObject(data);
                Log.d("Debug", data.toString());
                JSONArray jsonArray = jo_original.getJSONArray("athletics");

                kind_temp1 = new String[jsonArray.length()];
                sport_temp1 = new String[jsonArray.length()];
                name_temp1 = new String[jsonArray.length()];

                kind_temp2 = new String[jsonArray.length()];
                sport_temp2 = new String[jsonArray.length()];
                name_temp2 = new String[jsonArray.length()];

                kind_temp3 = new String[jsonArray.length()];
                sport_temp3 = new String[jsonArray.length()];
                name_temp3 = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject JO = (JSONObject) jsonArray.get(i);
                    if(!(JO.getString("kind").equals("")||JO.getString("kind")==null)) {
                        if(JO.getString("kind").equals("Spring")) {
                            kind_temp1[num1] = "1";
                            sport_temp1[num1] = JO.getString("sport");
                            name_temp1[num1] = JO.getString("name");
                            num1++;
                        }
                        if(JO.getString("kind").equals("Fall")){
                            kind_temp2[num2] = "2";
                            sport_temp2[num2] = JO.getString("sport");
                            name_temp2[num2] = JO.getString("name");
                            num2++;
                        }
                        if(JO.getString("kind").equals("Winter")){
                            kind_temp3[num3] = "3";
                            sport_temp3[num3] = JO.getString("sport");
                            name_temp3[num3] = JO.getString("name");
                            num3++;
                        }
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
            Log.d("aaaaa", "num1:"+String.valueOf(num1)+" num2:"+String.valueOf(num2)+" num3:"+String.valueOf(num3));

            kind = new String[num1+num2+num3+3];
            sport = new String[num1+num2+num3+3];
            name = new String[num1+num2+num3+3];

            kind[0] = "Spring";
            for(int i=1;i<num1+1;i++){
                kind[i] = kind_temp1[i-1];
                sport[i] = sport_temp1[i-1];
                name[i] = name_temp1[i-1];
            }
            kind[num1+1] = "Fall";
            for(int i=num1+2;i<num1+num2+2;i++){
                kind[i] = kind_temp2[i-num1-2];
                sport[i] = sport_temp2[i-num1-2];
                name[i] = name_temp2[i-num1-2];
            }
            kind[num1+num2+2] = "Winter";
            for(int i=num1+num2+3;i<num1+num2+num3+3;i++){
                kind[i] = kind_temp3[i-num1-num2-3];
                sport[i] = sport_temp3[i-num1-num2-3];
                name[i] = name_temp3[i-num1-num2-3];
            }

            list_adapter = new AthleticsAdapter((AthleticsActivity) activity, kind, sport, name);
            lv_athletics.setAdapter(list_adapter);
        }
    }
}

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

import admt.dev.kch_khs.adapter.ClubsAdapter;

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

public class ClubsActivity extends AppCompatActivity implements View.OnClickListener {

    ListView lv_clubs;
    ImageView img_home_back;
    ClubsAdapter list_adapter;
    String[] name;
    String[] rep;
    String[] email;
    String[] location;
    String [] date;
    String []time;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* String s= "KHS_APP powered by NHTS";
        SpannableString ss1=  new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 0,7, 0); // set size
        setTitle(ss1 );*/
        getSupportActionBar().hide();

        setContentView(admt.dev.kch_khs.R.layout.activity_clubs);

        activity = this;
        lv_clubs = (ListView) findViewById(admt.dev.kch_khs.R.id.lv_clubs);
        img_home_back = (ImageView) findViewById(admt.dev.kch_khs.R.id.img_home_back_clubs);
        img_home_back.setOnClickListener(this);

        fetchData process = new fetchData(this);
        process.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case admt.dev.kch_khs.R.id.img_home_back_clubs:
                finish();
                break;
        }
    }

    public class fetchData extends AsyncTask<Void,Void,Void> {

        String data = "";
        String[] name_temp;
        String[] rep_temp;
        String[] email_temp;
        String [] location_temp;
        String [] date_temp;
        String []time_temp;
        int num=0;
        private ProgressDialog dialog;

        public fetchData(ClubsActivity activity) {
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
                URL url = new URL("https://script.google.com/macros/s/AKfycbxk4CzvxKI_DZzKwwEDQzGpSOcieinw9JCMvPGM_ObsZ6O9YyN_/exec");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    if(line != null)
                        data = data + line;
                    Log.d("Debug Before Data", data.toString());

                }

                JSONObject jo_original = new JSONObject(data);
                Log.d("Debug After Data", data.toString());

                JSONArray jsonArray = jo_original.getJSONArray("club");

                name_temp = new String[jsonArray.length()];
                rep_temp = new String[jsonArray.length()];
                location_temp = new String[jsonArray.length()];
                email_temp = new String[jsonArray.length()];
                date_temp = new String [jsonArray.length()];
                time_temp = new String [jsonArray.length()];


                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject JO = (JSONObject) jsonArray.get(i);
                  //  if(!(JO.getString("email").equals("")||JO.getString("email")==null))
                    //
                        name_temp[num] = JO.getString("name");
                        Log.i("Name String", name_temp[num]);

                        rep_temp[num] = JO.getString("rep");
                        location_temp[num] = JO.getString("location");
                        email_temp[num] = JO.getString("email");
                        date_temp[num] = JO.getString("date");
                        time_temp[num] = JO.getString("time");
                        num++;

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

            name = new String[num];
            rep = new String[num];
            location = new String[num];
            email = new String[num];
            date = new String[num];
            time = new String[num];

            for(int i=0;i<num;i++){
                name[i] = name_temp[i];
                rep[i] = rep_temp[i];
                location[i] = location_temp[i];
                date[i] = date_temp[i];
                time[i] = time_temp[i];

                email[i] = email_temp[i];
            }

            list_adapter = new ClubsAdapter((ClubsActivity) activity, name, rep, location, email, date , time);


            lv_clubs.setAdapter(list_adapter);
        }
    }
}

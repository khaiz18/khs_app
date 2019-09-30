package admt.dev.kch_khs.util;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView img_home_back;
    TextView tv_info, tv_goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


      /*  String s= "KHS_APP powered by NHTS";
        SpannableString ss1=  new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 0,7, 0); // set size
        setTitle(ss1 );*/
        getSupportActionBar().hide();

        setContentView(admt.dev.kch_khs.R.layout.activity_about);

        tv_info = (TextView) findViewById(admt.dev.kch_khs.R.id.tv_info);
        tv_goal = (TextView) findViewById(admt.dev.kch_khs.R.id.tv_goal);
        img_home_back = (ImageView) findViewById(admt.dev.kch_khs.R.id.img_home_back_about);
        img_home_back.setOnClickListener(this);

        fetchData process = new fetchData(this);
        process.execute();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case admt.dev.kch_khs.R.id.img_home_back_about:
                finish();
                break;
        }
    }


    public class fetchData extends AsyncTask<Void,Void,Void> {

        String data = "";
        String aboutus = "";
        String goal = "";
        private ProgressDialog dialog;

        public fetchData(AboutActivity activity) {
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
                URL url = new URL("https://script.google.com/macros/s/AKfycbyi8KqFXHUmOfw_3pdVj5mJF240-_1Tw_uRCr30AW-H6kDZQO0x/exec");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    if(line != null)
                        data = data + line;
                }

                JSONObject jo_original = new JSONObject(data);
                JSONArray jsonArray = jo_original.getJSONArray("AboutKHS");
                JSONObject jo_each = (JSONObject) jsonArray.get(0);
                aboutus = jo_each.getString("about");

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

            tv_goal.setText(aboutus);
        }
    }
}






package admt.dev.kch_khs.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import admt.dev.kch_khs.R;
import admt.dev.kch_khs.adapter.ResourceAdatper;

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

public class ResourceActivity extends AppCompatActivity implements View.OnClickListener{
    ListView lv_resource;
    ImageView img_home_back;
    ResourceAdatper list_adapter;
    String[] title;
    String[] info;
    String[] domain;
    Activity activity;
    TextView tv_date;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       /* String s= "KHS_APP powered by NHTS";
        SpannableString ss1=  new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 0,7, 0); // set size
        setTitle(ss1 );*/
        getSupportActionBar().hide();

        setContentView(admt.dev.kch_khs.R.layout.activity_resource);



        activity = this;
        lv_resource = (ListView) findViewById(admt.dev.kch_khs.R.id.lv_resource);
        img_home_back = (ImageView) findViewById(admt.dev.kch_khs.R.id.img_home_back_res);
        img_home_back.setOnClickListener(this);
        tv_date = (TextView) findViewById(admt.dev.kch_khs.R.id.tv_date);








        fetchData process = new fetchData(this);
        process.execute();






    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case admt.dev.kch_khs.R.id.img_home_back_res:
                finish();
                break;
        }
    }

    public class fetchData extends AsyncTask<Void,Void,Void> {

        String data = "";
        String[] title_temp;
        String[] info_temp;
        String[] domain_temp;
        int num=0;
        private ProgressDialog dialog;

        public fetchData(ResourceActivity activity) {
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
                URL url = new URL("https://script.google.com/macros/s/AKfycby7INdrTU-2KrZ9Ts3trJqja-zticWdUgrCZtXje_5mfaPtIFYE/exec");
               // https://script.google.com/macros/s/AKfycbwUL9kaUSG_DTLUsnwhGfT_QaECSUHqqvE7Zb8_WqsqJa6V61lj/exec
                //https://script.google.com/macros/s/AKfycbzLKHCHMVd3VciwauCGQaBAPoy1ItdCBwYzjaY7loB7spBJgp8/exec

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
                JSONArray jsonArray = jo_original.getJSONArray("resources");

                title_temp = new String[jsonArray.length()];
                info_temp = new String[jsonArray.length()];
                domain_temp = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject JO = (JSONObject) jsonArray.get(i);
                    if(!(JO.getString("title").equals("")||JO.getString("title")==null)) {
                        title_temp[num] = JO.getString("title");
                        info_temp[num] = JO.getString("info");
                        domain_temp[num] = JO.getString("domain");
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
            for(int i=0;i<num;i++){
                title[i] = title_temp[i];
                info[i] = info_temp[i];
                domain[i] = domain_temp[i];
            }

            list_adapter = new ResourceAdatper((ResourceActivity) activity, title, info, domain);

            lv_resource.setAdapter(list_adapter);


        }
    }
}

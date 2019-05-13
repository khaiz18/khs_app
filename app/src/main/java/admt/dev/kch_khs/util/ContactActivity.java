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

import admt.dev.kch_khs.adapter.ContactAdapter;

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

public class ContactActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView img_home_back;
    ListView lv_contact;
    ContactAdapter list_adapter;
    String[] job;
    String[] name;
    String[] email;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportActionBar().hide();

        setContentView(admt.dev.kch_khs.R.layout.activity_contact);

        activity = this;

        lv_contact = (ListView) findViewById(admt.dev.kch_khs.R.id.lv_contact);
        img_home_back = (ImageView) findViewById(admt.dev.kch_khs.R.id.img_home_back_contact);
        img_home_back.setOnClickListener(this);

        fetchData process = new fetchData(this);
        process.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case admt.dev.kch_khs.R.id.img_home_back_contact:
                finish();
                break;
        }
    }

    public class fetchData extends AsyncTask<Void,Void,Void> {

        String data = "";
        String[] job_temp1, job_temp2, job_temp3, job_temp4,job_temp5;
        String[] name_temp1, name_temp2, name_temp3,name_temp4,name_temp5;
        String[] email_temp1, email_temp2, email_temp3;
        int num1=0, num2=0, num3=0, num4=0, num5=0, num6=0,num7=0,num8=0,num9=0,num10=0,num11=0,num12=0,num13=0;
        private ProgressDialog dialog;

        public fetchData(ContactActivity activity) {
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
                URL url = new URL("https://script.google.com/macros/s/AKfycbxmFfeRi5fKH3h7_Yfa4nuCxmQvEMn7x-lYHYuFOSkmn27EpYMl/exec");
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
                JSONArray jsonArray = jo_original.getJSONArray("contact");

                job_temp1 = new String[jsonArray.length()];
                name_temp1 = new String[jsonArray.length()];
                email_temp1 = new String[jsonArray.length()];

                job_temp2 = new String[jsonArray.length()];
                name_temp2 = new String[jsonArray.length()];
                email_temp2 = new String[jsonArray.length()];

                job_temp3 = new String[jsonArray.length()];
                name_temp3 = new String[jsonArray.length()];
                email_temp3 = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject JO = (JSONObject) jsonArray.get(i);
                    if(!(JO.getString("position").equals("")||JO.getString("position")==null)) {
                        if(JO.getString("position").equals("Administration")) {
                            job_temp1[num1] = JO.getString("job");
                            name_temp1[num1] = JO.getString("name");
                           // email_temp1[num1] = JO.getString("email");
                            num1++;
                        }
                        else if (JO.getString("position").equals("Counseling")) {
                            job_temp2[num2] = JO.getString("job");
                            name_temp2[num2] = JO.getString("name");
                          //  email_temp2[num2] = JO.getString("email");
                            num2++;
                        }else if (JO.getString("position").equals("Faculty (SPED)")){

                            job_temp3[num3] = JO.getString("job");
                            name_temp3[num3]= JO.getString("name");
                           // email_temp3[num3] = JO.getString("email");

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

            job = new String[num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+num12+num13+14];
            name = new String[num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+num12+num13+14];
          //  email = new String[num1+num2+2+num3+3];

            job[0] = "Administration";
            for(int i=1;i<num1+1;i++){
                job[i] = job_temp1[i-1];
                name[i] = name_temp1[i-1];
              //  email[i] = email_temp1[i-1];
            }
            job[num1+1] = "Counseling";
            for(int i=num1+2;i<num1+num2+2;i++){
                job[i] = job_temp2[i-num1-2];
                name[i] = name_temp2[i-num1-2];
              //  email[i] = email_temp2[i-num1-2];
            }
            job[num1+num2+2] = "Faculty (SPED)";
            for(int i=num1+num2+3;i<num1+num2+num3+3;i++){
                job[i] = job_temp3[i-num1-num2-3];
               name[i] = name_temp3[i-num1-num2-3];
               // email[i] = email_temp3[i-num1-2];
            }job[num1+num2+num3+3] = "English";
             job[num1+num2+num3+num4+4] = "Science";
             job[num1+num2+num3+num4+num5+5] = "Math";
             job[num1+num2+num3+num4+num5+num6+6] = "English";
             job[num1+num2+num3+num4+num5+num6+num7+7] = "Social Studies";
             job[num1+num2+num3+num4+num5+num6+num7+num8+8] = "Career and Tech Ed";
             job[num1+num2+num3+num4+num5+num6+num7+num8+num9+9] = "Physical Education/Health";
             job[num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+10] = "Special Education";
             job[num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+11] = "World Languages";
             job[num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+num12+12] = "Fine Arts";
             job[num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+num12+num13+13] = "Staff";

            list_adapter = new ContactAdapter((ContactActivity) activity, job, name);
            lv_contact.setAdapter(list_adapter);
        }
    }
}
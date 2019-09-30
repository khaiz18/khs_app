package admt.dev.kch_khs.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class ResourceActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lv_resource;
    ImageView img_home_back;
    ResourceAdatper list_adapter;
    String[] job;
    String[] info;
    String[] domain;
    String[] link;
    String[] webname;
    Activity activity;
    TextView tv_date;
    private String name;
    int extracount = 0;
    int OSWcount = 0;




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

    public class fetchData extends AsyncTask<Void, Void, Void> {

        String data = "";
        String[] title_temp1, title_temp2, title_temp3, title_temp4, title_temp5, title_temp6, title_temp7, title_temp8, title_temp9, title_temp10, title_temp11;
        String[] info_temp1, info_temp2, info_temp3, info_temp4, info_temp5, info_temp6, info_temp7, info_temp8, info_temp9, info_temp10, info_temp11;
        String[] domain_temp1, domain_temp2, domain_temp3, domain_temp4, domain_temp5, domain_temp6, domain_temp7, domain_temp8, domain_temp9, domain_temp10, domain_temp11;
        int num1 = 0, num2 = 0, num3 = 0, num4 = 0, num5 = 0, num6 = 0, num7 = 0, num8 = 0, num9 = 0, num10 = 0, num11 = 0, num12 = 0, num13 = 0, num14 = 0, num15 = 0;

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
                    if (line != null)
                        data = data + line;
                }

                JSONObject jo_original = new JSONObject(data);
                JSONArray jsonArray = jo_original.getJSONArray("resources");

                title_temp1 = new String[jsonArray.length()];
                info_temp1 = new String[jsonArray.length()];
                domain_temp1 = new String[jsonArray.length()];


                title_temp2 = new String[jsonArray.length()];
                info_temp2 = new String[jsonArray.length()];
                domain_temp2 = new String[jsonArray.length()];

                title_temp2 = new String[jsonArray.length()];
                info_temp2 = new String[jsonArray.length()];
                domain_temp2 = new String[jsonArray.length()];

                title_temp3 = new String[jsonArray.length()];
                info_temp3 = new String[jsonArray.length()];
                domain_temp3 = new String[jsonArray.length()];

                title_temp4 = new String[jsonArray.length()];
                info_temp4 = new String[jsonArray.length()];
                domain_temp4 = new String[jsonArray.length()];

                title_temp5 = new String[jsonArray.length()];
                info_temp5 = new String[jsonArray.length()];
                domain_temp5 = new String[jsonArray.length()];

                title_temp6 = new String[jsonArray.length()];
                info_temp6 = new String[jsonArray.length()];
                domain_temp6 = new String[jsonArray.length()];

                title_temp7 = new String[jsonArray.length()];
                info_temp7 = new String[jsonArray.length()];
                domain_temp7 = new String[jsonArray.length()];


                title_temp8 = new String[jsonArray.length()];
                info_temp8 = new String[jsonArray.length()];
                domain_temp8 = new String[jsonArray.length()];


                title_temp9 = new String[jsonArray.length()];
                info_temp9 = new String[jsonArray.length()];
                domain_temp9 = new String[jsonArray.length()];


                title_temp10 = new String[jsonArray.length()];
                info_temp10 = new String[jsonArray.length()];
                domain_temp10 = new String[jsonArray.length()];

                title_temp11 = new String[jsonArray.length()];
                info_temp11 = new String[jsonArray.length()];
                domain_temp11 = new String[jsonArray.length()];


                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject JO = (JSONObject) jsonArray.get(i);
                    if (!(JO.getString("title").equals("") || JO.getString("title") == null)) {
                        if (JO.getString("title").equals("Official School Websites")) {
                            //title_temp1[num1] = JO.getString("title");
                            info_temp1[num1] = JO.getString("info");
                            domain_temp1[num1] = JO.getString("domain");
                            num1++;
                            OSWcount = num1;
                        } else if (JO.getString("title").equals("Social Media")) {

                            //title_temp2[num2] = JO.getString("title");

                            info_temp2[num2] = JO.getString("info");
                            domain_temp2[num2] = JO.getString("domain");
                            num2++;

                        } else if (JO.getString("title").equals("Athletics")) {


                            info_temp3[num3] = JO.getString("info");
                            domain_temp3[num3] = JO.getString("domain");
                            num3++;

                        } else if (JO.getString("title").equals("School Resources")) {


                            info_temp4[num4] = JO.getString("info");
                            domain_temp4[num4] = JO.getString("domain");
                            num4++;

                        } else if (JO.getString("title").equals("Pupil Resources")) {


                            info_temp5[num5] = JO.getString("info");
                            domain_temp5[num5] = JO.getString("domain");
                            num5++;
                            Log.i(activity.getLocalClassName(),"Pupil"+  " "+ num5);


                        } else if (JO.getString("title").equals("Learning/Support Sites")) {


                            info_temp6[num6] = JO.getString("info");
                            domain_temp6[num6] = JO.getString("domain");
                            num6++;

                        } else if (JO.getString("title").equals("Volunteer Opportunities")) {


                            info_temp7[num7] = JO.getString("info");
                            domain_temp7[num7] = JO.getString("domain");
                            num7++;

                        } else if (JO.getString("title").equals("CTE Competitions")) {


                            info_temp8[num8] = JO.getString("info");
                            domain_temp8[num8] = JO.getString("domain");
                            num8++;

                        } else if (JO.getString("title").equals("External Student Orgs")) {


                            info_temp9[num9] = JO.getString("info");
                            domain_temp9[num9] = JO.getString("domain");
                            num9++;

                        } else if (JO.getString("title").equals("Extras")) {

                            info_temp10[num10] = JO.getString("info");
                            domain_temp10[num10] = JO.getString("domain");
                            num10++;
                            extracount = num10;

                            Log.i(activity.getLocalClassName(),"Application started"+  " "+ extracount);

                        } else {

                            //title_temp11[num11] = "End of Resource List";
                            info_temp11[num11] = "End of Resource List";
                            domain_temp11[num11] = "End of Resource List";

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


            job = new String[num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + num10 + num11 + 12];
            info = new String[num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + num10 + num11 + 12];
            domain = new String[num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + num10 + num11 + 12];


            job[0] = "Official School Websites";
            for (int i = 1; i < num1 + 1; i++) {
                //title[i] = title_temp1[i-1];
                info[i] = info_temp1[i - 1];

                domain[i] = domain_temp1[i - 1];


                //name[i] = name_temp1[i-1];
                //  email[i] = email_temp1[i-1];
            }
            Log.i(activity.getLocalClassName(),"Application started"+ "num " + num1 + " " + "extra "+ extracount);
            Log.i(activity.getLocalClassName(),"Pupil"+  " "+ num5);


            job[num1 + 1] = "Social Media";
            for (int i = num1 + 2; i < num1 + num2 + 2; i++) {
                // title[i] = title_temp2[i-num1-2];
                info[i] = info_temp2[i - num1 - 2];


                domain[i] = domain_temp2[i - num1 - 2];
                //  email[i] = email_temp2[i-num1-2];
            }

            job[num1 + num2 + 2] = "Athletics";
            for (int i = num1 + num2 + 3; i < num1 + num2 + num3 + 3; i++) {
                info[i] = info_temp3[i - num1 - num2 - 3];
                domain[i] = domain_temp3[i - num1 - num2 - 3];
                // email[i] = email_temp3[i-num1-2];
            }
            job[num1 + num2 + num3 + 3] = "School Resources";
            for (int i = num1 + num2 + num3 + 4; i < num1 + num2 + num3 + num4 + 4; i++) {
                info[i] = info_temp4[i - num1 - num2 - num3 - 4];
                domain[i] = domain_temp4[i - num1 - num2 - num3 - 4];
                // email[i] = email_temp3[i-num1-2];
            }
            job[num1 + num2 + num3 + num4 + 4] = "Pupil Resources";
            for (int i = num1 + num2 + num3 + num4 + 5; i < num1 + num2 + num3 + num4 + num5 + 5; i++) {
                info[i] = info_temp5[i - num1 - num2 - num3 - num4 - 5];
                domain[i] = domain_temp5[i - num1 - num2 - num3 - num4 - 5];
                // email[i] = email_temp3[i-num1-2];
            }
            job[num1 + num2 + num3 + num4 + num5 + 5] = "Learning/Support Sites";
            for (int i = num1 + num2 + num3 + num4 + num5 + 6; i < num1 + num2 + num3 + num4 + num5 + num6 + 6; i++) {
                info[i] = info_temp6[i - num1 - num2 - num3 - num4 - num5 - 6];
                domain[i] = domain_temp6[i - num1 - num2 - num3 - num4 - num5 - 6];
                // email[i] = email_temp3[i-num1-2];
            }
            job[num1 + num2 + num3 + num4 + num5 + num6 + 6] = "Volunteer Opportunities";
            for (int i = num1 + num2 + num3 + num4 + num5 + num6 + 7; i < num1 + num2 + num3 + num4 + num5 + num6 + num7 + 7; i++) {
                info[i] = info_temp7[i - num1 - num2 - num3 - num4 - num5 - num6 - 7];
                domain[i] = domain_temp7[i - num1 - num2 - num3 - num4 - num5 - num6 - 7];
                // email[i] = email_temp3[i-num1-2];
            }
            job[num1 + num2 + num3 + num4 + num5 + num6 + num7 + 7] = "CTE Competitions";
            for (int i = num1 + num2 + num3 + num4 + num5 + num6 + num7 + 8; i < num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + 8; i++) {
                info[i] = info_temp8[i - num1 - num2 - num3 - num4 - num5 - num6 - num7 - 8];
                domain[i] = domain_temp8[i - num1 - num2 - num3 - num4 - num5 - num6 - num7 - 8];
                // email[i] = email_temp3[i-num1-2];
            }
            job[num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + 8] = "External Student Orgs";
            for (int i = num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + 9; i < num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + 9; i++) {
                info[i] = info_temp9[i - num1 - num2 - num3 - num4 - num5 - num6 - num7 - num8 - 9];
                domain[i] = domain_temp9[i - num1 - num2 - num3 - num4 - num5 - num6 - num7 - num8 - 9];
                // email[i] = email_temp3[i-num1-2];
            }
            job[num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + 9] = "Extras";
            for (int i = num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + 10; i < num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + num10 + 10; i++) {
                info[i] = info_temp10[i - num1 - num2 - num3 - num4 - num5 - num6 - num7 - num8 - num9 - 10];
                domain[i] = domain_temp10[i - num1 - num2 - num3 - num4 - num5 - num6 - num7 - num8 - num9 - 10];
                // email[i] = email_temp3[i-num1-2];
            }
            job[num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + num10 + 10] = "End of Resource List";
            for (int i = num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + num10 + 11; i < num1 + num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9 + num10 + num11 + 11; i++) {
                info[i] = info_temp11[i - num1 - num2 - num3 - num4 - num5 - num6 - num7 - num8 - num9 - num10 - 11];
                domain[i] = domain_temp11[i - num1 - num2 - num3 - num4 - num5 - num6 - num7 - num8 - num9 - num10 - 11];
                // email[i] = email_temp3[i-num1-2];
            } 

            list_adapter = new ResourceAdatper((ResourceActivity) activity, job, info, domain);

            lv_resource.setAdapter(list_adapter);

            lv_resource.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // the position of the item clicked will come in as the 3rd parameter of the onItemClick callback
                    // which is 'position'. You can use the value to do whatever you want

                    /*Object object = list_adapter.getItem(position);
                    Toast.makeText(getApplicationContext(), "item clicked id="+id, Toast.LENGTH_LONG).show();

                    if (id == 1){

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dodea.edu/KaiserslauternHS/"));
                        startActivity(browserIntent);

                    }else if (id == 2){


                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/a/student.dodea.edu/kaiserslautern-high-school/"));
                        startActivity(browserIntent);
                    }*/

                }

            });


        }

    }

}

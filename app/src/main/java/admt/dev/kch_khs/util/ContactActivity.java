package admt.dev.kch_khs.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import admt.dev.kch_khs.R;
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
        String[] job_temp1, job_temp2, job_temp3, job_temp4,job_temp5, job_temp6,job_temp7,job_temp8,job_temp9,job_temp10,job_temp11,job_temp12,job_temp13,job_temp14;
        String[] name_temp1, name_temp2, name_temp3,name_temp4,name_temp5, name_temp6,name_temp7,name_temp8,name_temp9,name_temp10,name_temp11,name_temp12,name_temp13,name_temp14;
        String[] email_temp1, email_temp2, email_temp3, email_temp4, email_temp5, email_temp6,email_temp7,email_temp8,email_temp9,email_temp10,email_temp11,email_temp12,email_temp13,email_temp14;
        int num1=0, num2=0, num3=0, num4=0, num5=0, num6=0,num7=0,num8=0,num9=0,num10=0,num11=0,num12=0,num13=0, num14=0, num15=0;
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

                job_temp4 = new String[jsonArray.length()];
                name_temp4 = new String[jsonArray.length()];
                email_temp4 = new String[jsonArray.length()];

                job_temp5 = new String[jsonArray.length()];
                name_temp5 = new String[jsonArray.length()];
                email_temp5 = new String[jsonArray.length()];


                job_temp6 = new String[jsonArray.length()];
                name_temp6 = new String[jsonArray.length()];
                email_temp6 = new String[jsonArray.length()];


                job_temp7 = new String[jsonArray.length()];
                name_temp7 = new String[jsonArray.length()];
                email_temp7 = new String[jsonArray.length()];

                job_temp8 = new String[jsonArray.length()];
                name_temp8 = new String[jsonArray.length()];
                email_temp8 = new String[jsonArray.length()];


                job_temp9 = new String[jsonArray.length()];
                name_temp9 = new String[jsonArray.length()];
                email_temp9 = new String[jsonArray.length()];

                job_temp10 = new String[jsonArray.length()];
                name_temp10 = new String[jsonArray.length()];
                email_temp10 = new String[jsonArray.length()];

                job_temp11 = new String[jsonArray.length()];
                name_temp11 = new String[jsonArray.length()];
                email_temp11 = new String[jsonArray.length()];

                job_temp12 = new String[jsonArray.length()];
                name_temp12 = new String[jsonArray.length()];
                email_temp12 = new String[jsonArray.length()];

                job_temp13 = new String[jsonArray.length()];
                name_temp13 = new String[jsonArray.length()];
                email_temp13 = new String[jsonArray.length()];

                job_temp14 = new String[jsonArray.length()];
                name_temp14 = new String[jsonArray.length()];
                email_temp14 = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject JO = (JSONObject) jsonArray.get(i);
                    if(!(JO.getString("position").equals("")||JO.getString("position")==null)) {
                        if(JO.getString("position").equals("Administration")) {
                            job_temp1[num1] = JO.getString("name");
                            name_temp1[num1] = JO.getString("email");
                           // email_temp1[num1] = JO.getString("email");
                            num1++;
                        }
                        else if (JO.getString("position").equals("Counseling")) {
                            job_temp2[num2] = JO.getString("name");
                            name_temp2[num2] = JO.getString("email");
                          //  email_temp2[num2] = JO.getString("email");
                            num2++;
                        }else if (JO.getString("position").equals("Science")){

                            job_temp3[num3] = JO.getString("name");
                            name_temp3[num3]= JO.getString("email");
                           // email_temp3[num3] = JO.getString("email");

                            num3++;

                        }else if (JO.getString("position").equals("English")){

                            job_temp4[num4] = JO.getString("name");
                            name_temp4[num4]= JO.getString("email");
                            num4++;

                        }else if (JO.getString("position").equals("Social Studies")){

                            job_temp5[num5] = JO.getString("name");
                            name_temp5[num5]= JO.getString("email");
                            num5++;
                        }else if (JO.getString("position").equals("World Languages")){

                            job_temp6[num6] = JO.getString("name");
                            name_temp6[num6]= JO.getString("email");
                            num6++;
                        }else if (JO.getString("position").equals("Fine Arts")){

                            job_temp7[num7] = JO.getString("name");
                            name_temp7[num7]= JO.getString("email");
                            num7++;
                        }else if (JO.getString("position").equals("Math")){


                            job_temp8[num8] = JO.getString("name");
                            name_temp8[num8]= JO.getString("email");
                            num8++;

                        }else if (JO.getString("position").equals("PE/Health")){

                            job_temp9[num9] = JO.getString("name");
                            name_temp9[num9]= JO.getString("email");
                            num9++;

                        }else if (JO.getString("position").equals("Career & Technical")){


                            job_temp10[num10] = JO.getString("name");
                            name_temp10[num10]= JO.getString("email");
                            num10++;
                        }else if (JO.getString("position").equals("Staff")){


                            job_temp11[num11] = JO.getString("name");
                            name_temp11[num11]= JO.getString("email");
                            num11++;
                        }else if (JO.getString("position").equals("SPED")){


                            job_temp12[num12] = JO.getString("name");
                            name_temp12[num12]= JO.getString("email");
                            num12++;


                        }else{


                            job_temp13[num13] = "done";
                            name_temp13[num13]= "done";



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
            job[num1+num2+2] = "Science";
            for(int i=num1+num2+3;i<num1+num2+num3+3;i++){
                job[i] = job_temp3[i-num1-num2-3];
               name[i] = name_temp3[i-num1-num2-3];
               // email[i] = email_temp3[i-num1-2];
            }
             job[num1+num2+num3+3] = "English";
            for(int i=num1+num2+num3+4;i<num1+num2+num3+num4+4;i++){
                job[i] = job_temp4[i-num1-num2-num3-4];
                name[i] = name_temp4[i-num1-num2-num3-4];
                // email[i] = email_temp3[i-num1-2];
            }
             job[num1+num2+num3+num4+4] = "Social Studies";
            for(int i=num1+num2+num3+num4+5;i<num1+num2+num3+num4+num5+5;i++){
                job[i] = job_temp5[i-num1-num2-num3-num4-5];
                name[i] = name_temp5[i-num1-num2-num3-num4-5];
                // email[i] = email_temp3[i-num1-2];
            }
             job[num1+num2+num3+num4+num5+5] = "World Languages";
            for(int i=num1+num2+num3+num4+num5+6;i<num1+num2+num3+num4+num5+num6+6;i++){
                job[i] = job_temp6[i-num1-num2-num3-num4-num5-6];
                name[i] = name_temp6[i-num1-num2-num3-num4-num5-6];
                // email[i] = email_temp3[i-num1-2];
            }
             job[num1+num2+num3+num4+num5+num6+6] = "Fine Arts";
            for(int i=num1+num2+num3+num4+num5+num6+7;i<num1+num2+num3+num4+num5+num6+num7+7;i++){
                job[i] = job_temp7[i-num1-num2-num3-num4-num5-num6-7];
                name[i] = name_temp7[i-num1-num2-num3-num4-num5-num6-7];
                // email[i] = email_temp3[i-num1-2];
            }
             job[num1+num2+num3+num4+num5+num6+num7+7] = "Math";
            for(int i=num1+num2+num3+num4+num5+num6+num7+8;i<num1+num2+num3+num4+num5+num6+num7+num8+8;i++){
                job[i] = job_temp8[i-num1-num2-num3-num4-num5-num6-num7-8];
                name[i] = name_temp8[i-num1-num2-num3-num4-num5-num6-num7-8];
                // email[i] = email_temp3[i-num1-2];
            }
             job[num1+num2+num3+num4+num5+num6+num7+num8+8] = "PE/Health";
            for(int i=num1+num2+num3+num4+num5+num6+num7+num8+9;i<num1+num2+num3+num4+num5+num6+num7+num8+num9+9;i++){
                job[i] = job_temp9[i-num1-num2-num3-num4-num5-num6-num7-num8-9];
                name[i] = name_temp9[i-num1-num2-num3-num4-num5-num6-num7-num8-9];
                // email[i] = email_temp3[i-num1-2];
            }
             job[num1+num2+num3+num4+num5+num6+num7+num8+num9+9] = "Career & Technical";
            for(int i=num1+num2+num3+num4+num5+num6+num7+num8+num9+10;i<num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+10;i++){
                job[i] = job_temp10[i-num1-num2-num3-num4-num5-num6-num7-num8-num9-10];
                name[i] = name_temp10[i-num1-num2-num3-num4-num5-num6-num7-num8-num9-10];
                // email[i] = email_temp3[i-num1-2];
            }
             job[num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+10] = "Staff";
            for(int i=num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+11;i<num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+11;i++){
                job[i] = job_temp11[i-num1-num2-num3-num4-num5-num6-num7-num8-num9-num10-11];
                name[i] = name_temp11[i-num1-num2-num3-num4-num5-num6-num7-num8-num9-num10-11];
                // email[i] = email_temp3[i-num1-2];
            }
             job[num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+11] = "SPED";
            for(int i=num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+12;i<num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+num12+12;i++){
                job[i] = job_temp12[i-num1-num2-num3-num4-num5-num6-num7-num8-num9-num10-num11-12];
                name[i] = name_temp12[i-num1-num2-num3-num4-num5-num6-num7-num8-num9-num10-num11-12];
                // email[i] = email_temp3[i-num1-2];
            }
             job[num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+num12+12] = "End of Contact List";
            for(int i=num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+num12+13;i<num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+num12+num13+13;i++){
                job[i] = job_temp13[i-num1-num2-num3-num4-num5-num6-num7-num8-num9-num10-num11-num12-13];
                name[i] = name_temp13[i-num1-num2-num3-num4-num5-num6-num7-num8-num9-num10-num11-num12-13];
                // email[i] = email_temp3[i-num1-2];
            }/*job[num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+num12+num13+13] = "SPED";
            for(int i=num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+num12+num13+14;i<num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11+num12+num13+num14+14;i++){
                job[i] = job_temp14[i-num1-num2-num3-num4-num5-num6-num7-num8-num9-num10-num11-num12-num13-14];
                name[i] = name_temp14[i-num1-num2-num3-num4-num5-num6-num7-num8-num9-num10-num11-num12-num13-14];
                // email[i] = email_temp3[i-num1-2];
            }*/


            list_adapter = new ContactAdapter((ContactActivity) activity, job, name);
            lv_contact.setAdapter(list_adapter);
        }
    }
}
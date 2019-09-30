package admt.dev.kch_khs.util;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import admt.dev.kch_khs.R;


public class Activity_Settings extends AppCompatActivity implements View.OnClickListener {


    public Activity_Settings() {
        // Required empty public constructor
    }





    public void rateApp()
    {
        try
        {
            Intent rateIntent = rateIntentForUrl("market://details");
            startActivity(rateIntent);
        }
        catch (ActivityNotFoundException e)
        {
            Intent rateIntent = rateIntentForUrl("https://play.google.com/store/apps/details");
            startActivity(rateIntent);
        }
    }

    private Intent rateIntentForUrl(String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
        int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (Build.VERSION.SDK_INT >= 21)
        {
            flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
        }
        else
        {
            //noinspection deprecation
            flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
        }
        intent.addFlags(flags);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Button facebook_button = (Button)findViewById(R.id.facebook_settings);
        final Button school = (Button)findViewById(R.id.school);

        final Button rate = (Button)findViewById(R.id.rate);
        final Button feedback = (Button)findViewById(R.id.feedback);
        final Button share_with_friends = (Button)findViewById(R.id.share);
        final Button twitter = (Button)findViewById(R.id.twitter);
        final Button youtube = (Button)findViewById(R.id.youtube);

        final Button about_privacy = (Button)findViewById(R.id.about_privacy);


        twitter.setOnClickListener( this);
        school.setOnClickListener(this);
        rate.setOnClickListener( this);
        feedback.setOnClickListener( this);
        share_with_friends.setOnClickListener( this);
        about_privacy.setOnClickListener( this);
        youtube.setOnClickListener(this);

        facebook_button.setOnClickListener( this);




        //final View view = inflater.inflate(R.layout.activity_settings, container, false);


        // Inflate the layout for this fragment

    }

    @Override
    public void onClick(View v) {


        final Button facebook_button = (Button)findViewById(R.id.facebook_settings);
        final Button school = (Button)findViewById(R.id.school);

        final Button rate = (Button)findViewById(R.id.rate);
        final Button feedback = (Button)findViewById(R.id.feedback);
        final Button share_with_friends = (Button)findViewById(R.id.share);
        final Button twitter = (Button)findViewById(R.id.twitter);
        final Button youtube = (Button)findViewById(R.id.youtube);
        final Button about_privacy = (Button)findViewById(R.id.about_privacy);


        twitter.setOnClickListener( this);
        school.setOnClickListener(this);
        rate.setOnClickListener( this);
        feedback.setOnClickListener( this);
        share_with_friends.setOnClickListener( this);
        about_privacy.setOnClickListener( this);
        youtube.setOnClickListener(this);

        facebook_button.setOnClickListener( this);


        {


            if(v.equals(twitter)){
                startActivity(new Intent(Activity_Settings.this, ResourceActivity.class));
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://twitter.com/khs_raiders?lang=en\n")));


            }if(v.equals(feedback)){

            String subject = "Feedback for KHS APP";

            String body = "";

            String mailto = "mailto:khsdodea@gmail.com" +
                    "?subject=" + Uri.encode(subject) +
                    "&body=" + Uri.encode(body);


            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse(mailto));

            try {
                startActivity(emailIntent);
                finish();
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "no email app is available", Toast.LENGTH_SHORT);
                //TODO: Handle case where no email app is available
            }




        }    if(v.equals(about_privacy)){

            startActivity(new Intent(Activity_Settings.this, AboutActivity.class));


        } if(v.equals(facebook_button)){

            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/KaiserslauternHS/")));


        }if(v.equals(school)){

            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://sites.google.com/a/student.dodea.edu/kaiserslautern-high-school/")));


        }if(v.equals(youtube)){

            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.youtube.com")));


        }


        if(v.equals(rate)){

        rateApp();


        }if(v.equals(share_with_friends)){

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        }



        }

    }




}



/*
public class Activity_Settings extends AppCompatActivity {

    Button about_privacy;
    Button copy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        final Button facebook_button = (Button) findViewById(R.id.facebook_button);
        final Button visit_us = (Button) findViewById(R.id.visit);
        final Button rate = (Button) findViewById(R.id.rate);
        final Button feedback = (Button) findViewById(R.id.feedback);
        final Button share_with_friends = (Button) findViewById(R.id.share);
        final Button copy = (Button) findViewById(R.id.copyright);
        final Button about_privacy = (Button) findViewById(R.id.about_privacy);
      //  rate.setOnClickListener(new View());





        about_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == about_privacy) {

                    setContentView(R.layout.about_privacy);

                }if ( v == rate ){

                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://forcemobilestudios.com/ramstein-dorms-feedback")));


                }



            }
        });


        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == copy) {

                    setContentView(R.layout.copyright);


                }

            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == feedback) {


                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://forcemobilestudios.com/ramstein-dorms-feedback")));


                }
            }
        });
        facebook_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == facebook_button) {


                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/forcemobilestudios/")));


                }
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == rate) {

                    setContentView(R.layout.about_privacy);



                }
            }
        });
        share_with_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == share_with_friends) {

                    setContentView(R.layout.about_privacy);



                }
            }
        });
        visit_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == visit_us) {


                    setContentView(R.layout.about_privacy);


                }
            }
        });


    }

}*/







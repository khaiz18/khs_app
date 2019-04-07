package admt.dev.kch_khs.util;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


      /*  String s= "KHS_APP powered by NHTS";
        SpannableString ss1=  new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 0,7, 0); // set size
        setTitle(ss1 );*/
        getSupportActionBar().hide();

        setContentView(admt.dev.kch_khs.R.layout.activity_splash);


    }
}

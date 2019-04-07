package admt.dev.kch_khs.util;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import admt.dev.kch_khs.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tv_date;
    LinearLayout ll_cal, ll_about, ll_res, ll_ath, ll_clubs, ll_con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     /*   String s= "KHS_APP  powered by NHTS";
        SpannableString ss1=  new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(1.5f), 0,7, 0); // set size


        // ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, 5, 0);// set color
      //  TextView tv= (TextView) findViewById(R.id.textview);
      //  tv.setText(ss1);







        setTitle(ss1 );*/

        getSupportActionBar().hide();
        setContentView(admt.dev.kch_khs.R.layout.activity_main);

        ll_cal = (LinearLayout) findViewById(admt.dev.kch_khs.R.id.ll_cal);
        ll_about = (LinearLayout) findViewById(admt.dev.kch_khs.R.id.ll_about);
        ll_res = (LinearLayout) findViewById(admt.dev.kch_khs.R.id.ll_res);
        ll_ath = (LinearLayout) findViewById(admt.dev.kch_khs.R.id.ll_ath);
        ll_clubs = (LinearLayout) findViewById(admt.dev.kch_khs.R.id.ll_clubs);
        ll_con = (LinearLayout) findViewById(admt.dev.kch_khs.R.id.ll_con);
        tv_date = (TextView) findViewById(admt.dev.kch_khs.R.id.tv_date);

        ll_cal.setOnClickListener(this);
        ll_about.setOnClickListener(this);
        ll_res.setOnClickListener(this);
        ll_ath.setOnClickListener(this);
        ll_clubs.setOnClickListener(this);
        ll_con.setOnClickListener(this);

        setDate();
        final ValueAnimator animator = ValueAnimator.ofFloat(1.0f, -1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(9000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = tv_date.getWidth();
                final float translationX = width * progress;
                tv_date.setTranslationX(translationX);
            }
        });
        animator.start();
    }




    private void setDate(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        int year = c.getYear()+1900;
        int day = c.getDate();
        String monthname=(String)android.text.format.DateFormat.format("MMMM", new Date());
        String weekDay = dayFormat.format(c.getTime());

        tv_date.setText(weekDay+ " " + monthname + " " + String.valueOf(day)+", " + String.valueOf(year));
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case admt.dev.kch_khs.R.id.ll_cal:
                intent = new Intent(MainActivity.this, admt.dev.kch_khs.util.CalendarActivity.class);
                startActivity(intent);
                break;
            case admt.dev.kch_khs.R.id.ll_about:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case admt.dev.kch_khs.R.id.ll_res:
                intent = new Intent(MainActivity.this, admt.dev.kch_khs.util.ResourceActivity.class);
                startActivity(intent);
                break;
            case admt.dev.kch_khs.R.id.ll_ath:
                intent = new Intent(MainActivity.this, AthleticsActivity.class);
                startActivity(intent);
                break;
            case admt.dev.kch_khs.R.id.ll_clubs:
                intent = new Intent(MainActivity.this, ClubsActivity.class);
                startActivity(intent);
                break;
            case admt.dev.kch_khs.R.id.ll_con:
                intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(admt.dev.kch_khs.R.string.app_name);
        builder.setMessage("Do you want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

package admt.dev.kch_khs.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import admt.dev.kch_khs.util.CalendarActivity;
import admt.dev.kch_khs.R;

public class CalendarAdapter extends BaseAdapter {

    String[] kind, loc, etime, stime, title, descr, ename, day, month;
    Context context;
    private static LayoutInflater inflater = null;

    // public CalendarAdapter(CalendarActivity calendarActivity, String[] kind, String[] day, String[] week, String[] dec, String[] title, String[] descr) {
    public CalendarAdapter(CalendarActivity calendarActivity, String[] title, String[] descr, String[] ename,String[] loc, String[] stime, String[] etime, String[] day, String[] month) {

        //this.kind=kind;
        //this.day=day;
        //this.week=week;
        //this.dec=dec;
        this.title = title;
        this.day = day;
        this.month = month;
        this.descr = descr;
        this.ename = ename;
        this.stime = stime;
        this.loc = loc;
        this.etime = etime;

        Context context;
        context = calendarActivity;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        // return day.length;
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tv_calendar_stime, tv_calendar_ename, tv_calendar_dec, tv_calendar_title,tv_calendar_etime,tv_calendar_loc, tv_calendar_month, tv_calendar_day;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


        Holder holder = new Holder();
        View view;
        view = inflater.inflate(R.layout.listitem_cal, null);

        holder.tv_calendar_title = (TextView) view.findViewById(R.id.tv_cal_title);
        holder.tv_calendar_dec = (TextView) view.findViewById(R.id.tv_cal_desc);
      //  holder.tv_calendar_month = (TextView) view.findViewById(R.id.tv_cal_month);
      //  holder.tv_calendar_day = (TextView) view.findViewById(R.id.tv_cal_day);


        holder.tv_calendar_ename = (TextView) view.findViewById(R.id.tv_cal_ename);
        holder.tv_calendar_loc = (TextView) view.findViewById(R.id.tv_cal_loc);
        holder.tv_calendar_stime = (TextView) view.findViewById(R.id.tv_cal_stime);

        holder.tv_calendar_etime = (TextView) view.findViewById(R.id.tv_cal_etime);



        holder.tv_calendar_title.setText( title[position]);
        holder.tv_calendar_dec.setText(" Description: "+ descr[position]);
        holder.tv_calendar_ename.setText(" Event Name: " + ename[position]);
        holder.tv_calendar_loc.setText(" Location: " + loc[position]);

        holder.tv_calendar_stime.setText(" Start Time: " + stime[position]);

        holder.tv_calendar_etime.setText(" End Time: " + etime[position]);


        // holder.tv_res_domain.setText(domain[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //    Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
            }
        });
        if (title[position] == null)
            view.setVisibility(View.INVISIBLE);
        return view;
    }

}












      /*  View view;
        if(title[position] !=  null) {
     //   if(title[position].equals("")||title[position].equals("")||title[position].equals("")){
            Holder holder = new Holder();
            view = inflater.inflate(R.layout.listitem_calendar_title, null);
            holder.tv_calendar_title = (TextView) view.findViewById(R.id.tv_calendar_title);
            holder.tv_calendar_dec = (TextView) view.findViewById(R.id.tv_calendar_dec);
            holder.tv_calendar_dec.setText(descr[position]);

            holder.tv_calendar_title.setText(title[position]);
        }
        else {
            Holder holder = new Holder();
            view = inflater.inflate(R.layout.listitem_calendar_dec, null);

            holder.tv_calendar_dec = (TextView) view.findViewById(R.id.tv_calendar_dec);
         //   holder.tv_athletics_name = (TextView) view.findViewById(R.id.tv_athletics_name);

            holder.tv_calendar_dec.setText(descr[position]);
           // holder.tv_athletics_name.setText(name[position]);
        }
        return view;*/

/*
        if(title[position] !=null) {
            Holder holder=new Holder();

            //  if (kind[position].equals("Florida") || kind[position].equals("Florida")) {
                view = inflater.inflate(R.layout.listitem_calendar_title, null);
                holder.tv_calendar_title = view.findViewById(R.id.tv_calendar_title);
                holder.tv_calendar_title.setText(title[position]);


          //  }
        }
        else {
            if ((descr[position] != null)) {
                Holder holder=new Holder();

                if (!descr[position].equals("")) {
                    view = inflater.inflate(R.layout.listitem_calendar_dec, null);
                    holder.tv_calendar_dec = view.findViewById(R.id.tv_calendar_dec);
                    holder.tv_calendar_dec.setText(descr[position]);
                    Log.d("Debug tv desc", holder.tv_calendar_dec.toString());
                }
            }
        }*/























       // }*/
               /* else{
                    view = inflater.inflate(R.layout.listitem_calendar_day, null);
                    holder.tv_calendar_day = view.findViewById(R.id.tv_calendar_day);
                    holder.tv_calendar_week = view.findViewById(R.id.tv_calendar_week);
                    holder.tv_calendar_day.setText(day[position]);
                    holder.tv_calendar_week.setText(week[position]);
                    if(week[position].equals("Sunday") || week[position].equals("Saturday")){
                        holder.tv_calendar_day.setTextColor(Color.BLACK);
                        holder.tv_calendar_week.setTextColor(Color.BLACK);
                    }
                }
            }
            else{
                view = inflater.inflate(R.layout.listitem_calendar_day, null);
                holder.tv_calendar_day = view.findViewById(R.id.tv_calendar_day);
                holder.tv_calendar_week = view.findViewById(R.id.tv_calendar_week);
                holder.tv_calendar_day.setText(day[position]);
                holder.tv_calendar_week.setText(week[position]);
                if(week[position].equals("Sunday") || week[position].equals("Saturday")){
                    holder.tv_calendar_day.setTextColor(Color.BLACK);
                    holder.tv_calendar_week.setTextColor(Color.BLACK);
                }
            }
        }*/

   //     return view;
 //   }
//}

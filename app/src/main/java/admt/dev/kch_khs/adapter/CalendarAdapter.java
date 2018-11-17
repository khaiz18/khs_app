package admt.dev.kch_khs.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import admt.dev.kch_khs.util.CalendarActivity;
import admt.dev.kch_khs.R;

public class CalendarAdapter extends BaseAdapter {

    String [] kind, day, week, dec;
    Context context;
    private static LayoutInflater inflater=null;

    public CalendarAdapter(CalendarActivity calendarActivity, String[] kind, String[] day, String[] week, String[] dec) {

        this.kind=kind;
        this.day=day;
        this.week=week;
        this.dec=dec;
        context=calendarActivity;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return day.length;
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

    public class Holder
    {
        TextView tv_calendar_day, tv_calendar_week, tv_calendar_dec, tv_calendar_title;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View view = null;

        if(kind[position] !=null) {
            if (kind[position].equals("November") || kind[position].equals("December")) {
                view = inflater.inflate(R.layout.listitem_calendar_title, null);
                holder.tv_calendar_title = view.findViewById(R.id.tv_calendar_title);
                holder.tv_calendar_title.setText(kind[position]);
            }
        }
        else{
            if((dec[position] != null)){
                if(!dec[position].equals("")) {
                    view = inflater.inflate(R.layout.listitem_calendar_dec, null);
                    holder.tv_calendar_dec = view.findViewById(R.id.tv_calendar_dec);
                    holder.tv_calendar_dec.setText(dec[position]);
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
        }

        return view;
    }
}

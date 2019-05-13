package admt.dev.kch_khs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import admt.dev.kch_khs.util.AthleticsActivity;
import admt.dev.kch_khs.R;

public class AthleticsAdapter extends BaseAdapter {
    String [] kind, sport, name;
    Context context;
    private static LayoutInflater inflater=null;

    public AthleticsAdapter(AthleticsActivity athleticsActivity, String[] kind, String[] sport, String[] name) {
        // TODO Auto-generated constructor stub
        this.kind=kind;
        this.sport=sport;
        this.name=name;
        context=athleticsActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return name.length;
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
        TextView tv_athletics_sport, tv_athletics_name, tv_athletics_title, tv_athletics_email;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if(kind[position].equals("Spring")||kind[position].equals("Fall")||kind[position].equals("Winter")){
            Holder holder = new Holder();
            view = inflater.inflate(R.layout.listitem_athletics_title, null);
            holder.tv_athletics_title = (TextView) view.findViewById(R.id.tv_athletics_title);

            holder.tv_athletics_title.setText(kind[position]);
        }
        else {
            Holder holder = new Holder();
            view = inflater.inflate(R.layout.listitem_athletics, null);

            holder.tv_athletics_sport = (TextView) view.findViewById(R.id.tv_athletics_sport);
            holder.tv_athletics_name = (TextView) view.findViewById(R.id.tv_athletics_name);
            holder.tv_athletics_email = (TextView) view.findViewById(R.id.tv_athletics_email);

            holder.tv_athletics_sport.setText(sport[position]);
            holder.tv_athletics_name.setText(name[position]);
            holder.tv_athletics_email.setText(kind[position]);
        }
        return view;
    }
}

package admt.dev.kch_khs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import admt.dev.kch_khs.util.ClubsActivity;
import admt.dev.kch_khs.R;

public class ClubsAdapter extends BaseAdapter {
    String [] name, rep, email, location, date, time;
    Context context;
    private static LayoutInflater inflater=null;

    public ClubsAdapter(ClubsActivity clubsActivity, String[] name, String[] rep, String [] location, String [] email, String [] date, String [] time) {
        // TODO Auto-generated constructor stub
        this.name=name;
        this.rep=rep;
        this.email=email;
        this.location = location;
        this.date = date;
        this.time = time;
        context=clubsActivity;
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
        TextView tv_clubs_name, tv_clubs_rep, tv_clubs_email, tv_clubs_location, tv_clubs_date, tv_clubs_time;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View view;
        view = inflater.inflate(R.layout.listitem_clubs, null);

        holder.tv_clubs_name=(TextView) view.findViewById(R.id.tv_clubs_name);
        holder.tv_clubs_rep=(TextView) view.findViewById(R.id.tv_clubs_rep);
        holder.tv_clubs_email=(TextView) view.findViewById(R.id.tv_clubs_email);
        holder.tv_clubs_location = (TextView) view.findViewById(R.id.tv_clubs_location);
        holder.tv_clubs_date = (TextView) view.findViewById(R.id.tv_clubs_date);
        holder.tv_clubs_time = (TextView) view.findViewById(R.id.tv_clubs_time);



        holder.tv_clubs_name.setText(name[position]);
        holder.tv_clubs_rep.setText(" Name: " + rep[position]);
        holder.tv_clubs_location.setText(" Location: " + location[position]);
        holder.tv_clubs_email.setText(" Email: "+ email[position]);
        holder.tv_clubs_date.setText(" Date: "+ date[position]);
        holder.tv_clubs_time.setText(" Time: "+ time[position]);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                    Toast.makeText(context, "You Clicked " + name[position], Toast.LENGTH_LONG).show();

            }
        });
        if(name[position] == null)
            view.setVisibility(View.INVISIBLE);
        return view;
    }
}

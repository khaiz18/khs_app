package admt.dev.kch_khs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import admt.dev.kch_khs.util.ClubsActivity;
import admt.dev.kch_khs.R;

public class ClubsAdapter extends BaseAdapter {
    String [] name, rep, email;
    Context context;
    private static LayoutInflater inflater=null;

    public ClubsAdapter(ClubsActivity clubsActivity, String[] name, String[] rep, String[] email) {
        // TODO Auto-generated constructor stub
        this.name=name;
        this.rep=rep;
        this.email=email;
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
        TextView tv_clubs_name, tv_clubs_rep, tv_clubs_email;
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

        holder.tv_clubs_name.setText(name[position]);
        holder.tv_clubs_rep.setText(rep[position]);
        holder.tv_clubs_email.setText("Room : "+email[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //    Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
            }
        });
        if(name[position] == null)
            view.setVisibility(View.INVISIBLE);
        return view;
    }
}

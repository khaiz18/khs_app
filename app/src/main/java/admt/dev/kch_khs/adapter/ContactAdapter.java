package admt.dev.kch_khs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import admt.dev.kch_khs.util.ContactActivity;
import admt.dev.kch_khs.R;

public class ContactAdapter extends BaseAdapter {
    String [] job, name, email, location;
    Context context;
    private static LayoutInflater inflater=null;

    public ContactAdapter(ContactActivity contactActivity, String[] job, String[] name, String[] location) {
        // TODO Auto-generated constructor stub
        this.job=job;
        this.name=name;
        this.email=email;
        this.location = location;
        context=contactActivity;
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
        TextView tv_contact_job, tv_contact_name, tv_contact_email, tv_contact_title, tv_location;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if( "Administration".equalsIgnoreCase(job[position])||"Counseling".equalsIgnoreCase(job[position]) || "Science".equalsIgnoreCase(job[position])||"Math".equalsIgnoreCase(job[position])||"English".equalsIgnoreCase(job[position])||"Social Studies".equalsIgnoreCase(job[position])||"Career and Tech Ed".equalsIgnoreCase(job[position])||"Physical Education/Health".equalsIgnoreCase(job[position])||"Special Education".equalsIgnoreCase(job[position])||"World Languages".equalsIgnoreCase(job[position])||"Fine Arts".equalsIgnoreCase(job[position])||"SPED".equalsIgnoreCase(job[position])||"Staff".equalsIgnoreCase(job[position])||"PE/Health".equalsIgnoreCase(job[position])||"English".equalsIgnoreCase(job[position])||"Career & Technical".equalsIgnoreCase(job[position])|| "End of Contact List".equalsIgnoreCase(job[position])){
            Holder holder = new Holder();
            view = inflater.inflate(R.layout.listitem_contact_title, null);
            holder.tv_contact_title = (TextView) view.findViewById(R.id.tv_contact_title);

            holder.tv_contact_title.setText(job[position]);



        }




        else {
            Holder holder = new Holder();
            view = inflater.inflate(R.layout.listitem_contact, null);

            holder.tv_contact_job = (TextView) view.findViewById(R.id.tv_contact_kind);
            holder.tv_contact_name = (TextView) view.findViewById(R.id.tv_contact_name);
            holder.tv_location = (TextView) view.findViewById(R.id.tv_location);

            //    holder.tv_contact_email = (TextView) view.findViewById(R.id.tv_contact_email);



                holder.tv_contact_job.setText("Name:" + " " + job[position]);
                holder.tv_contact_name.setText("Email:" + " " + name[position]);
                holder.tv_location.setText("Location: " + " " + location[position]);



        }





        return view;
    }
}
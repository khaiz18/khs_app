package admt.dev.kch_khs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import admt.dev.kch_khs.R;
import admt.dev.kch_khs.util.ResourceActivity;

public class ResourceAdatper extends BaseAdapter {
    String [] job, info, domain;
    Context context;
    private static LayoutInflater inflater=null;

    public ResourceAdatper(ResourceActivity resourceActivity, String[] job, String[] info, String[] domain) {
        // TODO Auto-generated constructor stub
        this.job=job;
        this.info=info;
        this.domain=domain;
        context=resourceActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return job.length;
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
        TextView tv_res_title, tv_res_info, tv_res_domain, tv_job_title;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view;
        if( "Official School Websites".equalsIgnoreCase(job[position])||"Social Media".equalsIgnoreCase(job[position]) || "Athletics".equalsIgnoreCase(job[position])||"School Resources".equalsIgnoreCase(job[position])||"Pupil Resources".equalsIgnoreCase(job[position])||"Learning/Support Sites".equalsIgnoreCase(job[position])||"Volunteer Opportunities".equalsIgnoreCase(job[position])||"CTE Competitions".equalsIgnoreCase(job[position])||"External Student Orgs".equalsIgnoreCase(job[position])||"Extras".equalsIgnoreCase(job[position])|| "End of Resource List".equalsIgnoreCase(job[position]) ){
            Holder holder = new Holder();
            view = inflater.inflate(R.layout.listitem_resource_title, null);
            holder.tv_job_title = (TextView) view.findViewById(R.id.tv_contact_title);

            holder.tv_job_title.setText(job[position]);
        }
        else {


            Holder holder = new Holder();
            //View view;
            view = inflater.inflate(R.layout.listitem_resource, null);

           // holder.tv_res_title = (TextView) view.findViewById(R.id.tv_res_title);
            holder.tv_res_info = (TextView) view.findViewById(R.id.tv_res_info);
            holder.tv_res_domain = (TextView) view.findViewById(R.id.tv_res_domain);

//            holder.tv_res_title.setText(job[position]);
            holder.tv_res_info.setText(info[position]);
            holder.tv_res_domain.setText(domain[position]);

        }
        return view;
    }
}

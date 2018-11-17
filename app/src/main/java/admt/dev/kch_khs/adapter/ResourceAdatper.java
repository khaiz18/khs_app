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
    String [] title, info, domain;
    Context context;
    private static LayoutInflater inflater=null;

    public ResourceAdatper(ResourceActivity resourceActivity, String[] title, String[] info, String[] domain) {
        // TODO Auto-generated constructor stub
        this.title=title;
        this.info=info;
        this.domain=domain;
        context=resourceActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
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

    public class Holder
    {
        TextView tv_res_title, tv_res_info, tv_res_domain;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View view;
        view = inflater.inflate(R.layout.listitem_resource, null);

        holder.tv_res_title=(TextView) view.findViewById(R.id.tv_res_title);
        holder.tv_res_info=(TextView) view.findViewById(R.id.tv_res_info);
        holder.tv_res_domain=(TextView) view.findViewById(R.id.tv_res_domain);

        holder.tv_res_title.setText(title[position]);
        holder.tv_res_info.setText(info[position]);
        holder.tv_res_domain.setText(domain[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //    Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
            }
        });
        if(title[position] == null)
            view.setVisibility(View.INVISIBLE);
        return view;
    }
}

package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.heath.mdmsoftware.R;

import java.util.List;

import model.Job;

/**
 * Created by Heath on 3/11/14.
 */
public class JobListAdapter extends ArrayAdapter<Job> {

    private Context context;
    List<Job> jobs;

    public JobListAdapter(Context context, List<Job> jobs){
        super(context, R.layout.list_job_item, jobs);
        this.context = context;
        this.jobs = jobs;
    }

    private class ViewHolder{
        TextView jobIdTxt;
        TextView jobNameTxt;
        TextView jobLoggedByTxt;
        TextView jobRLTxt;
        TextView jobJobNumTxt;
    }

    @Override
    public int getCount(){
        return jobs.size();
    }

    @Override
    public Job getItem(int position){
        return jobs.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_job_item, null);
            holder = new ViewHolder();

            holder.jobIdTxt = (TextView) convertView.findViewById(R.id.txt_job_id);
            holder.jobNameTxt = (TextView)convertView.findViewById(R.id.txt_job_name);
            //holder.jobJobNumTxt = (TextView)convertView.findViewById(R.id.txt_job_num);
            holder.jobLoggedByTxt = (TextView)convertView.findViewById(R.id.txt_job_loggedBy);
            holder.jobRLTxt = (TextView) convertView.findViewById(R.id.txt_job_rl);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Job job = (Job) getItem(position);
        holder.jobIdTxt.setText(job.getJobId() + "ddd");
        holder.jobNameTxt.setText(job.getProjectName());
        //holder.jobJobNumTxt.setText(job.getJobNum());
        holder.jobLoggedByTxt.setText(job.getLoggedBy());
        holder.jobRLTxt.setText(job.getRL());

        return convertView;
    }

    @Override
    public void add(Job job){
        jobs.add(job);
        notifyDataSetChanged();
        super.add(job);
    }

    @Override
    public void remove(Job job){
        jobs.remove(job);
        notifyDataSetChanged();
        super.remove(job);
    }


}

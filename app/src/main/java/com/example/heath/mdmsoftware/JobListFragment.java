package com.example.heath.mdmsoftware;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import adapter.JobListAdapter;
import db.JobDAO;
import model.Job;

/**
 * Created by Heath on 14/11/14.
 */
public class JobListFragment extends ListFragment{
    Activity activity;
    ListView jobListView;
    ArrayList<Job> jobs;

    JobListAdapter jobListAdapter;
    JobDAO jobDAO;

    private GetJobTask task;





    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        activity = getActivity();
        jobDAO = new JobDAO(activity);
    }


    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_job_list, container, false);
        findViewsById(view);
        task = new GetJobTask(activity);
        task.execute((Void) null);

        return view;
    }

    private void findViewsById(View view) {
        jobListView = (ListView) view.findViewById(R.id.list_job);
    }


    public class GetJobTask extends AsyncTask<Void, Void, ArrayList<Job>> {

        private final WeakReference<Activity> activityWeakRef;

        public GetJobTask(Activity context){
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected ArrayList<Job> doInBackground(Void... arg0){
            ArrayList<Job> jobs = jobDAO.getAllJobs();
            return jobs;
        }

        @Override
        protected void onPostExecute(ArrayList<Job> jobList){
            if (activityWeakRef.get() != null && !activityWeakRef.get().isFinishing()){
                jobs = jobList;
                if (jobList.size() != 0){
                    jobListAdapter = new JobListAdapter(activity, jobList);
                    jobListView.setAdapter(jobListAdapter);
                } else {
                    Toast.makeText(activity, "No Job Records", Toast.LENGTH_LONG).show();
                }
            }
        }
    }



}

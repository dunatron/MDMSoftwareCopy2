package com.example.heath.mdmsoftware;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.widget.ListView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;


import java.lang.ref.WeakReference;
import java.util.ArrayList;

import adapter.JobListAdapter;
import db.JobDAO;
import model.Job;

/**
 * Created by Heath on 2/11/14.
 */
public class ListJobsFragment extends Fragment implements OnItemClickListener{

    Activity activity;
    ListView jobListView;
    ArrayList<Job> jobs;

    JobListAdapter jobListAdapter;
    JobDAO jobDAO;

    private GetJobTask task;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Comment out because of change from Fragment To FragmentActivity
        activity = getActivity();
        jobDAO = new JobDAO(activity);
    }


    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_job_list, container, false);
        findViewsById(view);
        task = new GetJobTask(activity);
        task.execute((Void) null);

        jobListView.setOnItemClickListener(this);
        //jobListView.setOnItemLongClickListener(this);
        return view;
    }

    private void findViewsById(View view) {
        jobListView = (ListView) view.findViewById(R.id.list_job);
    }


    @Override
    public void onItemClick(AdapterView<?> list, View view, int position,
                            long id) {
        Job job = (Job) list.getItemAtPosition(position);

        //if (job != null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable("selectedJob", job);
        //    CustomEmpDiaolgFragment customEmpDialogFragment = new CustomEmpDiaolgFragment();
        //    customEmpDialogFragment.setArguments(arguments);
       //     customEmpDialogFragment.show(getFragmentManager(),
       //             CustomEmpDiaolgFragment.ARG_ITEM_ID);
       // }


        JobDetailsFragment detsFrag = new JobDetailsFragment();
        //detsFrag = fm.findFragmentById(R.id.detailFragmentContainer);
        //JobListFragment detsFrag = new JobListFragment();
        //detsFrag.setArguments(arguments);
        //FragmentManager fm = getSupportFragmentManager();
        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.fragmentContainer, detsFrag);
        //transaction.addToBackStack(null);
        //transaction.commit();



    }

    //@Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long id) {
        Job job = (Job) parent.getItemAtPosition(position);
        // Use AsyncTask to delete from database
        //jobDAO.deleteEmployee(employee);
        //jobDAO.deleteJob(job);
        //jobListAdapter.remove(job);
        //employeeListAdapter.remove(employee);

        return true;
    }


    public class GetJobTask extends AsyncTask<Void, Void, ArrayList<Job>>{

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

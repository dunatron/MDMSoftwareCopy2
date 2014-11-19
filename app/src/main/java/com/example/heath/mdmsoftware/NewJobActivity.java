package com.example.heath.mdmsoftware;

import android.app.Activity;
import android.app.DatePickerDialog;

import android.os.AsyncTask;
import android.os.Bundle;


import android.app.DatePickerDialog.OnDateSetListener;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import db.JobDAO;
import model.Job;


/**
 * Created by Heath on 2/11/14.
 */
public class NewJobActivity extends Fragment implements View.OnClickListener {

    NewJobListener mCallback;

    // Container Activity must implement this interface
    public interface NewJobListener {
        public void onArticleSelected(String projectName, String jobNum, String loggedBy,
                                      String RL, String Equipment);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (NewJobListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NewJobListener");
        }
    }


    // UI REFERENCES
    private EditText project_name, job_num, logged_by, rl, equipment;
    private Button nextButton;
    private Button resetButton;


    public String projectName, loggedBy, RL, Equipment;
    public String jobNum;
    Date excavDatum;

    // ENABLE DATE DIALOG PICKER
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    DatePickerDialog datePickerDialog;
    Calendar dateCalendar;

    Job job = null;

    private JobDAO jobDAO;
    private AddJobTask addJobTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.new_job);
        jobDAO = new JobDAO(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.new_job, container, false);

        findViewsById(rootView);

        setListeners();

        return rootView;

    }


    @Override
    public void onClick(View view) {


        if (view == nextButton) {
            //setJob();
            //addJobTask = new AddJobTask(getActivity());
            //addJobTask.execute((Void) null);
            //jobDAO.save(job);
            projectName = project_name.getText().toString();
            jobNum = job_num.getText().toString();
            loggedBy = logged_by.getText().toString();
            RL = rl.getText().toString();
            Equipment = equipment.getText().toString();
            mCallback.onArticleSelected(projectName, jobNum, loggedBy, RL, Equipment);

        } else if (view == resetButton) {
            //resetAllFields();
        }

    }

    //private void setJob() {
       // job = new Job();
       // job.setProjectName(project_name.getText().toString());
       // job.setJobNum(Integer.parseInt(job_num.getText().toString()));
       // job.setLoggedBy(logged_by.getText().toString());
      //  if (dateCalendar != null) {
       //     job.setExcavdatum(dateCalendar.getTime());
      //      job.setExcavStart(dateCalendar.getTime());
      //      job.setExcavFinished(dateCalendar.getTime());
     //   }
     //   job.setRL(rl.getText().toString());
    //    job.setEquipment(equipment.getText().toString());
        //job.setOperator(operator.getText().toString());
        //job.setCompany(company.getText().toString());
        //job.setLocation(location.getText().toString());
        //if (dateCalendar != null)
        //	job.setExcavStart(dateCalendar.getTime());
        //if (dateCalendar != null)
        //	job.setExcavFinished(dateCalendar.getTime());
   // }

    private void findViewsById(View rootView) {
        project_name = (EditText) rootView.findViewById(R.id.etxt_project_name);
        job_num = (EditText) rootView.findViewById(R.id.etxt_job_num);
        logged_by = (EditText) rootView.findViewById(R.id.etxt_logged_by);
        rl = (EditText) rootView.findViewById(R.id.etxt_rl);
        equipment = (EditText) rootView.findViewById(R.id.etxt_equipment);

        nextButton = (Button) rootView.findViewById(R.id.button_next);
        nextButton.setEnabled(true);
        resetButton = (Button) rootView.findViewById(R.id.button_reset);

    }

    private void setListeners() {
        //excavdatum.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getActivity(),
                new OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateCalendar = Calendar.getInstance();
                        dateCalendar.set(year, monthOfYear, dayOfMonth);
                        //excavdatum.setText(formatter.format(dateCalendar.getTime()));

                    }
                }, newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));

        nextButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }

    public class AddJobTask extends AsyncTask<Void, Void, Long> {
        private final WeakReference<Activity> activityWeakRef;

        public AddJobTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected Long doInBackground(Void... arg0) {
            long result = jobDAO.save(job);
            return result;
        }

        @Override
        protected void onPostExecute(Long result) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                if (result != -1)
                    Toast.makeText(activityWeakRef.get(), "Job Saved",
                            Toast.LENGTH_LONG).show();
            }
        }

    }


}
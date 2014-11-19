package com.example.heath.mdmsoftware;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.app.DatePickerDialog.OnDateSetListener;

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
public class NewJobFragTwo extends Fragment implements View.OnClickListener {
    final static String ARG_PROJECTNAME = "project_name";
    final static String ARG_JOBNUMBER = "1";
    final static String ARG_LOGGEDBY = "logged_by";
    final static String ARG_RL = "rl";
    final static String ARG_EQUIPMENT = "equipment";

    String currentProjectName = "filler";
    String currentJobNum = "69";
    String currentLoggedBy = "filler";
    String currentRL = "filler";
    String currentEquipment = "filler";

    Job job = null;

    private JobDAO jobDAO;
    private AddJobTask addJobTask;

    TextView myEdit;
    EditText operator, company, location, excav_start, excav_finished, excav_datum;

    Date excavDatum, excavStart, excavFinished;

    Button btnAdd, btnreset;

    // ENABLE DATE DIALOG PICKER
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    DatePickerDialog datePickerDialog;
    Calendar dateCalendar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.new_job_two, container, false);
        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            currentProjectName = savedInstanceState.getString(ARG_PROJECTNAME);
        }


        findViewsById(rootView);

        setListeners();

        return rootView;

    }

    @Override
    public void onStart() {
        super.onStart();
        jobDAO = new JobDAO(getActivity());

        Bundle args = getArguments();
        if (args != null) {
            //myEdit.setText(args.getString(ARG_PROJECTNAME));
            currentProjectName = args.getString(ARG_PROJECTNAME);
            currentJobNum = args.getString(ARG_JOBNUMBER);
            currentLoggedBy = args.getString(ARG_LOGGEDBY);
            currentRL = args.getString(ARG_RL);
            currentEquipment = args.getString(ARG_EQUIPMENT);

        }
    }

    public void updateJobFields(String project_name) {

    }

    private void findViewsById(View rootView) {
        //myEdit = (TextView) rootView.findViewById(R.id.textView22);
        operator = (EditText) rootView.findViewById(R.id.etxt_operator);
        company = (EditText) rootView.findViewById(R.id.etxt_company);
        location = (EditText) rootView.findViewById(R.id.etxt_location);
        excav_start = (EditText) rootView.findViewById(R.id.etxt_excav_start);
        excav_start.setInputType(InputType.TYPE_NULL);
        excav_finished = (EditText) rootView.findViewById(R.id.etxt_excav_finished);
        excav_finished.setInputType(InputType.TYPE_NULL);
        excav_datum = (EditText) rootView.findViewById(R.id.etxt_excavdatum);
        excav_datum.setInputType(InputType.TYPE_NULL);

        btnAdd = (Button) rootView.findViewById(R.id.button_add);
        btnAdd.setEnabled(true);
        btnreset = (Button) rootView.findViewById(R.id.button_reset);


    }

    private void setListeners() {
        excav_start.setOnClickListener(this);
        excav_finished.setOnClickListener(this);
        excav_datum.setOnClickListener(this);

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

        btnAdd.setOnClickListener(this);
        btnreset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == excav_start) {
            datePickerDialog.show();
        } else if (view == excav_finished) {
            datePickerDialog.show();
        }else if (view == btnAdd){
            setJob();
            addJobTask = new AddJobTask(getActivity());
            addJobTask.execute((Void) null);
            //Lol was saving Twice to the database on each click
            //jobDAO.save(job);
            //setJob();
            //addJobTask = new AddJobTask(getActivity());
            //addJobTask.execute((Void) null);
            //jobDAO.save(job);
        }

    }

    private void setJob(){
        job = new Job();
        job.setProjectName(currentProjectName);
        job.setJobNum(Integer.parseInt(currentJobNum));
        job.setLoggedBy(currentLoggedBy);
        job.setRL(currentRL);
        job.setEquipment(currentEquipment);
        if (dateCalendar != null) {
            job.setExcavdatum(dateCalendar.getTime());
            job.setExcavStart(dateCalendar.getTime());
            job.setExcavFinished(dateCalendar.getTime());
        }
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

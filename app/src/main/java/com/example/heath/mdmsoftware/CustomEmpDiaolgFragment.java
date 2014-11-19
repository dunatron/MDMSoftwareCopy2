package com.example.heath.mdmsoftware;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import db.JobDAO;
import model.Job;

/**
 * Created by Heath on 14/11/14.
 */
public class CustomEmpDiaolgFragment extends DialogFragment{

    // UI references
    private TextView jobNameTxt;

    private LinearLayout submitLayout;

    private Job job;

    JobDAO jobDAO;


    public static final String ARG_ITEM_ID = "emp_dialog_fragment";



    /*
     * Callback used to communicate with EmpListFragment to notify the list adapter.
     * MainActivity implements this interface and communicates with EmpListFragment.
     */
    public interface CustomEmpDialogFragmentListener {
        void onFinishDialog();
    }

    public CustomEmpDiaolgFragment() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        jobDAO = new JobDAO(getActivity());

        Bundle bundle = this.getArguments();
        job = bundle.getParcelable("selectedJob");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View customDialogView = inflater.inflate(R.layout.new_job_fragment,
                null);
        builder.setView(customDialogView);


        jobNameTxt = (TextView) customDialogView.findViewById(R.id.txt_job_name);


        AlertDialog alertDialog = builder.create();

        return alertDialog;
    }


}
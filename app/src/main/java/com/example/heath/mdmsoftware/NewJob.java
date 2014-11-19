package com.example.heath.mdmsoftware;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by Heath on 2/11/14.
 */
public class NewJob extends FragmentActivity implements NewJobActivity.NewJobListener{
    //FragmentManager manager;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_job_fragment);

        NewJobActivity firstfrag = new NewJobActivity();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstfrag).commit();
    }

    public void onArticleSelected(String projectName, String jobNum, String loggedBy, String RL, String Equipment){


        NewJobFragTwo fragtwo = new NewJobFragTwo();
        Bundle args = new Bundle();
        args.putString(NewJobFragTwo.ARG_PROJECTNAME, projectName);
        args.putString(NewJobFragTwo.ARG_JOBNUMBER, jobNum);
        args.putString(NewJobFragTwo.ARG_LOGGEDBY, loggedBy);
        args.putString(NewJobFragTwo.ARG_RL, RL);
        args.putString(NewJobFragTwo.ARG_EQUIPMENT, Equipment);
        fragtwo.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragtwo);
        transaction.addToBackStack(null);
        transaction.commit();

    }


}

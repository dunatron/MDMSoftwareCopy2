package com.example.heath.mdmsoftware;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import fragments.SingleFragmentActivity;

/**
 * Created by Heath on 3/11/14.
 */
public class ListJobsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new ListJobsFragment();
    }

    //@Override
    //protected Fragment createFragment(){
    //    return new JobListFragment();
    //}




    @Override
    protected int getLayoutResId(){
        return R.layout.activity_masterdetail;
    }





    //protected void onCreate(Bundle savedInstanceState){
    //    super.onCreate(savedInstanceState);
     //   setContentView(R.layout.two_pane_layout);

        //ListJobsFragment listFrag = new ListJobsFragment();

        //getSupportFragmentManager().beginTransaction()
         //       .add(R.id.fragment_container, listFrag).commit();

     //   ListJobsFragment fragDisplay = new ListJobsFragment();
     //   FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

      //  trans.replace(R.id.fragment_container, fragDisplay);
      //  trans.addToBackStack(null);
      //  trans.commit();
   // }

}

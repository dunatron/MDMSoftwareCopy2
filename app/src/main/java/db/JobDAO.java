package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import model.Job;

/**
 * Created by Heath on 2/11/14.
 */
public class JobDAO extends ReadWriteMDM{

    private static final String WHERE_ID_EQUALS = DataBaseHelper.JOB_ID + "=?";

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    public JobDAO(Context context) {
        super(context);

    }

    public long save(Job job)
    {
        ContentValues values = new ContentValues();
        //VALUES TO SAVE
        values.put(DataBaseHelper.PROJECT_NAME_COL, job.getProjectName());
        values.put(DataBaseHelper.JOB_NUM, job.getJobNum());
        values.put(DataBaseHelper.LOGGED_BY, job.getLoggedBy());
        //values.put(DataBaseHelper.EXCAVDATUM, formatter.format(job.getExcavdatum()));
        values.put(DataBaseHelper.RL, job.getRL());
        //values.put(DataBaseHelper.EQUIPMENT, job.getEquipment());
        //values.put(DataBaseHelper.OPERATOR, job.getOperator());
        //values.put(DataBaseHelper.COMPANY, job.getCompany());
        //values.put(DataBaseHelper.LOCATION, job.getLoaction());
        //values.put(DataBaseHelper.EXCAV_START, formatter.format(job.getExcavStart()));
        //values.put(DataBaseHelper.EXCAV_FINISHED, formatter.format(job.getExcavFinished()));
        return database.insert(DataBaseHelper.JOB_TABLE, null, values);
    }


    //Getting all The JObs
    public ArrayList<Job> getAllJobs(){
        ArrayList<Job> jobs = new ArrayList<Job>();
        //Select All query
        String selectQuery = "SELECT * FROM " + DataBaseHelper.JOB_TABLE;
        Log.d("selectQuery", selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        while (cursor.moveToNext()){
            Job job = new Job();
            job.setJobId(cursor.getInt(0));
            job.setProjectName(cursor.getString(1));
            job.setJobNum(cursor.getInt(2));

            jobs.add(job);

        }
        return jobs;
    }




}

package db;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import model.Job;

/**
 * Created by Heath on 2/11/14.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    static final String DATABASE_NAME = "mdmdatabase";
    static final int DATABASE_VERSION = 1;

    //MDM DATABASE TABLES
    public static final String JOB_TABLE = "job";
    public static final String PIT_TABLE = "pit";
    public static final String LAYER_TABLE = "layer";

    public static final String EMPLOYEE_TABLE = "employee";

    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String EMPLOYEE_DOB = "dob";
    public static final String EMPLOYEE_SALARY = "salary";
    public static final String EMPLOYEE_DEPARTMENT_ID = "dept_id";

    public static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE "
            + EMPLOYEE_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY, "
            + NAME_COLUMN + " TEXT, " + EMPLOYEE_SALARY + " DOUBLE, "
            + EMPLOYEE_DOB + " DATE "
            +  ")";




    //JOB TABLE COLUMNS
    public static final String JOB_ID = "job_id";
    public static final String PROJECT_NAME_COL = "project_name";
    public static final String JOB_NUM = "job_num";
    public static final String LOGGED_BY = "logged_by";
    public static final String EXCAVDATUM = "excavdatum";
    public static final String RL = "rl";
    public static final String EQUIPMENT = "equipment";
    public static final String OPERATOR = "operator";
    public static final String COMPANY = "company";
    public static final String LOCATION = "location";
    public static final String EXCAV_START = "excav_start";
    public static final String EXCAV_FINISHED = "excav_finished";

    //PIT TABLE COLUMNS
    public static final String PIT_ID = "pit_id";
    public static final String PIT_TYPE_COL = "pit_type";
    public static final String PIT_NUM_COL = "pit_num";
    public static final String PIT_JOB_FK = "fk_job_id";

    //LAYER TABLE COLUMNS
    public static final String LAYER_ID = "layer_id";
    public static final String FK_PIT_ID = "fk_pit_id";
    public static final String METRE_FROM = "metre_from";
    public static final String METRE_TO = "metre_to";
    public static final String COLOUR = "colour";
    public static final String SUBORDINATE_FRACTION = "subordinate_fraction";
    public static final String MAJOR_FRACTION = "major_fraction";
    public static final String MINOR_FRACTION = "minor_fraction";
    public static final String PARTICLE_SIZE = "particle_size";
    public static final String PARTICLE_SHAPE = "particle_shape";
    public static final String STRUCTURE = "structure";
    public static final String PLASTICITY = "plasticity";
    public static final String SENSITIVITY = "sensitivity";
    public static final String GRADING = "grading";
    public static final String BEDDING = "bedding";
    public static final String BEDDING_TWO = "bedding_two";
    public static final String DILATANCY = "dilatancy";
    public static final String STRENGTH = "strength";
    public static final String MOISTURE = "moisture";
    public static final String SAMPLE_TAKEN = "sample_taken";


    //QUERY FOR CREATE JOBS TBL
    public static final String CREATE_JOB_TBL = "CREATE TABLE "
            + JOB_TABLE + "(" + JOB_ID + " INTEGER PRIMARY KEY, "
            + PROJECT_NAME_COL + " TEXT, " + JOB_NUM + " INTEGER, " + LOGGED_BY + " TEXT, "
            + EXCAVDATUM + " DATE, " + RL + " TEXT, " + EQUIPMENT + " TEXT, "
            + OPERATOR + " TEXT, " + COMPANY + " TEXT, " + LOCATION + " TEXT, "
            + EXCAV_START + " DATE, " + EXCAV_FINISHED + " DATE "
            + ")";

    //QUERY FOR CREATE PITS TBL
    public static final String CREATE_PIT_TBL = "CREATE TABLE "
            + PIT_TABLE + "(" + PIT_ID + " INTEGER PRIMARY KEY, "
            + PIT_TYPE_COL + " TEXT, "
            + PIT_NUM_COL + " INTEGER, "
            + PIT_JOB_FK + "INT, " + "FOREIGN KEY(" + PIT_JOB_FK + ")REFERENCES "
            + JOB_TABLE + "(job_id) " + ")";

    //QUERY FOR CREATE LAYERS
    public static final String CREATE_LAYER_TBL = "CREATE TABLE "
            + LAYER_TABLE
            + "("
            + LAYER_ID + " INTEGER PRIMARY KEY, "
            + FK_PIT_ID + " INT, " + "FOREIGN KEY(" + FK_PIT_ID + ")REFERENCES "
            + PIT_TABLE + "(pit_id)"
            + METRE_FROM + " INTEGER, " + METRE_TO + " INTEGER, "
            + COLOUR + " TEXT, " + SUBORDINATE_FRACTION + " TEXT, " + MAJOR_FRACTION + " TEXT, "
            + MINOR_FRACTION + " TEXT, " + PARTICLE_SIZE + " TEXT, " + PARTICLE_SHAPE + " TEXT, "
            + STRUCTURE + " TEXT, " + PLASTICITY + " TEXT, " + GRADING + " TEXT," + BEDDING + " TEXT, "
            + BEDDING_TWO + " TEXT, " + DILATANCY + " TEXT, " + STRENGTH + " TEXT, "
            + MOISTURE + " TEXT, "
            + SAMPLE_TAKEN + " TEXT, "
            + SENSITIVITY + " TEXT, "
            + ")";


    //DATABASE INSTANCE
    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context)
    {
        if(instance == null)
            instance = new DataBaseHelper(context);
        return instance;

    }


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
        if (!db.isReadOnly())
        {
            //enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_JOB_TBL);
        //db.execSQL(CREATE_PIT_TBL);
        //db.execSQL(CREATE_LAYER_TBL);
        //db.execSQL(CREATE_EMPLOYEE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }



}
//NOTES
//make sure to enable foreign key constraints
package db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Heath on 2/11/14.
 */
public class ReadWriteMDM {

    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;

    public ReadWriteMDM(Context context)
    {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);

            open();//Didn't want to put this try catch here...

    }

    public void open() throws SQLException
    {
        if(dbHelper == null)
            dbHelper = DataBaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

}

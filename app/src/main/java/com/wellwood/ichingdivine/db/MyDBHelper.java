package com.wellwood.ichingdivine.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by waterwoodwell on 2016/6/15.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "geniuzWondrousDoorHidenGaapRecords.db3";
    private static int DB_VERSION = 1;
    private static String SQL_CREATE_INFO = "create table if not exists estRecord(ID INTEGER PRIMARY KEY AUTOINCREMENT,DTIME DATETIME,NAME VARCHAR (30),REASON text,VERIFY text,ORIBIN VARCHAR (30),FURTBIN VARCHAR (30) )";
    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_INFO);
    }

    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}

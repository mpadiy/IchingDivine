package com.wellwood.ichingdivine.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "gua.db";
    private static int DB_VERSION = 1;
    private static String SQL_CREATE_DETAIL = "create table detail (id INTEGER primary key,title TEXT,content BLOB,icon BLOB)";
    private static String SQL_CREATE_INFO = "create table info (id INTEGERprimary key,name TEXT,icon BLOB,msg TEXT)";
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_INFO);
        db.execSQL(SQL_CREATE_DETAIL);
    }

    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}

package com.wellwood.ichingdivine.db;

/**
 * Created by waterwoodwell on 2016/6/15.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wellwood.ichingdivine.model.Recordinfo;

import java.util.ArrayList;
import java.util.List;


public class RecordDao {
    private MyDBHelper dbHelper;
    private Context context;
    public RecordDao(Context context) {
        this.context = context;
        this.dbHelper = new MyDBHelper(new DatabaseContext(context));
    }

    public List<Recordinfo> query(String key) {
        List<Recordinfo> list = new ArrayList();
        //rawQuery  return a cursor object
        //Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery("select ID,DTIME,NAME,REASON,VERIFY from estRecord where NAME and REASON and VERIFY like '%" + key + "%'", null);

        SQLiteDatabase openOrCreateDatabase = context.openOrCreateDatabase("geniuzWondrousDoorHidenGaapRecords.db3",0 ,null);
//        Cursor cursor = openOrCreateDatabase.rawQuery("select ID,DTIME,NAME,REASON,VERIFY from estRecord where NAME and REASON and VERIFY like '%" + key + "%'", null);

        Object obj = "select DTIME,NAME,REASON,VERIFY from estRecord where 1=1";
        //where 1=1的写法是为了检化程序中对条件的检测
//        obj = new StringBuilder(String.valueOf(obj)).append(" and NAME ").append(" or REASON ").append(" or VERIFY like '%").append(key).append("%'").toString();
        obj = new StringBuilder(String.valueOf(obj)).append(" and NAME ").append("like '%").append(key).append("%'").append(" or REASON ").append("like '%").append(key).append("%'").toString();


        //and REASON and VERIFY
        Cursor cursor = openOrCreateDatabase.rawQuery(new StringBuilder(String.valueOf(obj)).append(" order by id desc").toString(), new String[0]);
        while (cursor.moveToNext()) {
            Recordinfo info = new Recordinfo();
            info.setReason(cursor.getString(2));
            info.setName(cursor.getString(1));
            info.setVerify(cursor.getString(3));
            info.setdtime(cursor.getString(0));
            list.add(info);
        }
        return list;
    }
}


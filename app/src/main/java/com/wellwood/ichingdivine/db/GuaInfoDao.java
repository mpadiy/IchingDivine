package com.wellwood.ichingdivine.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import com.wellwood.ichingdivine.model.GuaInfo;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GuaInfoDao {
    private DBHelper dbHelper;

    public GuaInfoDao(Context context) {
        this.dbHelper = new DBHelper(new DatabaseContext(context));
    }

    public void insert(GuaInfo info) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(info.getId()));
        values.put("name", info.getName());
        values.put("icon", Bitmap2Bytes(info.getIcon()));
        values.put("msg", info.getMsg());
        db.insert("info", null, values);
        db.close();
    }

    private byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public List<GuaInfo> queryAll() {
        List<GuaInfo> list = new ArrayList();
        Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery("select id,name,icon,msg from info", null);
        while (cursor.moveToNext()) {
            GuaInfo info = new GuaInfo();
            info.setId(cursor.getInt(0));
            info.setName(cursor.getString(1));
            byte[] data = cursor.getBlob(2);
            info.setIcon(BitmapFactory.decodeByteArray(data, 0, data.length));
            info.setMsg(cursor.getString(3));
            list.add(info);
        }
        return list;
    }

    public List<GuaInfo> query(String key) {
        List<GuaInfo> list = new ArrayList();
        //rawQuery  return a cursor object
        Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery("select id,name,icon,msg from info where name like '%" + key + "%'", null);
        while (cursor.moveToNext()) {
            GuaInfo info = new GuaInfo();
            info.setId(cursor.getInt(0));
            info.setName(cursor.getString(1));

            byte[] data = cursor.getBlob(2);
            info.setIcon(BitmapFactory.decodeByteArray(data, 0, data.length));

            info.setMsg(cursor.getString(3));
            list.add(info);
        }
        return list;
    }
}

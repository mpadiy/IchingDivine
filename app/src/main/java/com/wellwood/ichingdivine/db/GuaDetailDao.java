package com.wellwood.ichingdivine.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

import com.wellwood.ichingdivine.model.GuaDetail;

import java.io.ByteArrayOutputStream;

public class GuaDetailDao {
    private DBHelper dbHelper;
    public GuaDetailDao(Context context) {
        this.dbHelper = new DBHelper(new DatabaseContext(context));
    }

    public void insert(GuaDetail detail) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", Integer.valueOf(detail.getId()));          //getId return string type
        values.put("title", detail.getTitle());
        values.put("icon", Bitmap2Bytes(detail.getIcon()));
        values.put("keyword", detail.getContent().getBytes());
        //SocializeDBConstants.h >>  "keyword"
        db.insert("detail", null, values);
        db.close();
    }

    public GuaDetail query(int id) {
        GuaDetail detail = null;         //init   null object
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id,title,content,icon from detail where id=" + id, null);
        if (cursor.moveToFirst()) {
            detail = new GuaDetail();
            detail.setId(cursor.getInt(0));
            detail.setTitle(cursor.getString(1));
            detail.setContent(new String(cursor.getBlob(2)));

            /*setIcon 将图片转化为位图
            创建一个字节数组输出流,置位图的压缩格式，质量为100%，并放入字节数组输出流中,
              ,将字节数组输出流转化为字节数组byte[],blob is binary data
            */
            byte[] data = cursor.getBlob(3);
            detail.setIcon(BitmapFactory.decodeByteArray(data, 0, data.length));

        }
        cursor.close();
        db.close();
        return detail;
    }

    private byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}

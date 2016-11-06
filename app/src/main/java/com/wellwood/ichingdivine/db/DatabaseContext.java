package com.wellwood.ichingdivine.db;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import java.io.File;
import java.io.IOException;

public class DatabaseContext extends ContextWrapper {
    public DatabaseContext(Context base) {
        super(base);
    }

    public static File getFilePath(String filePath, String fileName) {
        makeRootDirectory(filePath);
        try {
            return new File(new StringBuilder(String.valueOf(filePath)).append(fileName).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void makeRootDirectory(String filePath) {
        File file;
        try {
            File file2 = new File(filePath);
            try {
                if (file2.exists()) {
                    return;
                }
                file2.mkdirs();
                file = file2;
            } catch (Exception e) {
                file = file2;
            }
        } catch (Exception e2) {
        }
    }

    public File getDatabasePath(String name) {
        boolean isFileCreateSuccess = false;
        File dbFile = new File(new StringBuilder(String.valueOf(getApplicationContext().getFilesDir().getAbsolutePath())).append("/").append(name).toString());
        if (dbFile.exists()) {
            return dbFile;
        }
        try {
            isFileCreateSuccess = dbFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isFileCreateSuccess ? dbFile : null;
    }

    public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
    }

    public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory, DatabaseErrorHandler errorHandler) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
    }
}

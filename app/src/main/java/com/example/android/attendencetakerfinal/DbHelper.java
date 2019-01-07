package com.example.android.attendencetakerfinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by emon on 2/22/18.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = DbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "student.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    public Cursor showStudentInformation(String CurrentTableName) {

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor;
        String SelectDb="SELECT * FROM "+CurrentTableName;

        cursor=db.rawQuery(SelectDb,null);

        return cursor;
    }
}


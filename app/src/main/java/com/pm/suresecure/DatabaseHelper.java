package com.pm.suresecure;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "sresu.db";
    public static final String TABLE_NAME = "user_data";
    public static final String COL1 = "user_id";
    public static final String COL2 = "email";
    public static final String COL3 = "password";
    public static final String COL4 = "phone";
    //public static final String COL5 = "phone";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE p" + TABLE_NAME + " (user_id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT, phone TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String email, String password, String phone){ //get original values for user account setup
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, email);
        contentValues.put(COL3, password);
        contentValues.put(COL4, phone);
        long result = db.insert(TABLE_NAME, null, contentValues); //check to see if values were inserted

        if (result == -1) //get return value
            return false;
        else
            return true;

        //source: https://www.youtube.com/channel/UCs6nmQViDpUw0nuIx9c_WvA
    }
}


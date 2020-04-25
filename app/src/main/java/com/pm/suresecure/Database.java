package com.pm.suresecure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String ACCOUNTS_DB = "Yew.db";
    public static final String TABLE_NAME = "ACCOUNTS";
    public static final String COL_1 = "ID" ;
    public static final String COL_2 = "ACCOUNT" ;
    public static final String COL_3 = "USER_NAME" ;
    public static final String COL_4 = "PASSWORD" ;
    public static final String COL_5 = "URL" ;
    public static final String COL_6 = "CREAT_DATE" ;

    //NEED TO GO INTO A SEPERATE TABLE
    public static final String M_TABLE_NAME = "MASTER";
    public static final String M_COL_1= "USER_NAME";
    public static final String M_COL_2= "PASSWORD" ;
    public static final String M_COL_3= "PHONE_NUM" ;
    public static final String M_COL_4= "EMAIL" ;
    public static final String M_COL_5= "IP_ADDRESS" ;
    public static final String M_COL_6= "CREAT_DATE" ;
    public Database(@Nullable Context context) {
        //use function from parent class
        super(context, ACCOUNTS_DB,null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //table to store seperate accounts user wants to save
        db.execSQL("CREATE TABLE ACCOUNTS (ID INTEGER PRIMARY KEY AUTOINCREMENT, ACCOUNT TEXT, USER_NAME TEXT, PASSWORD TEXT, URL TEXT, CREAT_DATE TEXT)");
        //db.execSQL("CREATE TABLE " + ACCOUNTS_DB + " ("+ COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                //+ COL_2 +" TEXT, " + COL_3 + " TEXT, " + COL_4 + " TEXT, " + COL_5 + " TEXT, " + COL_6 + " TEXT)");
        //Table to store master info
        db.execSQL("CREATE TABLE MASTER (USER_NAME TEXT, PASSWORD TEXT, PHONE_NUM TEXT, EMAIL TEXT, IP TEXT, CREAT_DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertMasterData(String userName,String email, String pw, String phoneNum)  {
        SQLiteDatabase db = this.getWritableDatabase(); 
        ContentValues M_record = new ContentValues();
        M_record.put(M_COL_1, userName);
        M_record.put(M_COL_2, pw);
        M_record.put(M_COL_3, phoneNum);
        M_record.put(M_COL_4, email);
        long M_answer = db.insert(M_TABLE_NAME, null, M_record);
        if(M_answer > 0)
            return true;
        else
            return false;
    }
    // insert data for seperate accounts user wants to store data for
    public boolean insertAccountData(String siteName, String siteURL, String userName, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues record = new ContentValues();
        record.put(COL_2, siteName);
        record.put(COL_3, userName);
        record.put(COL_4, password);
        record.put(COL_5, siteURL);
        long answer = db.insert(TABLE_NAME, null, record );

        if(answer > 0)
            return true;
        else
            return false;

    }
    //Query Database by username and return Value that you need
    public String getValues(String user, String need){
        //only need to read from database
        SQLiteDatabase db = this.getReadableDatabase();
        String whatINeeded;
        //get all values from table if user name exists
        Cursor getThis = db.rawQuery("SELECT "+ need +  "  FROM " + M_TABLE_NAME + " WHERE USER_NAME = '" + user + "'" , null);
        //move cursor to the first value, otherwise it's positioned at -1 causing errors
        getThis.moveToFirst();
        //store the value of a requested column as a string
        //inner function returns index via string argument
        //outer function returns string via int (the index of the column in table) argument
        whatINeeded = getThis.getString(getThis.getColumnIndex(need));
        return whatINeeded;
    }
    public ArrayList<String> returnList(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor theGoods = db.rawQuery("SELECT " + COL_2 + " FROM " + TABLE_NAME,  null);
        theGoods.moveToFirst();
        ArrayList<String> finishedProduct = new ArrayList<>();
        int i;
        /**
        for(theGoods.moveToFirst(); !theGoods.isAfterLast(); theGoods.moveToNext()){
            finishedProduct.add(theGoods.getString(i));
            System.out.println(theGoods.getString(i));
            i++;
        }**/
        //loop through cursor item and add each item to the Array<String> list
        for(i = 0; !theGoods.isAfterLast(); i++)
        {
            finishedProduct.add(theGoods.getString(theGoods.getColumnIndex(COL_2)));
            theGoods.moveToNext();
        }
        return finishedProduct;
    }
}

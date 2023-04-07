package com.example.mobile.cashier;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "travelit.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table review(username text null, review text null);";
        Log.d("Data", "onCreate: "+sql);
        db.execSQL(sql);
        sql = "INSERT INTO review (usernwame, review) VALUES('Developer', 'Nice Info');";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){
        // TODO Auto-generated method stub
    }
}


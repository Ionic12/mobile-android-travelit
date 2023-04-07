package com.example.mobile.cashier.data;

import android.content.Context;

import androidx.room.Room;

public class AppDbProvider
{
    private static DTSAppDatabase instance;
    private static DTSAppDatabase asynchronousInstance;

    public static DTSAppDatabase getInstance(Context context)
    {
        if(AppDbProvider.instance == null)
        {
            AppDbProvider.instance = Room.databaseBuilder(
                    context, DTSAppDatabase.class, "dtsapp.db").allowMainThreadQueries().build();
        }

        return AppDbProvider.instance;
    }
    public static DTSAppDatabase getAsynchronousInstance(Context context)
    {
        if(AppDbProvider.asynchronousInstance == null)
        {
            AppDbProvider.asynchronousInstance = Room.databaseBuilder(
                    context, DTSAppDatabase.class, "dtsapp.db").build();
        }

        return AppDbProvider.asynchronousInstance;
    }
}
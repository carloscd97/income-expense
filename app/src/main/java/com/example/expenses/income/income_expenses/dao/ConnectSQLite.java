package com.example.expenses.income.income_expenses.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.expenses.income.income_expenses.database.MigrationsDataBase;

public class ConnectSQLite {

    public MigrationsDataBase connectDB;

    public ConnectSQLite(Context context){
        this.connectDB = new MigrationsDataBase(context);
    }

    protected SQLiteDatabase getInstaceWriteSQLite(){
        return this.connectDB.getWritableDatabase();
    }

    protected SQLiteDatabase getInstaceReadSQLite(){
        return this.connectDB.getReadableDatabase();
    }

}

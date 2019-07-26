package com.example.expenses.income.income_expenses.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MigrationsDataBase extends SQLiteOpenHelper {
    static String database = "IncomeExpense";
    static int version = 1;

    public MigrationsDataBase(Context context) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TableDataBase tables = new TableDataBase();
        db.execSQL(tables.users);
        db.execSQL(tables.typeIncome);
        db.execSQL(tables.tableIncome);
        db.execSQL(tables.tableTypeConcept);
        db.execSQL(tables.tableExpense);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS typeIncome");
        db.execSQL("DROP TABLE IF EXISTS conceptExpense");
        onCreate(db);
    }
}

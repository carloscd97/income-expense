package com.example.expenses.income.income_expenses.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.expenses.income.income_expenses.models.TypeIncomeModel;

import java.util.ArrayList;
import java.util.List;

public class TypeIncomeDAO extends ConnectSQLite {

    private static String NAME_TABLE = "typeIncome";

    public TypeIncomeDAO(Context context){
        super(context);

    }

    public TypeIncomeModel findById(Integer idTypeIncome){
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT idTypeIncome, name FROM '" + NAME_TABLE + "' WHERE idTypeIncome = " + idTypeIncome, null);
        if (cursor.moveToFirst()){
            return new TypeIncomeModel(cursor.getInt(0), cursor.getString(1));
        }
        return null;
    }

    public TypeIncomeModel findByName(String name){
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT idTypeIncome, name FROM '" + NAME_TABLE + "' WHERE name = '" + name + "'", null);
        if (cursor.moveToFirst()){
            return new TypeIncomeModel(cursor.getInt(0), cursor.getString(1));
        }
        return null;
    }

    public List<String> findAll(){
        List<String> typeIncomes = new ArrayList<String>();
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT name FROM '" + NAME_TABLE + "'", null);
        if (cursor.moveToFirst()){
            do{
                typeIncomes.add(cursor.getString(0));
            }while (cursor.moveToNext());
            getInstaceReadSQLite().close();
        }
        return typeIncomes;
    }

    public void save(TypeIncomeModel typeIncomeModel){
        if(getInstaceWriteSQLite() != null){
            insertInDataBase(typeIncomeModel);
        }
    }

    private void insertInDataBase(TypeIncomeModel typeIncomeModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", typeIncomeModel.getName());
        getInstaceWriteSQLite().insert(NAME_TABLE, null, contentValues);
        getInstaceWriteSQLite().close();
    }
}

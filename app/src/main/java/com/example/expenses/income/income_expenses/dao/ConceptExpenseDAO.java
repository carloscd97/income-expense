package com.example.expenses.income.income_expenses.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.example.expenses.income.income_expenses.models.ConceptExpenseModel;
import com.example.expenses.income.income_expenses.models.TypeIncomeModel;

import java.util.ArrayList;
import java.util.List;

public class ConceptExpenseDAO extends ConnectSQLite {

    private static String NAME_TABLE = "conceptExpense";

    public ConceptExpenseDAO(Context context){
        super(context);

    }

    public ConceptExpenseModel findById(Integer idConceptExpense){
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT idConceptExpense, name FROM '" + NAME_TABLE + "' WHERE idConceptExpense = " + idConceptExpense, null);
        if (cursor.moveToFirst()){
            return new ConceptExpenseModel(cursor.getInt(0), cursor.getString(1));
        }
        return null;
    }

    public ConceptExpenseModel findByName(String name){
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT idConceptExpense, name FROM '" + NAME_TABLE + "' WHERE name = '" + name + "'", null);
        if (cursor.moveToFirst()){
            return new ConceptExpenseModel(cursor.getInt(0), cursor.getString(1));
        }
        return null;
    }

    public List<String> findAll(){
        List<String> typesConceptExpense = new ArrayList<String>();
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT name FROM '" + NAME_TABLE + "'", null);
        if (cursor.moveToFirst()){
            do{
                typesConceptExpense.add(cursor.getString(0));
            }while (cursor.moveToNext());
            getInstaceReadSQLite().close();
        }
        return typesConceptExpense;
    }

    public void save(ConceptExpenseModel conceptExpenseModel){
        if(getInstaceWriteSQLite() != null){
            insertInDataBase(conceptExpenseModel);
        }
    }

    private void insertInDataBase(ConceptExpenseModel conceptExpenseModel){
       try{
           ContentValues contentValues = new ContentValues();
           contentValues.put("name", conceptExpenseModel.getName());
           getInstaceWriteSQLite().insert(NAME_TABLE, null, contentValues);
           getInstaceWriteSQLite().close();
       }catch (Exception e){

       }
    }
}

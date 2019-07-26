package com.example.expenses.income.income_expenses.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.expenses.income.income_expenses.models.ExpenseModel;

import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO extends ConnectSQLite {

    private static String NAME_TABLE = "expense";

    public ExpenseDAO(Context context){
        super(context);
    }

    public double getTotalMoneyForUser(Integer idUser, Integer idConceptExpense){
        double totalMoney = 0;
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT  amount FROM '" + NAME_TABLE + "' WHERE idUser = " + idUser + " AND idConceptExpense = " + idConceptExpense , null);
        if (cursor.moveToFirst()){
            do{
                totalMoney += Double.parseDouble(cursor.getString(0));
            }while (cursor.moveToNext());
            getInstaceReadSQLite().close();
        }
        return totalMoney;
    }

    public List<ExpenseModel> getTotalMoneyForConcept(Integer idUser){
        List<ExpenseModel> expenses = new ArrayList<>();
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT idConceptExpense, SUM(amount) FROM '" + NAME_TABLE + "' WHERE idUser = " + idUser + " GROUP BY idConceptExpense", null);
        if (cursor.moveToFirst()){
            do{
                expenses.add(new ExpenseModel(cursor.getInt(0), cursor.getDouble(1)));
            }while (cursor.moveToNext());
            getInstaceReadSQLite().close();
        }
        return expenses;
    }

    public void save(ExpenseModel expenseModel){
        if(getInstaceWriteSQLite() != null){
            insertInDataBase(expenseModel);
        }
    }

    private void insertInDataBase(ExpenseModel expenseModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", expenseModel.getAmount());
        contentValues.put("idTypeIncome", expenseModel.getIdTypeIncome());
        contentValues.put("idConceptExpense", expenseModel.getIdConceptExpense());
        contentValues.put("idUser", expenseModel.getIdUser());
        getInstaceWriteSQLite().insert(NAME_TABLE, null, contentValues);
        getInstaceWriteSQLite().close();
    }
}

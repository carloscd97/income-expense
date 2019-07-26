package com.example.expenses.income.income_expenses.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.expenses.income.income_expenses.models.IncomeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IncomeDAO extends ConnectSQLite {

    private static String NAME_TABLE = "income";

    public IncomeDAO(Context context){
        super(context);
    }

    public double getTotalMoneyForTypeIncome(int idUser, int idTypeIncome){
        double totalMoney = 0;
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT amount FROM '" + NAME_TABLE + "' WHERE idUser = " + idUser + " AND idTypeIncome = " + idTypeIncome, null);
        if (cursor.moveToFirst()){
            do{
               totalMoney += Double.parseDouble(cursor.getString(0));
            }while (cursor.moveToNext());
            getInstaceReadSQLite().close();
        }
        return totalMoney;
    }

    public List<IncomeModel> getIncomesForUser(int idUser){
        List<IncomeModel> incomes = new ArrayList<>();
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT idTypeIncome, SUM(amount) FROM '" + NAME_TABLE + "' WHERE idUser = " + idUser + " GROUP BY idTypeIncome", null);
        if (cursor.moveToFirst()){
            do{
                incomes.add(new IncomeModel(cursor.getInt(0), cursor.getDouble(1)));
            }while (cursor.moveToNext());
            getInstaceReadSQLite().close();
        }
        return incomes;
    }

    public void save(IncomeModel incomeModel){
        if(getInstaceWriteSQLite() != null){
            insertInDataBase(incomeModel);
        }
    }

    private void insertInDataBase(IncomeModel incomeModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", incomeModel.getAmount());
        contentValues.put("idTypeIncome", incomeModel.getIdTypeIncome());
        contentValues.put("idUser", incomeModel.getIdUser());
        getInstaceWriteSQLite().insert(NAME_TABLE, null, contentValues);
        getInstaceWriteSQLite().close();
    }
}

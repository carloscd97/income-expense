package com.example.expenses.income.income_expenses.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.example.expenses.income.income_expenses.models.UserModel;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends ConnectSQLite{

    private static String NAME_TABLE = "users";

    public UserDAO(Context context){
        super(context);
    }

    public UserModel existsUser(String email, String password){
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT idUser, email, password, name, surname FROM '" + NAME_TABLE + "' WHERE email LIKE '" + email + "' AND password LIKE '" + password + "'", null);
        if (cursor.moveToFirst()){
            return new UserModel(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
        }
        return null;
    }

    public UserModel findById(Integer idUser){
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT idUser, email, password, name, surname FROM '" + NAME_TABLE + "' WHERE idUser = " + idUser, null);
        if (cursor.moveToFirst()){
            return new UserModel(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
        }
        return null;
    }

    public UserModel findByEmail(String email){
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT idUser, email, password, name, surname FROM '" + NAME_TABLE + "' WHERE name = '" + email + "'", null);
        if (cursor.moveToFirst()){
            return new UserModel(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
        }
        return null;
    }

    public List<UserModel> findAll(){
        List<UserModel> users = new ArrayList<UserModel>();
        Cursor cursor = getInstaceReadSQLite().rawQuery("SELECT idUser, email, password, name, surname FROM '" + NAME_TABLE + "'", null);
        if (cursor.moveToFirst()){
            do{
                users.add(new UserModel(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)));
            }while (cursor.moveToNext());
            getInstaceReadSQLite().close();
        }
        return users;
    }

    public void save(UserModel userModel){
        if(getInstaceWriteSQLite() != null){
            insertInDataBase(userModel);
        }
    }

    private void insertInDataBase(UserModel userModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", userModel.getEmail());
        contentValues.put("password", userModel.getPassword());
        contentValues.put("name", userModel.getName());
        contentValues.put("surname", userModel.getSurname());
        getInstaceWriteSQLite().insert(NAME_TABLE, null, contentValues);
        getInstaceWriteSQLite().close();
    }
}

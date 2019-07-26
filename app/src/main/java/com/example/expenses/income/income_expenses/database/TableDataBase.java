package com.example.expenses.income.income_expenses.database;

public class TableDataBase {

    public String users = "CREATE TABLE users(idUser INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL, surname TEXT NOT NULL)";
    public String typeIncome = "CREATE TABLE typeIncome(idTypeIncome INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE NOT NULL)";
    public String tableIncome = "CREATE TABLE income(idIncome INTEGER PRIMARY KEY AUTOINCREMENT, amount DOUBLE NOT NULL, idTypeIncome INTEGER NOT NULL, idUser INTEGER NOT NULL)";
    public String tableTypeConcept = "CREATE TABLE conceptExpense(idConceptExpense INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE NOT NULL)";
    public String tableExpense = "CREATE TABLE expense(idExpense INTEGER PRIMARY KEY AUTOINCREMENT, amount DOUBLE NOT NULL, idTypeIncome INTEGER NOT NULL, idConceptExpense INTEGER NOT NULL, idUser INTEGER NOT NULL)";

    public TableDataBase(){}
}

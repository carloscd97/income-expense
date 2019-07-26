package com.example.expenses.income.income_expenses.models;

public class IncomeModel {

    private Integer idIncome;
    private double amount;
    private Integer idTypeIncome;
    private Integer idUser;

    public IncomeModel(double amount, Integer idTypeIncome, Integer idUser) {
        this.amount = amount;
        this.idTypeIncome = idTypeIncome;
        this.idUser = idUser;
    }

    public IncomeModel(Integer idIncome, double amount, Integer idTypeIncome, Integer idUser) {
        this.idIncome = idIncome;
        this.amount = amount;
        this.idTypeIncome = idTypeIncome;
        this.idUser = idUser;
    }

    public IncomeModel(Integer idTypeIncome, Double amount) {
        this.idTypeIncome = idTypeIncome;
        this.amount = amount;
    }

    public Integer getIdIncome() {
        return idIncome;
    }

    public void setIdIncome(Integer idIncome) {
        this.idIncome = idIncome;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getIdTypeIncome() {
        return idTypeIncome;
    }

    public void setIdTypeIncome(Integer idTypeIncome) {
        this.idTypeIncome = idTypeIncome;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}

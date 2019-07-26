package com.example.expenses.income.income_expenses.models;

public class ExpenseModel {

    private Integer idExpense;
    private Double amount;
    private Integer idTypeIncome;
    private Integer idConceptExpense;
    private Integer idUser;

    public ExpenseModel(Integer idExpense, Double amount, Integer idTypeIncome, Integer idConceptExpense, Integer idUser) {
        this.idExpense = idExpense;
        this.amount = amount;
        this.idTypeIncome = idTypeIncome;
        this.idConceptExpense = idConceptExpense;
        this.idUser = idUser;
    }

    public ExpenseModel(Double amount, Integer idTypeIncome, Integer idConceptExpense, Integer idUser) {
        this.amount = amount;
        this.idTypeIncome = idTypeIncome;
        this.idConceptExpense = idConceptExpense;
        this.idUser = idUser;
    }

    public ExpenseModel(Integer idConceptExpense, Double amount) {
        this.idConceptExpense = idConceptExpense;
        this.amount = amount;
    }

    public Integer getIdExpense() {
        return idExpense;
    }

    public void setIdExpense(Integer idExpense) {
        this.idExpense = idExpense;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getIdTypeIncome() {
        return idTypeIncome;
    }

    public void setIdTypeIncome(Integer idTypeIncome) {
        this.idTypeIncome = idTypeIncome;
    }

    public Integer getIdConceptExpense() {
        return idConceptExpense;
    }

    public void setIdConceptExpense(Integer idConceptExpense) {
        this.idConceptExpense = idConceptExpense;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}

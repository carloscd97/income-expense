package com.example.expenses.income.income_expenses.models;

public class TypeIncomeModel {

    private Integer idTypeIncome;
    private String name;

    public TypeIncomeModel(String name) {
        this.name = name;
    }

    public TypeIncomeModel(Integer idTypeIncome, String name) {
        this.idTypeIncome = idTypeIncome;
        this.name = name;
    }

    public Integer getIdTypeIncome() {
        return idTypeIncome;
    }

    public String getName() {
        return name;
    }

    public void setIdTypeIncome(Integer idTypeIncome) {
        this.idTypeIncome = idTypeIncome;
    }

    public void setName(String name) {
        this.name = name;
    }
}

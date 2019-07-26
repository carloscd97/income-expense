package com.example.expenses.income.income_expenses.models;

public class ConceptExpenseModel {

    private Integer idConceptExpense;
    private String name;

    public ConceptExpenseModel(String name) {
        this.name = name;
    }

    public ConceptExpenseModel(Integer idConceptExpense, String name) {
        this.idConceptExpense = idConceptExpense;
        this.name = name;
    }

    public Integer getIdConceptExpense() {
        return idConceptExpense;
    }

    public void setIdConceptExpense(Integer idConceptExpense) {
        this.idConceptExpense = idConceptExpense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.example.expenses.income.income_expenses;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.expenses.income.income_expenses.dao.ConceptExpenseDAO;
import com.example.expenses.income.income_expenses.dao.IncomeDAO;
import com.example.expenses.income.income_expenses.models.ConceptExpenseModel;
import com.example.expenses.income.income_expenses.validations.CoreValidator;

public class ConceptExpense extends CoreValidator {

    EditText conceptExpense;
    final ConceptExpenseDAO conceptExpenseDAO = new ConceptExpenseDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concept_expense);
        setTitle("Concepto de Egreso");
        conceptExpense = findViewById(R.id.conceptExpense);

    }

    public void saveConceptExpense(View view){
        if(inputRequired(conceptExpense)){
            ConceptExpenseModel conceptExpenseModel = new ConceptExpenseModel(conceptExpense.getText().toString());
            conceptExpenseDAO.save(conceptExpenseModel);
            redirectToReport();
        }
    }
}

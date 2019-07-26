package com.example.expenses.income.income_expenses;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.expenses.income.income_expenses.dao.ConceptExpenseDAO;
import com.example.expenses.income.income_expenses.dao.ExpenseDAO;
import com.example.expenses.income.income_expenses.dao.IncomeDAO;
import com.example.expenses.income.income_expenses.dao.TypeIncomeDAO;
import com.example.expenses.income.income_expenses.models.ExpenseModel;
import com.example.expenses.income.income_expenses.validations.CoreValidator;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;

public class Expense extends CoreValidator {

    private int idTypeIncome = 0;
    private int idConceptExpense = 0;
    TextView balanceExpense;
    EditText typeIncome;
    EditText conceptExpense;
    EditText amountExpense;
    ArrayAdapter<String> selectedTypeIncome = null;
    ArrayAdapter<String> selectedConceptExpense = null;
    final IncomeDAO incomeDAO = new IncomeDAO(this);
    final TypeIncomeDAO typeIncomeDAO = new TypeIncomeDAO(this);
    final ConceptExpenseDAO conceptExpenseDAO = new ConceptExpenseDAO(this);
    final ExpenseDAO expenseDAO = new ExpenseDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        setTitle("Egresos");
        initData();
    }

    private void initData(){
        balanceExpense = findViewById(R.id.balanceExpense);
        typeIncome = findViewById(R.id.typeIncome);
        conceptExpense = findViewById(R.id.conceptExpense);
        amountExpense = findViewById(R.id.amountExpense);
        selectedTypeIncome = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        selectedTypeIncome.addAll(typeIncomeDAO.findAll());
        selectedConceptExpense = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        selectedConceptExpense.addAll(conceptExpenseDAO.findAll());
        balanceExpense.setTextColor(getResources().getColor(R.color.green));
    }

    public void selectedTypeIncome(View view){
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        //builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Tipo de Ingreso: ");
        builderSingle.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(selectedTypeIncome, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = selectedTypeIncome.getItem(which);
                typeIncome.setText(strName);
                idTypeIncome = typeIncomeDAO.findByName(strName).getIdTypeIncome();
                Double disponibility = incomeDAO.getTotalMoneyForTypeIncome(getIdUser(), idTypeIncome) - expenseDAO.getTotalMoneyForUser(getIdUser(), idConceptExpense);
                balanceExpense.setText("S/. " + Math.ceil(disponibility));
                if(disponibility <= 0){
                    balanceExpense.setTextColor(getResources().getColor(R.color.red));
                }else{
                    balanceExpense.setTextColor(getResources().getColor(R.color.green));
                }
                dialog.dismiss();
            }
        });
        builderSingle.show();
    }

    public void selectedConceptExpense(View view){
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(this);
        //builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Tipo de Egreso: ");
        builderSingle.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(selectedConceptExpense, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = selectedConceptExpense.getItem(which);
                conceptExpense.setText(strName);
                idConceptExpense = conceptExpenseDAO.findByName(strName).getIdConceptExpense();
                Double disponibility = incomeDAO.getTotalMoneyForTypeIncome(getIdUser(), idTypeIncome) - expenseDAO.getTotalMoneyForUser(getIdUser(), idConceptExpense);
                balanceExpense.setText("S/. " + Math.ceil(disponibility));
                if(disponibility <= 0){
                    balanceExpense.setTextColor(getResources().getColor(R.color.red));
                }else{
                    balanceExpense.setTextColor(getResources().getColor(R.color.green));
                }
                dialog.dismiss();
            }
        });
        builderSingle.show();
    }

    public void saveExpense(View view){
        boolean requiredValidation = inputRequired(typeIncome) && inputRequired(conceptExpense) && inputRequired(amountExpense);
        boolean amountValidation = numberMajorToZero(amountExpense) && idTypeIncome > 0 && idConceptExpense > 0;
        if(requiredValidation && amountValidation){
            Double disponibility = incomeDAO.getTotalMoneyForTypeIncome(getIdUser(), idTypeIncome) - expenseDAO.getTotalMoneyForUser(getIdUser(), idConceptExpense);
            if(Math.ceil(disponibility) < Double.parseDouble(amountExpense.getText().toString())){
                Snackbar.make(view, "El monto es mayor a sus ingreso disponible", Snackbar.LENGTH_LONG).show();
                return;
            }
            ExpenseModel expenseModel = new ExpenseModel(Double.parseDouble(amountExpense.getText().toString()), idTypeIncome, idConceptExpense, getIdUser());
            expenseDAO.save(expenseModel);
            redirectToReport();
        }
    }



}

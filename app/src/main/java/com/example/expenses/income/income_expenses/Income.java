package com.example.expenses.income.income_expenses;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import com.example.expenses.income.income_expenses.dao.IncomeDAO;
import com.example.expenses.income.income_expenses.dao.TypeIncomeDAO;
import com.example.expenses.income.income_expenses.models.IncomeModel;
import com.example.expenses.income.income_expenses.validations.CoreValidator;
import androidx.appcompat.app.AlertDialog;

public class Income extends CoreValidator {

    private int idTypeIncome;
    EditText typeIncome;
    EditText amountIncome;
    ArrayAdapter<String> arrayAdapter = null;
    final TypeIncomeDAO typeIncomeDAO = new TypeIncomeDAO(this);
    final IncomeDAO incomeDAO = new IncomeDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        setTitle("Ingresos");
        initData();
    }

    private void initData(){
        typeIncome = findViewById(R.id.typeIncome);
        amountIncome = findViewById(R.id.amountIncome);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        arrayAdapter.addAll(typeIncomeDAO.findAll());
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

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                typeIncome.setText(strName);
                idTypeIncome = typeIncomeDAO.findByName(strName).getIdTypeIncome();
                dialog.dismiss();
            }
        });
        builderSingle.show();
    }

    public void saveIncome(View view){
        boolean requiredValidation = inputRequired(typeIncome) && inputRequired(amountIncome);
        boolean amountValidation = numberMajorToZero(amountIncome) && idTypeIncome > 0;
        if(requiredValidation && amountValidation){
            IncomeModel incomeModel = new IncomeModel(Double.parseDouble(amountIncome.getText().toString()), idTypeIncome, getIdUser());
            incomeDAO.save(incomeModel);
            redirectToReport();
        }
    }
}

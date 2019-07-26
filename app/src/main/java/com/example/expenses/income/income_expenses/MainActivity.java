package com.example.expenses.income.income_expenses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.expenses.income.income_expenses.dao.TypeIncomeDAO;
import com.example.expenses.income.income_expenses.dao.UserDAO;
import com.example.expenses.income.income_expenses.models.TypeIncomeModel;
import com.example.expenses.income.income_expenses.models.UserModel;
import com.example.expenses.income.income_expenses.validations.CoreValidator;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CoreValidator {

    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Administrador de Ingresos y Egresos");
        closeOptionsMenu();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        if (isSignIn()){
            Intent intent = new Intent(this, Report.class);
            startActivity(intent);
            finish();
        }
        initData();
    }

    private void initData(){
        TypeIncomeDAO typeIncomeDAO = new TypeIncomeDAO(this);
        List<TypeIncomeModel> typesIncomes = new ArrayList<>();
        typesIncomes.add(new TypeIncomeModel("Sueldo"));
        typesIncomes.add(new TypeIncomeModel("Ahorros"));
        typesIncomes.add(new TypeIncomeModel("Beneficios"));
        typesIncomes.add(new TypeIncomeModel("Empresa"));
        typesIncomes.add(new TypeIncomeModel("Pensión"));
        typesIncomes.add(new TypeIncomeModel("Tienda"));
        for (TypeIncomeModel typeIncome: typesIncomes) {
            typeIncomeDAO.save(typeIncome);
        }
        UserDAO userDAO = new UserDAO(this);
        List<UserModel> users = new ArrayList<>();
        users.add(new UserModel("cdiazc15@unc.edu.pe", "carlos", "Carlos", "Díaz Chávez"));
        users.add(new UserModel("ediazm14@unc.edu.pe", "emerson", "Emerson", "Díaz Mejía"));
        for (UserModel user: users) {
            userDAO.save(user);
        }
    }

    public void signIn(View view){
        boolean validations = inputRequired(email) && inputEmail(email) && inputRequired(password);
        if (validations){
            UserModel userModel = validateUser();
            if(userModel != null){
                Intent intent = new Intent(this, Report.class);
                startActivity(intent);
                setCurrentUser(userModel);
                finish();
            }else{
                Snackbar.make(view, "Credenciales incorrectas", Snackbar.LENGTH_INDEFINITE).show();
            }
        }
    }

    private UserModel validateUser(){
        UserDAO userDAO = new UserDAO(this);
        return userDAO.existsUser(email.getText().toString(), password.getText().toString());
    }
}

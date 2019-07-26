package com.example.expenses.income.income_expenses.validations;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.service.autofill.RegexValidator;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.expenses.income.income_expenses.ConceptExpense;
import com.example.expenses.income.income_expenses.Expense;
import com.example.expenses.income.income_expenses.Income;
import com.example.expenses.income.income_expenses.MainActivity;
import com.example.expenses.income.income_expenses.R;
import com.example.expenses.income.income_expenses.Report;
import com.example.expenses.income.income_expenses.models.UserModel;

import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class CoreValidator extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_generic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.income:
                Intent intent = new Intent(this, Income.class);
                startActivity(intent);
                break;
            case R.id.concept_expenses:
                intent = new Intent(this, ConceptExpense.class);
                startActivity(intent);
                break;
            case R.id.expenses:
                intent = new Intent(this, Expense.class);
                startActivity(intent);
                break;
            case R.id.report:
                intent = new Intent(this, Report.class);
                startActivity(intent);
                break;
            case R.id.logout:
                logout();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void redirectToReport(){
        Intent intent = new Intent(this, Report.class);
        startActivity(intent);
        finish();
    }

    public boolean numberMajorToZero(EditText editText){
        String amountText = editText.getText().toString();
        try{
            double amount = Double.parseDouble(amountText);
            if(amount <= 0){
                editText.setError("El monto tiene que ser mayor.");
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public  boolean inputRequired(EditText editText){
        String text = editText.getText().toString();
        if (text.isEmpty()){
            editText.setError("El campo es requerido.");
            return false;
        }
        return true;
    }

    public  boolean inputEmail(EditText editText){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        String text = editText.getText().toString();
        if (!pattern.matcher(text).matches()){
            editText.setError("Email incorrecto.");
            return false;
        }
        return true;
    }

    public void logout(){
        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isAuth", false);
        editor.putInt("idUser", 0);
        editor.putString("email", null);
        editor.putString("password", null);
        editor.putString("name", null);
        editor.putString("surname", null);
        editor.commit();
    }

    public Integer getIdUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("idUser", 0);
    }

    public void setCurrentUser(UserModel userModel){
        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isAuth", true);
        editor.putInt("idUser", userModel.getIdUser());
        editor.putString("email", userModel.getEmail());
        editor.putString("password", userModel.getPassword());
        editor.putString("name", userModel.getName());
        editor.putString("surname", userModel.getSurname());
        editor.commit();
    }

    public boolean isSignIn(){
        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isAuth", false);
    }

    public UserModel getCurrentUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        return new UserModel(
                sharedPreferences.getInt("idUser", 0),
                sharedPreferences.getString("email", ""),
                sharedPreferences.getString("password", ""),
                sharedPreferences.getString("name", ""),
                sharedPreferences.getString("surname", "")
        );
    }
}

package com.example.expenses.income.income_expenses;

import android.os.Bundle;

import com.example.expenses.income.income_expenses.dao.ConceptExpenseDAO;
import com.example.expenses.income.income_expenses.dao.ExpenseDAO;
import com.example.expenses.income.income_expenses.dao.IncomeDAO;
import com.example.expenses.income.income_expenses.dao.TypeIncomeDAO;
import com.example.expenses.income.income_expenses.models.ExpenseModel;
import com.example.expenses.income.income_expenses.models.IncomeModel;
import com.example.expenses.income.income_expenses.models.TypeIncomeModel;
import com.example.expenses.income.income_expenses.models.UserModel;
import com.example.expenses.income.income_expenses.validations.CoreValidator;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Report extends CoreValidator {


    private BarChart graph;
    private PieChart pieChart;

    private IncomeDAO incomeDAO = new IncomeDAO(this);
    private TypeIncomeDAO typeIncomeDAO = new TypeIncomeDAO(this);
    private ExpenseDAO expenseDAO = new ExpenseDAO(this);
    private ConceptExpenseDAO conceptExpenseDAO = new ConceptExpenseDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        UserModel userModel = getCurrentUser();
        setTitle(userModel.getName() + " " + userModel.getSurname());
        init();
    }

    private void init(){
        initBarChart();
        initPieChart();
    }

    private void initBarChart(){
        ArrayList<String> barChartX = new ArrayList<>();
        ArrayList<BarEntry> barChartY = new ArrayList<>();
        graph = findViewById(R.id.graph);
        List<IncomeModel> incomes = incomeDAO.getIncomesForUser(getIdUser());
        for (int i = 0; i < incomes.size(); i++){
            IncomeModel incomeModel = incomes.get(i);
            TypeIncomeModel typeIncomeModel = typeIncomeDAO.findById(incomeModel.getIdTypeIncome());
            barChartY.add(new BarEntry(i, (float) incomeModel.getAmount()));
            barChartX.add(typeIncomeModel.getName());
        }
        loadDataBarChart(barChartX, barChartY);
    }

    private void loadDataBarChart(ArrayList<String> barChartX, ArrayList<BarEntry> barChartY){
        BarDataSet barsY = new BarDataSet(barChartY, "Tipo de Ingreso");
        BarData barData = new BarData(barsY);
        graph.setData(barData);
        graph.getDescription().setText("Ingresos");
        graph.getDescription().setPosition(350,20);
        graph.getDescription().setTextSize(12);
        EjeX(graph.getXAxis(), barChartX);
        EjeYDerecha(graph.getAxisRight());
        EjeY(graph.getAxisLeft());
    }

    private void EjeY(YAxis y){
        y.setSpaceTop(30);
        y.setAxisMaximum(0);
    }

    private void EjeYDerecha(YAxis y){
        y.setEnabled(false);
    }

    private void EjeX(XAxis x, ArrayList<String> barChartX){
        x.setGranularityEnabled(true);
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setValueFormatter(new IndexAxisValueFormatter(barChartX));
    }

    private void initPieChart(){
        pieChart = findViewById(R.id.chartpie);
        List<PieEntry> entriespie = new ArrayList<>();
        List<ExpenseModel> expenses = expenseDAO.getTotalMoneyForConcept(getIdUser());
        for (ExpenseModel expenseModel: expenses){
            entriespie.add(new PieEntry(Float.parseFloat(expenseModel.getAmount().toString()), conceptExpenseDAO.findById(expenseModel.getIdConceptExpense()).getName()));
        }
        PieDataSet set = new PieDataSet(entriespie, "Egresos");
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate();
    }

}

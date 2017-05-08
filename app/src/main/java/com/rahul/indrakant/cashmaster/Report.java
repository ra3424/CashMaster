package com.rahul.indrakant.cashmaster;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Report extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    Helper helper;
    private ArrayList<Entry> LineEntry;
    ArrayList<String> labels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        spinner = (Spinner) findViewById(R.id.report_dropdown);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<>();
        categories.add("Select Duration");
        categories.add("Weekly");
        categories.add("Monthly");
        categories.add("Quaterly");
        categories.add("Half Yearly");
        categories.add("Yearly");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        helper=new Helper(this,LoginActivity.loginid);

    }




    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Cursor c;
        String[] category={"Food","Education","Personal","Travel","Misc"};
        String cat,price;
        float sum;
        float sumx[] = new float[5]; // array contains value of sum of data all categories
        float total=0; //contains total sum

        if(adapterView.getSelectedItemPosition()!=0)
        {

            switch (adapterView.getSelectedItemPosition()) {

                case 1:
                    c = helper.expense(7);
                    for (int x = 0; x < 5; x++) {
                        sum = 0;

                        if (c.moveToFirst()) {
                            do {
                                cat = c.getString(2);
                                price = c.getString(3);
                                if (cat.equals(category[x])) {
                                    sum += Float.parseFloat(price);
                                }

                            }
                            while (c.moveToNext());

                        }
                        total += sum;
                        sumx[x] = sum;
                    }
                    break;
                case 2:
                    c = helper.expense(30);
                    for (int x = 0; x < 5; x++) {
                        sum = 0;

                        if (c.moveToFirst()) {
                            do {
                                cat = c.getString(2);
                                price = c.getString(3);
                                if (cat.equals(category[x])) {
                                    sum += Float.parseFloat(price);
                                }

                            }
                            while (c.moveToNext());

                        }
                        total += sum;
                        sumx[x] = sum;
                    }

                    break;

                case 3:
                    c = helper.expense(120);
                    for (int x = 0; x < 5; x++) {
                        sum = 0;

                        if (c.moveToFirst()) {
                            do {
                                cat = c.getString(2);
                                price = c.getString(3);
                                if (cat.equals(category[x])) {
                                    sum += Float.parseFloat(price);
                                }

                            }
                            while (c.moveToNext());

                        }
                        total += sum;
                        sumx[x] = sum;
                    }

                    break;
                case 4:
                    c = helper.expense(182);
                    for (int x = 0; x < 5; x++) {
                        sum = 0;

                        if (c.moveToFirst()) {
                            do {
                                cat = c.getString(2);
                                price = c.getString(3);
                                if (cat.equals(category[x])) {
                                    sum += Float.parseFloat(price);
                                }

                            }
                            while (c.moveToNext());

                        }
                        total += sum;
                        sumx[x] = sum;
                    }

                    break;
                case 5:
                    c = helper.expense(365);
                    for (int x = 0; x < 5; x++) {
                        sum = 0;

                        if (c.moveToFirst()) {
                            do {
                                cat = c.getString(2);
                                price = c.getString(3);
                                if (cat.equals(category[x])) {
                                    sum += Float.parseFloat(price);
                                }

                            }
                            while (c.moveToNext());

                        }
                        total += sum;
                        sumx[x] = sum;
                    }

                    break;

            }

            labels = new ArrayList<>();

            LineEntry = new ArrayList<>();
            for (int ind=0;ind<5;ind++)
            {
                if (sumx[ind]!=0)
                {
                    LineEntry.add(new Entry(sumx[ind], ind));
                    labels.add(category[ind]);
                }

            }

            PieDataSet dataSet=new PieDataSet(LineEntry,"Expenses");

            PieChart chart = new PieChart(Report.this);
            setContentView(chart);


            PieData data = new PieData(labels,dataSet);

            dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
            chart.setData(data);
            chart.setDescriptionTextSize(20);
            chart.setCenterText("Total Expense:\n"+String.valueOf(total));
            chart.setCenterTextSize(20);
            chart.setCenterTextColor(Color.GRAY);
            chart.setDescription("Report for "+adapterView.getSelectedItem()+" ");
            chart.animateY(1000);


        }
        else
        {
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return  true;
    }

    public  boolean onOptionsItemSelected(MenuItem menuItem)
    {
        Intent intent;
        switch (menuItem.getItemId())
        {
            case R.id.action_settings:
                intent=new Intent(this,Menu_Settings.class);
                startActivity(intent);
                break;
            case R.id.action_About:
                intent=new Intent(this,AboutUs.class);
                startActivity(intent);
                break;
            case R.id.action_help:
                intent=new Intent(this,Feedback.class);
                startActivity(intent);
                break;
            case R.id.action_signOut:
                LoginActivity.loginid="";
                Intent i=new Intent(this,LoginActivity.class);
                startActivity(i);
                finish();
                Toast.makeText(Report.this, "Sign Out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_Exit:
                System.exit(0);
                break;
        }
        return  true;
    }

}

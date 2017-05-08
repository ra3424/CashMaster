package com.rahul.indrakant.cashmaster;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Expense extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        spinner = (Spinner) findViewById(R.id.expenses_dropdown);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
/*        String item = adapterView.getItemAtPosition(i).toString();
        if (i>0)
            Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        else Toast.makeText(this, "Select a Category", Toast.LENGTH_SHORT).show();
        */
        TextView t[] = new TextView[5];
        TextView text = (TextView) findViewById(R.id.textView);

        t[0] = (TextView) findViewById(R.id.editText);
        t[1] = (TextView) findViewById(R.id.editText2);
        t[2] = (TextView) findViewById(R.id.editText3);
        t[3] = (TextView) findViewById(R.id.editText4);
        t[4] = (TextView) findViewById(R.id.editText5);
        Cursor c;
        String[] category = {"Food", "Education", "Personal", "Travel", "Misc"};
        String cat, price;
        float sum;
        float total = 0;
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
                        t[x].setText(String.valueOf(sum));
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

                        t[x].setText(String.valueOf(sum));
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

                        t[x].setText(String.valueOf(sum));
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
                        t[x].setText(String.valueOf(sum));
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
                        t[x].setText(String.valueOf(sum));
                    }

                    break;

            }


            text.setText("Total Expenses : Rs. " + String.valueOf(total));

        }
        else
        {
            for (int ind=0;ind<5;ind++)
            {
                t[ind].setText(String.valueOf(0.0));
            }
            text.setText("Please Select Duration First To view Expenses");
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
                Toast.makeText(Expense.this, "Sign Out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_Exit:
                System.exit(0);
                break;
        }
        return  true;
    }

}

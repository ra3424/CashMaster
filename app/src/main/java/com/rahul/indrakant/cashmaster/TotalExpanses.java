package com.rahul.indrakant.cashmaster;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class TotalExpanses extends AppCompatActivity{


    private int pYear;
    private int pMonth;
    private int pDay;
    static final int DATE_DIALOG_ID = 0;
    TextView textView;
    HelperGroup helperGroup;
    ListView view;
    ArrayList<String> array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_expanses);
        textView=(TextView) findViewById(R.id.viewEntry_datePicker);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_DIALOG_ID);


            }
        });


        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
        /** Display the current date in the TextView */
       // updateDisplay();


    }


   public void displayData(){

       EditText edt = (EditText) findViewById(R.id.noOfMembers);
       float number=0;
       try{
           number = Float.valueOf(edt.getText().toString());
       } catch (NumberFormatException e) {
           e.printStackTrace();
       }

        String dt=textView.getText().toString();
        array = new ArrayList<>();
        float sum=0;
        helperGroup=new HelperGroup(this,LoginActivity.loginid);
        Cursor c = helperGroup.disp(dt);
        if (c.moveToFirst()) {
            do {
                array.add("Cash Spent = "+c.getString(0)+"\n Item Name: "+c.getString(1));
                sum=sum+ Float.parseFloat(c.getString(0));
            } while (c.moveToNext());
        }

        TextView tv=(TextView) findViewById(R.id.viewEntry_total);
        tv.setText("Total Group Expenses : Rs " + String.valueOf(sum));
        TextView textView=(TextView)findViewById(R.id.viewEntry_individual);
        if (number==0){
            Toast.makeText(TotalExpanses.this,"Please Select Date",Toast.LENGTH_SHORT).show();
        }
       else
        textView.setText("Individual Exp = " + sum/number);

        view = (ListView) findViewById(R.id.viewEntry_listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.viewentry, R.id.list, array);
        view.setAdapter(adapter);
    }

    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;
                    updateDisplay();

                }
            };

    private void updateDisplay() {
        if(pMonth+1 <10 && pDay<10)
            textView.setText(
                    new StringBuilder().append(pYear).append("-0").append(pMonth + 1).append("-0").append(pDay).append(""));
        else if(pMonth+1 <10)
            textView.setText(
                    new StringBuilder().append(pYear).append("-0").append(pMonth + 1).append("-").append(pDay).append(""));
        else if( pDay<10)
            textView.setText(
                    new StringBuilder().append(pYear).append("-").append(pMonth + 1).append("-0").append(pDay).append(""));
        else
            textView.setText(
                    new StringBuilder().append(pYear).append("-").append(pMonth + 1).append("-").append(pDay).append(""));

        displayData();

    }



    /** Create a new dialog for date picker */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                DatePickerDialog datePickerDialog= new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                return datePickerDialog;
        }
        return null;
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
                Toast.makeText(TotalExpanses.this, "Sign Out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_Exit:
                System.exit(0);
                break;
        }
        return  true;
    }

}


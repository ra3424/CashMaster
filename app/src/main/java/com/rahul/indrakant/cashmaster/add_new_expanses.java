package com.rahul.indrakant.cashmaster;


    import android.app.DatePickerDialog;
    import android.app.Dialog;
    import android.content.Intent;
    import android.database.sqlite.SQLiteDatabase;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.DatePicker;
    import android.widget.Spinner;
    import android.widget.TextView;
    import android.widget.Toast;

    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.List;

    public class add_new_expanses extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener {
        private int pYear;
        private int pMonth;
        private int pDay;
        static final int DATE_DIALOG_ID = 0;
        Spinner spinner;
        private HelperGroup helpergroup;
        private BeanGroup beanGroup;
        TextView t1,t2,t4;
        Button button;
        TextView textView;

        SQLiteDatabase db;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_new_expanses);



            spinner=(Spinner) findViewById(R.id.NE_dropDown);
            spinner.setOnItemSelectedListener(this);

            List<String> categories = new ArrayList<>();
            categories.add("Select Category");
            categories.add("Food");
            categories.add("Education");
            categories.add("Personal");
            categories.add("Travel");
            categories.add("Misc");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);


            button=(Button) findViewById(R.id.NE_button);
            button.setOnClickListener(this);

            textView=(TextView) findViewById(R.id.NE_datePicker);
            textView.setOnClickListener(this);

            final Calendar cal = Calendar.getInstance();
            pYear = cal.get(Calendar.YEAR);
            pMonth = cal.get(Calendar.MONTH);
            pDay = cal.get(Calendar.DAY_OF_MONTH);
            /** Display the current date in the TextView */
            updateDisplay();

            t1=(TextView) findViewById(R.id.NE_editText1);
            t2=(TextView) findViewById(R.id.NE_editText2);

            helpergroup=new HelperGroup(this,LoginActivity.loginid);

        }

        public boolean validate()
        {
            if(t1.getText().toString().equals("") || t2.getText().toString().equals(" ") || spinner.getSelectedItem().toString().equals("Select Category"))
                return  false;
            return true;
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        }



        @Override
        public void onClick(View view) {
            if ((view.getId()==R.id.NE_button))
            {
                beanGroup=new BeanGroup();
                if(validate()) {
                    beanGroup.setName(t1.getText().toString());
                    beanGroup.setPrice(Float.parseFloat(t2.getText().toString()));
                    beanGroup.setCategory(spinner.getSelectedItem().toString());

                    String dt=textView.getText().toString();
                    beanGroup.setDate(dt);

                    String s = helpergroup.save(beanGroup);
                    this.finish();
                    Intent intent = new Intent(this,add_new_expanses.class);
                    startActivity(intent);
                    Toast.makeText(add_new_expanses.this,"Data Saved",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(com.rahul.indrakant.cashmaster.add_new_expanses.this," All fields must be filled", Toast.LENGTH_SHORT).show();


            }

            if (view.getId()==R.id.NE_datePicker)
            {
                showDialog(DATE_DIALOG_ID);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
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
                    Toast.makeText(com.rahul.indrakant.cashmaster.add_new_expanses.this, "Sign Out", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_Exit:
                    System.exit(0);
                    break;
            }
            return  true;
        }


    }


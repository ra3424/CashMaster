package com.rahul.indrakant.cashmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity  implements View.OnClickListener{
    Button b[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        b=new Button[5];
        b[0]=(Button) findViewById(R.id.button1);
        b[1]=(Button) findViewById(R.id.button2);
        b[2]=(Button) findViewById(R.id.button3);
        b[3]=(Button) findViewById(R.id.button4);
        b[4]=(Button) findViewById(R.id.button5);

        for(int i=0;i<5;i++)
            b[i].setOnClickListener(this);

        TextView textView=(TextView) findViewById(R.id.dislpay_username);
        textView.setText("Hello "+ LoginActivity.uid);
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId())
        {
            case R.id.button1:
                intent=new Intent(getApplicationContext(),NewEntry.class);
                startActivity(intent);
                break;

            case R.id.button2:
                intent=new Intent(getApplicationContext(),ViewEntry.class);
                startActivity(intent);
                break;

            case R.id.button3:
                intent=new Intent(getApplicationContext(),Expense.class);
                startActivity(intent);
                break;

            case R.id.button4:
                intent=new Intent(getApplicationContext(),Report.class);
                startActivity(intent);
                break;
            case R.id.button5:
                intent=new Intent(getApplicationContext(),Group.class);
                startActivity(intent);
                break;

        }

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
                Toast.makeText(HomeActivity.this, "Sign Out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_Exit:
                System.exit(0);
                break;
        }
        return  true;
    }

}

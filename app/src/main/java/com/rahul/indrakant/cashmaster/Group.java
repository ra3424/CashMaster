package com.rahul.indrakant.cashmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Group extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

    }

    public void add(View view){
        Intent intent = new Intent(this,add_new_expanses.class);
        startActivity(intent);

    }

    public void view(View view){
        Intent intent = new Intent(this,TotalExpanses.class);
        startActivity(intent);

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
                Toast.makeText(Group.this, "Sign Out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_Exit:
                System.exit(0);
                break;
        }
        return  true;
    }

}

package com.rahul.indrakant.cashmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }
    public void submit(View view) {
        TextView textView = (TextView) findViewById(R.id.editText6);
        if (!textView.getText().toString().equals(""))

        {
            Toast.makeText(Feedback.this, "Feedback has been Sent", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
            finish();
        }
        else Toast.makeText(Feedback.this, "Please enter valid feedback", Toast.LENGTH_SHORT).show();
    }
}

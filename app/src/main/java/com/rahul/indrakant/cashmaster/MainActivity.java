package com.rahul.indrakant.cashmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent main = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(main);
                    MainActivity.this.finish();
                }
            }, 2500);


    }
}

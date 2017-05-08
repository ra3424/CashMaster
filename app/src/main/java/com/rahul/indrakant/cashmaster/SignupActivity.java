package com.rahul.indrakant.cashmaster;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private Bean beanClass;
    private HelperLogin helper;
    TextView t1, t2, t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        try {
//    beanClass = new Bean();
            t1 = (TextView) findViewById(R.id.et1);
            t2 = (TextView) findViewById(R.id.et2);
            t3 = (TextView) findViewById(R.id.et3);


            Button b=(Button)  findViewById(R.id.button_signup);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    beanClass = new Bean();
                    String n = t1.getText().toString();
                    String p = t2.getText().toString();
                    String pass = t3.getText().toString();
                    if (!n.equals("") && !p.equals("") && !pass.equals("")) {
                        if(!p.contains(".")) {
                            if (p.length() == 10) {
                                if (pass.length() >= 5) {

                                        beanClass.setName(n);
                                        beanClass.setPhone(p);
                                        beanClass.setPassword(pass);
                                        helper = new HelperLogin(getApplicationContext());
                                    Cursor c = helper.disp(beanClass);
                                    if(!c.moveToFirst())
                                    {
                                        String s = helper.save(beanClass);
                                        Toast.makeText(SignupActivity.this, s, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(SignupActivity.this, "User already registered", Toast.LENGTH_SHORT).show();
                                        t1.setText("");
                                        t2.setText("");
                                        t3.setText("");
                                    }
                                    }
                                else
                                    Toast.makeText(SignupActivity.this, "Password must be 5 char long", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(SignupActivity.this,"Phone no. must be 10 digits", Toast.LENGTH_LONG).show();
                        }
                        else Toast.makeText(SignupActivity.this,"Invalid Phone Number", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(SignupActivity.this, "Fill in all credentials", Toast.LENGTH_SHORT).show();
                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}

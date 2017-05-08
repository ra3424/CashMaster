package com.rahul.indrakant.cashmaster;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{
    Button b;
    TextView t1,t2;
public static String loginid;
    public static String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t1=(TextView) findViewById(R.id.et1);
        t2=(TextView) findViewById(R.id.et2);
        b=(Button) findViewById(R.id.button_login);
        b.setOnClickListener(this);
        Button b1=(Button)  findViewById(R.id.button_signin);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Toast.makeText(Activity2.this, "Sign in", Toast.LENGTH_SHORT).show();

                Intent i=new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

            @Override
            public void onClick(View view) {
            if(view.getId()==R.id.button_login) {
            Bean b = new Bean();
            if(!t1.getText().toString().equals("") && !t2.getText().toString().equals(""))
            {
                b.setPhone(t1.getText().toString());
                HelperLogin h = new HelperLogin(this);


                Cursor c = h.disp(b);
                if (c.moveToFirst()) {
                    if (t2.getText().toString().equals(c.getString(0))) {
                        loginid="u"+t1.getText().toString();
                        uid=c.getString(1);
                        Toast.makeText(LoginActivity.this, "Logged In as "+uid, Toast.LENGTH_SHORT).show();
                        //create session
                        Intent intent = new Intent(this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else

                        Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(LoginActivity.this, "User not Registered", Toast.LENGTH_SHORT).show();
            }
                else Toast.makeText(LoginActivity.this, " Fill in Required fields", Toast.LENGTH_SHORT).show();
        }



    }
}

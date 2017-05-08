package com.rahul.indrakant.cashmaster;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Menu_Settings extends AppCompatActivity {

    TextView t1,t2,t3;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__settings);

        t1=(TextView) findViewById(R.id.settings_et1);
        t2=(TextView) findViewById(R.id.settings_et2);
        t3=(TextView) findViewById(R.id.settings_et3);

        button=(Button) findViewById(R.id.settings_b);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old = t1.getText().toString();
                String pnew = t2.getText().toString();
                String pconf = t3.getText().toString();
                Bean b = new Bean();
                b.setPhone(LoginActivity.loginid.substring(1, 11));
                HelperLogin h = new HelperLogin(getApplicationContext());
                Cursor c = h.disp(b);
                String userold="";
                if (c.moveToFirst()) {
                    userold = c.getString(0);
                }

                if (old.equals(userold)) {
                    if (pnew.equals(pconf)) {
                        if(pconf.length()>=8)
                        {
                            String s = h.updt(LoginActivity.loginid.substring(1, 11), pnew);
                            Toast.makeText(Menu_Settings.this, s, Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(Menu_Settings.this, HomeActivity.class);
                            startActivity(in);
                            finish();
                        }else Toast.makeText(Menu_Settings.this, "Pass word must be atleast 8 char", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(Menu_Settings.this, "Passwords Donot Match", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(Menu_Settings.this, "Enter Correct old pass", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

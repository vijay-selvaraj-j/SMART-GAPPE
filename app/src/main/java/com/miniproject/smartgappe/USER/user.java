package com.miniproject.smartgappe.USER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miniproject.smartgappe.R;

public class user extends AppCompatActivity {

    EditText uname,upass,rupass;
    Button bru,blu;
    DBULogin udb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        uname = (EditText)findViewById(R.id.nuname);
        upass = (EditText)findViewById(R.id.nupass);
        rupass = (EditText)findViewById(R.id.rupass);

        bru = (Button)findViewById(R.id.ru);
        blu = (Button)findViewById(R.id.lu);

        udb = new DBULogin(this);

        bru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = uname.getText().toString();
                String pass = upass.getText().toString();
                String repass = rupass.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(user.this, "Fill all the Details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                        Boolean checkuname = udb.cuname(user);
                        if(checkuname == false)
                        {
                            Boolean regres = udb.insertUser(user,pass);
                            if(regres ==true)
                            {
                                Toast.makeText(user.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), ulogin.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(user.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else
                        {
                            Toast.makeText(user.this, "User already exists\nSign In", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else
                    {
                        Toast.makeText(user.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        blu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ulogin.class);
                startActivity(i);
            }
        });
    }
}
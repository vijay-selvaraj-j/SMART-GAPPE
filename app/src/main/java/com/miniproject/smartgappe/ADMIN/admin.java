package com.miniproject.smartgappe.ADMIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miniproject.smartgappe.R;

public class admin extends AppCompatActivity {

    EditText aname,apass,rapass,naloc;
    Button bra,bla;
    DBALogin adb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        aname = (EditText)findViewById(R.id.naname);
        apass = (EditText)findViewById(R.id.napass);
        rapass = (EditText)findViewById(R.id.rapass);
        naloc = (EditText)findViewById(R.id.naloc);

        bra = (Button)findViewById(R.id.ra);
        bla = (Button)findViewById(R.id.la);

        adb = new DBALogin(this);

        bra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String admin = aname.getText().toString();
                String pass = apass.getText().toString();
                String repass = rapass.getText().toString();
                String loc = naloc.getText().toString();

                if(admin.equals("") || pass.equals("") || repass.equals("") || loc.equals(""))
                {
                    Toast.makeText(admin.this, "Fill all the Details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                        Boolean checkaname = adb.caname(admin);
                        if(checkaname == false)
                        {
                            Boolean regres = adb.insertAdmin(admin,pass,loc);
                            if(regres == true)
                            {
                                Toast.makeText(admin.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), alogin.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(admin.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                            }
                        }
                        else
                        {
                            Toast.makeText(admin.this, "Admin already exists\nSign In", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else
                    {
                        Toast.makeText(admin.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        bla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),alogin.class);
                startActivity(i);
            }
        });
    }
}
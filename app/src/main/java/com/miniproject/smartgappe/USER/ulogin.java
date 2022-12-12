package com.miniproject.smartgappe.USER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miniproject.smartgappe.R;

public class ulogin extends AppCompatActivity {

    EditText username,password;
    Button blogin;
    DBULogin udb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulogin);

        username = (EditText)findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.upass);
        blogin = (Button) findViewById(R.id.login);

        udb = new DBULogin(this);
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals(""))
                {
                    Toast.makeText(ulogin.this, "Please Enter Credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean lres = udb.checkunamepass(user,pass);
                    if(lres == true)
                    {
                        Intent i = new Intent(getApplicationContext(), udash.class);
                        i.putExtra("username",username.getText().toString());
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(ulogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
package com.miniproject.smartgappe.ADMIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miniproject.smartgappe.R;

public class alogin extends AppCompatActivity {

    EditText adminname,password;
    Button balogin;
    DBALogin adb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alogin);

        adminname = (EditText)findViewById(R.id.aname);
        password = (EditText) findViewById(R.id.apass);
        balogin = (Button) findViewById(R.id.alogin);

        adb = new DBALogin(this);
        balogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = adminname.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals(""))
                {
                    Toast.makeText(alogin.this, "Please Enter Credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean lres = adb.checkanamepass(user,pass);
                    if(lres)
                    {
                        Intent i = new Intent(getApplicationContext(), adash.class);
                        i.putExtra("name",user);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(alogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
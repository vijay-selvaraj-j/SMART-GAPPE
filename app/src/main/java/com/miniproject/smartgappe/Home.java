package com.miniproject.smartgappe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button user = (Button)findViewById(R.id.userB);
        Button admin = (Button)findViewById(R.id.adminB);

            user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent iu = new Intent(getApplicationContext(), com.miniproject.smartgappe.USER.user.class);
                    startActivity(iu);
                }
            });
                    admin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent ia = new Intent(getApplicationContext(), com.miniproject.smartgappe.ADMIN.admin.class);
                            startActivity(ia);
                        }
                    });
    }
}
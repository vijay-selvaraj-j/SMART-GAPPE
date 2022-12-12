package com.miniproject.smartgappe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Thread thread = new Thread(){
            public void run()
            {
                try {
                    sleep(2500);
                }
                catch(Exception e){
                    e.printStackTrace();
            }
                finally{
                    Intent i = new Intent(MainActivity.this , Home.class);
                    startActivity(i);
                    finish();
            }
            }
        };thread.start();
    }
}
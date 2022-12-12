package com.miniproject.smartgappe.USER;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.miniproject.smartgappe.ADMIN.DBALogin;
import com.miniproject.smartgappe.R;

import java.util.List;

public class udash extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Button b1,b2,b3,b4,b5,b6,submit;
    Spinner spinner;
    TextView t1,t2,t3,tot,panic,churc,dahic;
    String spdata, i1="Pani Puri", i2="Churmur Chat", i3 = "Dahi Puri";
    int costpani = 25, costchur = 30, costdahi = 40;
    DBALogin udsdb = new DBALogin(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udash);

        Bundle bun = getIntent().getExtras();
        String s1 = bun.getString("username");
        t1 = (TextView) findViewById(R.id.pc);
        b1 = (Button) findViewById(R.id.pmin);
        b2 = (Button) findViewById(R.id.pmax);

        t2 = (TextView) findViewById(R.id.cc);
        b3 = (Button) findViewById(R.id.cmin);
        b4 = (Button) findViewById(R.id.cmax);

        t3 = (TextView) findViewById(R.id.dc);
        b5 = (Button) findViewById(R.id.dmin);
        b6 = (Button) findViewById(R.id.dmax);

        tot = (TextView) findViewById(R.id.total);
        panic = (TextView) findViewById(R.id.c1);
        churc = (TextView) findViewById(R.id.c2);
        dahic = (TextView) findViewById(R.id.c3);


        submit = (Button) findViewById(R.id.placeorder);
        spinner = (Spinner)findViewById(R.id.spinneruloc);
        spinner.setOnItemSelectedListener(this);
        loadSD();

        panic.setText(costpani+"");
        churc.setText(costchur+"");
        dahic.setText(costdahi+"");


        int cost1 = Integer.parseInt(String.valueOf(panic.getText()));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fc,a,b,c,t=0;
                int c1 = Integer.parseInt(String.valueOf(t1.getText()));
                if (c1 > 0)
                {
                    c1--;
                }
                t1.setText(c1 + "");
                fc = c1*cost1;
                panic.setText(fc+"");
                a = Integer.parseInt(String.valueOf(panic.getText()));
                b = Integer.parseInt(String.valueOf(churc.getText()));
                c = Integer.parseInt(String.valueOf(dahic.getText()));
                t = a+b+c;
                tot.setText(t+"");

            }
        });

        int cost2 = Integer.parseInt(String.valueOf(panic.getText()));
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBALogin cdbu1 = new DBALogin(getApplicationContext());
                int panicount = cdbu1.panic(spdata);
                int fc,a,b,c,t=0;
                int c2 = Integer.parseInt(String.valueOf(t1.getText()));
                if (c2 >= 0 && c2<panicount) {
                    c2++;
                }
                t1.setText(c2 + "");
                fc = c2*cost2;
                panic.setText(fc+"");
                a = Integer.parseInt(String.valueOf(panic.getText()));
                b = Integer.parseInt(String.valueOf(churc.getText()));
                c = Integer.parseInt(String.valueOf(dahic.getText()));
                t = a+b+c;
                tot.setText(t+"");
            }
        });

        int cost3 = Integer.parseInt(String.valueOf(churc.getText()));
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fc,a,b,c,t=0;
                int c1 = Integer.parseInt(String.valueOf(t2.getText()));
                if (c1 > 0)
                    c1--;
                t2.setText(c1 + "");
                fc = c1*cost3;
                churc.setText(fc+"");
                a = Integer.parseInt(String.valueOf(panic.getText()));
                b = Integer.parseInt(String.valueOf(churc.getText()));
                c = Integer.parseInt(String.valueOf(dahic.getText()));
                t = a+b+c;
                tot.setText(t+"");
            }
        });

        int cost4 = Integer.parseInt(String.valueOf(churc.getText()));
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fc,a,b,c,t=0;
                DBALogin cdbu1 = new DBALogin(getApplicationContext());
                int churcount = cdbu1.churc(spdata);

                int c2 = Integer.parseInt(String.valueOf(t2.getText()));
                if (c2 >= 0 && c2<churcount)
                    c2++;
                t2.setText(c2 + "");
                fc = c2*cost4;
                churc.setText(fc+"");
                a = Integer.parseInt(String.valueOf(panic.getText()));
                b = Integer.parseInt(String.valueOf(churc.getText()));
                c = Integer.parseInt(String.valueOf(dahic.getText()));
                t = a+b+c;
                tot.setText(t+"");
            }
        });

        int cost5 = Integer.parseInt(String.valueOf(dahic.getText()));
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fc,a,b,c,t=0;

                int c1 = Integer.parseInt(String.valueOf(t3.getText()));
                if (c1 > 0)
                    c1--;
                t3.setText(c1 + "");
                fc = c1*cost5;
                dahic.setText(fc+"");
                a = Integer.parseInt(String.valueOf(panic.getText()));
                b = Integer.parseInt(String.valueOf(churc.getText()));
                c = Integer.parseInt(String.valueOf(dahic.getText()));
                t = a+b+c;
                tot.setText(t+"");
            }
        });

        int cost6 = Integer.parseInt(String.valueOf(dahic.getText()));
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fc,a,b,c,t=0;
                DBALogin cdbu1 = new DBALogin(getApplicationContext());
                int dahicount = cdbu1.dahic(spdata);
                int c2 = Integer.parseInt(String.valueOf(t3.getText()));
                if (c2 >= 0 && c2< dahicount)
                    c2++;
                t3.setText(c2 + "");
                fc = c2*cost6;
                dahic.setText(fc+"");
                a = Integer.parseInt(String.valueOf(panic.getText()));
                b = Integer.parseInt(String.valueOf(churc.getText()));
                c = Integer.parseInt(String.valueOf(dahic.getText()));
                t = a+b+c;
                tot.setText(t+"");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lv1,lv2,lv3,lv4,lv5,lv6,a = 0,b=0,c=0,churcount,dahicount,panicount;
                String lv7;
                lv1 = Integer.parseInt(String.valueOf(t1.getText()));
                lv2 = Integer.parseInt(String.valueOf(t2.getText()));
                lv3 = Integer.parseInt(String.valueOf(t3.getText()));
                lv4 = Integer.parseInt(String.valueOf(panic.getText()));
                lv5 = Integer.parseInt(String.valueOf(churc.getText()));
                lv6 = Integer.parseInt(String.valueOf(dahic.getText()));
                lv7 = spdata;
                if(lv1>0)
                {
                    if(udsdb.upanic(lv1, lv7))
                    {
                        a = udsdb.insertuorder(i1, lv1, lv4, lv7, s1);
                        udsdb.insertalluorder(i1, lv1, lv4, lv7, s1);
                        Toast.makeText(udash.this, "Order Placed", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(udash.this, "Pani Puri Unavailable", Toast.LENGTH_SHORT).show();
                }
                if(lv2>0)
                {
                    if(udsdb.uchurc(lv2,lv7))
                    {
                        b = udsdb.insertuorder(i2, lv2, lv5, lv7,s1);
                        udsdb.insertalluorder(i2, lv2, lv5, lv7,s1);
                        Toast.makeText(udash.this, "Order Placed", Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(udash.this, "Churmur Chat Unavailable", Toast.LENGTH_SHORT).show();
                }
                if(lv3>0){
                    if (udsdb.udahic(lv3,lv7))
                    {
                        udsdb.insertuorder(i3,lv3,lv6,lv7,s1);
                        udsdb.insertalluorder(i3,lv3,lv6,lv7,s1);
                        Toast.makeText(udash.this, "Order Placed", Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(udash.this, "Dahi Puri Unavailable", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void loadSD()
    {
        DBALogin dbau = new DBALogin(this);
        List<String> labels = dbau.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, labels);
        dataAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String label = parent.getItemAtPosition(position).toString();
        spdata = label;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {

    }
}
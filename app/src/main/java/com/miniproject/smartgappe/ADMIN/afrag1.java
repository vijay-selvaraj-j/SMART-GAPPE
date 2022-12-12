package com.miniproject.smartgappe.ADMIN;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.miniproject.smartgappe.R;
import com.miniproject.smartgappe.USER.CustomAdaptor;

import java.util.ArrayList;
import java.util.List;

public class afrag1 extends Fragment implements AdapterView.OnItemSelectedListener {

    Button cl, r, allor;
    RecyclerView rv;
    DBALogin myDB = new DBALogin(getActivity());
    Spinner spin;
    public String spdata1, s;
    TextView tv;


    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.fragment_afrag1, container, false);

        tv = (TextView) v.findViewById(R.id.tvout);
        tv.setMovementMethod(new ScrollingMovementMethod());

        cl = (Button) v.findViewById(R.id.done);
        r = (Button) v.findViewById(R.id.ref);
        allor = (Button) v.findViewById(R.id.allor);

        spin = (Spinner) v.findViewById(R.id.spin);
        spin.setOnItemSelectedListener(this);

        loadSD();

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disp();
            }
        });

        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBALogin cldb = new DBALogin(getContext());
                cldb.clear(spdata1);
                tv.setText("");
                Toast.makeText(getContext(), "Cleared", Toast.LENGTH_SHORT).show();
            }
        });

        allor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispall();
            }
        });


        return v;
    }

    public void dispall() {
        DBALogin cdb18 = new DBALogin(getContext());
        Cursor c = cdb18.readAllOrder(spdata1);
        if (c.getCount() == 0) {
            Toast.makeText(getActivity(), "No Sales", Toast.LENGTH_SHORT).show();
            tv.setText("");
        } else {
            StringBuffer bf = new StringBuffer();
            while (c.moveToNext()) {
                bf.append("Item: " + c.getString(0) + "\n");
                bf.append("Count: " + c.getString(1) + "\n");
                bf.append("Cost: Rs. " + c.getString(2) + "/-\n");
                bf.append("Name: " + c.getString(4) + "\n");
                bf.append("\n**********************************************\n\n");
            }
            tv.setText(bf);
        }
        int tot = cdb18.totalcost(spdata1);
        tv.append("\n**********************************************\n");
        tv.append("Total Sales: Rs. "+tot+"/-");
        tv.append("\n**********************************************\n");

    }


    public void disp()
    {
        DBALogin cdb12 = new DBALogin(getContext());
        Cursor c = cdb12.readAll(spdata1);
        if(c.getCount() == 0) {
            Toast.makeText(getActivity(), "No Orders Available", Toast.LENGTH_SHORT).show();
            tv.setText("");
        }
        else
        {
            StringBuffer bf = new StringBuffer();
            while(c.moveToNext())
            {
                bf.append("Item: "+c.getString(0)+"\n");
                bf.append("Count: "+c.getString(1)+"\n");
                bf.append("Cost: Rs. "+c.getString(2)+"/-\n");
                bf.append("Name: "+c.getString(4)+"\n");
                bf.append("\n**********************************************\n\n");

            }
            tv.setText(bf);
        }


    }

    public void loadSD()
    {
        DBALogin dbau = new DBALogin(getContext());
        List<String> labels = dbau.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, labels);
        dataAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,int position,long id)
    {
        String label = parent.getItemAtPosition(position).toString();
        spdata1 = label;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {

    }

}
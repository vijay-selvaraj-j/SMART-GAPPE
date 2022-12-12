package com.miniproject.smartgappe.ADMIN;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.miniproject.smartgappe.ADMIN.DBALogin;
import com.miniproject.smartgappe.R;

import java.util.List;

public class afrag2 extends Fragment implements AdapterView.OnItemSelectedListener {

    Button bt1,bt2,bt3,bt4,bt5,bt6,sub;
    TextView ap,cc,pp;
    DBALogin adb = new DBALogin(getActivity());
    Spinner spinner;
    String spdata;


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_afrag2, container, false);

        ap = (TextView) view.findViewById(R.id.dahic);
        bt1 = (Button) view.findViewById(R.id.dahimin);
        bt2 = (Button) view.findViewById(R.id.dahiplus2);

        cc = (TextView) view.findViewById(R.id.churc);
        bt3 = (Button) view.findViewById(R.id.churmin);
        bt4 = (Button) view.findViewById(R.id.churplus);

        pp = (TextView) view.findViewById(R.id.panic);
        bt5 = (Button) view.findViewById(R.id.panimin);
        bt6 = (Button) view.findViewById(R.id.paniplus);

        sub = (Button) view.findViewById(R.id.afrag2sub);
        spinner = (Spinner)view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        loadSD();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c1 = Integer.parseInt(String.valueOf(ap.getText()));
                if (c1 > 0)
                    c1--;
                ap.setText(c1 + "");
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c2 = Integer.parseInt(String.valueOf(ap.getText()));
                if (c2 >= 0)
                    c2++;
                ap.setText(c2 + "");
            }
        });


        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c1 = Integer.parseInt(String.valueOf(cc.getText()));
                if (c1 > 0)
                    c1--;
                cc.setText(c1 + "");
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c2 = Integer.parseInt(String.valueOf(cc.getText()));
                if (c2 >= 0)
                    c2++;
                cc.setText(c2 + "");
            }
        });


        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c1 = Integer.parseInt(String.valueOf(pp.getText()));
                if (c1 > 0)
                    c1--;
                pp.setText(c1 + "");
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c2 = Integer.parseInt(String.valueOf(pp.getText()));
                if (c2 >= 0)
                    c2++;
                pp.setText(c2 + "");
            }
        });


            sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBALogin cdb = new DBALogin(getContext());
                int dc = Integer.parseInt(String.valueOf(ap.getText()));
                int cp = Integer.parseInt(String.valueOf(cc.getText()));
                int pc = Integer.parseInt(String.valueOf(pp.getText()));
                boolean res;
                    if (cdb.insertstock(dc, cp, pc, spdata))
                        res = true;
                    else
                        res = false;

                if (res) {
                     Toast.makeText(getActivity(), "Stock Updated", Toast.LENGTH_SHORT).show();

                }
            }
        });

        return view;
    }

    public void loadSD()
    {
        DBALogin dbau = new DBALogin(getContext());
        List<String> labels = dbau.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, labels);
        dataAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,int position,long id)
    {
        String label = parent.getItemAtPosition(position).toString();
        spdata = label;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0)
    {

    }

}
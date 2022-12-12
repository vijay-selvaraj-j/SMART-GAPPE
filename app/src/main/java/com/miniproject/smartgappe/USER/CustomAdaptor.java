package com.miniproject.smartgappe.USER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miniproject.smartgappe.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHilder> {

    int i=1;
    Context context;
    ArrayList item,ic,c;
    public CustomAdaptor(Context context, ArrayList item, ArrayList ic, ArrayList c)
    {
        this.context = context;
        this.item = item;
        this.ic = ic;
        this.c = c;
    }


    @NonNull
    @Override
    public MyViewHilder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.my_row, parent, false);
        return new MyViewHilder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHilder holder, int position) {
        holder.item.setText(String.valueOf(item.get(position)));
        holder.itemc.setText(String.valueOf(ic.get(position)));
        holder.cost.setText(String.valueOf(c.get(position)));

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class MyViewHilder extends RecyclerView.ViewHolder{

        TextView item,itemc,cost;

        public MyViewHilder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.iname);
            itemc = itemView.findViewById(R.id.icount);
            cost = itemView.findViewById(R.id.icost);

        }
    }
}

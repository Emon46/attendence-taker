package com.example.android.attendencetakerfinal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by emon on 4/6/18.
 */

public class termtestArrayAdapter extends ArrayAdapter<termtestHelper> implements ListAdapter {
    Context mcontext;
    int mresource;
    String CURRENT_TABLE_NAME;


    public termtestArrayAdapter(String currentTableName,
                                @NonNull Context context, int resource,
                                @NonNull ArrayList<termtestHelper> objects)
    {
        super(context, resource, objects);
        mcontext=context;
        mresource=resource;
        CURRENT_TABLE_NAME=currentTableName;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String reg=getItem(position).reg;
        String term1=getItem(position).term1;
        String term2=getItem(position).term2;

        LayoutInflater inflater=LayoutInflater.from(mcontext);
        convertView=inflater.inflate(mresource,parent,false);

        TextView Term1=(TextView) convertView.findViewById(R.id.term1p);
        TextView Term2=(TextView) convertView.findViewById(R.id.term2p);
        TextView Reg=(TextView) convertView.findViewById(R.id.regTERrm);

        Term1.setText(term1);
        Term2.setText(term2);
        Reg.setText(reg);

        return convertView;
    }
}

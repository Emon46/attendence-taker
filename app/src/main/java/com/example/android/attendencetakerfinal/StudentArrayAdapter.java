package com.example.android.attendencetakerfinal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;

/**
 * Created by emon on 2/22/18.
 */

public class StudentArrayAdapter extends ArrayAdapter<StudentHelper> {
    Context mcontext;
    int mresource;


    public StudentArrayAdapter(@NonNull Context context, int resource, @NonNull List<StudentHelper> objects) {
        super(context, resource, objects);
        mcontext=context;
        mresource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name=getItem(position).NAME;
        String Reg=getItem(position).Reg;
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        convertView=inflater.inflate(mresource,parent,false);

        EditText RegView=(EditText) convertView.findViewById(R.id.RegView);

        EditText Nameview=(EditText) convertView.findViewById(R.id.NameView);
        RegView.setText(Reg);
        Nameview.setText(name);
        return convertView;
    }
}
